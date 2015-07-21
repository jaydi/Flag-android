package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByItem extends FlagRequest<ItemCollection> {
	private static final String REST_PATH = "item_item";

	protected ListByItem(FlagClient client) {
		super(client, "GET", REST_PATH, null, ItemCollection.class);
	}

	@Override
	public HttpResponse executeUsingHead() throws IOException {
		return super.executeUsingHead();
	}

	@Override
	public HttpRequest buildHttpRequestUsingHead() throws IOException {
		return super.buildHttpRequestUsingHead();
	}

	@Override
	public ListByItem setAlt(String alt) {
		return (ListByItem) super.setAlt(alt);
	}

	@Override
	public ListByItem setFields(String fields) {
		return (ListByItem) super.setFields(fields);
	}

	@Override
	public ListByItem setKey(String key) {
		return (ListByItem) super.setKey(key);
	}

	@Override
	public ListByItem setOauthToken(String oauthToken) {
		return (ListByItem) super.setOauthToken(oauthToken);
	}

	@Override
	public ListByItem setPrettyPrint(Boolean prettyPrint) {
		return (ListByItem) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListByItem setQuotaUser(String quotaUser) {
		return (ListByItem) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListByItem setUserIp(String userIp) {
		return (ListByItem) super.setUserIp(userIp);
	}

	@Key
	private long userId;

	@Key
	private long itemId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

}
