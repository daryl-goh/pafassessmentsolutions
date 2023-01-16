package vttp2022.paf.assessment.eshop.services;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
@Service
public class WarehouseService {

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus  dispatch(Order order) {

		// TODO: Task 4
		
		// post order to paf.chuklee.com/dispatch/<order_id>
		final String WAREHOUSE_URL = "http://paf.chuklee.com/dispatch";
		System.out.println(WAREHOUSE_URL);
		// build the URL
		String url = UriComponentsBuilder
		.fromUriString(WAREHOUSE_URL)
		.path("/")
		.path(order.getOrderId())
		.toUriString();

		System.out.println("URL >>> " + url);

        // build the requestbody (JsonObject) from Order
		JsonObject requestBody = order.toJson("daryl");

		System.out.println("requestBody >>> " + requestBody);

		// build request entity
		RequestEntity<String> req = RequestEntity
		.post(url)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(requestBody.toString());

		// create orderStatus
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrderId(order.getOrderId());

		// send request, if successful, set deliveryId and status to dispatched
		RestTemplate template = new RestTemplate();
		try {
		// get response from dispatch server (33% chance of returning 500 error)
		ResponseEntity<String> resp = template.exchange(req, String.class);
		JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
		JsonObject json = reader.readObject();

		// if successful, set order status to dispatched and set deliveryId
		orderStatus.setDeliveryId(json.getString("deliveryId"));
		orderStatus.setStatus("dispatched");
		} catch (Exception ex) {
		ex.printStackTrace();
		return orderStatus;
		}
		// if fail, set order status to pending without deliveryId
		return orderStatus;
 
		}
}
