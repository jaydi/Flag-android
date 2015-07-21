package com.tankers.smile.services.apis.rewards;

import com.tankers.smile.models.Redemption;
import com.tankers.smile.models.User;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Redeem extends FlagRequest<User> {
	private static final String REST_PATH = "reward_redeem";

	protected Redeem(FlagClient client, Redemption redemption) {
		super(client, "POST", REST_PATH, redemption, User.class);
	}

	@Override
	public Redeem setAlt(String alt) {
		return (Redeem) super.setAlt(alt);
	}

	@Override
	public Redeem setFields(String fields) {
		return (Redeem) super.setFields(fields);
	}

	@Override
	public Redeem setKey(String key) {
		return (Redeem) super.setKey(key);
	}

	@Override
	public Redeem setOauthToken(String oauthToken) {
		return (Redeem) super.setOauthToken(oauthToken);
	}

	@Override
	public Redeem setPrettyPrint(Boolean prettyPrint) {
		return (Redeem) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Redeem setQuotaUser(String quotaUser) {
		return (Redeem) super.setQuotaUser(quotaUser);
	}

	@Override
	public Redeem setUserIp(String userIp) {
		return (Redeem) super.setUserIp(userIp);
	}
}
