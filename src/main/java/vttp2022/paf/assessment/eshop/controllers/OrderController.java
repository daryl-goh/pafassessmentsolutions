package vttp2022.paf.assessment.eshop.controllers;


import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.services.CustomerService;
import vttp2022.paf.assessment.eshop.services.OrderService;
import vttp2022.paf.assessment.eshop.services.WarehouseService;
import vttp2022.paf.assessment.eshop.services.exceptions.OrderException;


@RestController
@RequestMapping
public class OrderController {

	@Autowired
	private CustomerService custSvc;

	@Autowired
	private OrderService orderSvc;

	@Autowired
	private WarehouseService warehouseSvc;

	//TODO: Task 3
	@PostMapping(path = "/api/order", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String json) {
		System.out.println(json);

		/* PAYLOAD
		 {"name": "Daryl",
		 "lineItems": [
			{"item": "Apple","quantity": 1},
			{"item": "Banana","quantity": 2}
         	]
		}
		 */
		
		// convert requestbody String to JSONObject
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject orderJson =  reader.readObject();
		// check if customer exists in database
		String customerName = orderJson.getString("name");

		System.out.println(customerName);

		Optional<Customer> opt = custSvc.findCustomerByName(customerName);
		// if customer does not exist, return 404 error
		if (opt.isEmpty()) {
			JsonObject error = Json
			.createObjectBuilder()
			.add("error", "Customer %s not found".formatted(customerName))
			.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
		}

		// if exists, create new order

		// create customer
		Customer customer = opt.get();

        // create order
		Order order = new Order();
		order.setOrderId(UUID.randomUUID().toString().substring(0, 8));
		order.setName(customer.getName());
		order.setAddress(customer.getAddress());
		order.setEmail(customer.getEmail());
		
		// create line items
		JsonArray jsonArray = orderJson.getJsonArray("lineItems");

		// convert JsonValues in array to Jsonobjects
		List<LineItem> lineItems = new LinkedList<>(); 
		for (JsonValue jv : jsonArray) {
			JsonObject jo = (JsonObject) jv; // convert JsonValue to JsonObject
			LineItem li = LineItem.fromJson(jo); // convert JsonObject to LineItem
			lineItems.add(li); // add LineItem to list
		}
		order.setLineItems(lineItems);

        // save order and line items to db
    try {
		orderSvc.createOrder(order);
	  } catch (OrderException ex) {
		JsonObject errJson = Json
		  .createObjectBuilder()
		  .add("error", "Cannot create order %s".formatted(order.getOrderId()))
		  .build();
		return ResponseEntity
		  .status(HttpStatus.INTERNAL_SERVER_ERROR)
		  .body(errJson.toString());
	  }

	  // dispatch the order
	  OrderStatus orderStatus = warehouseSvc.dispatch(order);
	  
	  // save order status
	  if(!orderSvc.createOrderStatus(orderStatus)){
		JsonObject error = Json
		  .createObjectBuilder()
		  .add("error", "Cannot create order status".formatted(order.getOrderId()))
		  .build();

		return ResponseEntity
		  .status(HttpStatus.INTERNAL_SERVER_ERROR)
		  .body(error.toString());
	  };

		return null;
	}
	

}
