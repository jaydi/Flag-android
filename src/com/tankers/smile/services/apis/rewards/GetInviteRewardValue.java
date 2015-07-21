package com.tankers.smile.services.apis.rewards;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.tankers.smile.models.InviteRewardValue;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class GetInviteRewardValue extends FlagRequest<InviteRewardValue> {
	private static final String REST_PATH = "reward_invite_value";

	protected GetInviteRewardValue(FlagClient client) {
		super(client, "GET", REST_PATH, null, InviteRewardValue.class);
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
	public GetInviteRewardValue setAlt(String alt) {
		return (GetInviteRewardValue) super.setAlt(alt);
	}

	@Override
	public GetInviteRewardValue setFields(String fields) {
		return (GetInviteRewardValue) super.setFields(fields);
	}

	@Override
	public GetInviteRewardValue setKey(String key) {
		return (GetInviteRewardValue) super.setKey(key);
	}

	@Override
	public GetInviteRewardValue setOauthToken(String oauthToken) {
		return (GetInviteRewardValue) super.setOauthToken(oauthToken);
	}

	@Override
	public GetInviteRewardValue setPrettyPrint(Boolean prettyPrint) {
		return (GetInviteRewardValue) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public GetInviteRewardValue setQuotaUser(String quotaUser) {
		return (GetInviteRewardValue) super.setQuotaUser(quotaUser);
	}

	@Override
	public GetInviteRewardValue setUserIp(String userIp) {
		return (GetInviteRewardValue) super.setUserIp(userIp);
	}

}
