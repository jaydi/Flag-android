package com.tankers.smile.services.apis.userinfos;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Get extends FlagRequest<UserInfo> {
	private static final String REST_PATH = "user_info";

	protected Get(FlagClient client) {
		super(client, "GET", REST_PATH, null, UserInfo.class);
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
