package com.playground.PostgreSQL.repository;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {

    private static CustomerRepository repository;
    private static String uniqueEmail;
    private static Long savedCustomerId;

    @BeforeAll
    static void setUp() {
        repository = new CustomerRepository();
        // Generate a unique email for each test run to avoid conflicts
        uniqueEmail = "test_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        // Clean up any existing test data
        cleanUpTestData();
    }

    @AfterAll
    static void tearDown() {
        cleanUpTestData();
        HibernateUtil.shutdown();
    }

    private static void cleanUpTestData() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Delete customers with emails that start with "test_"
            session.createQuery("DELETE FROM Customer c WHERE c.email LIKE 'test_%'")
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error cleaning up test data", e);
        }
    }

    @Test
    @Order(1)
    void testSaveCustomer() {
        // Create a test customer
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setEmail(uniqueEmail);

        // Save the customer
        repository.save(customer);

        // Verify the customer was saved by finding it by email
        Optional<Customer> found = repository.findByEmail(uniqueEmail);
        log.info("Found customer: {}", found);
        assertTrue(found.isPresent(), "Customer should be saved and findable by email");
        assertNotNull(found.get().getId(), "Customer ID should be generated");

        // Store the ID for later tests
        savedCustomerId = found.get().getId();
    }

    @Test
    @Order(2)
    void testFindById() {
        // Find the customer by ID
        Optional<Customer> found = repository.findById(savedCustomerId);

        // Verify the customer was found
        assertTrue(found.isPresent(), "Customer should be found by ID");
        assertEquals(uniqueEmail, found.get().getEmail(), "Email should match");
        assertEquals("Test Customer", found.get().getName(), "Name should match");
    }

    @Test
    @Order(3)
    void testFindByName() {
        // Find customers by name
        List<Customer> customers = repository.findByName("Test Customer");

        // Verify at least one customer was found
        assertFalse(customers.isEmpty(), "At least one customer should be found by name");

        // Verify our test customer is among the results
        boolean found = customers.stream()
                .anyMatch(c -> c.getEmail().equals(uniqueEmail));
        assertTrue(found, "Our test customer should be found by name");
    }

    @Test
    @Order(4)
    void testFindByNameContaining() {
        // Find customers by partial name
        List<Customer> customers = repository.findByNameContaining("Test");

        // Verify at least one customer was found
        assertFalse(customers.isEmpty(), "At least one customer should be found by partial name");

        // Verify our test customer is among the results
        boolean found = customers.stream()
                .anyMatch(c -> c.getEmail().equals(uniqueEmail));
        assertTrue(found, "Our test customer should be found by partial name");
    }

    @Test
    @Order(5)
    void testUpdateCustomer() {
        // Find the customer by ID
        Optional<Customer> foundOptional = repository.findById(savedCustomerId);
        assertTrue(foundOptional.isPresent(), "Customer should exist for update test");

        Customer customer = foundOptional.get();

        // Update the customer name
        String newName = "Updated Test Customer";
        customer.setName(newName);

        // Save the updated customer
        repository.update(customer);

        // Verify the customer was updated
        Optional<Customer> updatedOptional = repository.findById(savedCustomerId);
        assertTrue(updatedOptional.isPresent(), "Customer should still exist after update");
        assertEquals(newName, updatedOptional.get().getName(), "Name should be updated");
    }

    @Test
    @Order(6)
    void testSearch() {
        // Create search criteria for our test customer
        CustomerSearchCriteria criteria = new CustomerSearchCriteria()
                .withName("Updated")
                .withEmail(uniqueEmail.substring(0, 10)) // Partial email match
                .withCreatedAfter(LocalDateTime.now().minusDays(1))
                .withSortField("name")
                .withSortAscending(true);

        // Search for customers
        List<Customer> customers = repository.search(criteria);

        // Verify our test customer is found
        assertFalse(customers.isEmpty(), "At least one customer should match search criteria");
        boolean found = customers.stream()
                .anyMatch(c -> c.getEmail().equals(uniqueEmail));
        assertTrue(found, "Our test customer should be found by search criteria");
    }

    @Test
    @Order(7)
    void testFindAll() {
        // Get all customers
        List<Customer> allCustomers = repository.findAll();

        // Verify at least our test customer is included
        assertFalse(allCustomers.isEmpty(), "There should be at least one customer");
        boolean found = allCustomers.stream()
                .anyMatch(c -> c.getEmail().equals(uniqueEmail));
        assertTrue(found, "Our test customer should be included in all customers");
    }

    @Test
    @Order(8)
    void testDeleteCustomer() {
        // Find the customer by ID
        Optional<Customer> foundOptional = repository.findById(savedCustomerId);
        assertTrue(foundOptional.isPresent(), "Customer should exist for delete test");

        Customer customer = foundOptional.get();

        // Delete the customer
        repository.delete(customer);


        // Verify the customer was deleted
        Optional<Customer> deletedOptional = repository.findById(savedCustomerId);
        assertFalse(deletedOptional.isPresent(), "Customer should be deleted");
    }

    @Test
    @Order(9)
    void testTransactionRollback() {
        // Create a test customer
        Customer customer = new Customer();
        customer.setName("Rollback Test Customer");
        customer.setEmail("rollback_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com");

        // Save the customer first to ensure it exists
        repository.save(customer);

        // Verify the customer was saved
        Optional<Customer> found = repository.findByEmail(customer.getEmail());
        assertTrue(found.isPresent(), "Customer should be saved successfully");

        // Now create a method that will fail during a transaction
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            // Get the current SessionFactory

            // Start a transaction
//            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                try {
                    // First operation - update the customer name
                    customer.setName("Updated Name That Should Be Rolled Back");
                    session.merge(customer);

                    // Second operation - deliberately cause an exception
                    // For example, try to insert a customer with the same email (violates unique constraint)
                    Customer duplicateCustomer = new Customer();
                    duplicateCustomer.setName("Duplicate Customer");
                    duplicateCustomer.setEmail(customer.getEmail()); // Same email will cause constraint violation
                    session.merge(duplicateCustomer);

                    // This line should never be reached
                    transaction.commit();
                    fail("Transaction should have failed but didn't");
                } catch (Exception e) {
                    // Verify that the transaction is rolled back
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    // The exception is expected, so we don't rethrow it
                }
            }


//            // Verify the rollback worked - customer name should be unchanged
            Optional<Customer> afterRollback = repository.findByEmail(customer.getEmail());
            assertTrue(afterRollback.isPresent(), "Customer should still exist after rollback");
            assertEquals("Rollback Test Customer", afterRollback.get().getName(),
                    "Customer name should be unchanged after rollback");

        } finally {
            // Clean up - delete the test customer
            found.ifPresent(repository::delete);
            // Verify the rollback worked - customer name should be unchanged

            // TODO:
//            String emailPattern = "rollback_%";
//            List<Customer> leftoverCustomers = repository.findByEmailLike(emailPattern);
//            leftoverCustomers.forEach(repository::delete);
        }
    }
}