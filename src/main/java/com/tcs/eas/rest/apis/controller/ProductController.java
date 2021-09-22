package com.tcs.eas.rest.apis.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.eas.rest.apis.db.ProductDaoService;
import com.tcs.eas.rest.apis.exception.ProductNotFound;
import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.model.Product;
import com.tcs.eas.rest.apis.utility.Utility;

/**
 * 
 * @author 44745
 *
 */
@RestController
@RequestMapping("/apis/v1")
public class ProductController {

	@Autowired
	LoggingService loggingService;
	/**
	 * 
	 */
	@Autowired
	ProductDaoService productDaoService;

	/**
	 * 
	 * @param product
	 * @param headers
	 * @return
	 */
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product,
			@RequestHeader Map<String, String> headers) {
		Product savedProduct = productDaoService.save(product);
		// loggingService.writeProcessLog("POST","product","createproduct",product);
		// loggingService.clearMDC();
		// productEntity ce = test();
		loggingService.writeProcessLog("POST", "products", "createProduct", product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productid}")
				.buildAndExpand(savedProduct.getProductid()).toUri();
		return ResponseEntity.created(location).headers(Utility.getCustomResponseHeaders(headers)).build();
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(@RequestHeader Map<String, String> headers) {
		List<Product> products = productDaoService.findAll(headers.get("x-admin-key"));
		loggingService.writeProcessLog("GET", "products", "getProduct", products);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(products);
	}

	@GetMapping("/products/{productid}")
	public ResponseEntity<Product>  getProduct(@PathVariable int productid,@RequestHeader Map<String, String> headers) {
		Product product = productDaoService.getProductById(productid);
		if (product == null) {
			throw new ProductNotFound("productid " + productid+ " does not exist");
		}
		loggingService.writeProcessLog("GET", "products", "getProduct by id", product);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(product);
	}

	@PutMapping("/products/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productid,@RequestHeader Map<String, String> headers) {
		Product product = productDaoService.getProductById(productid);
		if (product == null) {
			throw new ProductNotFound("productid " + productid+ " does not exist");
		}
		loggingService.writeProcessLog("PUT", "products", "updateProduct by id", product);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(product);
	}

}
