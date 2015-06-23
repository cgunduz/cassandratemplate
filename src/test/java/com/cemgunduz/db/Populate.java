package com.cemgunduz.db;

import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;
import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;
import com.cemgunduz.cassandratemplate.service.ProductPriceService;
import com.cemgunduz.cassandratemplate.service.ProductService;
import com.cemgunduz.db.models.Row;
import com.cemgunduz.db.models.Rows;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cem.gunduz on 21.06.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/testConfig.xml")
@Ignore
public class Populate {

    //  TODO : Why not relative path ?
    public static final String ABSOLUTE_SITE_DATA_PATH = "E:\\Developer\\workspace\\personalwebsite-master\\src\\main\\resources\\site-data\\";

    private long uniqueCassandraId = 0;

    @Autowired
    ProductPriceService productPriceService;

    @Autowired
    ProductService productService;

    /**
     * Responsible for db population
     * The data is extracted from the xml files and stored in cassandra and mysql as required
     *
     * @throws JAXBException
     */
    @Test
    public void populateDbs() throws JAXBException {

        long startTime = System.currentTimeMillis();

        // Inýtializations for unmarshalling
        JAXBContext jaxbContext = JAXBContext.newInstance(Rows.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // Init for all distinct sites
        List<String> siteNameList = Arrays.asList("site1", "site2", "site3", "site4");
        for(String siteName : siteNameList)
        {
            System.out.println("Starting for : " + siteName);
            long opStartTime = System.currentTimeMillis();

            // Unmarshalling completed here, for a site xml
            String xmlFileName = siteName.concat(".xml");
            Rows rows = (Rows) jaxbUnmarshaller.unmarshal(new File(ABSOLUTE_SITE_DATA_PATH.concat(xmlFileName)));

            // RDBMs insert operation would be done as batch to gain time
            List<Product> siteProductList = new ArrayList<Product>();

            for(Row row : rows.getRows())
            {
                // For each product, basic product info mapping is completed and stored in a list to be
                // later on batch inserted to the RDBMS ( Mysql )
                siteProductList.add(rowToProductConverter(row, siteName));

                // For each product, price over time is extracted and persisted to cassandra
                productPriceService.saveProductPrices(rowToProductPriceConverter(row, siteName));
            }

            System.out.println(System.currentTimeMillis() - opStartTime);

            // Mysql insertion made here
            productService.saveProductList(siteProductList);

            System.out.println(System.currentTimeMillis() - opStartTime);
        }
    }

    /**
     * Conversion from unmarshalled pojo to Mysql storable
     *
     * @param row
     * @param siteId
     * @return
     */
    private Product rowToProductConverter(Row row, String siteId)
    {
        Product product = new Product();
        product.setId(row.getId());
        product.setBrand(row.getBrand());
        product.setCategory(row.getCategory());
        product.setSiteName(siteId);
        product.setTitle(row.getTitle());
        product.setUrl(row.getUrl());

        return product;
    }

    /**
     * Conversion from unmarshalled pojo to cassandra storable
     *
     * @param row
     * @param siteId
     * @return
     */
    private List<ProductPrice> rowToProductPriceConverter(Row row, String siteId)
    {
        List<ProductPrice> productPriceList = new ArrayList<ProductPrice>();

        List<String> dateList = Arrays.asList(row.getDates().split(","));
        List<String> priceList = Arrays.asList(row.getPrices().split(","));

        for(int i = 0; i < dateList.size(); i++)
        {
            /*
            System.out.println(dateList.get(i));
            System.out.println(priceList.get(i));
            System.out.println(row.getTitle());
            System.out.println(siteId);
            */

            ProductPrice productPrice = new ProductPrice();
            productPrice.setProductPriceId(uniqueCassandraId++);
            productPrice.setProductId(row.getId());
            productPrice.setTimestamp(Long.valueOf(dateList.get(i)));
            productPrice.setSiteId(siteId);

            // Sometimes there are no price informations
            productPrice.setPrice(priceList.get(i).isEmpty() ? 0.0 : Double.valueOf(priceList.get(i)));

            productPriceList.add(productPrice);
        }

        return productPriceList;
    }
}
