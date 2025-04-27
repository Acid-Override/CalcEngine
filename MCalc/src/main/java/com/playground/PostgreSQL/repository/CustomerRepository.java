package com.playground.PostgreSQL.repository;

import com.playground.PostgreSQL.entities.*;
import com.playground.PostgreSQL.utilities.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CustomerRepository {

    public void save(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            log.info("Saved customer: {}", customer);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error saving customer", e);
        }
    }

    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error updating customer", e);
        }
    }

    public void delete(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error("Error deleting customer", e);
        }
    }

    /**
     * Find a customer by ID
     */
    public Optional<Customer> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root);
            Predicate predicate =
                    cb.and(
                            cb.equal(root.get(Customer_.id), id),
                            cb.isFalse(root.get(Customer_.deleted))

                    );
            query.where(predicate);

            Customer customer = session.createQuery(query).getSingleResultOrNull();
//                Customer customer = session.get(Customer.class, id);
            return Optional.ofNullable(customer);
        }
    }

    /**
     * Find all customers
     */
    public List<Customer> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root);

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            log.error("Error finding all customers", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Find customers by name (using Metamodel)
     */
    public List<Customer> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            // Using the generated metamodel for type-safety
            query.where(cb.equal(root.get(Customer_.name), name));

            return session.createQuery(query).getResultList();
        }
    }

    /**
     * Find customers by name containing a substring (using Metamodel)
     */
    public List<Customer> findByNameContaining(String nameSubstring) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            // Using the generated metamodel for type-safety
            query.where(cb.like(root.get(Customer_.name), "%" + nameSubstring + "%"));

            return session.createQuery(query).getResultList();
        }
    }

    public Optional<Customer> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
            Root<Customer> root = cq.from(Customer.class);
            cq.select(root);
            Predicate predicate =
                    cb.and(
                            cb.equal(root.get(Customer_.EMAIL), email),
                            cb.isFalse(root.get(Customer_.deleted))
            );
            cq.where(predicate);
            TypedQuery<Customer> query = session.createQuery(cq);
            return Optional.ofNullable(query.getSingleResultOrNull());

        } catch (Exception e) {
            log.error("Error finding customer", e);
            return null;
        }
    }

    /**
     * Complex search with multiple criteria (using Metamodel)
     */
    public List<Customer> search(CustomerSearchCriteria criteria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);

            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on provided criteria
            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                predicates.add(cb.like(root.get(Customer_.name), "%" + criteria.getName() + "%"));
            }

            if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
                predicates.add(cb.like(root.get(Customer_.email), "%" + criteria.getEmail() + "%"));
            }

            if (criteria.getCreatedAfter() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Customer_.creationDate), criteria.getCreatedAfter()));
            }

            if (criteria.getCreatedBefore() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(Customer_.creationDate), criteria.getCreatedBefore()));
            }

            // Add the predicates to the query
            if (!predicates.isEmpty()) {
                query.where(cb.and(predicates.toArray(new Predicate[0])));
            }

            // Add sorting if specified
            if (criteria.getSortField() != null) {
                switch (criteria.getSortField()) {
                    case "name":
                        query.orderBy(criteria.isSortAscending() ?
                                cb.asc(root.get(Customer_.name)) :
                                cb.desc(root.get(Customer_.name)));
                        break;
                    case "email":
                        query.orderBy(criteria.isSortAscending() ?
                                cb.asc(root.get(Customer_.email)) :
                                cb.desc(root.get(Customer_.email)));
                        break;
                    case "createdAt":
                        query.orderBy(criteria.isSortAscending() ?
                                cb.asc(root.get(Customer_.creationDate)) :
                                cb.desc(root.get(Customer_.creationDate)));
                        break;
                    default:
                        // Default sort by ID
                        query.orderBy(criteria.isSortAscending() ?
                                cb.asc(root.get(Customer_.id)) :
                                cb.desc(root.get(Customer_.id)));
                }
            }

            return session.createQuery(query).getResultList();
        }
    }
}
