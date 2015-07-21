package com.tankers.smile.services.apis.logs;

import java.io.IOException;

import com.tankers.smile.models.ItemViewPair;
import com.tankers.smile.models.Log;
import com.tankers.smile.services.apis.FlagClient;

public class Logs {
	private FlagClient client;

	public Logs(FlagClient client) {
		super();
		this.client = client;
	}

	public Insert insert(Log log) throws IOException {
		Insert insert = new Insert(client, log);
		client.initialize(insert);
		return insert;
	}

	public InsertItemViewPair insertItemViewPair(ItemViewPair pair) throws IOException {
		InsertItemViewPair insertItemViewPair = new InsertItemViewPair(client, pair);
		client.initialize(insertItemViewPair);
		return insertItemViewPair;
	}
}
