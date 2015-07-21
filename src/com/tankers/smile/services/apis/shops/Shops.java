package com.tankers.smile.services.apis.shops;

import java.io.IOException;
import java.util.Collection;

import com.tankers.smile.services.apis.FlagClient;

public class Shops {
	private FlagClient client;

	public Shops(FlagClient client) {
		super();
		this.client = client;
	}

	public Start start(long userId, int mark) throws IOException {
		Start start = new Start(client);
		start.setUserId(userId);
		start.setMark(mark);
		client.initialize(start);
		return start;
	}

	public Get get(Long userId, Long id) throws IOException {
		Get get = new Get(client);
		get.setUserId(userId);
		get.setId(id);
		client.initialize(get);
		return get;
	}

	public List list(long userId, Collection<Long> ids) throws IOException {
		List list = new List(client);
		list.setUserId(userId);
		list.setIds(ids);
		client.initialize(list);
		return list;
	}

	public ListByReward listByReward(long userId, int mark) throws IOException {
		ListByReward listByReward = new ListByReward(client);
		listByReward.setUserId(userId);
		listByReward.setMark(mark);
		client.initialize(listByReward);
		return listByReward;
	}

	public Recommend recommend(long userId, Collection<Long> shopIds) throws IOException {
		Recommend recommend = new Recommend(client);
		recommend.setUserId(userId);
		recommend.setIds(shopIds);
		client.initialize(recommend);
		return recommend;
	}
}
