package com.mediaocean.retailcounter.resource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.service.RetailCounterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/product/")
@Api(value = "/product", tags = "Product API")
public class ProductResource {

	@Autowired
	private RetailCounterService retailCounterService;
	
	private static final Logger LOGGER = Logger.getLogger(ProductResource.class.getName());
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new Product - Returns the product id", 
	notes = "Create a new Product", 
	response = Integer.class)
	public Response product(Product product) {
		try {
			Integer productId = retailCounterService.createProduct(product);
			return Response.ok().entity(productId).build();
		}catch(RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not create Product").build();
		}
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a list of new Products", 
	notes = "Create a list of new Products", 
	response = Integer.class)
	public Response products(List<Product> products) {
		try {
			retailCounterService.createProducts(products);
			return Response.ok().entity(Boolean.TRUE).build();
		}catch(RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not create Products").build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Products", 
	notes = "Find All Products", 
	response = List.class)
	public Response products() {
		try {
			List<Product> products = retailCounterService.findAllProducts();
			return Response.ok().entity(products).build();
		} catch (RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not find Products").build();
		}
	}
	
	
	@GET
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Product by product id", 
	notes = "Find Product by product id", 
	response = Product.class)
	public Response productById(@PathParam("productId") String productId) {
		if(StringUtils.isEmpty(productId) || !StringUtils.isNumeric(productId)) {
			return Response.status(422).entity("Correct productId is required to find Product").build();
		}
		try {
			Product product = retailCounterService.productById(Integer.valueOf(productId));
			return Response.ok().entity(product).build();
		}catch(RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not find Product by id: " + productId).build();
		
		}
	}
}
