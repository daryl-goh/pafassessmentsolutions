package vttp2022.paf.assessment.eshop.controllers;


import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.services.CustomerService;

@RestController
@RequestMapping
public class OrderController {

	@Autowired
	private CustomerService custSvc;

	//TODO: Task 3
	@PostMapping(path = "/api/order", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String json) {
		System.out.println(json);
		
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject orderJson =  reader.readObject();
		String customerName = orderJson.getString("name");
		// String jLineItems = orderJson.getString("lineItems");

		System.out.println(customerName);

		Optional<Customer> opt = custSvc.findCustomerByName(customerName);
		
		if (opt.isEmpty()) {
			JsonObject error = Json
			.createObjectBuilder()
			.add("error", "Customer %s not found".formatted(customerName))
			.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
		}
		
		

		return null;
	}


}
