package com.tankers.smile.services.apis.shops;

import java.io.IOException;
import java.util.Collection;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.Shop;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Recommend extends FlagRequest<Shop> {
	private static final String REST_PATH = "shop_recommend_near";

	protected Recommend(FlagClient client) {
		super(client, "GET", REST_PATH, null, Shop.class);
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
	public Recommend setAlt(String alt) {
		return (Recommend) super.setAlt(alt);
	}

	@Override
	public Recommend setFields(String fields) {
		return (Recommend) super.setFields(fields);
	}

	@Override
	public Recommend setKey(String key) {
		return (Recommend) super.setKey(key);
	}

	@Override
	public Recommend setOauthToken(String oauthToken) {
		return (Recommend) super.setOauthToken(oauthToken);
	}

	@Override
	public Recommend setPrettyPrint(Boolean prettyPrint) {
		return (Recommend) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Recommend setQuotaUser(String quotaUser) {
		return (Recommend) super.setQuotaUser(quotaUser);
	}

	@Override
	public Recommend setUserIp(String userIp) {
		return (Recommend) super.setUserIp(userIp);
	}

	@Key
	private long userId;

	@Key
	private Collection<Long> ids;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Collection<Long> getIds() {
		return ids;
	}

	public void setIds(Collection<Long> ids) {
		this.ids = ids;
	}
}
