package com.playground.PostgreSQL;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.entities.Customer_;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;

@Slf4j
public class NPlusOneDemonstration {

    /**
     * ❌ BAD: This will cause N+1 problem
     * If you have 5 customers, this will execute 6 queries:
     * 1 query to get customers + 5 queries (one for each customer's orders)
     */
    public static void demonstrateNPlusOneProblem() {
        log.info("=== DEMONSTRATING N+1 PROBLEM (BAD) ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Query 1: Get all customers
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            
            query.select(root).where(cb.isFalse(root.get(Customer_.deleted)));
            
            List<Customer> customers = session.createQuery(query).getResultList();
            log.info("✅ Query 1: Loaded {} customers", customers.size());
            
            // The N+1 problem happens here:
            for (Customer customer : customers) {
                // Each call to .getOrders().size() triggers a separate SQL query!
                // Query 2, 3, 4, 5, 6... (one for each customer)
                int orderCount = customer.getOrders().size();
                log.info("❌ Additional Query: Customer '{}' has {} orders", 
                        customer.getName(), orderCount);
            }
            
            log.info("💥 RESULT: Executed 1 + {} = {} total queries!", 
                    customers.size(), 1 + customers.size());
        }
    }

    /**
     * ✅ GOOD: This prevents N+1 problem with JOIN FETCH
     * No matter how many customers, this will execute only 1 query
     */
    public static void demonstrateJoinFetchSolution() {
        log.info("\n=== DEMONSTRATING JOIN FETCH SOLUTION (GOOD) ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            
            // 🔑 KEY: fetch join loads related orders in the SAME query
            root.fetch(Customer_.orders, jakarta.persistence.criteria.JoinType.LEFT);
            
            query.select(root)
                 .where(cb.isFalse(root.get(Customer_.deleted)))
                 .distinct(true); // Remove duplicates caused by JOIN
            
            List<Customer> customers = session.createQuery(query).getResultList();
            log.info("✅ Single Query: Loaded {} customers with ALL their orders", customers.size());
            
            // No additional queries are executed here!
            for (Customer customer : customers) {
                // The orders are already loaded - no additional SQL queries!
                int orderCount = customer.getOrders().size();
                log.info("✅ No Extra Query: Customer '{}' has {} orders", 
                        customer.getName(), orderCount);
            }
            
            log.info("🎉 RESULT: Executed only 1 total query!");
        }
    }

    /**
     * 📊 Alternative: Using explicit JOIN in Criteria API
     */
    public static void demonstrateExplicitJoin() {
        log.info("\n=== DEMONSTRATING EXPLICIT JOIN (ALTERNATIVE) ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<jakarta.persistence.Tuple> query = cb.createTupleQuery();
            Root<Customer> customerRoot = query.from(Customer.class);
            
            // Create explicit join
            var orderJoin = customerRoot.join(Customer_.orders, jakarta.persistence.criteria.JoinType.LEFT);
            
            // Select customer and count of orders
            query.select(cb.tuple(
                customerRoot.get(Customer_.name).alias("customerName"),
                cb.count(orderJoin.get("id")).alias("orderCount")
            )).where(cb.isFalse(customerRoot.get(Customer_.deleted)))
             .groupBy(customerRoot.get(Customer_.id), customerRoot.get(Customer_.name));
            
            List<jakarta.persistence.Tuple> results = session.createQuery(query).getResultList();
            
            log.info("✅ Single Query with GROUP BY: Loaded customer names with order counts");
            for (jakarta.persistence.Tuple result : results) {
                String customerName = result.get("customerName", String.class);
                Long orderCount = result.get("orderCount", Long.class);
                log.info("✅ Customer '{}' has {} orders", customerName, orderCount);
            }
            
            log.info("🎉 RESULT: Executed only 1 optimized query with aggregation!");
        }
    }

    public static void main(String[] args) {
        // Demonstrate the problem
        demonstrateNPlusOneProblem();
        
        // Show the solution
        demonstrateJoinFetchSolution();
        
        // Show alternative approach
        demonstrateExplicitJoin();
    }
}