package com.playground.Virtual_Threads.Claude.KakfaToMongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.bson.Document;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadsKafkaMongoDemo {

    private static final String BOOTSTRAP_SERVERS = "localhost:29092";
    private static final String MONGO_CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String SOURCE_TOPIC = "source-topic";
    private static final String TARGET_TOPIC = "target-topic";
    private static final String CONSUMER_GROUP_ID = "virtual-threads-demo-group";
    private static final String MONGO_DATABASE = "demo";
    private static final String MONGO_COLLECTION = "user_data";

    private static final AtomicBoolean running = new AtomicBoolean(true);
    private static final AtomicInteger processedCount = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("Java " + Runtime.version() + " Virtual Threads Kafka & MongoDB Demo");
        System.out.println("=========================================================");

        // Setup MongoDB client
        MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING);
        MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE);
        MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION);

        // Setup Kafka producer
        KafkaProducer<String, String> producer = createKafkaProducer();

        // Setup Kafka consumer
        KafkaConsumer<String, String> consumer = createKafkaConsumer();
        consumer.subscribe(Collections.singletonList(SOURCE_TOPIC));

        // Create a virtual thread executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("Starting to consume messages...");

            // Register shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down...");
                running.set(false);
            }));

            while (running.get()) {
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofMillis(100));

                if (!messages.isEmpty()) {
                    // Count how many messages we're processing in this batch
                    CountDownLatch latch = new CountDownLatch(messages.count());

                    for (ConsumerRecord<String, String> message : messages) {
                        // Process each record in a separate virtual thread
                        executor.submit(() -> {
                            try {
                                processRecord(message, collection, producer);
                                latch.countDown();
                            } catch (Exception e) {
                                System.err.println("Error processing message: " + e.getMessage());
                                e.printStackTrace();
                                latch.countDown();
                            }
                        });
                    }

                    // Wait for all messages in this batch to be processed before committing
                    try {
                        latch.await();
                        consumer.commitSync();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Interrupted while waiting for batch to complete");
                    }
                }
            }
        } finally {
            System.out.println("Processed " + processedCount.get() + " messages");
            consumer.close();
            producer.close();
            mongoClient.close();
        }
    }

    private static void processRecord(
            ConsumerRecord<String, String> record,
            MongoCollection<Document> collection,
            KafkaProducer<String, String> producer) {

        String userId = record.key();
        String originalMessage = record.value();

        System.out.println("[" + Thread.currentThread() + "] Processing message: " + originalMessage);

        // Simulate some processing delay
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        // Query MongoDB to enrich data
        Document userDocument = collection.find(new Document("userId", userId)).first();

        String enrichedMessage;
        if (userDocument != null) {
            // Build enriched message
            enrichedMessage = String.format(
                    "{\"originalMessage\":%s,\"userData\":{\"name\":\"%s\",\"tier\":\"%s\",\"region\":\"%s\"}}",
                    originalMessage,
                    userDocument.getString("name"),
                    userDocument.getString("tier"),
                    userDocument.getString("region")
            );
        } else {
            // User not found, just add a placeholder
            enrichedMessage = String.format(
                    "{\"originalMessage\":%s,\"userData\":\"NOT_FOUND\"}",
                    originalMessage
            );
        }

        // Produce to target topic
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(TARGET_TOPIC, userId, enrichedMessage);

        producer.send(producerRecord, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Error sending enriched message: " + exception.getMessage());
                exception.printStackTrace();
            } else {
                int count = processedCount.incrementAndGet();
                if (count % 100 == 0) {
                    System.out.println("Processed " + count + " messages so far");
                }
            }
        });
    }

    private static KafkaProducer<String, String> createKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-" + UUID.randomUUID());
        return new KafkaProducer<>(props);
    }

    private static KafkaConsumer<String, String> createKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        return new KafkaConsumer<>(props);
    }

    // Utility class for testing - can populate MongoDB with test data
    public static class TestDataGenerator {
        public static void populateTestData(MongoDatabase database) {
            MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION);

            // Clear existing data
            collection.deleteMany(new Document());

            // Insert sample user data
            String[] tiers = {"FREE", "BASIC", "PREMIUM", "ENTERPRISE"};
            String[] regions = {"NA", "EU", "APAC", "LATAM"};

            for (int i = 1; i <= 1000; i++) {
                String userId = "user-" + i;
                Document doc = new Document()
                        .append("userId", userId)
                        .append("name", "User " + i)
                        .append("tier", tiers[i % tiers.length])
                        .append("region", regions[i % regions.length])
                        .append("createdAt", System.currentTimeMillis());

                collection.insertOne(doc);
            }

            System.out.println("Inserted 1000 test users into MongoDB");
        }

        public static void generateTestMessages(KafkaProducer<String, String> producer, int count) {
            for (int i = 1; i <= count; i++) {
                String userId = "user-" + (i % 1000 + 1);  // Cycle through users 1-1000
                String message = String.format(
                        "{\"timestamp\":%d,\"action\":\"CLICK\",\"page\":\"page-%d\"}",
                        System.currentTimeMillis(),
                        (i % 50) + 1
                );

                ProducerRecord<String, String> record =
                        new ProducerRecord<>(SOURCE_TOPIC, userId, message);

                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        System.err.println("Error sending test message: " + exception.getMessage());
                    }
                });

                if (i % 100 == 0) {
                    System.out.println("Generated " + i + " test messages");
                }
            }

            producer.flush();
            System.out.println("Finished generating " + count + " test messages");
        }
    }
}
