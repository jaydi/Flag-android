package com.tankers.smile.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tankers.smile.models.Shop;
import com.tankers.smile.utils.JsonCodec;
import com.tankers.smile.utils.PushUtils;

public class GcmIntentService extends IntentService {
	private static final String TAG = "GcmIntentService";

	public static final String EXTRA_PUSH_TYPE = "type";
	public static final String EXTRA_PUSH_ENTITY = "entity";

	public static final String TYPE_SHOP = "shop_type";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty())
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType))
				Log.i(TAG, "Send error: " + extras.toString());
			else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType))
				Log.i(TAG, "Deleted messages on server: " + extras.toString());
			else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType))
				push(extras);

		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void push(Bundle extras) {
		System.out.println("push extras: " + extras);
		if (extras.getString(EXTRA_PUSH_TYPE).equals(TYPE_SHOP)) {
			Shop shop = JsonCodec.decode(extras.getString(EXTRA_PUSH_ENTITY), Shop.class);
			PushUtils.pushShop(shop);
		}
	}

}
