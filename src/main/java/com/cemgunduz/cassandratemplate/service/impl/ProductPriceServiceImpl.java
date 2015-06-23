package com.cemgunduz.cassandratemplate.service.impl;

import com.cemgunduz.cassandratemplate.persistence.cassandra.dao.ProductPriceDao;
import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;
import com.cemgunduz.cassandratemplate.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Created by cem.gunduz on 20.06.2015.
 *
 */

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    ProductPriceDao productPriceDao;

    @Override
    public List<ProductPrice> getProductPricesByProductId(Long productId) {

        List<ProductPrice> productPriceList = productPriceDao.getProductPricesByProductId(productId);
        productPriceList.sort(new Comparator<ProductPrice>() {

            @Override
            public int compare(ProductPrice o1, ProductPrice o2) {

                if(o1.getTimestamp() > o2.getTimestamp()) return 1;
                else if(o1.getTimestamp().equals(o2.getTimestamp())) return 0;

                return -1;
            }
        });

        return productPriceList;
    }

    @Override
    public void saveProductPrices(List<ProductPrice> productPriceList) {

        productPriceDao.saveProductPrices(productPriceList);
    }
}
