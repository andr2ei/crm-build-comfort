package ru.andronov.crm.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.andronov.crm.domain.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ProductRepository.class)
class ProductRepositoryTest {

    @Autowired
    private IProductRepository productRepository;

    @Test
    void save() {
        var product = new Product(0, "name 5", 200.50, 5, "comment 5", 1, false);
        var actualProduct = productRepository.save(product);
        var expected = new Product(5, "name 5", 200.50, 5, "comment 5", 1, false);
        Assertions.assertEquals(expected, actualProduct);
    }

    @Test
    void update() {
        var product = new Product(1, "name 5", 200.50, 5, "comment 5", 1, false);
        var actualProduct = productRepository.update(product);
        Assertions.assertEquals(product, actualProduct);
    }

    @Test
    void findAllProductsByLeadId() {
        List<Product> allProductsByLeadId = productRepository.findAllProductsByLeadId(1);
        Assertions.assertEquals(1, allProductsByLeadId.size());
    }

    @Test
    void findAllServicesByLeadId() {
        List<Product> allServicesByLeadId = productRepository.findAllServicesByLeadId(1);
        Assertions.assertEquals(1, allServicesByLeadId.size());
    }

    @Test
    void deleteById() {
        productRepository.deleteById(1);
        List<Product> allServicesByLeadId = productRepository.findAllServicesByLeadId(1);
        Assertions.assertEquals(0, allServicesByLeadId.size());
    }
}