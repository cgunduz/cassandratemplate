package com.cemgunduz.cassandratemplate.service;

import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;

import java.util.List;

/**
 * Created by cem.gunduz on 22.06.2015.
 *
 * Pretty dumb atm, but is left out for the sake of template structure
 */
public interface ProductService {

    Product getProductById(Long id);
    void saveProduct(Product product);
    void saveProductList(List<Product> productList);
}
