package com.tankers.smile.services.apis.shops;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ShopCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByReward extends FlagRequest<ShopCollection> {
	private static final String REST_PATH = "shop_list_reward";

	protected ListByReward(FlagClient client) {
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
	public ListByReward setAlt(String alt) {
		return (ListByReward) super.setAlt(alt);
	}

	@Override
	public ListByReward setFields(String fields) {
		return (ListByReward) super.setFields(fields);
	}

	@Override
	public ListByReward setKey(String key) {
		return (ListByReward) super.setKey(key);
	}

	@Override
	public ListByReward setOauthToken(String oauthToken) {
		return (ListByReward) super.setOauthToken(oauthToken);
	}

	@Override
	public ListByReward setPrettyPrint(Boolean prettyPrint) {
		return (ListByReward) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListByReward setQuotaUser(String quotaUser) {
		return (ListByReward) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListByReward setUserIp(String userIp) {
		return (ListByReward) super.setUserIp(userIp);
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
