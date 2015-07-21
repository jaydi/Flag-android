package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Event;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.Tracker;

public class PopupActivity extends BaseActivity {
	public static final String EXTRA_POPUP_MSG = "com.flag.activities.extra.popup.msg";

	private ShopParcelable shopParcelable;
	private Event event;
	private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_popup);
		viewId = Log.VIEW_POPUP;

		if (savedInstanceState == null) {
			shopParcelable = getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL);
			event = getIntent().getParcelableExtra(Event.EXTRA_EVENT);
		}

		turnOn();
		showMsg();
	}

	@Override
	public void onPause() {
		super.onPause();
		if (i == 1)
			finish();
		i++;
	}

	private void turnOn() {
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
						| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
	}

	private void showMsg() {
		if (shopParcelable != null)
			showRewardMsg();
		else if (event != null)
			showEventMsg();
	}

	private void showRewardMsg() {
		findViewById(R.id.linear_popup_reward).setVisibility(View.VISIBLE);

		TextView textMsg = (TextView) findViewById(R.id.text_popup_reward_message);
		textMsg.setText(String.format(ResourceUtils.getString(R.string.message_notify_rewarded_shop), shopParcelable.getName(),
				shopParcelable.getReward()));
	}

	private void showEventMsg() {
		findViewById(R.id.linear_popup_event).setVisibility(View.VISIBLE);

		TextView textTitle = (TextView) findViewById(R.id.text_popup_event_title);
		textTitle.setText(event.getTitle());

		ImageView image = (ImageView) findViewById(R.id.image_popup_event_image);
		NetworkInter.getImage(null, image, event.getImageUrl(), 200, 200, true);

		TextView textMsg = (TextView) findViewById(R.id.text_popup_event_message);
		textMsg.setText(event.getMessage());
	}

	public void goToContents(View view) {
		Intent intent = null;
		if (event != null && event.getReward() > 0) {
			intent = new Intent(this, ItemsActivity.class);
			intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP_PUSH);
			intent.putExtra(Shop.EXTRA_SHOP_ID, event.getShopId());

			// -- Tracking -- //
			Tracker.trackShopView(event.getShopId(), 0);
		} else if (event != null) {
			intent = new Intent(this, ItemsActivity.class);
			intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP_PUSH);
			intent.putExtra(Shop.EXTRA_SHOP_ID, event.getShopId());

			// -- Tracking -- //
			Tracker.trackShopView(event.getShopId(), 0);
		} else if (shopParcelable != null) {
			intent = new Intent(this, MenuActivity.class);
			// intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP);
			// intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shopParcelable);

			// // -- Tracking -- //
			// Tracker.trackShopView(shopParcelable.getId(), 0);
		}

		if (intent != null)
			startActivity(intent);

		finish();
	}

	public void dismiss(View view) {
		finish();
	}
}
