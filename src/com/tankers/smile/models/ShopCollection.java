package com.tankers.smile.models;

import java.util.List;
import java.util.Map;

import com.google.api.client.util.Key;

public class ShopCollection extends BaseModel {
	@Key
	private List<Shop> shops;

	@Key
	private List<Flag> flags;

	@Key
	private Map<String, Integer> pointMap;

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public Map<String, Integer> getPointMap() {
		return pointMap;
	}

	public void setPointMap(Map<String, Integer> pointMap) {
		this.pointMap = pointMap;
	}

}
