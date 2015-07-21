package com.tankers.smile.services.apis.userinfos;

import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class PhoneTest extends FlagRequest<UserInfo> {
	private static final String REST_PATH = "user_info_phone_test";

	protected PhoneTest(FlagClient client, UserInfo userInfo) {
		super(client, "POST", REST_PATH, userInfo, UserInfo.class);
	}

	@Override
	public PhoneTest setAlt(String alt) {
		return (PhoneTest) super.setAlt(alt);
	}

	@Override
	public PhoneTest setFields(String fields) {
		return (PhoneTest) super.setFields(fields);
	}

	@Override
	public PhoneTest setKey(String key) {
		return (PhoneTest) super.setKey(key);
	}

	@Override
	public PhoneTest setOauthToken(String oauthToken) {
		return (PhoneTest) super.setOauthToken(oauthToken);
	}

	@Override
	public PhoneTest setPrettyPrint(Boolean prettyPrint) {
		return (PhoneTest) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public PhoneTest setQuotaUser(String quotaUser) {
		return (PhoneTest) super.setQuotaUser(quotaUser);
	}

	@Override
	public PhoneTest setUserIp(String userIp) {
		return (PhoneTest) super.setUserIp(userIp);
	}
}
