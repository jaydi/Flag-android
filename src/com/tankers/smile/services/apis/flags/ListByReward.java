package com.tankers.smile.services.apis.flags;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class ListByReward extends FlagRequest<FlagCollection> {
	private static final String REST_PATH = "flag_list_byreward";

	protected ListByReward(FlagClient client) {
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
	private double lat;

	@Key
	private double lon;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

}
