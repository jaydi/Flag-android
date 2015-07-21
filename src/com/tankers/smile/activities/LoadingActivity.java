package com.tankers.smile.activities;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tankers.smile.R;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.RetainForm;
import com.tankers.smile.models.User;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.DBInter;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.IBeaconUtils;
import com.tankers.smile.utils.Tracker;

public class LoadingActivity extends BaseActivity {
	private static final String SENDER_ID = "620366918607";
	private static final String PREFERENCE_GCM = "preference_gcm";
	private static final String PROPERTY_REG_ID = "gcm_registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";

	private GoogleCloudMessaging gcm;

	private boolean able = true;

	// private String action;
	// private long shopId;
	// private long itemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		viewId = Log.VIEW_LOADING;

		// if (savedInstanceState == null) {
		// Uri uri = getIntent().getData();
		// if (uri != null) {
		// action = uri.getQueryParameter("action");
		// try {
		// shopId = Long.valueOf(uri.getQueryParameter("shopId"));
		// } catch (NumberFormatException e) {
		// shopId = 0;
		// }
		// try {
		// itemId = Long.valueOf(uri.getQueryParameter("itemId"));
		// } catch (NumberFormatException e) {
		// itemId = 0;
		// }
		// }
		// }
	}

	@Override
	protected void networkAlert() {
		able = false;
		DialogUtils.networkAlert(this, true);
	}

	@Override
	public void onResume() {
		super.onResume();
		bindLocationUtils();
		bindBeaconUtils();
		getReady();
	}

	private void bindLocationUtils() {
		DalshopApplication.getInstance().initLocationUtils();
	}

	private void bindBeaconUtils() {
		if (IBeaconUtils.verifyBluetooth(this))
			DalshopApplication.getInstance().bindBeaconUtils();
	}

	private void getReady() {
		if (!able)
			return;

		User localUser = LocalUser.getUser();
		if (localUser.isFirst())
			guestUser();
		else
			retainUser(localUser.getId());

		getFlagListAll();
	}

	private void guestUser() {
		NetworkInter.guestUser(new ResponseHandler<User>() {

			@Override
			protected void onResponse(User response) {
				if (response == null)
					return;

				response.setGuest(true);
				LocalUser.setUser(response);
				moveOn();

				// -- Tracking -- //
				Tracker.trackFirstUser(0, 0);
			}

		});
	}

	private void retainUser(long id) {
		NetworkInter.retainUser(new ResponseHandler<User>() {

			@Override
			protected void onResponse(User response) {
				if (response == null) {
					guestUser();
					return;
				}

				boolean isGuest = response.getEmail() == null || response.getEmail().isEmpty();
				response.setGuest(isGuest);
				LocalUser.setUser(response);
				moveOn();
			}

		}, new RetainForm(id));
	}

	private void getFlagListAll() {
		NetworkInter.getFlagListAll(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				DBInter.storeFlagsData(response);
			}

		}, DBInter.getFlagListTag());
	}

	private void moveOn() {
		registerGCM();
		sendInfo();

		// if (action == null || action.isEmpty())
		// goToMain();
		// else if (action.equals("shop") && shopId != 0)
		// goToShop();
		// else if (action.equals("item") && itemId != 0)
		// goToItem();
		// else

		goToMain();
	}

	private void sendInfo() {
		NetworkInter.updateUserInfo(new ResponseHandler<UserInfo>() {

			@Override
			protected void onResponse(UserInfo response) {
				// do nothing
			}

		}, UserInfo.checkingInstance());
	}

	private void goToMain() {
		startActivity(new Intent(this, MenuActivity.class));
		finish();
	}

	// private void goToShop() {
	// // -- Tracking -- //
	// Tracker.trackShopView(shopId, 0);
	//
	// Intent intent = new Intent(this, ItemsActivity.class);
	// intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP_PUSH);
	// intent.putExtra(Shop.EXTRA_SHOP_ID, shopId);
	// startActivity(intent);
	// finish();
	// }
	//
	// private void goToItem() {
	// // -- Tracking -- //
	// Tracker.trackItemView(itemId, 0);
	//
	// Intent intent = new Intent(this, ItemDescActivity.class);
	// intent.putExtra(ItemDescActivity.EXTRA_ITEM_DESC_TYPE, ItemDescActivity.ITEM_DESC_TYPE_ITEM_PUSH);
	// intent.putExtra(Shop.EXTRA_SHOP_ID, shopId);
	// intent.putExtra(Item.EXTRA_ITEM_ID, itemId);
	// startActivity(intent);
	// finish();
	// }

	// cloud messaging register

	private void registerGCM() {
		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			if (getRegistrationId().isEmpty())
				registerInBackground();
		} else {
			android.util.Log.i("GCM", "No valid Google Play Services APK found.");
		}
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				android.util.Log.i("GCM", "This user is recoverable");
			} else {
				android.util.Log.i("GCM", "This device is not supported.");
			}
			return false;
		}
		return true;
	}

	public String getRegistrationId() {
		final SharedPreferences prefs = getGCMPreferences();
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			android.util.Log.i("GCM", "Registration not found.");
			return "";
		}

		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersionCode();
		if (registeredVersion != currentVersion) {
			android.util.Log.i("GCM", "App version changed.");
			return "";
		}

		return registrationId;
	}

	private SharedPreferences getGCMPreferences() {
		return getSharedPreferences(PREFERENCE_GCM, Context.MODE_PRIVATE);
	}

	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(DalshopApplication.getInstance());
					}

					String regId = gcm.register(SENDER_ID);
					storeRegistrationId(regId);

					return regId;

				} catch (IOException ex) {
					return "";
				}
			}

			@Override
			protected void onPostExecute(String regId) {
				if (!regId.isEmpty())
					sendRegistrationIdToBackend(regId);
			}
		}.execute(null, null, null);
	}

	private void sendRegistrationIdToBackend(String regId) {
		NetworkInter.updateUserInfo(new ResponseHandler<UserInfo>() {

			@Override
			protected void onResponse(UserInfo response) {
				// nothing to do
			}

		}, new UserInfo(LocalUser.getUser().getId(), regId));
	}

	private void storeRegistrationId(String regId) {
		final SharedPreferences prefs = getGCMPreferences();
		int appVersion = getAppVersionCode();
		android.util.Log.i("GCM", "Saving regId on app version " + appVersion);

		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

	private int getAppVersionCode() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
