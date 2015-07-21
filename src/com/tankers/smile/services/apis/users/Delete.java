package com.tankers.smile.services.apis.users;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Delete extends FlagRequest<Void> {
	private static final String REST_PATH = "user";

	protected Delete(FlagClient client) {
		super(client, "GET", REST_PATH, null, Void.class);
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
	public Delete setAlt(String alt) {
		return (Delete) super.setAlt(alt);
	}

	@Override
	public Delete setFields(String fields) {
		return (Delete) super.setFields(fields);
	}

	@Override
	public Delete setKey(String key) {
		return (Delete) super.setKey(key);
	}

	@Override
	public Delete setOauthToken(String oauthToken) {
		return (Delete) super.setOauthToken(oauthToken);
	}

	@Override
	public Delete setPrettyPrint(Boolean prettyPrint) {
		return (Delete) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Delete setQuotaUser(String quotaUser) {
		return (Delete) super.setQuotaUser(quotaUser);
	}

	@Override
	public Delete setUserIp(String userIp) {
		return (Delete) super.setUserIp(userIp);
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
