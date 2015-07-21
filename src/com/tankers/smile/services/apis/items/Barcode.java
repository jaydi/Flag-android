package com.tankers.smile.services.apis.items;

import java.io.IOException;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;
import com.tankers.smile.models.Item;
import com.tankers.smile.services.apis.FlagClient;
import com.tankers.smile.services.apis.FlagRequest;

public class Barcode extends FlagRequest<Item> {
	private static final String REST_PATH = "barcode";

	protected Barcode(FlagClient client) {
		super(client, "GET", REST_PATH, null, Item.class);
	}

	@Override
	public HttpResponse executeUsingHead() throws IOException {
		return super.executeUsingHead();
	}

	@Override
	public HttpRequest buildHttpRequestUsingHead() throws IOException {
		return super.buildHttpRequestUsingHead();
	}

	@Override
	public Barcode setAlt(String alt) {
		return (Barcode) super.setAlt(alt);
	}

	@Override
	public Barcode setFields(String fields) {
		return (Barcode) super.setFields(fields);
	}

	@Override
	public Barcode setKey(String key) {
		return (Barcode) super.setKey(key);
	}

	@Override
	public Barcode setOauthToken(String oauthToken) {
		return (Barcode) super.setOauthToken(oauthToken);
	}

	@Override
	public Barcode setPrettyPrint(Boolean prettyPrint) {
		return (Barcode) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Barcode setQuotaUser(String quotaUser) {
		return (Barcode) super.setQuotaUser(quotaUser);
	}

	@Override
	public Barcode setUserIp(String userIp) {
		return (Barcode) super.setUserIp(userIp);
	}

	@Key
	private Long userId;

	@Key
	private String barcodeId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}
}