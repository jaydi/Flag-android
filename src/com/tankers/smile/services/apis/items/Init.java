package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Init extends FlagRequest<ItemCollection> {
	private static final String REST_PATH = "item_init";

	protected Init(FlagClient client) {
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
	public Init setAlt(String alt) {
		return (Init) super.setAlt(alt);
	}

	@Override
	public Init setFields(String fields) {
		return (Init) super.setFields(fields);
	}

	@Override
	public Init setKey(String key) {
		return (Init) super.setKey(key);
	}

	@Override
	public Init setOauthToken(String oauthToken) {
		return (Init) super.setOauthToken(oauthToken);
	}

	@Override
	public Init setPrettyPrint(Boolean prettyPrint) {
		return (Init) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Init setQuotaUser(String quotaUser) {
		return (Init) super.setQuotaUser(quotaUser);
	}

	@Override
	public Init setUserIp(String userIp) {
		return (Init) super.setUserIp(userIp);
	}

	@Key
	private Long userId;

	@Key
	private int mark;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}
