package com.tankers.smile.services.apis.users;

import java.io.IOException;

import com.tankers.smile.models.RetainForm;
import com.tankers.smile.models.UserForm;
import com.tankers.smile.services.apis.FlagClient;

public class Users {
	private FlagClient client;

	public Users(FlagClient client) {
		super();
		this.client = client;
	}

	public Guest guest() throws IOException {
		Guest guest = new Guest(client);
		client.initialize(guest);
		return guest;
	}

	public Insert insert(UserForm userForm) throws IOException {
		Insert insert = new Insert(client, userForm);
		client.initialize(insert);
		return insert;
	}

	public Get get(UserForm userForm) throws IOException {
		Get get = new Get(client, userForm);
		client.initialize(get);
		return get;
	}

	public Retain retain(RetainForm retainForm) throws IOException {
		Retain retain = new Retain(client, retainForm);
		client.initialize(retain);
		return retain;
	}

	public Delete delete(long userId) throws IOException {
		Delete delete = new Delete(client);
		delete.setUserId(userId);
		client.initialize(delete);
		return delete;
	}
}
