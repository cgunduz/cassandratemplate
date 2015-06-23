package com.cemgunduz.cassandratemplate.service.impl;

import com.cemgunduz.cassandratemplate.persistence.mysql.dao.ProductDao;
import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;
import com.cemgunduz.cassandratemplate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cem.gunduz on 22.06.2015.
 *
 * Not much going on on the service layer, but keeping it regardless for the sake of example / template unity
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    @SuppressWarnings("all")
    ProductDao productDao;

    @Override
    public Product getProductById(Long id) {

        return productDao.findOne(id);
    }

    @Override
    public void saveProduct(Product product) {

        productDao.save(product);
    }

    @Override
    public void saveProductList(List<Product> productList) {

        productDao.save(productList);
    }
}
