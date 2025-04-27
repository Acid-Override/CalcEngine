package com.playground.PostgreSQL;


import java.sql.*;
import java.util.Properties;

public class PostgresSetup {


    /**
     * PostgreSQL Database Setup Utility
     *
     * A simple utility class for:
     * - Connecting to a PostgreSQL database
     * - Creating a new database
     * - Creating tables
     * - Populating tables with sample data
     */
    public static class PostgreSQLSetup {

        // Database connection parameters
        private String host;
        private int port;
        private String username;
        private String password;
        private String defaultDB;
        private String targetDB;

        /**
         * Constructor with connection parameters
         */
        public PostgreSQLSetup(String host, int port, String username, String password, String targetDB) {
            this.host = host;
            this.port = port;
            this.username = username;
            this.password = password;
            this.defaultDB = "postgres"; // Connect to default DB first
            this.targetDB = targetDB;
        }

        /**
         * Get connection to PostgreSQL
         */
        private Connection getConnection(String database) throws SQLException {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;

            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("ssl", "false");

            return DriverManager.getConnection(url, props);
        }

        /**
         * Create a new database
         */
        public void createDatabase() {
            try (Connection conn = getConnection(defaultDB);
                 Statement stmt = conn.createStatement()) {

                // Check if database exists first
                ResultSet rs = stmt.executeQuery(
                        "SELECT 1 FROM pg_database WHERE datname = '" + targetDB + "'"
                );

                if (!rs.next()) {
                    // Database doesn't exist, create it
                    stmt.executeUpdate("CREATE DATABASE " + targetDB);
                    System.out.println("Database " + targetDB + " created successfully.");
                } else {
                    System.out.println("Database " + targetDB + " already exists.");
                }

            } catch (SQLException e) {
                System.err.println("Error creating database: " + e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * Create tables in the database
         */
        public void createTables() {
            try (Connection conn = getConnection(targetDB);
                 Statement stmt = conn.createStatement()) {

                // Create customers table
                String createCustomersTable =
                        "CREATE TABLE IF NOT EXISTS customers (" +
                                "  customer_id SERIAL PRIMARY KEY," +
                                "  name VARCHAR(100) NOT NULL," +
                                "  email VARCHAR(100) UNIQUE NOT NULL," +
                                "  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                                ")";
                stmt.executeUpdate(createCustomersTable);

                // Create products table
                String createProductsTable =
                        "CREATE TABLE IF NOT EXISTS products (" +
                                "  product_id SERIAL PRIMARY KEY," +
                                "  name VARCHAR(100) NOT NULL," +
                                "  description TEXT," +
                                "  price DECIMAL(10,2) NOT NULL," +
                                "  stock_quantity INTEGER NOT NULL DEFAULT 0" +
                                ")";
                stmt.executeUpdate(createProductsTable);

                // Create orders table
                String createOrdersTable =
                        "CREATE TABLE IF NOT EXISTS orders (" +
                                "  order_id SERIAL PRIMARY KEY," +
                                "  customer_id INTEGER REFERENCES customers(customer_id)," +
                                "  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                                "  status VARCHAR(20) DEFAULT 'pending'" +
                                ")";
                stmt.executeUpdate(createOrdersTable);

                // Create order_items table
                String createOrderItemsTable =
                        "CREATE TABLE IF NOT EXISTS order_items (" +
                                "  item_id SERIAL PRIMARY KEY," +
                                "  order_id INTEGER REFERENCES orders(order_id)," +
                                "  product_id INTEGER REFERENCES products(product_id)," +
                                "  quantity INTEGER NOT NULL," +
                                "  price DECIMAL(10,2) NOT NULL" +
                                ")";
                stmt.executeUpdate(createOrderItemsTable);

                System.out.println("Tables created successfully.");

            } catch (SQLException e) {
                System.err.println("Error creating tables: " + e.getMessage());
                e.printStackTrace();
            }
        }

//        /**
//         * Insert sample data into tables
//         */
//        public void insertSampleData() {
//            try (Connection conn = getConnection(targetDB)) {
//                // Disable auto-commit for batch operations
//                conn.setAutoCommit(false);
//
//                // Insert sample customers
//                String insertCustomer =
//                        "INSERT INTO customers (name, email) VALUES (?, ?)";
//                try (PreparedStatement pstmt = conn.prepareStatement(insertCustomer)) {
//                    // Customer 1
//                    pstmt.setString(1, "John Smith");
//                    pstmt.setString(2, "john.smith@example.com");
//                    pstmt.addBatch();
//
//                    // Customer 2
//                    pstmt.setString(1, "Emma Johnson");
//                    pstmt.setString(2, "emma.johnson@example.com");
//                    pstmt.addBatch();
//
//                    // Customer 3
//                    pstmt.setString(1, "Michael Davis");
//                    pstmt.setString(2, "michael.davis@example.com");
//                    pstmt.addBatch();
//
//                    pstmt.executeBatch();
//                }
//
//                // Insert sample products
//                String insertProduct =
//                        "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
//                try (PreparedStatement pstmt = conn.prepareStatement(insertProduct)) {
//                    // Product 1
//                    pstmt.setString(1, "Laptop");
//                    pstmt.setString(2, "High-performance laptop with 16GB RAM");
//                    pstmt.setDouble(3, 999.99);
//                    pstmt.setInt(4, 25);
//                    pstmt.addBatch();
//
//                    // Product 2
//                    pstmt.setString(1, "Smartphone");
//                    pstmt.setString(2, "Latest model with 128GB storage");
//                    pstmt.setDouble(3, 699.99);
//                    pstmt.setInt(4, 50);
//                    pstmt.addBatch();
//
//                    // Product 3
//                    pstmt.setString(1, "Headphones");
//                    pstmt.setString(2, "Noise-cancelling wireless headphones");
//                    pstmt.setDouble(3, 199.99);
//                    pstmt.setInt(4, 100);
//                    pstmt.addBatch();
//
//                    pstmt.executeBatch();
//                }

//                // Insert sample orders
//                String insertOrder =
//                        "INSERT INTO orders (customer_id, status) VALUES (?, ?)";
//                try (PreparedStatement pstmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
//                    // Order for Customer 1
//                    pstmt.setInt(1, 1);
//                    pstmt.setString(2, "completed");
//                    pstmt.executeUpdate();
//
//                    ResultSet generatedKeys = pstmt.getGeneratedKeys();
//                    int orderId = 0;
//                    if (generatedKeys.next()) {
//                        orderId = generatedKeys.getInt(1);
//                    }
//
//                    // Insert order items
//                    if (orderId > 0) {
//                        String insertOrderItem =
//                                "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
//                        try (PreparedStatement itemStmt = conn.prepareStatement(insertOrderItem)) {
//                            // Item 1 of Order
//                            itemStmt.setInt(1, orderId);
//                            itemStmt.setInt(2, 1); // Laptop
//                            itemStmt.setInt(3, 1); // Quantity
//                            itemStmt.setDouble(4, 999.99);
//                            itemStmt.addBatch();
//
//                            // Item 2 of Order
//                            itemStmt.setInt(1, orderId);
//                            itemStmt.setInt(2, 3); // Headphones
//                            itemStmt.setInt(3, 2); // Quantity
//                            itemStmt.setDouble(4, 199.99);
//                            itemStmt.addBatch();
//
//                            itemStmt.executeBatch();
//                        }
//                    }
//
//                    // Order for Customer 2
//                    pstmt.setInt(1, 2);
//                    pstmt.setString(2, "pending");
//                    pstmt.executeUpdate();
//
//                    generatedKeys = pstmt.getGeneratedKeys();
//                    orderId = 0;
//                    if (generatedKeys.next()) {
//                        orderId = generatedKeys.getInt(1);
//                    }
//
//                    // Insert order items
//                    if (orderId > 0) {
//                        String insertOrderItem =
//                                "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
//                        try (PreparedStatement itemStmt = conn.prepareStatement(insertOrderItem)) {
//                            // Item of Order
//                            itemStmt.setInt(1, orderId);
//                            itemStmt.setInt(2, 2); // Smartphone
//                            itemStmt.setInt(3, 1); // Quantity
//                            itemStmt.setDouble(4, 699.99);
//                            itemStmt.executeUpdate();
//                        }
//                    }
//                }
//
//                // Commit all changes
//                conn.commit();
//                System.out.println("Sample data inserted successfully.");
//
//            } catch (SQLException e) {
//                System.err.println("Error inserting sample data: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }


                /**
                 * Insert large sample dataset into tables
                 */
                public void insertSampleData() {
                    try (Connection conn = getConnection(targetDB)) {
                        // Disable auto-commit for batch operations
                        conn.setAutoCommit(false);

                        // Configuration for dataset size
                        final int NUM_CUSTOMERS = 1000;
                        final int NUM_PRODUCTS = 100;
                        final int NUM_ORDERS = 5000;
                        final int MAX_ITEMS_PER_ORDER = 5;
                        final int BATCH_SIZE = 500;

                        // Arrays for product data to reference later
                        double[] productPrices = new double[NUM_PRODUCTS + 1];

                        System.out.println("Starting data insertion...");
                        long startTime = System.currentTimeMillis();

                        // ==================== INSERT CUSTOMERS ====================
                        System.out.println("Inserting " + NUM_CUSTOMERS + " customers...");
                        String insertCustomer = "INSERT INTO customers (name, email) VALUES (?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertCustomer)) {

                            String[] firstNames = {"John", "Jane", "Michael", "Emma", "William", "Olivia",
                                    "James", "Sophia", "Robert", "Ava", "David", "Isabella",
                                    "Joseph", "Mia", "Daniel", "Charlotte", "Thomas", "Amelia",
                                    "Matthew", "Harper"};

                            String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
                                    "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
                                    "Wilson", "Anderson", "Taylor", "Thomas", "Moore", "Jackson",
                                    "Martin", "Lee"};

                            String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};

                            int count = 0;
                            for (int i = 1; i <= NUM_CUSTOMERS; i++) {
                                // Generate random name
                                String firstName = firstNames[(int)(Math.random() * firstNames.length)];
                                String lastName = lastNames[(int)(Math.random() * lastNames.length)];
                                String name = firstName + " " + lastName;

                                // Generate random email
                                String email = firstName.toLowerCase() + "." + lastName.toLowerCase() +
                                        (int)(Math.random() * 1000) + "@" +
                                        domains[(int)(Math.random() * domains.length)];

                                pstmt.setString(1, name);
                                pstmt.setString(2, email);
                                pstmt.addBatch();

                                // Execute batch in chunks to avoid memory issues
                                if (++count % BATCH_SIZE == 0 || i == NUM_CUSTOMERS) {
                                    pstmt.executeBatch();
                                    pstmt.clearBatch();
                                    conn.commit();
                                    System.out.println("  Processed " + i + " customers");
                                }
                            }
                        }

                        // ==================== INSERT PRODUCTS ====================
                        System.out.println("Inserting " + NUM_PRODUCTS + " products...");
                        String insertProduct =
                                "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS)) {

                            String[] productTypes = {"Laptop", "Smartphone", "Tablet", "Headphones", "Monitor",
                                    "Keyboard", "Mouse", "Printer", "Camera", "Speaker",
                                    "Microphone", "Charger", "Cable", "Adapter", "Case",
                                    "Stand", "Mount", "Docking Station", "External Drive", "Memory Card"};

                            String[] brands = {"TechPro", "Nexus", "Quantum", "Elite", "Prime",
                                    "Spectrum", "Fusion", "Zenith", "Vertex", "Apex"};

                            String[] adjectives = {"Pro", "Ultra", "Max", "Elite", "Premium",
                                    "Deluxe", "Advanced", "Turbo", "Extreme", "Supreme"};

                            int count = 0;
                            for (int i = 1; i <= NUM_PRODUCTS; i++) {
                                // Generate random product data
                                String productType = productTypes[(int)(Math.random() * productTypes.length)];
                                String brand = brands[(int)(Math.random() * brands.length)];
                                String adjective = adjectives[(int)(Math.random() * adjectives.length)];

                                // Randomize whether to use adjective
                                String name = (Math.random() > 0.5)
                                        ? brand + " " + adjective + " " + productType
                                        : brand + " " + productType + " " + (2020 + (int)(Math.random() * 6));

                                // Create varying descriptions
                                String description = "High-quality " + productType.toLowerCase() +
                                        " featuring advanced technology and premium design.";

                                // Generate price with appropriate range based on product type
                                double basePrice = 0;
                                switch (productType) {
                                    case "Laptop": basePrice = 800 + Math.random() * 1200; break;
                                    case "Smartphone": basePrice = 400 + Math.random() * 1000; break;
                                    case "Tablet": basePrice = 200 + Math.random() * 800; break;
                                    case "Monitor": basePrice = 150 + Math.random() * 850; break;
                                    case "Camera": basePrice = 300 + Math.random() * 700; break;
                                    default: basePrice = 20 + Math.random() * 180;
                                }
                                double price = Math.round(basePrice * 100) / 100.0;

                                // Generate random stock quantity
                                int stockQuantity = 10 + (int)(Math.random() * 190);

                                pstmt.setString(1, name);
                                pstmt.setString(2, description);
                                pstmt.setDouble(3, price);
                                pstmt.setInt(4, stockQuantity);
                                pstmt.addBatch();

                                // Store price for later reference when creating order items
                                productPrices[i] = price;

                                // Execute batch in chunks
                                if (++count % BATCH_SIZE == 0 || i == NUM_PRODUCTS) {
                                    pstmt.executeBatch();
                                    pstmt.clearBatch();
                                    conn.commit();
                                    System.out.println("  Processed " + i + " products");
                                }
                            }
                        }

