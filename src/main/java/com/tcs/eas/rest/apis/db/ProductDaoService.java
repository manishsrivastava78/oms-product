package com.tcs.eas.rest.apis.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.model.Inventory;
import com.tcs.eas.rest.apis.model.Product;

/**
 * 
 * @author 44745
 *
 */
@Component
public class ProductDaoService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	LoggingService loggingService;

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Product save(Product product) {
		// Address address = customer.getAddress();
		// Address addressEntity = new AddressEntity(address.getStreet(),
		// address.getCity(), address.getPostcode(), address.getState(),
		// address.getCountry());
		// Customer customer = new
		// Customer(customer.getFirstname(),customer.getLastname(),customer.getMobile(),customer.getEmail(),addressEntity);
		productRepository.save(product);
		return product;
	}

	/**
	 * 
	 * @return
	 */
	public List<Product> findAll(String key) {
		List<Product> productDetails = new ArrayList<Product>();
		String adminKey = "abc123";
		List<Product> products = productRepository.findAll();
		if (key == null || !key.equals(adminKey)) {
			RestTemplate restTemplate = new RestTemplate();
			for (Product product : products) {
				String fooResourceUrl = "http://oms-inventory-svc.app.svc.cluster.local:8080/apis/v1/inventories/products/"
						+ product.getProductid();
				try {
					ResponseEntity<Inventory> response = restTemplate.getForEntity(fooResourceUrl, Inventory.class);
					Inventory inventory = response.getBody();
					if (inventory.getAvailablequantity() > inventory.getMinquantity()) {
						productDetails.add(product);
					}
				} catch (Exception e) {
					loggingService.logError(e.getMessage());
				}
			}
			return productDetails;
		} else {
			return products;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Product getProductById(Integer id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else {
			return null;
		}
	}
}
