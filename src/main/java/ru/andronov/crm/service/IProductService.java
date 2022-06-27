package ru.andronov.crm.service;

import ru.andronov.crm.domain.Product;

import java.util.List;

public interface IProductService {

    Product create(Product product);

    Product update(Product product);

    List<Product> getProductsByLeadId(int leadId);

    List<Product> getServicesByLeadId(int leadId);

    void deleteById(int id);

}
