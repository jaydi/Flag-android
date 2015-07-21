package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByUser extends FlagRequest<ItemCollection> {
	private static final String REST_PATH = "item_user";

	protected ListByUser(FlagClient client) {
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
	public ListByUser setAlt(String alt) {
		return (ListByUser) super.setAlt(alt);
	}

	@Override
	public ListByUser setFields(String fields) {
		return (ListByUser) super.setFields(fields);
	}

	@Override
	public ListByUser setKey(String key) {
		return (ListByUser) super.setKey(key);
	}

	@Override
	public ListByUser setOauthToken(String oauthToken) {
		return (ListByUser) super.setOauthToken(oauthToken);
	}

	@Override
	public ListByUser setPrettyPrint(Boolean prettyPrint) {
		return (ListByUser) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListByUser setQuotaUser(String quotaUser) {
		return (ListByUser) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListByUser setUserIp(String userIp) {
		return (ListByUser) super.setUserIp(userIp);
	}
	
	@Key
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
