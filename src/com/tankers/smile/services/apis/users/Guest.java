package com.tankers.smile.services.apis.users;

import com.tankers.smile.models.User;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Guest extends FlagRequest<User> {
	private static final String REST_PATH = "guest_user";

	protected Guest(FlagClient client) {
		super(client, "POST", REST_PATH, null, User.class);
	}

	@Override
	public Guest setAlt(String alt) {
		return (Guest) super.setAlt(alt);
	}

	@Override
	public Guest setFields(String fields) {
		return (Guest) super.setFields(fields);
	}

	@Override
	public Guest setKey(String key) {
		return (Guest) super.setKey(key);
	}

	@Override
	public Guest setOauthToken(String oauthToken) {
		return (Guest) super.setOauthToken(oauthToken);
	}

	@Override
	public Guest setPrettyPrint(Boolean prettyPrint) {
		return (Guest) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Guest setQuotaUser(String quotaUser) {
		return (Guest) super.setQuotaUser(quotaUser);
	}

	@Override
	public Guest setUserIp(String userIp) {
		return (Guest) super.setUserIp(userIp);
	}
}
