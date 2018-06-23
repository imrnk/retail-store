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

import com.mediaocean.retailcounter.model.Bill;
import com.mediaocean.retailcounter.model.LineItem;
import com.mediaocean.retailcounter.model.Order;
import com.mediaocean.retailcounter.model.Product;
import com.mediaocean.retailcounter.service.RetailCounterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/store/")
@Api(value = "/store", tags = "Retail Counter API")
public class RetailCounterResource {
	
	@Autowired
	private RetailCounterService retailCounterService;
	
	private static final Logger LOGGER = Logger.getLogger(RetailCounterResource.class.getName());
	
	@POST
	@Path("/order")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new order - Returns the order id", 
	notes = "Create a new order", 
	response = Integer.class)
	public Response order(List<LineItem> items) {
		try {
			Integer orderId = retailCounterService.createOrder(items);
			return Response.ok().entity(orderId).build();
		}catch(RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not create Order").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Orders", 
	notes = "Find All Orders", 
	response = List.class)
	public Response orders() {
		try {
			List<Order> orders = retailCounterService.findAllOrders();
			return Response.ok().entity(orders).build();
		} catch (RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not find Orders").build();
		}
	}
	@GET
	@Path("/order/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find an order by Id", 
	notes = "Find an order by Id", 
	response = Order.class)
	public Response orderById(@PathParam("orderId") String orderId) {
		if(StringUtils.isEmpty(orderId) || !StringUtils.isNumeric(orderId)) {
			return Response.status(422).entity("Correct orderId is required to find Order").build();
		}
		try {
			Order order = retailCounterService.orderById(Integer.valueOf(orderId));
			return Response.ok().entity(order).build();
		}catch(RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not find Order by id: " + orderId).build();
		
		}
	}
	
	@GET
	@Path("/bill/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find the Bill by orderId", 
	notes = "Find the Bill by orderId", 
	response = Bill.class)
	public Response billForOrder(@PathParam("orderId") String orderId) {
		if(StringUtils.isEmpty(orderId) || !StringUtils.isNumeric(orderId)) {
			return Response.status(422).entity("Correct orderId is required to find Bill").build();
		}
		try {
		 Bill bill = retailCounterService.findBill(Integer.valueOf(orderId));
		 return Response.ok().entity(bill).build();
		}catch (RuntimeException re) {
			LOGGER.log(Level.SEVERE, re.getMessage());
			return Response.status(422).entity("Could not find Bill by order id: " + orderId).build();
		
		}
	}
	
	/*@POST
	@Path("/product")
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
	}
	
	
	@GET
	@Path("/product/{productId}")
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
	}*/
}
