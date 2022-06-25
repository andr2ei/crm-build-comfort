package ru.andronov.crm.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.andronov.crm.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    public List<Product> findAllProductsByLeadId(int lead_id) {
        var ql = "SELECT p FROM Product p WHERE p.isService = false AND p.leadId = :lead_id";
        TypedQuery<Product> query = em.createQuery(ql, Product.class);
        query.setParameter("lead_id", lead_id);
        log.debug("Querying by plql {}", ql);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllServicesByLeadId(int lead_id) {
        var ql = "SELECT p FROM Product p WHERE p.service = true AND p.leadId = :lead_id";
        TypedQuery<Product> query = em.createQuery(ql, Product.class);
        query.setParameter("lead_id", lead_id);
        log.debug("Querying by plql {}", ql);
        return  query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        var ql = "DELETE FROM Product p WHERE p.id = :id";
        Query query = em.createQuery(ql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
