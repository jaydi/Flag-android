package com.tankers.smile.services.apis;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.UriTemplate;

public abstract class FlagRequest<T> extends AbstractGoogleJsonClientRequest<T> {

	/**
	 * @param client
	 *            Google client
	 * @param method
	 *            HTTP Method
	 * @param uriTemplate
	 *            URI template for the path relative to the base URL. If it starts with a "/"
	 *            the base path from the base URL will be stripped out. The URI template can also be a
	 *            full URL. URI template expansion is done using {@link UriTemplate#expand(String, String, Object, boolean)}
	 * @param content
	 *            A POJO that can be serialized into JSON or {@code null} for none
	 * @param responseClass
	 *            response class to parse into
	 */
	protected FlagRequest(FlagClient flagClient, String requestMethod, String uriTemplate, Object jsonContent, Class<T> responseClass) {
		super(flagClient, requestMethod, uriTemplate, jsonContent, responseClass);
	}

	/** Data format for the response. */
	@com.google.api.client.util.Key
	private String alt;

	/**
	 * Data format for the response. [default: json]
	 */
	public String getAlt() {
		return alt;
	}

	/** Data format for the response. */
	public FlagRequest<T> setAlt(String alt) {
		this.alt = alt;
		return this;
	}

	/** Selector specifying which fields to include in a partial response. */
	@com.google.api.client.util.Key
	private String fields;

	/**
	 * Selector specifying which fields to include in a partial response.
	 */
	public String getFields() {
		return fields;
	}

	/** Selector specifying which fields to include in a partial response. */
	public FlagRequest<T> setFields(String fields) {
		this.fields = fields;
		return this;
	}

	/**
	 * API key. Your API key identifies your project and provides you with API access, quota, and
	 * reports. Required unless you provide an OAuth 2.0 token.
	 */
	@com.google.api.client.util.Key
	private String key;

	/**
	 * API key. Your API key identifies your project and provides you with API access, quota, and
	 * reports. Required unless you provide an OAuth 2.0 token.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * API key. Your API key identifies your project and provides you with API access, quota, and
	 * reports. Required unless you provide an OAuth 2.0 token.
	 */
	public FlagRequest<T> setKey(String key) {
		this.key = key;
		return this;
	}

	/** OAuth 2.0 token for the current user. */
	@com.google.api.client.util.Key("oauth_token")
	private String oauthToken;

	/**
	 * OAuth 2.0 token for the current user.
	 */
	public String getOauthToken() {
		return oauthToken;
	}

	/** OAuth 2.0 token for the current user. */
	public FlagRequest<T> setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
		return this;
	}

	/** Returns response with indentations and line breaks. */
	@com.google.api.client.util.Key
	private Boolean prettyPrint;

	/**
	 * Returns response with indentations and line breaks. [default: true]
	 */
	public Boolean getPrettyPrint() {
		return prettyPrint;
	}

	/** Returns response with indentations and line breaks. */
	public FlagRequest<T> setPrettyPrint(Boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
		return this;
	}

	/**
	 * Available to use for quota purposes for server-side applications. Can be any arbitrary string
	 * assigned to a user, but should not exceed 40 characters. Overrides userIp if both are provided.
	 */
	@com.google.api.client.util.Key
	private String quotaUser;

	/**
	 * Available to use for quota purposes for server-side applications. Can be any arbitrary string
	 * assigned to a user, but should not exceed 40 characters. Overrides userIp if both are provided.
	 */
	public String getQuotaUser() {
		return quotaUser;
	}

	/**
	 * Available to use for quota purposes for server-side applications. Can be any arbitrary string
	 * assigned to a user, but should not exceed 40 characters. Overrides userIp if both are provided.
	 */
	public FlagRequest<T> setQuotaUser(String quotaUser) {
		this.quotaUser = quotaUser;
		return this;
	}

	/**
	 * IP address of the site where the request originates. Use this if you want to enforce per-user
	 * limits.
	 */
	@com.google.api.client.util.Key
	private String userIp;

	/**
	 * IP address of the site where the request originates. Use this if you want to enforce per-user
	 * limits.
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * IP address of the site where the request originates. Use this if you want to enforce per-user
	 * limits.
	 */
	public FlagRequest<T> setUserIp(String userIp) {
		this.userIp = userIp;
		return this;
	}

	@Override
	public final FlagClient getAbstractGoogleClient() {
		return (FlagClient) super.getAbstractGoogleClient();
	}

	@Override
	public FlagRequest<T> setDisableGZipContent(boolean disableGZipContent) {
		return (FlagRequest<T>) super.setDisableGZipContent(disableGZipContent);
	}

	@Override
	public FlagRequest<T> setRequestHeaders(HttpHeaders headers) {
		return (FlagRequest<T>) super.setRequestHeaders(headers);
	}
}
