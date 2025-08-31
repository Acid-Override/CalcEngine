package com.playground.PostgreSQL;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.entities.Customer_;
import com.playground.PostgreSQL.entities.Order;
import com.playground.PostgreSQL.entities.Order_;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class ConditionalLoadingStrategies {

    /**
     * Strategy 1: Load customers only, then conditionally load orders when needed
     * This is the most flexible approach for your use case
     */
    public static void strategyOne_ConditionalLoading() {
        log.info("=== STRATEGY 1: CONDITIONAL LOADING ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // STEP 1: Load customers only (fast, lightweight)
            log.info("📋 Loading customers only...");
            List<Customer> customers = loadCustomersOnly(session);
            log.info("✅ Loaded {} customers with minimal data", customers.size());
            
            // STEP 2: Conditionally load orders when needed
            boolean needOrdersForDisplay = true; // Your business logic determines this
            
            if (needOrdersForDisplay) {
                log.info("📦 Orders needed - loading efficiently...");
                
                // Option A: Load orders for specific customers
                List<Long> customerIds = customers.stream()
                    .map(Customer::getId)
                    .toList();
                
                loadOrdersForCustomers(session, customerIds);
                log.info("✅ Orders loaded efficiently in batch");
                
                // Now you can safely access orders without N+1 problem
                for (Customer customer : customers) {
                    int orderCount = customer.getOrders().size();
                    log.info("👤 {} has {} orders", customer.getName(), orderCount);
                }
            } else {
                log.info("⚡ Skipping order loading - not needed for this operation");
            }
        }
    }

    /**
     * Strategy 2: Repository-level methods for different scenarios
     */
    public static void strategyTwo_RepositoryPatterns() {
        log.info("\n=== STRATEGY 2: REPOSITORY PATTERNS ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // Scenario A: Just need customer list (e.g., dropdown, search results)
            log.info("📋 Scenario A: Loading customers for dropdown list");
            List<Customer> customersForDropdown = loadCustomersOnly(session);
            log.info("✅ Fast load: {} customers", customersForDropdown.size());
            
            // Scenario B: Need customers with order summary (e.g., dashboard)
            log.info("📊 Scenario B: Loading customers with order counts");
            List<Object[]> customerSummaries = loadCustomersWithOrderCounts(session);
            for (Object[] summary : customerSummaries) {
                String name = (String) summary[0];
                Long orderCount = (Long) summary[1];
                log.info("📊 {} has {} orders", name, orderCount);
            }
            
            // Scenario C: Need full customer details with orders (e.g., customer profile)
            log.info("👤 Scenario C: Loading specific customer with full order details");
            Customer customerWithOrders = loadCustomerWithOrdersById(session, 1L);
            if (customerWithOrders != null) {
                log.info("👤 {} has {} full orders loaded", 
                        customerWithOrders.getName(), 
                        customerWithOrders.getOrders().size());
            }
        }
    }

    /**
     * Strategy 3: Batch loading with Hibernate's batch-size
     */
    public static void strategyThree_BatchLoading() {
        log.info("\n=== STRATEGY 3: BATCH LOADING ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // Load customers first
            List<Customer> customers = loadCustomersOnly(session);
            log.info("📋 Loaded {} customers", customers.size());
            
            // This will use batch loading if @BatchSize is configured on Customer.orders
            // Instead of N queries, it will use fewer batch queries
            log.info("🔄 Accessing orders with batch loading...");
            
            for (Customer customer : customers) {
                // Hibernate will batch-load orders in groups instead of one-by-one
                int orderCount = customer.getOrders().size();
                log.info("📦 {} has {} orders (batch loaded)", customer.getName(), orderCount);
            }
        }
    }

    /**
     * Strategy 4: Dynamic loading using JPA Entity Graphs
     * Entity Graphs allow you to define what should be loaded at runtime
     */
    public static void strategyFour_EntityGraphs() {
        log.info("\n=== STRATEGY 4: JPA ENTITY GRAPHS ===");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // Option A: Create a dynamic Entity Graph for loading customers with orders
            log.info("📊 Creating dynamic Entity Graph...");
            
            // Create a dynamic Entity Graph that includes orders
            jakarta.persistence.EntityGraph<Customer> customerWithOrdersGraph = 
                session.createEntityGraph(Customer.class);
            customerWithOrdersGraph.addAttributeNodes("orders");
            log.info("✅ Created dynamic Entity Graph for Customer with orders");
            
            // Option B: Use Entity Graph with Criteria Query
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            
            query.select(root).where(cb.isFalse(root.get(Customer_.deleted)));
            
            // Apply the Entity Graph to the query
            List<Customer> customersWithOrders = session.createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", customerWithOrdersGraph)
                .getResultList();
            
            log.info("📦 Loaded {} customers using Entity Graph", customersWithOrders.size());
            
            // Verify orders are loaded (no additional SQL queries)
            for (Customer customer : customersWithOrders) {
                int orderCount = customer.getOrders().size();
                log.info("✅ {} has {} orders (loaded via Entity Graph)", customer.getName(), orderCount);
            }
            
            // Option C: Create different graphs for different scenarios
            demonstrateMultipleEntityGraphs(session);
            
            // Option D: Use Named Entity Graphs (defined in @NamedEntityGraph annotation)
            demonstrateNamedEntityGraphs(session);
        }
    }

    /**
     * Demonstrates creating different Entity Graphs for different loading scenarios
     */
    private static void demonstrateMultipleEntityGraphs(Session session) {
        log.info("\n--- Multiple Entity Graph Scenarios ---");
        
        // Scenario 1: Load customer with orders only
        jakarta.persistence.EntityGraph<Customer> customerOrdersGraph = 
            session.createEntityGraph(Customer.class);
        customerOrdersGraph.addAttributeNodes("orders");
        
        // Scenario 2: Load customer with orders only (items require separate query)
        // NOTE: Can't fetch multiple collections (Customer.orders + Order.items) simultaneously
        // This would cause MultipleBagFetchException
        jakarta.persistence.EntityGraph<Customer> customerOrdersOnlyGraph = 
            session.createEntityGraph(Customer.class);
        customerOrdersOnlyGraph.addAttributeNodes("orders");
        
        // Use different graphs based on need
        boolean needDeepLoading = false; // Your business logic
        
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        query.select(root).where(cb.equal(root.get(Customer_.id), 1L));
        
        Customer customer;
        if (needDeepLoading) {
            log.info("📦 Loading customer with orders (items would need separate query)...");
            // Note: Can't load both Customer.orders and Order.items in same query
            // Would need to load items in a separate query after loading orders
            customer = session.createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", customerOrdersOnlyGraph)
                .getSingleResultOrNull();
        } else {
            log.info("📋 Loading customer with orders only...");
            customer = session.createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", customerOrdersGraph)
                .getSingleResultOrNull();
        }
        
        if (customer != null) {
            log.info("✅ Loaded customer: {} with {} orders", 
                    customer.getName(), customer.getOrders().size());
        }
    }

    /**
     * Demonstrates using Named Entity Graphs defined with @NamedEntityGraph annotation
     */
    private static void demonstrateNamedEntityGraphs(Session session) {
        log.info("\n--- Named Entity Graphs ---");
        
        try {
            // Use the named entity graph defined in Customer entity
            jakarta.persistence.EntityGraph<?> namedGraph = 
                session.getEntityGraph("Customer.withOrders");
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            
            query.select(root).where(cb.isFalse(root.get(Customer_.deleted)));
            
            List<Customer> customers = session.createQuery(query)
                .setHint("jakarta.persistence.fetchgraph", namedGraph)
                .setMaxResults(5)
                .getResultList();
            
            log.info("📦 Loaded {} customers using Named Entity Graph", customers.size());
            
            for (Customer customer : customers) {
                int orderCount = customer.getOrders().size();
                log.info("✅ {} has {} orders (via Named Graph)", customer.getName(), orderCount);
            }
            
        } catch (Exception e) {
            log.warn("Named Entity Graph not available or configured: {}", e.getMessage());
            log.info("💡 To use named graphs, ensure they're properly defined in the entity class");
        }
    }

    // ==================== UTILITY METHODS ====================

    private static List<Customer> loadCustomersOnly(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        
        query.select(root).where(cb.isFalse(root.get(Customer_.deleted)));
        
        return session.createQuery(query).getResultList();
    }

    private static void loadOrdersForCustomers(Session session, List<Long> customerIds) {
        if (customerIds.isEmpty()) return;
        
        // Pre-load orders for all customers in one query
        String hql = """
            SELECT o FROM Order o 
            WHERE o.customer.id IN :customerIds
            """;
        
        Query<Order> orderQuery = session.createQuery(hql, Order.class);
        orderQuery.setParameter("customerIds", customerIds);
        
        // Execute the query - this populates Hibernate's session cache
        List<Order> orders = orderQuery.getResultList();
        log.info("🔄 Pre-loaded {} orders for {} customers", orders.size(), customerIds.size());
    }

    private static List<Object[]> loadCustomersWithOrderCounts(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Customer> customerRoot = query.from(Customer.class);
        
        var orderJoin = customerRoot.join(Customer_.orders, jakarta.persistence.criteria.JoinType.LEFT);
        
        query.multiselect(
                customerRoot.get(Customer_.name),
                cb.count(orderJoin.get(Order_.id))
        ).where(cb.isFalse(customerRoot.get(Customer_.deleted)))
         .groupBy(customerRoot.get(Customer_.id), customerRoot.get(Customer_.name));
        
        return session.createQuery(query).getResultList();
    }

    private static Customer loadCustomerWithOrdersById(Session session, Long customerId) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        
        // Fetch join for orders
        root.fetch(Customer_.orders, jakarta.persistence.criteria.JoinType.LEFT);
        
        query.select(root).where(
            cb.and(
                cb.equal(root.get(Customer_.id), customerId),
                cb.isFalse(root.get(Customer_.deleted))
            )
        ).distinct(true);
        
        return session.createQuery(query).getSingleResultOrNull();
    }

    private static boolean shouldLoadOrders() {
        // Your business logic to determine if orders are needed
        // Could be based on user role, request parameters, etc.
        return Math.random() > 0.5; // Random for demo
    }

    public static void main(String[] args) {
        strategyOne_ConditionalLoading();
        strategyTwo_RepositoryPatterns();
        strategyThree_BatchLoading();
        strategyFour_EntityGraphs();
    }
}