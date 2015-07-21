package com.tankers.smile.services.apis.userinfos;

import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Update extends FlagRequest<UserInfo> {
	private static final String REST_PATH = "user_info";

	protected Update(FlagClient client, UserInfo userInfo) {
		super(client, "PUT", REST_PATH, userInfo, UserInfo.class);
	}

	@Override
	public Update setAlt(String alt) {
		return (Update) super.setAlt(alt);
	}

	@Override
	public Update setFields(String fields) {
		return (Update) super.setFields(fields);
	}

	@Override
	public Update setKey(String key) {
		return (Update) super.setKey(key);
	}

	@Override
	public Update setOauthToken(String oauthToken) {
		return (Update) super.setOauthToken(oauthToken);
	}

	@Override
	public Update setPrettyPrint(Boolean prettyPrint) {
		return (Update) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Update setQuotaUser(String quotaUser) {
		return (Update) super.setQuotaUser(quotaUser);
	}

	@Override
	public Update setUserIp(String userIp) {
		return (Update) super.setUserIp(userIp);
	}
}
