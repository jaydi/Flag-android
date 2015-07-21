package com.tankers.smile.services.apis.apps;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.tankers.smile.models.PP;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class GetPP extends FlagRequest<PP> {
	private static final String REST_PATH = "pp";

	protected GetPP(FlagClient client) {
		super(client, "GET", REST_PATH, null, PP.class);
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
	public GetPP setAlt(String alt) {
		return (GetPP) super.setAlt(alt);
	}

	@Override
	public GetPP setFields(String fields) {
		return (GetPP) super.setFields(fields);
	}

	@Override
	public GetPP setKey(String key) {
		return (GetPP) super.setKey(key);
	}

	@Override
	public GetPP setOauthToken(String oauthToken) {
		return (GetPP) super.setOauthToken(oauthToken);
	}

	@Override
	public GetPP setPrettyPrint(Boolean prettyPrint) {
		return (GetPP) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public GetPP setQuotaUser(String quotaUser) {
		return (GetPP) super.setQuotaUser(quotaUser);
	}

	@Override
	public GetPP setUserIp(String userIp) {
		return (GetPP) super.setUserIp(userIp);
	}
}
