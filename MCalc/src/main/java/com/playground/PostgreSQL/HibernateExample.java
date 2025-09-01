package com.playground.PostgreSQL;

import lombok.extern.slf4j.Slf4j;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.repository.CustomerRepository;
import com.playground.PostgreSQL.repository.CustomerSearchCriteria;
import com.playground.PostgreSQL.utilities.HibernateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class HibernateExample {
    public static void main(String[] args) {
        try {
            // Initialize the repository
            CustomerRepository customerRepository = new CustomerRepository();

            // Create and save some test customers
//            Customer customer1 = new Customer("John Doe", "john.doe@example.com");
//            Customer customer2 = new Customer("Jane Smith", "jane.smith@example.com");
//            Customer customer3 = new Customer("Bob Johnson", "bob.johnson@example.com");
//
//            customerRepository.save(customer1);
//            customerRepository.save(customer2);
//            customerRepository.save(customer3);
//
//            log.info("Customers saved successfully!");

            // Find all customers
            List<Customer> allCustomers = customerRepository.findAll();
            log.info("\nAll customers:");
            allCustomers.forEach(c -> log.info(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Find customers by name containing "oh"
            List<Customer> customersWithNameContainingOh = customerRepository.findByNameContaining("oh");
            log.info("\nCustomers with name containing 'oh':");
            customersWithNameContainingOh.forEach(c -> log.info(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Find customer by email
            Optional<Customer> customerByEmail = customerRepository.findByEmail("jane.smith@example.com");
            log.info("\nCustomer by email:");
            customerByEmail.ifPresent(c -> log.info(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Complex search with multiple criteria
            CustomerSearchCriteria criteria = new CustomerSearchCriteria()
                    .withName("o")  // Name contains 'o'
                    .withCreatedAfter(LocalDateTime.now().minusDays(1))
                    .withSortField("name")
                    .withSortAscending(false);

            List<Customer> searchResults = customerRepository.search(criteria);
            log.info("\nSearch results for customers with name containing 'o', created in the last day, sorted by name:");
            searchResults.forEach(c -> log.info(" - " + c.getName() + " (" + c.getEmail() + ")"));

        } finally {
            // Shutdown Hibernate
            HibernateUtil.shutdown();
        }
    }
}
