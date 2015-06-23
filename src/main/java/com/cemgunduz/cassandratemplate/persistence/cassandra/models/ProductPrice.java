package com.cemgunduz.cassandratemplate.persistence.cassandra.models;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by cem.gunduz on 19.06.2015.
 *
 * Product prices bom for storage in cassandra
 */
@Table
public class ProductPrice {

    @PrimaryKey
    private Long productPriceId;
    private Long productId;
    private Double price;
    private Long timestamp;

    // Left out as string in the name of simplicity
    private String siteId;

    // Generated getters and setters below
    public Long getProductPriceId() {
        return productPriceId;
    }

    public void setProductPriceId(Long productPriceId) {
        this.productPriceId = productPriceId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
