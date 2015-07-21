package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.tankers.smile.services.apis.FlagClient;

public class Items {
	private FlagClient client;

	public Items(FlagClient client) {
		super();
		this.client = client;
	}

	public Get get(Long userId, Long itemId) throws IOException {
		Get get = new Get(client);
		get.setUserId(userId);
		get.setItemId(itemId);
		client.initialize(get);
		return get;
	}

	public Init init(Long userId, int mark) throws IOException {
		Init init = new Init(client);
		init.setUserId(userId);
		init.setMark(mark);
		client.initialize(init);
		return init;
	}

	public List list(Long userId, Long shopId) throws IOException {
		List list = new List(client);
		list.setUserId(userId);
		list.setShopId(shopId);
		client.initialize(list);
		return list;
	}

	public ListByUser listByUser(Long userId) throws IOException {
		ListByUser listByUser = new ListByUser(client);
		listByUser.setUserId(userId);
		client.initialize(listByUser);
		return listByUser;
	}

	public ListByReward listByReward(long userId, int mark) throws IOException {
		ListByReward listByReward = new ListByReward(client);
		listByReward.setUserId(userId);
		listByReward.setMark(mark);
		client.initialize(listByReward);
		return listByReward;
	}

	public ListByItem listByItem(long userId, long itemId) throws IOException {
		ListByItem listByItem = new ListByItem(client);
		listByItem.setUserId(userId);
		listByItem.setItemId(itemId);
		client.initialize(listByItem);
		return listByItem;
	}

}
