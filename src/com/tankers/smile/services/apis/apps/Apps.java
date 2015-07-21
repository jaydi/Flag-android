package com.tankers.smile.services.apis.apps;

import java.io.IOException;

import com.tankers.smile.models.FeedbackMessage;
import com.tankers.smile.services.apis.FlagClient;

public class Apps {
	private FlagClient client;

	public Apps(FlagClient client) {
		super();
		this.client = client;
	}

	public Feedback feedback(FeedbackMessage feedbackMessage) throws IOException {
		Feedback feedback = new Feedback(client, feedbackMessage);
		client.initialize(feedback);
		return feedback;
	}

	public GetVersion getVersion() throws IOException {
		GetVersion getVersion = new GetVersion(client);
		client.initialize(getVersion);
		return getVersion;
	}

	public GetNotice getNotice() throws IOException {
		GetNotice getNotice = new GetNotice(client);
		client.initialize(getNotice);
		return getNotice;
	}

	public GetTOU getTOU() throws IOException {
		GetTOU getTOU = new GetTOU(client);
		client.initialize(getTOU);
		return getTOU;
	}

	public GetPP getPP() throws IOException {
		GetPP getPP = new GetPP(client);
		client.initialize(getPP);
		return getPP;
	}
}
