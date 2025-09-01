package com.playground.Virtual_Threads.Claude.KakfaToMongo;

import lombok.extern.slf4j.Slf4j;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

/**
 * Utility class to generate test data for the Virtual Threads Kafka MongoDB Demo
 */
@Slf4j
public class TestDataGenerator {

    private static final String BOOTSTRAP_SERVERS = "localhost:29092, localhost:29093, localhost:29094";
    private static final String MONGO_CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String MONGO_DATABASE = "demo";

    public static void main(String[] args) {
        log.info("Test Data Generator for Virtual Threads Kafka MongoDB Demo");
        log.info("==========================================================");

        try (Scanner scanner = new Scanner(System.in)) {
            log.info("\nSelect an option:");
            log.info("1. Generate MongoDB test data");
            log.info("2. Send test messages to Kafka");
            log.info("3. Do both");
            System.out.print("\nEnter your choice (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1 || choice == 3) {
                generateMongoData();
            }

            if (choice == 2 || choice == 3) {
                System.out.print("How many messages to generate? ");
                int messageCount = scanner.nextInt();
                scanner.nextLine(); // consume newline

                generateKafkaMessages(messageCount);
            }
        }

        log.info("Data generation complete!");
    }

    private static void generateMongoData() {
        log.info("\nGenerating MongoDB test data...");

        try (MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE);
            VirtualThreadsKafkaMongoDemo.TestDataGenerator.populateTestData(database);
        }
    }

    private static void generateKafkaMessages(int count) {
        log.info("\nSending " + count + " test messages to Kafka...");

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            VirtualThreadsKafkaMongoDemo.TestDataGenerator.generateTestMessages(producer, count);
        }
    }
}