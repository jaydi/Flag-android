package com.tankers.smile.services.apis.rewards;

import com.tankers.smile.models.InvitationForm;
import com.tankers.smile.models.User;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Invited extends FlagRequest<User> {
	private static final String REST_PATH = "reward_invite";

	protected Invited(FlagClient client, InvitationForm invitationForm) {
		super(client, "POST", REST_PATH, invitationForm, User.class);
	}

	@Override
	public Invited setAlt(String alt) {
		return (Invited) super.setAlt(alt);
	}

	@Override
	public Invited setFields(String fields) {
		return (Invited) super.setFields(fields);
	}

	@Override
	public Invited setKey(String key) {
		return (Invited) super.setKey(key);
	}

	@Override
	public Invited setOauthToken(String oauthToken) {
		return (Invited) super.setOauthToken(oauthToken);
	}

	@Override
	public Invited setPrettyPrint(Boolean prettyPrint) {
		return (Invited) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Invited setQuotaUser(String quotaUser) {
		return (Invited) super.setQuotaUser(quotaUser);
	}

	@Override
	public Invited setUserIp(String userIp) {
		return (Invited) super.setUserIp(userIp);
	}
}
