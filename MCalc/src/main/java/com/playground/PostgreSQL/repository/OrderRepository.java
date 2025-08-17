package com.playground.PostgreSQL.repository;

import com.playground.PostgreSQL.entities.Customer;
import com.playground.PostgreSQL.entities.Customer_;
import com.playground.PostgreSQL.entities.Order;
import com.playground.PostgreSQL.entities.Order_;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OrderRepository {

    /**
     * Save an order
     */
    public void save(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error saving order", e);
        }
    }

    /**
     * Update an order
     */
    public void update(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error updating order", e);
        }
    }

    /**
     * Delete an order
     */
    public void delete(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Error deleting order", e);
        }
    }

    /**
     * Find an order by ID
     */
    public Optional<Order> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.select(root);
            Predicate predicate = cb.and(
                    cb.equal(root.get(Order_.id), id),
                    cb.isFalse(root.get(Order_.deleted))
            );
            query.where(predicate);
            return Optional.ofNullable(session.createQuery(query).getSingleResultOrNull());
        }
    }

    /**
     * Find all orders
     */
    public List<Order> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.select(root).where(cb.isFalse(root.get(Order_.deleted)));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding all orders", e);
            return new ArrayList<>();
        }
    }

    /**
     * Find orders by customer ID
     */
    public List<Order> findByCustomerId(Long customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);

            // Join with customer
            Join<Order, Customer> customerJoin = root.join(Order_.customer);

            // Add where clause
            query.where(cb.equal(customerJoin.get(Customer_.id), customerId));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding orders by customer ID", e);
            return new ArrayList<>();
        }
    }

    /**
     * Find orders between two dates
     */
    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);

            // Add where clause
            query.where(
                    cb.between(root.get(Order_.orderDate), startDate, endDate)
            );

            // Order by date descending
            query.orderBy(cb.desc(root.get(Order_.orderDate)));

            List<Order> orders = session.createQuery(query).getResultList();
            return orders;
        } catch (Exception e) {
            log.error("Error finding orders between dates", e);
            return new ArrayList<>();
        }
    }

    /**
     * Find orders by status
     */
    public List<Order> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);

            // Add where clause
            query.where(cb.equal(root.get(Order_.status), status));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding orders by status", e);
            return new ArrayList<>();
        }
    }

    /**
     * Find customers with orders
     */
    public List<Customer> findCustomersWithOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            // Join with orders
            root.join(Customer_.orders, JoinType.INNER);

            // Use distinct to avoid duplicates
            query.select(root).distinct(true);

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding customers with orders", e);
            return new ArrayList<>();
        }
    }

    /**
     * Find customers with orders of a specific status
     */
    public List<Customer> findCustomersWithOrderStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            // Join with orders
            Join<Customer, Order> ordersJoin = root.join(Customer_.orders);

            // Add where clause on order status
            query.where(cb.equal(ordersJoin.get(Order_.status), status));

            // Use distinct to avoid duplicates
            query.select(root).distinct(true);

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding customers with order status", e);
            return new ArrayList<>();
        }
    }

    /**
     * Count orders for each customer
     * @return List of Tuple where tuple.get(0) = Customer, tuple.get(1) = order count
     */
    public List<Tuple> countOrdersByCustomer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<Order> root = query.from(Order.class);

            // Join with customer
            Join<Order, Customer> customerJoin = root.join(Order_.customer);

            // Group by customer
            query.groupBy(customerJoin.get(Customer_.id));

            // Select customer and count using modern tuple approach
            query.select(cb.tuple(customerJoin, cb.count(root)));

            // Order by count descending
            query.orderBy(cb.desc(cb.count(root)));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error counting orders by customer", e);
            return new ArrayList<>();
        }
    }
}
