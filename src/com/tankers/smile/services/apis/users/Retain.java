package com.tankers.smile.services.apis.users;

import com.tankers.smile.models.RetainForm;
import com.tankers.smile.models.User;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Retain extends FlagRequest<User> {
	private static final String REST_PATH = "retain_user";

	protected Retain(FlagClient client, RetainForm retainForm) {
		super(client, "POST", REST_PATH, retainForm, User.class);
	}

	@Override
	public Retain setAlt(String alt) {
		return (Retain) super.setAlt(alt);
	}

	@Override
	public Retain setFields(String fields) {
		return (Retain) super.setFields(fields);
	}

	@Override
	public Retain setKey(String key) {
		return (Retain) super.setKey(key);
	}

	@Override
	public Retain setOauthToken(String oauthToken) {
		return (Retain) super.setOauthToken(oauthToken);
	}

	@Override
	public Retain setPrettyPrint(Boolean prettyPrint) {
		return (Retain) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Retain setQuotaUser(String quotaUser) {
		return (Retain) super.setQuotaUser(quotaUser);
	}

	@Override
	public Retain setUserIp(String userIp) {
		return (Retain) super.setUserIp(userIp);
	}
}
