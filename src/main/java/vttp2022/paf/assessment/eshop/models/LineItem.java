package vttp2022.paf.assessment.eshop.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT CHANGE THIS CLASS
public class LineItem {

	private String item;
	private Integer quantity;

	public String getItem() { return this.item; }
	public void setItem(String item) { this.item = item; }

	public Integer getQuantity() { return this.quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }

	public static LineItem fromJson(JsonObject jo) {
        LineItem l = new LineItem();
        l.setItem(jo.getString("item"));
        l.setQuantity(jo.getInt("quantity"));
        return l;
    }

	// Created

	public JsonObject toJson() {
		JsonObject json = Json
		.createObjectBuilder()
		.add("item", item)
		.add("quantity", quantity)
        .build();
		return json;
	}


}
