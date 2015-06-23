package com.cemgunduz.cassandratemplate.service;

import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;

import java.util.List;

/**
 * Created by cem.gunduz on 20.06.2015.
 */
public interface ProductPriceService {

    /**
     * Get all known product prices by a product id
     * Sorts the list by timestamp before returning
     *
     * @param productId
     * @return
     */
    List<ProductPrice> getProductPricesByProductId(Long productId);

    /**
     * Persist a given list - bulk insert
     *
     * @param productPriceList
     */
    void saveProductPrices(List<ProductPrice> productPriceList);
}
