package com.tankers.smile.utils;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;

import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.CheckinForm;
import com.tankers.smile.models.User;
import com.tankers.smile.models.UserShopSet;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;

public class CheckInUtils {
	private static final long PUSH_EXPIRATION_TIME = 1000 * 60 * 60 * 24;

	private Context context;

	public CheckInUtils(Context context) {
		super();
		this.context = context;
	}

	public void checkIn(final long flagId) {
		if (!isCachedItem(flagId)) {
			System.out.println("checkin");
			NetworkInter.checkin(new ResponseHandler<UserShopSet>(context.getMainLooper()) {

				@Override
				protected void onResponse(UserShopSet response) {
					System.out.println("checkin response");

					if (response == null)
						return;

					if (response.getUser() == null) {
						PushUtils.pushShop(response.getShop());
						System.out.println("checked in shop, push");
						return;
					}

					System.out.println("rewarded, push");

					User user = response.getUser();
					user.setGuest(LocalUser.getGuestPref());
					LocalUser.setUser(user);
					PushUtils.pushShopReward(response.getShop());
				}

			}, new CheckinForm(LocalUser.getUser().getId(), flagId));

			cache(flagId);

			// -- Tracking -- //
			Tracker.trackVisit(flagId, 0);
		}
	}

	private boolean isCachedItem(long flagId) {
		SharedPreferences pref = null;
		pref = getPreferenceCheckIn();

		long expTime = pref.getLong(String.valueOf(flagId), 0);
		if (expTime < new Date().getTime())
			return false;
		else
			return true;
	}

	private void cache(long flagId) {
		SharedPreferences pref = null;
		pref = getPreferenceCheckIn();

		SharedPreferences.Editor editor = pref.edit();
		editor.putLong(String.valueOf(flagId), new Date().getTime() + PUSH_EXPIRATION_TIME);
		editor.commit();
	}

	public static SharedPreferences getPreferenceCheckIn() {
		return DalshopApplication.getInstance().getSharedPreferences("CheckedInShopIds" + LocalUser.getUser().getId(), Context.MODE_PRIVATE);
	}
}
