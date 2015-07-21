package com.tankers.smile.services.apis.logs;

import com.tankers.smile.models.ItemViewPair;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class InsertItemViewPair extends FlagRequest<ItemViewPair> {
	private static final String REST_PATH = "log_ivpair";

	protected InsertItemViewPair(FlagClient client, ItemViewPair pair) {
		super(client, "POST", REST_PATH, pair, ItemViewPair.class);
	}

	@Override
	public InsertItemViewPair setAlt(String alt) {
		return (InsertItemViewPair) super.setAlt(alt);
	}

	@Override
	public InsertItemViewPair setFields(String fields) {
		return (InsertItemViewPair) super.setFields(fields);
	}

	@Override
	public InsertItemViewPair setKey(String key) {
		return (InsertItemViewPair) super.setKey(key);
	}

	@Override
	public InsertItemViewPair setOauthToken(String oauthToken) {
		return (InsertItemViewPair) super.setOauthToken(oauthToken);
	}

	@Override
	public InsertItemViewPair setPrettyPrint(Boolean prettyPrint) {
		return (InsertItemViewPair) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public InsertItemViewPair setQuotaUser(String quotaUser) {
		return (InsertItemViewPair) super.setQuotaUser(quotaUser);
	}

	@Override
	public InsertItemViewPair setUserIp(String userIp) {
		return (InsertItemViewPair) super.setUserIp(userIp);
	}
}
