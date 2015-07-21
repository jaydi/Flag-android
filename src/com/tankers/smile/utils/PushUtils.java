package com.tankers.smile.utils;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Vibrator;

import com.tankers.smile.activities.PopupActivity;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Event;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;

public class PushUtils {
	public static void vibrate() {
		Vibrator vibrator = (Vibrator) DalshopApplication.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(600);
	}

	public static boolean isScreenOn() {
		return ((PowerManager) DalshopApplication.getInstance().getApplicationContext().getSystemService(Context.POWER_SERVICE)).isScreenOn();
	}

	public static void pushEvent(Event event) {
		if (event == null)
			return;

		NotificationUtils.notifyEvent(event);
		if (isScreenOn())
			ToastUtils.showEvent(event);
		else
			popup(event);

		vibrate();
	}

	public static void pushShop(Shop shop) {
		if (shop == null)
			return;

		NotificationUtils.notifyEvent(shop.toEvent());
		if (isScreenOn())
			ToastUtils.showEvent(shop.toEvent());
		else
			popup(shop.toEvent());

		vibrate();
	}

	public static void pushShopReward(Shop shop) {
		if (shop == null)
			return;

		NotificationUtils.notifyShopReward(shop);
		if (isScreenOn())
			ToastUtils.showShopReward(shop);
		else
			popup(shop);

		vibrate();
	}

	public static void pushItemReward(Item item) {
		if (item == null)
			return;

		ToastUtils.showItemReward(item);
		vibrate();
	}

//	public static void pushInvitedReward() {
//		ToastUtils.showInvitedReward();
//		vibrate();
//	}

	public static void popup(Event event) {
		Intent popupIntent = new Intent(DalshopApplication.getInstance().getApplicationContext(), PopupActivity.class);
		popupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		popupIntent.putExtra(Event.EXTRA_EVENT, event);
		DalshopApplication.getInstance().getApplicationContext().startActivity(popupIntent);
	}

	public static void popup(Shop shop) {
		Intent popupIntent = new Intent(DalshopApplication.getInstance().getApplicationContext(), PopupActivity.class);
		popupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		popupIntent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		DalshopApplication.getInstance().getApplicationContext().startActivity(popupIntent);
	}
}