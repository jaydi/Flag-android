package com.tankers.smile.services.apis;

import java.io.IOException;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.tankers.smile.services.apis.apps.Apps;
import com.tankers.smile.services.apis.flags.Flags;
import com.tankers.smile.services.apis.items.Items;
import com.tankers.smile.services.apis.likes.Likes;
import com.tankers.smile.services.apis.logs.Logs;
import com.tankers.smile.services.apis.redeems.Redeems;
import com.tankers.smile.services.apis.rewards.Rewards;
import com.tankers.smile.services.apis.shops.Shops;
import com.tankers.smile.services.apis.userinfos.UserInfos;
import com.tankers.smile.services.apis.users.Users;

public class FlagClient extends AbstractGoogleJsonClient {

//	static {
//		Preconditions.checkState(GoogleUtils.VERSION.equals("1.13.2-beta"), "You are currently running with version %s of google-api-client. "
//				+ "You need version 1.13.2-beta of google-api-client to run version " + "1.13.2-beta of the  library.", GoogleUtils.VERSION);
//	}

	public static final String DEFAULT_ROOT_URL = "https://genuine-evening-455.appspot.com/_ah/api/";
	public static final String DEFAULT_SERVICE_PATH = "flagengine/v1/";
	@Deprecated
	public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

	public FlagClient(HttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
		super(new Builder(transport, jsonFactory, httpRequestInitializer));
	}

	FlagClient(Builder builder) {
		super(builder);
	}

	// public FlagClient(HttpTransport transport, HttpRequestInitializer httpRequestInitializer, String rootUrl, String servicePath,
	// JsonObjectParser jsonObjectParser, GoogleClientRequestInitializer googleClientRequestInitializer, String applicationName,
	// boolean suppressPatternChecks) {
	// super(transport, httpRequestInitializer, rootUrl, servicePath, jsonObjectParser, googleClientRequestInitializer, applicationName,
	// suppressPatternChecks);
	// }

	@Override
	public void initialize(AbstractGoogleClientRequest<?> httpClientRequest) throws IOException {
		super.initialize(httpClientRequest);
	}

	public Users users() {
		return new Users(this);
	}

	public UserInfos userInfos() {
		return new UserInfos(this);
	}

	public Flags flags() {
		return new Flags(this);
	}

	public Shops shops() {
		return new Shops(this);
	}

	public Items items() {
		return new Items(this);
	}

	public Likes likes() {
		return new Likes(this);
	}

	public Rewards rewards() {
		return new Rewards(this);
	}

	public Redeems redeems() {
		return new Redeems(this);
	}

	public Apps apps() {
		return new Apps(this);
	}

	public Logs logs() {
		return new Logs(this);
	}

	public static final class Builder extends AbstractGoogleJsonClient.Builder {
		public Builder(HttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
			super(transport, jsonFactory, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, httpRequestInitializer, false);
		}

		@Override
		public FlagClient build() {
			return new FlagClient(this);
		}

		@Override
		public Builder setRootUrl(String rootUrl) {
			return (Builder) super.setRootUrl(rootUrl);
		}

		@Override
		public Builder setServicePath(String servicePath) {
			return (Builder) super.setServicePath(servicePath);
		}

		@Override
		public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
			return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
		}

		@Override
		public Builder setApplicationName(String applicationName) {
			return (Builder) super.setApplicationName(applicationName);
		}

		@Override
		public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
			return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
		}

		public Builder setFlagRequestInitializer(FlagRequestInitializer FlagRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(FlagRequestInitializer);
		}

		@Override
		public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
		}
	}
}
