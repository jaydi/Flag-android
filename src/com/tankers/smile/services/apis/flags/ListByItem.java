package com.tankers.smile.services.apis.flags;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByItem extends FlagRequest<FlagCollection> {
	private static final String REST_PATH = "flag_list_byitem";

	protected ListByItem(FlagClient client) {
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
	public ListByItem setAlt(String alt) {
		return (ListByItem) super.setAlt(alt);
	}

	@Override
	public ListByItem setFields(String fields) {
		return (ListByItem) super.setFields(fields);
	}

	@Override
	public ListByItem setKey(String key) {
		return (ListByItem) super.setKey(key);
	}

	@Override
	public ListByItem setOauthToken(String oauthToken) {
		return (ListByItem) super.setOauthToken(oauthToken);
	}

	@Override
	public ListByItem setPrettyPrint(Boolean prettyPrint) {
		return (ListByItem) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public ListByItem setQuotaUser(String quotaUser) {
		return (ListByItem) super.setQuotaUser(quotaUser);
	}

	@Override
	public ListByItem setUserIp(String userIp) {
		return (ListByItem) super.setUserIp(userIp);
	}

	@Key
	private long itemId;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
}
