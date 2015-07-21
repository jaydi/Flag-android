package com.tankers.smile.services.apis.userinfos;

import java.io.IOException;

import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;

public class UserInfos {
	private FlagClient client;

	public UserInfos(FlagClient client) {
		super();
		this.client = client;
	}

	public Insert insert(UserInfo userInfo) throws IOException {
		Insert insert = new Insert(client, userInfo);
		client.initialize(insert);
		return insert;
	}

	public Get get(long userId) throws IOException {
		Get get = new Get(client);
		get.setUserId(userId);
		client.initialize(get);
		return get;
	}

	public Update update(UserInfo userInfo) throws IOException {
		Update update = new Update(client, userInfo);
		client.initialize(update);
		return update;
	}

	public PhoneTest phoneTest(UserInfo userInfo) throws IOException {
		PhoneTest phoneTest = new PhoneTest(client, userInfo);
		client.initialize(phoneTest);
		return phoneTest;
	}

	public PhoneInsert phoneInsert(UserInfo userInfo) throws IOException {
		PhoneInsert phoneInsert = new PhoneInsert(client, userInfo);
		client.initialize(phoneInsert);
		return phoneInsert;
	}
}
