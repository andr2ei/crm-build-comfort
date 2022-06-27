package ru.andronov.crm.repository;

import ru.andronov.crm.domain.Product;

import java.util.List;

public interface IProductRepository {

    Product save(Product product);

    Product update(Product product);

    List<Product> findAllProductsByLeadId(int lead_id);

    List<Product> findAllServicesByLeadId(int lead_id);

    void deleteById(int id);

}
