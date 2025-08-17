
package com.playground.PostgreSQL.repository;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.entities.Order;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderRepositoryTest {

    private static OrderRepository orderRepository;
    private static CustomerRepository customerRepository;
    private static Customer testCustomer1;
    private static Customer testCustomer2;
    private static Order testOrder1;
    private static Order testOrder2;
    private static Order testOrder3;

    @BeforeAll
    static void setUp() {
        orderRepository = new OrderRepository();
        customerRepository = new CustomerRepository();

        // Create test data
        createTestData();
    }

    @AfterAll
    static void tearDown() {
        // Clean up test data
        cleanUpTestData();
        HibernateUtil.shutdown();
    }

    private static void createTestData() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Create test customers
            testCustomer1 = new Customer();
            testCustomer1.setName("Test Customer 1");
            testCustomer1.setEmail("test_customer1_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com");
            session.persist(testCustomer1);

            testCustomer2 = new Customer();
            testCustomer2.setName("Test Customer 2");
            testCustomer2.setEmail("test_customer2_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com");
            session.persist(testCustomer2);

            // Create test orders
            testOrder1 = new Order();
            testOrder1.setCustomer(testCustomer1);
            testOrder1.setOrderDate(LocalDateTime.now().minusDays(5));
            testOrder1.setStatus("completed");
            session.persist(testOrder1);

            testOrder2 = new Order();
            testOrder2.setCustomer(testCustomer1);
            testOrder2.setOrderDate(LocalDateTime.now().minusDays(2));
            testOrder2.setStatus("pending");
            session.persist(testOrder2);

            testOrder3 = new Order();
            testOrder3.setCustomer(testCustomer2);
            testOrder3.setOrderDate(LocalDateTime.now().minusDays(1));
            testOrder3.setStatus("processing");
            session.persist(testOrder3);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error creating test data", e);
        }
    }

    private static void cleanUpTestData() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Delete test orders
            if (testOrder1 != null && testOrder1.getId() != null) {
                Order order = session.get(Order.class, testOrder1.getId());
                if (order != null) {
                    session.remove(order);
                }
            }

            if (testOrder2 != null && testOrder2.getId() != null) {
                Order order = session.get(Order.class, testOrder2.getId());
                if (order != null) {
                    session.remove(order);
                }
            }

            if (testOrder3 != null && testOrder3.getId() != null) {
                Order order = session.get(Order.class, testOrder3.getId());
                if (order != null) {
                    session.remove(order);
                }
            }

            // Delete test customers
            if (testCustomer1 != null && testCustomer1.getId() != null) {
                Customer customer = session.get(Customer.class, testCustomer1.getId());
                if (customer != null) {
                    session.remove(customer);
                }
            }

            if (testCustomer2 != null && testCustomer2.getId() != null) {
                Customer customer = session.get(Customer.class, testCustomer2.getId());
                if (customer != null) {
                    session.remove(customer);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error cleaning up test data", e);
        }
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testFindById() {
        // Get one of the test orders by ID
        Optional<Order> found = orderRepository.findById(testOrder1.getId());

        // Verify the order was found
        assertTrue(found.isPresent(), "Order should be found by ID");
        assertEquals(testOrder1.getId(), found.get().getId(), "IDs should match");
        assertEquals("completed", found.get().getStatus(), "Status should match");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testFindAll() {
        // Get all orders
        List<Order> allOrders = orderRepository.findAll();

        // Verify we have at least our 3 test orders
        assertTrue(allOrders.size() >= 3, "Should find at least 3 orders");

        // Verify our test orders are in the list
        boolean foundTestOrder1 = allOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder1.getId()));
        boolean foundTestOrder2 = allOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder2.getId()));
        boolean foundTestOrder3 = allOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder3.getId()));

        assertTrue(foundTestOrder1, "Should find testOrder1");
        assertTrue(foundTestOrder2, "Should find testOrder2");
        assertTrue(foundTestOrder3, "Should find testOrder3");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testFindByCustomerId() {
        // Get orders for customer 1
        List<Order> customer1Orders = orderRepository.findByCustomerId(testCustomer1.getId());

        // Verify we found both orders for customer 1
        assertEquals(2, customer1Orders.size(), "Should find 2 orders for customer 1");

        // Get orders for customer 2
        List<Order> customer2Orders = orderRepository.findByCustomerId(testCustomer2.getId());

        // Verify we found one order for customer 2
        assertEquals(1, customer2Orders.size(), "Should find 1 order for customer 2");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void testFindByOrderDateBetween() {
        // Get orders from the last 3 days
        LocalDateTime startDate = LocalDateTime.now().minusDays(3);
        LocalDateTime endDate = LocalDateTime.now();

        List<Order> recentOrders = orderRepository.findByOrderDateBetween(startDate, endDate);

        // Should find orders 2 and 3, but not order 1 (which is 5 days old)
        assertEquals(2, recentOrders.size(), "Should find 2 recent orders");

        boolean foundTestOrder1 = recentOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder1.getId()));
        boolean foundTestOrder2 = recentOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder2.getId()));
        boolean foundTestOrder3 = recentOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder3.getId()));

        assertFalse(foundTestOrder1, "Should not find testOrder1 (too old)");
        assertTrue(foundTestOrder2, "Should find testOrder2");
        assertTrue(foundTestOrder3, "Should find testOrder3");
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void testFindByStatus() {
        // Get completed orders
        List<Order> completedOrders = orderRepository.findByStatus("completed");

        // Verify we find at least order 1
        assertTrue(completedOrders.size() >= 1, "Should find at least 1 completed order");

        boolean foundTestOrder1 = completedOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder1.getId()));

        assertTrue(foundTestOrder1, "Should find testOrder1 with 'completed' status");

        // Get pending orders
        List<Order> pendingOrders = orderRepository.findByStatus("pending");

        // Verify we find at least order 2
        assertTrue(pendingOrders.size() >= 1, "Should find at least 1 pending order");

        boolean foundTestOrder2 = pendingOrders.stream()
                .anyMatch(o -> o.getId().equals(testOrder2.getId()));

        assertTrue(foundTestOrder2, "Should find testOrder2 with 'pending' status");
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void testFindCustomersWithOrders() {
        // Get customers with orders
        List<Customer> customersWithOrders = orderRepository.findCustomersWithOrders();

        // Verify both test customers are found
        assertTrue(customersWithOrders.size() >= 2, "Should find at least 2 customers with orders");

        boolean foundTestCustomer1 = customersWithOrders.stream()
                .anyMatch(c -> c.getId().equals(testCustomer1.getId()));
        boolean foundTestCustomer2 = customersWithOrders.stream()
                .anyMatch(c -> c.getId().equals(testCustomer2.getId()));

        assertTrue(foundTestCustomer1, "Should find testCustomer1");
        assertTrue(foundTestCustomer2, "Should find testCustomer2");
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    void testFindCustomersWithOrderStatus() {
        // Get customers with completed orders
        List<Customer> customersWithCompletedOrders = orderRepository.findCustomersWithOrderStatus("completed");

        // Verify customer 1 is found (has a completed order)
        assertTrue(customersWithCompletedOrders.size() >= 1, "Should find at least 1 customer with completed orders");

        boolean foundTestCustomer1 = customersWithCompletedOrders.stream()
                .anyMatch(c -> c.getId().equals(testCustomer1.getId()));
        boolean foundTestCustomer2 = customersWithCompletedOrders.stream()
                .anyMatch(c -> c.getId().equals(testCustomer2.getId()));

        assertTrue(foundTestCustomer1, "Should find testCustomer1 (has completed order)");
        assertFalse(foundTestCustomer2, "Should not find testCustomer2 (no completed orders)");
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    void testCountOrdersByCustomer() {
        // Get order counts by customer using modern Tuple approach
        List<Tuple> orderCounts = orderRepository.countOrdersByCustomer();

        // Verify we have results
        assertFalse(orderCounts.isEmpty(), "Should have order counts");

        // Find our test customers in the results
        Tuple customer1Result = null;
        Tuple customer2Result = null;

        for (Tuple result : orderCounts) {
            Customer customer = result.get(0, Customer.class);  // Type-safe access
            if (customer.getId().equals(testCustomer1.getId())) {
                customer1Result = result;
            } else if (customer.getId().equals(testCustomer2.getId())) {
                customer2Result = result;
            }
        }

        // Verify we found both customers and their correct order counts
        assertNotNull(customer1Result, "Should find testCustomer1 in results");
        assertNotNull(customer2Result, "Should find testCustomer2 in results");

        // Type-safe access to count values
        assertEquals(2L, customer1Result.get(1, Long.class), "Customer 1 should have 2 orders");
        assertEquals(1L, customer2Result.get(1, Long.class), "Customer 2 should have 1 order");
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    void testUpdateOrder() {
        // Update an order's status
        testOrder3.setStatus("shipped");
        orderRepository.update(testOrder3);

        // Verify the update was successful
        Optional<Order> updatedOrder = orderRepository.findById(testOrder3.getId());
        assertTrue(updatedOrder.isPresent(), "Updated order should exist");
        assertEquals("shipped", updatedOrder.get().getStatus(), "Status should be updated to 'shipped'");
    }
}