package com.tankers.smile.services.apis.users;

import com.tankers.smile.models.User;
import com.tankers.smile.models.UserForm;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Get extends FlagRequest<User> {
	private static final String REST_PATH = "old_user";

	protected Get(FlagClient client, UserForm userForm) {
		super(client, "POST", REST_PATH, userForm, User.class);
	}

	@Override
	public Get setAlt(String alt) {
		return (Get) super.setAlt(alt);
	}

	@Override
	public Get setFields(String fields) {
		return (Get) super.setFields(fields);
	}

	@Override
	public Get setKey(String key) {
		return (Get) super.setKey(key);
	}

	@Override
	public Get setOauthToken(String oauthToken) {
		return (Get) super.setOauthToken(oauthToken);
	}

	@Override
	public Get setPrettyPrint(Boolean prettyPrint) {
		return (Get) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Get setQuotaUser(String quotaUser) {
		return (Get) super.setQuotaUser(quotaUser);
	}

	@Override
	public Get setUserIp(String userIp) {
		return (Get) super.setUserIp(userIp);
	}
}
