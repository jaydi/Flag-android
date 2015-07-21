package com.tankers.smile.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tankers.smile.R;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Event;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.Redeem;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;

public class ToastUtils {
	public static void show(int src) {
		show(ResourceUtils.getString(src));
	}

	public static void showLong(int src) {
		showLong(ResourceUtils.getString(src));
	}

	public static void show(String msg) {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView msgView = (TextView) inflater.inflate(R.layout.toast_simple_layout, null, false);
		msgView.setText(msg);

		toast.setView(msgView);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showLong(String msg) {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		TextView msgView = (TextView) inflater.inflate(R.layout.toast_simple_layout, null, false);
		msgView.setText(msg);

		toast.setView(msgView);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showEvent(Event event) {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_event_layout, null, false);

		TextView textTitle = (TextView) view.findViewById(R.id.text_toast_event_title);
		textTitle.setText(event.getTitle());

		ImageView image = (ImageView) view.findViewById(R.id.image_toast_event_image);
		NetworkInter.getImage(null, image, event.getImageUrl(), 200, 200, true);

		TextView textMsg = (TextView) view.findViewById(R.id.text_toast_event_message);
		textMsg.setText(event.getMessage());

		toast.setView(view);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showShopReward(Shop shop) {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_reward_layout, null, false);

		TextView textMsg = (TextView) view.findViewById(R.id.text_toast_reward_message);
		textMsg.setText(String.format(ResourceUtils.getString(R.string.message_notify_rewarded_shop), shop.getName(), shop.getReward()));

		toast.setView(view);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showItemReward(Item item) {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_reward_layout, null, false);

		TextView textMsg = (TextView) view.findViewById(R.id.text_toast_reward_message);
		textMsg.setText(String.format(ResourceUtils.getString(R.string.message_notify_rewarded_item), item.getName(), item.getReward()));

		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showScanRewardFail() {
		final Context context = DalshopApplication.getInstance().getApplicationContext();
		final Toast toast = new Toast(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.toast_scan_reward_fail_layout, null, false);

		toast.setView(view);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

//	public static void showInvitedReward() {
//		final Context context = DalshopApplication.getInstance().getApplicationContext();
//		final Toast toast = new Toast(context);
//
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View view = inflater.inflate(R.layout.toast_reward_layout, null, false);
//
//		TextView textMsg = (TextView) view.findViewById(R.id.text_toast_reward_message);
//		textMsg.setText(String.format(ResourceUtils.getString(R.string.message_notify_rewarded_invited), Reward.VALUE_INVITED));
//
//		toast.setView(view);
//		toast.setDuration(Toast.LENGTH_SHORT);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.show();
//	}

	public static void showRedeemed(UserInfo userInfo, Redeem redeem) {
		String msg = String.format(ResourceUtils.getString(R.string.message_redeemed), redeem.getName(), userInfo.getPhone());
		showLong(msg);
	}
}
