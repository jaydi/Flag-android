package com.tankers.smile.services.apis.apps;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.tankers.smile.models.TOU;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class GetTOU extends FlagRequest<TOU> {
	private static final String REST_PATH = "tou";

	protected GetTOU(FlagClient client) {
		super(client, "GET", REST_PATH, null, TOU.class);
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
	public GetTOU setAlt(String alt) {
		return (GetTOU) super.setAlt(alt);
	}

	@Override
	public GetTOU setFields(String fields) {
		return (GetTOU) super.setFields(fields);
	}

	@Override
	public GetTOU setKey(String key) {
		return (GetTOU) super.setKey(key);
	}

	@Override
	public GetTOU setOauthToken(String oauthToken) {
		return (GetTOU) super.setOauthToken(oauthToken);
	}

	@Override
	public GetTOU setPrettyPrint(Boolean prettyPrint) {
		return (GetTOU) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public GetTOU setQuotaUser(String quotaUser) {
		return (GetTOU) super.setQuotaUser(quotaUser);
	}

	@Override
	public GetTOU setUserIp(String userIp) {
		return (GetTOU) super.setUserIp(userIp);
	}
}
