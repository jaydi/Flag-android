package com.tankers.smile.services.apis.rewards;

import com.tankers.smile.models.CheckinForm;
import com.tankers.smile.models.UserShopSet;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Checkin extends FlagRequest<UserShopSet> {
	private static final String REST_PATH = "reward_checkin";

	protected Checkin(FlagClient client, CheckinForm checkinForm) {
		super(client, "POST", REST_PATH, checkinForm, UserShopSet.class);
	}

	@Override
	public Checkin setAlt(String alt) {
		return (Checkin) super.setAlt(alt);
	}

	@Override
	public Checkin setFields(String fields) {
		return (Checkin) super.setFields(fields);
	}

	@Override
	public Checkin setKey(String key) {
		return (Checkin) super.setKey(key);
	}

	@Override
	public Checkin setOauthToken(String oauthToken) {
		return (Checkin) super.setOauthToken(oauthToken);
	}

	@Override
	public Checkin setPrettyPrint(Boolean prettyPrint) {
		return (Checkin) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Checkin setQuotaUser(String quotaUser) {
		return (Checkin) super.setQuotaUser(quotaUser);
	}

	@Override
	public Checkin setUserIp(String userIp) {
		return (Checkin) super.setUserIp(userIp);
	}
}
