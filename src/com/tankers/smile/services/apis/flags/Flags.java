package com.tankers.smile.services.apis.flags;

import java.io.IOException;

import com.tankers.smile.services.apis.FlagClient;

public class Flags {
	private FlagClient client;

	public Flags(FlagClient client) {
		super();
		this.client = client;
	}

	public ListAll listAll(long tag) throws IOException {
		ListAll listAll = new ListAll(client);
		listAll.setTag(tag);
		client.initialize(listAll);
		return listAll;
	}

	public ListByShop listByShop(long shopId) throws IOException {
		ListByShop listByShop = new ListByShop(client);
		listByShop.setShopId(shopId);
		client.initialize(listByShop);
		return listByShop;
	}

	public ListByItem listByItem(long itemId) throws IOException {
		ListByItem listByItem = new ListByItem(client);
		listByItem.setItemId(itemId);
		client.initialize(listByItem);
		return listByItem;
	}

	public ListByReward listByReward(long userId, double lat, double lon) throws IOException {
		ListByReward listByReward = new ListByReward(client);
		listByReward.setUserId(userId);
		listByReward.setLat(lat);
		listByReward.setLon(lon);
		client.initialize(listByReward);
		return listByReward;
	}
}
