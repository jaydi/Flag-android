package com.tankers.smile.services.apis.users;

import com.tankers.smile.models.User;
import com.tankers.smile.models.UserForm;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Insert extends FlagRequest<User> {
	private static final String REST_PATH = "new_user";

	protected Insert(FlagClient client, UserForm userForm) {
		super(client, "POST", REST_PATH, userForm, User.class);
	}

	@Override
	public Insert setAlt(String alt) {
		return (Insert) super.setAlt(alt);
	}

	@Override
	public Insert setFields(String fields) {
		return (Insert) super.setFields(fields);
	}

	@Override
	public Insert setKey(String key) {
		return (Insert) super.setKey(key);
	}

	@Override
	public Insert setOauthToken(String oauthToken) {
		return (Insert) super.setOauthToken(oauthToken);
	}

	@Override
	public Insert setPrettyPrint(Boolean prettyPrint) {
		return (Insert) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Insert setQuotaUser(String quotaUser) {
		return (Insert) super.setQuotaUser(quotaUser);
	}

	@Override
	public Insert setUserIp(String userIp) {
		return (Insert) super.setUserIp(userIp);
	}
}
