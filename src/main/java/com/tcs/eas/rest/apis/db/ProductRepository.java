package com.tcs.eas.rest.apis.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.eas.rest.apis.model.Product;


/**
 * 
 * @author 44745
 *
 */
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
}
