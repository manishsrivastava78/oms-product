package com.tcs.eas.rest.apis.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author 44745
 *
 */
@ApiModel
@Entity(name = "Product")
public class Product implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691676148924563243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	
	@NotNull(message="productname field is missing")
	@Size(min = 2,max = 50,message = "minimum 2 and maximum 50 characters are allowed for productname")
	private String productname;
	
	@NotNull(message="manufacturer field is missing")
	@Size(min = 2,max = 50,message = "minimum 2 and maximum 50 characters are allowed for manufacturer")
	private String manufacturer;
	

	@NotNull(message="manufactureddate field is missing")
	private Date manufactureddate;
	

	@NotNull(message="batchnumber field is missing")
	@Size(min = 2,max = 50,message = "minimum 2 and maximum 50 characters are allowed for batchnumber")
	private String batchnumber;
	
	@NotNull(message="productdesc field is missing")
	@Size(min = 2,max = 50,message = "minimum 2 and maximum 50 characters are allowed for productdesc")
	private String productdesc;
	
	@NotNull(message="category field is missing")
	@Size(min = 2,max = 20,message = "minimum 2 and maximum 50 characters are allowed for category")
	private String category;
	
	@NotNull(message="price field is missing")
	private Double price;
	
	@NotNull(message="currency field is missing")
	@Size(min = 2,max = 3,message = "minimum 2 and maximum 3 characters are allowed for currency")
	private String currency;
	
	
	
	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}


	/**
	 * @param productid the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}


	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}


	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * @return the batchnumber
	 */
	public String getBatchnumber() {
		return batchnumber;
	}


	/**
	 * @param batchnumber the batchnumber to set
	 */
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}


	/**
	 * @return the manufactureddate
	 */
	public Date getManufactureddate() {
		return manufactureddate;
	}


	/**
	 * @param manufactureddate the manufactureddate to set
	 */
	public void setManufactureddate(Date manufactureddate) {
		this.manufactureddate = manufactureddate;
	}


	public Product() {
		
	}


	

	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}


	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}


	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}


	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	/**
	 * @return the productdesc
	 */
	public String getProductdesc() {
		return productdesc;
	}


	/**
	 * @param productdesc the productdesc to set
	 */
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}


	public Product(
			@NotNull(message = "productname field is missing") @Size(min = 2, max = 50, message = "minimum 2 and maximum 50 characters are allowed for productname") String productname,
			@NotNull(message = "manufacturer field is missing") @Size(min = 2, max = 50, message = "minimum 2 and maximum 50 characters are allowed for manufacturer") String manufacturer,
			@NotNull(message = "manufactureddate field is missing") Date manufactureddate,
			@NotNull(message = "batchnumber field is missing") @Size(min = 2, max = 50, message = "minimum 2 and maximum 50 characters are allowed for batchnumber") String batchnumber,
			@NotNull(message = "productdesc field is missing") @Size(min = 2, max = 50, message = "minimum 2 and maximum 50 characters are allowed for productdesc") String productdesc,
			@NotNull(message = "category field is missing") @Size(min = 2, max = 20, message = "minimum 2 and maximum 50 characters are allowed for category") String category,
			@NotNull(message = "price field is missing") Double price,
			@NotNull(message = "currency field is missing") @Size(min = 2, max = 3, message = "minimum 2 and maximum 3 characters are allowed for currency") String currency) {
		super();
		this.productname = productname;
		this.manufacturer = manufacturer;
		this.manufactureddate = manufactureddate;
		this.batchnumber = batchnumber;
		this.productdesc = productdesc;
		this.category = category;
		this.price = price;
		this.currency = currency;
	}


	


	
	



	




	
	
		
}
