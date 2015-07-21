package com.tankers.smile.services.apis.redeems;

import java.io.IOException;

import com.tankers.smile.services.apis.FlagClient;

public class Redeems {
	private FlagClient client;

	public Redeems(FlagClient client) {
		super();
		this.client = client;
	}
	
	public List list(int mark) throws IOException {
		List list = new List(client);
		list.setMark(mark);
		client.initialize(list);
		return list;
	}
}
