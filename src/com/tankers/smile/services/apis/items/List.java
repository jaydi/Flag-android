package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class List extends FlagRequest<ItemCollection> {
	private static final String REST_PATH = "item";

	protected List(FlagClient client) {
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
	public List setAlt(String alt) {
		return (List) super.setAlt(alt);
	}

	@Override
	public List setFields(String fields) {
		return (List) super.setFields(fields);
	}

	@Override
	public List setKey(String key) {
		return (List) super.setKey(key);
	}

	@Override
	public List setOauthToken(String oauthToken) {
		return (List) super.setOauthToken(oauthToken);
	}

	@Override
	public List setPrettyPrint(Boolean prettyPrint) {
		return (List) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public List setQuotaUser(String quotaUser) {
		return (List) super.setQuotaUser(quotaUser);
	}

	@Override
	public List setUserIp(String userIp) {
		return (List) super.setUserIp(userIp);
	}
	
	@Key
	private Long userId;

	@Key
	private Long shopId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
