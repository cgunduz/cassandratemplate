package com.cemgunduz.cassandratemplate.persistence.cassandra.dao;

import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;

import java.util.List;

/**
 * Created by cem.gunduz on 20.06.2015.
 */
public interface ProductPriceDao {

    /**
     * Returns all known prices of a product for different time intervals and sites
     *
     * @param productId
     * @return
     */
    List<ProductPrice> getProductPricesByProductId(Long productId);

    /**
     * Persists the product price list
     *
     * @param productPriceList
     */
    void saveProductPrices(List<ProductPrice> productPriceList);
}
