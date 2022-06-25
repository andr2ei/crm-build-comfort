package ru.andronov.crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.andronov.crm.domain.Product;
import ru.andronov.crm.repository.IProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService  implements  IProductService {

    private final IProductRepository productRepository;

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED)
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED)
    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    @Override
    public List<Product> getProductsByLeadId(int leadId) {
        return productRepository.findAllProductsByLeadId(leadId);
    }

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            readOnly = true)
    @Override
    public List<Product> getServicesByLeadId(int leadId) {
        return productRepository.findAllServicesByLeadId(leadId);
    }

    @Transactional(
            isolation = Isolation.READ_UNCOMMITTED,
            propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