                        // ==================== INSERT ORDERS AND ORDER ITEMS ====================
                        System.out.println("Inserting " + NUM_ORDERS + " orders with items...");
                        String insertOrder = "INSERT INTO orders (customer_id, status) VALUES (?, ?)";
                        String insertOrderItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

                        String[] orderStatuses = {"pending", "processing", "shipped", "delivered", "completed", "cancelled"};

                        int count = 0;
                        for (int i = 1; i <= NUM_ORDERS; i++) {
                            // Select a random customer
                            int customerId = 1 + (int)(Math.random() * NUM_CUSTOMERS);

                            // Select a random status
                            String status = orderStatuses[(int)(Math.random() * orderStatuses.length)];

                            // Insert order
                            try (PreparedStatement pstmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
                                pstmt.setInt(1, customerId);
                                pstmt.setString(2, status);
                                pstmt.executeUpdate();

                                // Get the generated order ID
                                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                                int orderId = 0;
                                if (generatedKeys.next()) {
                                    orderId = generatedKeys.getInt(1);
                                }

                                // Insert order items for this order
                                if (orderId > 0) {
                                    // Determine random number of items for this order (1 to MAX_ITEMS_PER_ORDER)
                                    int numItems = 1 + (int)(Math.random() * MAX_ITEMS_PER_ORDER);

                                    try (PreparedStatement itemStmt = conn.prepareStatement(insertOrderItem)) {
                                        for (int j = 0; j < numItems; j++) {
                                            // Select a random product
                                            int productId = 1 + (int)(Math.random() * NUM_PRODUCTS);

                                            // Generate random quantity (1 to 5)
                                            int quantity = 1 + (int)(Math.random() * 5);

                                            // Use the stored product price
                                            double price = productPrices[productId];

                                            itemStmt.setInt(1, orderId);
                                            itemStmt.setInt(2, productId);
                                            itemStmt.setInt(3, quantity);
                                            itemStmt.setDouble(4, price);
                                            itemStmt.addBatch();
                                        }
                                        itemStmt.executeBatch();
                                    }
                                }
                            }

                            // Commit in batches to improve performance
                            if (++count % (BATCH_SIZE / 10) == 0 || i == NUM_ORDERS) {
                                conn.commit();
                                System.out.println("  Processed " + i + " orders");
                            }
                        }

