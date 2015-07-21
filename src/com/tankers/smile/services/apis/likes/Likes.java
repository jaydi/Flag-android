package com.tankers.smile.services.apis.likes;

import java.io.IOException;

import com.tankers.smile.models.Like;
import com.tankers.smile.services.apis.FlagClient;

public class Likes {
	private FlagClient client;
	
	public Likes(FlagClient client) {
		super();
		this.client = client;
	}
	
	public Insert insert(Like like) throws IOException {
		Insert insert = new Insert(client, like);
		client.initialize(insert);
		return insert;
	}
	
	public Delete delete(Long userId, Long itemId, int type) throws IOException {
		Delete delete = new Delete(client);
		delete.setUserId(userId);
		delete.setItemId(itemId);
		delete.setType(type);
		client.initialize(delete);
		return delete;
	}
}
