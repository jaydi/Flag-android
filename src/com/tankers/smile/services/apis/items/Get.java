package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.Item;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Get extends FlagRequest<Item> {
	private static final String REST_PATH = "one_item";

	protected Get(FlagClient client) {
		super(client, "GET", REST_PATH, null, Item.class);
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
	public Get setAlt(String alt) {
		return (Get) super.setAlt(alt);
	}

	@Override
	public Get setFields(String fields) {
		return (Get) super.setFields(fields);
	}

	@Override
	public Get setKey(String key) {
		return (Get) super.setKey(key);
	}

	@Override
	public Get setOauthToken(String oauthToken) {
		return (Get) super.setOauthToken(oauthToken);
	}

	@Override
	public Get setPrettyPrint(Boolean prettyPrint) {
		return (Get) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Get setQuotaUser(String quotaUser) {
		return (Get) super.setQuotaUser(quotaUser);
	}

	@Override
	public Get setUserIp(String userIp) {
		return (Get) super.setUserIp(userIp);
	}

	@Key
	private Long userId;

	@Key
	private Long itemId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
