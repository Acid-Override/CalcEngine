package com.playground.PostgreSQL;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.entities.Customer_;
import com.playground.PostgreSQL.entities.Order;
import com.playground.PostgreSQL.entities.Order_;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;

@Slf4j
public class PGTest {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Create CriteriaBuilder and CriteriaQuery
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            
            // Define root entities
            Root<Customer> customerRoot = query.from(Customer.class);
            Root<Order> orderRoot = query.from(Order.class);
            
            // Create JOIN condition: c.customer_id = o.customer_id
            Predicate joinCondition = cb.equal(customerRoot.get(Customer_.id), orderRoot.get(Order_.customer).get(Customer_.id));
            
            // Select specific fields (equivalent to SELECT c.customer_id, c.name, o.order_id)
            query.select(cb.tuple(
                    customerRoot.get(Customer_.id).alias("customerId"),
                    customerRoot.get(Customer_.name).alias("customerName"),
                    orderRoot.get(Order_.id).alias("orderId")
            )).where(
                    cb.and(
                            joinCondition,
                            cb.isFalse(customerRoot.get(Customer_.deleted)) // Only non-deleted customers
                    )
            );
            
            // Execute query and process results
            List<Tuple> results = session.createQuery(query).getResultList();
            
            log.info("Found {} customer-order combinations", results.size());
            
            for (Tuple tuple : results) {
                Long customerId = tuple.get("customerId", Long.class);
                String customerName = tuple.get("customerName", String.class);
                Long orderId = tuple.get("orderId", Long.class);
                
                log.info("Customer ID: {}, Name: {}, Order ID: {}", customerId, customerName, orderId);
            }
            
        } catch (Exception e) {
            log.error("Error executing criteria query: {}", e.getMessage(), e);
        }
    }

    /**
     * Alternative approach using JOIN FETCH for better performance
     * This fetches customers with their orders in one query
     */
    public static void demonstrateJoinFetch() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            
            // Fetch join to avoid N+1 problem
            root.fetch(Customer_.orders, JoinType.LEFT);
            
            query.select(root)
                 .where(cb.isFalse(root.get(Customer_.deleted)))
                 .distinct(true); // Remove duplicates caused by JOIN
            
            List<Customer> customers = session.createQuery(query).getResultList();
            
            log.info("Found {} customers with their orders", customers.size());
            
            for (Customer customer : customers) {
                log.info("Customer: {} has {} orders", 
                        customer.getName(), 
                        customer.getOrders().size());
                
                for (Order order : customer.getOrders()) {
                    log.info("  - Order ID: {}, Date: {}", 
                            order.getId(), 
                            order.getOrderDate());
                }
            }
            
        } catch (Exception e) {
            log.error("Error executing join fetch query: {}", e.getMessage(), e);
        }
    }
}
