package com.tankers.smile.services.apis.likes;

import com.tankers.smile.models.Like;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Insert extends FlagRequest<Like> {
	private static final String REST_PATH = "like";

	protected Insert(FlagClient client, Like like) {
		super(client, "POST", REST_PATH, like, Like.class);
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
