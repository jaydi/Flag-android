package com.tankers.smile.services.apis.shops;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ShopCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Start extends FlagRequest<ShopCollection> {
	private static final String REST_PATH = "shop_start";

	protected Start(FlagClient client) {
		super(client, "GET", REST_PATH, null, ShopCollection.class);
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
	public Start setAlt(String alt) {
		return (Start) super.setAlt(alt);
	}

	@Override
	public Start setFields(String fields) {
		return (Start) super.setFields(fields);
	}

	@Override
	public Start setKey(String key) {
		return (Start) super.setKey(key);
	}

	@Override
	public Start setOauthToken(String oauthToken) {
		return (Start) super.setOauthToken(oauthToken);
	}

	@Override
	public Start setPrettyPrint(Boolean prettyPrint) {
		return (Start) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Start setQuotaUser(String quotaUser) {
		return (Start) super.setQuotaUser(quotaUser);
	}

	@Override
	public Start setUserIp(String userIp) {
		return (Start) super.setUserIp(userIp);
	}

	@Key
	private long userId;

	@Key
	private int mark;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}
