package com.cemgunduz.proofing;

import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;
import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;
import com.cemgunduz.cassandratemplate.service.ProductPriceService;
import com.cemgunduz.cassandratemplate.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/testConfig.xml")
public class AppTests {

    @Autowired
    ProductPriceService productPriceService;

    @Autowired
    ProductService productService;

    /**
     * Cassandra sanity test
     *
     * Queries cassandra regarding a specific products prices over time
     * Has some expectation regarding what the values would/should be
     *
     */
    @Test
    public void getProductPrices()
    {
        List<ProductPrice> productPriceList =
                productPriceService.getProductPricesByProductId(1017561L);

        Assert.assertTrue(productPriceList.size() == 5);
        Assert.assertTrue(productPriceList.get(0).getPrice() == 169.0);
        Assert.assertTrue(productPriceList.get(1).getPrice() == 189.0);
        Assert.assertTrue(productPriceList.get(2).getPrice() == 84.5);

        // Note : I know it can be a longer and a more reliable check but leaving it here with minimal checks
        // for the sake of example
    }

    /**
     * Mysql sanity test
     *
     * Queries mysql regarding a specific product expects certain values to be returned
     * Mostly checks success of population and mysql is up and running or not
     *
     */
    @Test
    public void getProductDetails()
    {
        Product product = productService.getProductById(1017561L);

        Assert.assertTrue(product.getId() == 1017561L);
        Assert.assertTrue(product.getBrand().contains("TOMTOM"));
        Assert.assertTrue(product.getCategory().contains("Navigasyon"));

        // Same as above
    }
}
