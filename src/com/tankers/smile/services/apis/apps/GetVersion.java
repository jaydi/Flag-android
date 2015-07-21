package com.tankers.smile.services.apis.apps;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.tankers.smile.models.Version;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class GetVersion extends FlagRequest<Version> {
	private static final String REST_PATH = "version";

	protected GetVersion(FlagClient client) {
		super(client, "GET", REST_PATH, null, Version.class);
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
	public GetVersion setAlt(String alt) {
		return (GetVersion) super.setAlt(alt);
	}

	@Override
	public GetVersion setFields(String fields) {
		return (GetVersion) super.setFields(fields);
	}

	@Override
	public GetVersion setKey(String key) {
		return (GetVersion) super.setKey(key);
	}

	@Override
	public GetVersion setOauthToken(String oauthToken) {
		return (GetVersion) super.setOauthToken(oauthToken);
	}

	@Override
	public GetVersion setPrettyPrint(Boolean prettyPrint) {
		return (GetVersion) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public GetVersion setQuotaUser(String quotaUser) {
		return (GetVersion) super.setQuotaUser(quotaUser);
	}

	@Override
	public GetVersion setUserIp(String userIp) {
		return (GetVersion) super.setUserIp(userIp);
	}
}
