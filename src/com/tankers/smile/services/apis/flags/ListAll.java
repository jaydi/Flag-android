package com.tankers.smile.services.apis.flags;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListAll extends FlagRequest<FlagCollection> {
	private static final String REST_PATH = "flag_list_all";

	protected ListAll(FlagClient client) {
		super(client, "GET", REST_PATH, null, FlagCollection.class);
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
	public ListAll setAlt(String alt) {
		return (ListAll) super.setAlt(alt);
	}

	@Override
	public ListAll setFields(String fields) {
		return (ListAll) super.setFields(fields);
	}

	@Override
	public ListAll setKey(String key) {
		return (ListAll) super.setKey(key);
	}

	@Override
	public ListAll setOauthToken(String oauthToken) {
		return (ListAll) super.setOauthToken(oauthToken);
	}

	@Override
	public ListAll setPrettyPrint(Boolean prettyPrint) {
		return (ListAll) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListAll setQuotaUser(String quotaUser) {
		return (ListAll) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListAll setUserIp(String userIp) {
		return (ListAll) super.setUserIp(userIp);
	}

	@Key
	private long tag;

	public long getTag() {
		return tag;
	}

	public void setTag(long tag) {
		this.tag = tag;
	}
}
