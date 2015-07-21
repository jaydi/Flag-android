package com.tankers.smile.services.apis.apps;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.tankers.smile.models.Notice;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class GetNotice extends FlagRequest<Notice> {
	private static final String REST_PATH = "notice";

	protected GetNotice(FlagClient client) {
		super(client, "GET", REST_PATH, null, Notice.class);
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
	public GetNotice setAlt(String alt) {
		return (GetNotice) super.setAlt(alt);
	}

	@Override
	public GetNotice setFields(String fields) {
		return (GetNotice) super.setFields(fields);
	}

	@Override
	public GetNotice setKey(String key) {
		return (GetNotice) super.setKey(key);
	}

	@Override
	public GetNotice setOauthToken(String oauthToken) {
		return (GetNotice) super.setOauthToken(oauthToken);
	}

	@Override
	public GetNotice setPrettyPrint(Boolean prettyPrint) {
		return (GetNotice) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public GetNotice setQuotaUser(String quotaUser) {
		return (GetNotice) super.setQuotaUser(quotaUser);
	}

	@Override
	public GetNotice setUserIp(String userIp) {
		return (GetNotice) super.setUserIp(userIp);
	}
}
