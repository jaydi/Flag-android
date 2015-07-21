package com.tankers.smile.models;

import java.util.List;

import com.google.api.client.util.Key;

public class ItemCollection extends BaseModel {
	@Key
	private List<Item> items;

	public ItemCollection() {
		super();
	}

	public ItemCollection(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
