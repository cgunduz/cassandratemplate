package com.cemgunduz.cassandratemplate.presentation.controller;

import com.cemgunduz.cassandratemplate.persistence.cassandra.models.ProductPrice;
import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;
import com.cemgunduz.cassandratemplate.presentation.model.URI;
import com.cemgunduz.cassandratemplate.service.ProductPriceService;
import com.cemgunduz.cassandratemplate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomePageController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductPriceService productPriceService;

	@RequestMapping("/")
	public String homepageInterceptor(ModelMap model) {

		model.addAttribute("product", new Product());

		return URI.HOMEPAGE.getAddress();
	}


	@RequestMapping(value = "/queryProduct")
	public String queryProduct(@RequestParam Long id ,ModelMap model) {

		// Create an empty product as would be required by the template engine
		// If the queried result is not found
		Product product = productService.getProductById(Long.valueOf(id));
		if(product == null)
		{
			product = new Product();
			model.addAttribute("priceListShared", false);
		}
		else
		{
			List<ProductPrice> productPriceList = productPriceService.getProductPricesByProductId(id);
			if(productPriceList.size() > 1)
			{
				model.addAttribute("priceListShared", true);
				model.addAttribute("productPriceList", productPriceList);
			}
			else
			{
				model.addAttribute("priceListShared", false);
			}
		}

		model.addAttribute("product", product);

		return URI.HOMEPAGE.getAddress();
	}
}