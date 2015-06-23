package com.cemgunduz.cassandratemplate.persistence.cassandra.dao.impl;

import com.cemgunduz.cassandratemplate.persistence.cassandra.dao.ProductPriceDao;
import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cem.gunduz on 20.06.2015.
 */

@Repository
public class ProductPriceDaoImpl implements ProductPriceDao {

    @Autowired
    private CassandraOperations cassandraOperations;

    @Override
    public List<ProductPrice> getProductPricesByProductId(Long productId) {

        Select select = QueryBuilder.select().from("ProductPrice");
        select.where(QueryBuilder.eq("productId", productId));

        return cassandraOperations.select(select, ProductPrice.class);
    }

    @Override
    public void saveProductPrices(List<ProductPrice> productPriceList) {

        cassandraOperations.insert(productPriceList);
    }
}
