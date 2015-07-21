package com.tankers.smile.services.apis.apps;

import com.tankers.smile.models.FeedbackMessage;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Feedback extends FlagRequest<Void> {
	private static final String REST_PATH = "feedback";

	protected Feedback(FlagClient client, FeedbackMessage feedbackMessage) {
		super(client, "POST", REST_PATH, feedbackMessage, Void.class);
	}

	@Override
	public Feedback setAlt(String alt) {
		return (Feedback) super.setAlt(alt);
	}

	@Override
	public Feedback setFields(String fields) {
		return (Feedback) super.setFields(fields);
	}

	@Override
	public Feedback setKey(String key) {
		return (Feedback) super.setKey(key);
	}

	@Override
	public Feedback setOauthToken(String oauthToken) {
		return (Feedback) super.setOauthToken(oauthToken);
	}

	@Override
	public Feedback setPrettyPrint(Boolean prettyPrint) {
		return (Feedback) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Feedback setQuotaUser(String quotaUser) {
		return (Feedback) super.setQuotaUser(quotaUser);
	}

	@Override
	public Feedback setUserIp(String userIp) {
		return (Feedback) super.setUserIp(userIp);
	}
}
