package com.tankers.smile.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.tankers.smile.R;
import com.tankers.smile.activities.ItemsActivity;
import com.tankers.smile.activities.MenuActivity;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Event;
import com.tankers.smile.models.Shop;

public class NotificationUtils {
	public static void notifyEvent(Event event) {
		Intent newIntent = new Intent(DalshopApplication.getInstance(), ItemsActivity.class);
		newIntent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP_PUSH);
		newIntent.putExtra(Shop.EXTRA_SHOP_ID, event.getShopId());
		PendingIntent pendingIntent = PendingIntent.getActivity(DalshopApplication.getInstance(), 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(DalshopApplication.getInstance());
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle(ResourceUtils.getString(R.string.app_name));
		builder.setContentText(event.getTitle() + "\n" + event.getMessage());
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);

		NotificationManager manager = (NotificationManager) DalshopApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(0);
		manager.notify(0, builder.build());
	}

	public static void notifyShopReward(Shop shop) {
		Intent newIntent = new Intent(DalshopApplication.getInstance(), MenuActivity.class);
		// newIntent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP_PUSH);
		// newIntent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		PendingIntent pendingIntent = PendingIntent.getActivity(DalshopApplication.getInstance(), 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(DalshopApplication.getInstance());
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle(ResourceUtils.getString(R.string.app_name));
		builder.setContentText(String.format(ResourceUtils.getString(R.string.message_notify_rewarded_shop), shop.getName(), shop.getReward()));
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);

		NotificationManager manager = (NotificationManager) DalshopApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(0);
		manager.notify(0, builder.build());
	}

}
