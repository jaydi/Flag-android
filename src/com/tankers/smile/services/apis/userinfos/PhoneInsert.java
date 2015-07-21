package com.tankers.smile.services.apis.userinfos;

import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class PhoneInsert extends FlagRequest<UserInfo> {
	private static final String REST_PATH = "user_info_phone_insert";

	protected PhoneInsert(FlagClient client, UserInfo userInfo) {
		super(client, "POST", REST_PATH, userInfo, UserInfo.class);
	}

	@Override
	public PhoneInsert setAlt(String alt) {
		return (PhoneInsert) super.setAlt(alt);
	}

	@Override
	public PhoneInsert setFields(String fields) {
		return (PhoneInsert) super.setFields(fields);
	}

	@Override
	public PhoneInsert setKey(String key) {
		return (PhoneInsert) super.setKey(key);
	}

	@Override
	public PhoneInsert setOauthToken(String oauthToken) {
		return (PhoneInsert) super.setOauthToken(oauthToken);
	}

	@Override
	public PhoneInsert setPrettyPrint(Boolean prettyPrint) {
		return (PhoneInsert) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public PhoneInsert setQuotaUser(String quotaUser) {
		return (PhoneInsert) super.setQuotaUser(quotaUser);
	}

	@Override
	public PhoneInsert setUserIp(String userIp) {
		return (PhoneInsert) super.setUserIp(userIp);
	}
}
