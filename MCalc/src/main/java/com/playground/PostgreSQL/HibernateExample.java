package com.playground.PostgreSQL;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.repository.CustomerRepository;
import com.playground.PostgreSQL.repository.CustomerSearchCriteria;
import com.playground.PostgreSQL.utilities.HibernateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
//            System.out.println("Customers saved successfully!");

            // Find all customers
            List<Customer> allCustomers = customerRepository.findAll();
            System.out.println("\nAll customers:");
            allCustomers.forEach(c -> System.out.println(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Find customers by name containing "oh"
            List<Customer> customersWithNameContainingOh = customerRepository.findByNameContaining("oh");
            System.out.println("\nCustomers with name containing 'oh':");
            customersWithNameContainingOh.forEach(c -> System.out.println(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Find customer by email
            Optional<Customer> customerByEmail = customerRepository.findByEmail("jane.smith@example.com");
            System.out.println("\nCustomer by email:");
            customerByEmail.ifPresent(c -> System.out.println(" - " + c.getName() + " (" + c.getEmail() + ")"));

            // Complex search with multiple criteria
            CustomerSearchCriteria criteria = new CustomerSearchCriteria()
                    .withName("o")  // Name contains 'o'
                    .withCreatedAfter(LocalDateTime.now().minusDays(1))
                    .withSortField("name")
                    .withSortAscending(true);

            List<Customer> searchResults = customerRepository.search(criteria);
            System.out.println("\nSearch results for customers with name containing 'o', created in the last day, sorted by name:");
            searchResults.forEach(c -> System.out.println(" - " + c.getName() + " (" + c.getEmail() + ")"));

        } finally {
            // Shutdown Hibernate
            HibernateUtil.shutdown();
        }
    }
}