                        // Final commit to ensure all data is saved
                        conn.commit();

                        long endTime = System.currentTimeMillis();
                        double elapsedSeconds = (endTime - startTime) / 1000.0;

                        System.out.println("Large dataset insertion completed successfully!");
                        System.out.println("Summary:");
                        System.out.println("  - " + NUM_CUSTOMERS + " customers inserted");
                        System.out.println("  - " + NUM_PRODUCTS + " products inserted");
                        System.out.println("  - " + NUM_ORDERS + " orders inserted");
                        System.out.println("  - Time taken: " + elapsedSeconds + " seconds");

                    } catch (SQLException e) {
                        System.err.println("Error inserting sample data: " + e.getMessage());
                        e.printStackTrace();
                    }
                }


        /**
         * Run the complete setup process
         */
        public void runFullSetup() {
            try {
                // Load the PostgreSQL JDBC driver
                Class.forName("org.postgresql.Driver");

                // Execute setup steps
                createDatabase();
                createTables();
                insertSampleData();

                System.out.println("Database setup completed successfully!");

            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL JDBC driver not found!");
                e.printStackTrace();
            }
        }

        /**
         * Example usage
         */
        public static void main(String[] args) {
            // Create an instance with connection parameters
            PostgreSQLSetup setup = new PostgreSQLSetup(
                    "localhost",      // host
                    5432,             // port
                    "jupiter",       // username
                    "",   // password
                    "demo"         // target database name
            );

            // Run the full setup
            setup.runFullSetup();
        }
    }
}
