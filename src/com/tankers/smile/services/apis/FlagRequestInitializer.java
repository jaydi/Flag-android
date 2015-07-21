package com.tankers.smile.services.apis;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;

public class FlagRequestInitializer extends CommonGoogleJsonClientRequestInitializer {

	public FlagRequestInitializer() {
		super();
	}

	/**
	 * @param key
	 *            API key or {@code null} to leave it unchanged
	 */
	public FlagRequestInitializer(String key) {
		super(key);
	}

	/**
	 * @param key
	 *            API key or {@code null} to leave it unchanged
	 * @param userIp
	 *            user IP or {@code null} to leave it unchanged
	 */
	public FlagRequestInitializer(String key, String userIp) {
		super(key, userIp);
	}

	@Override
	public final void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> request) throws java.io.IOException {
		super.initializeJsonRequest(request);
		initializeFlagRequest((FlagRequest<?>) request);
	}

	/**
	 * Initializes Flag request.
	 * 
	 * <p>
	 * Default implementation does nothing. Called from {@link #initializeJsonRequest(AbstractGoogleJsonClientRequest)}.
	 * </p>
	 * 
	 * @throws java.io.IOException
	 *             I/O exception
	 */
	protected void initializeFlagRequest(FlagRequest<?> request) throws java.io.IOException {
	}
}
