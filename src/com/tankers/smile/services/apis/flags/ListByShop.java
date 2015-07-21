package com.tankers.smile.services.apis.flags;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByShop extends FlagRequest<FlagCollection> {
	private static final String REST_PATH = "flag_list_byshop";

	protected ListByShop(FlagClient client) {
		super(client, "GET", REST_PATH, null, FlagCollection.class);
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
	public ListByShop setAlt(String alt) {
		return (ListByShop) super.setAlt(alt);
	}

	@Override
	public ListByShop setFields(String fields) {
		return (ListByShop) super.setFields(fields);
	}

	@Override
	public ListByShop setKey(String key) {
		return (ListByShop) super.setKey(key);
	}

	@Override
	public ListByShop setOauthToken(String oauthToken) {
		return (ListByShop) super.setOauthToken(oauthToken);
	}

	@Override
	public ListByShop setPrettyPrint(Boolean prettyPrint) {
		return (ListByShop) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListByShop setQuotaUser(String quotaUser) {
		return (ListByShop) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListByShop setUserIp(String userIp) {
		return (ListByShop) super.setUserIp(userIp);
	}

	@Key
	private long shopId;

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

}
