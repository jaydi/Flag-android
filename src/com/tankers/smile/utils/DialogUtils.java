package com.tankers.smile.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.activities.ItemsActivity;
import com.tankers.smile.activities.LoginActivity;
import com.tankers.smile.activities.PhoneInputActivity;
import com.tankers.smile.activities.RewardActivity;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.fragments.CheckinGuideDialog;
import com.tankers.smile.fragments.DatePickerFragment;
import com.tankers.smile.fragments.JobPickerFragment;
import com.tankers.smile.fragments.SexPickerFragment;
import com.tankers.smile.models.FeedbackMessage;
import com.tankers.smile.models.Redeem;
import com.tankers.smile.models.Redemption;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.models.User;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.services.ResponseHandlerWithDialog;

public class DialogUtils {
	public static ProgressDialog getWaitingDialog(Context context) {
		ProgressDialog progressDlg = new ProgressDialog(context);
		progressDlg.setMessage(ResourceUtils.getString(R.string.please_wait));
		progressDlg.setCancelable(true);
		progressDlg.show();
		return progressDlg;
	}

	public static void networkAlert(final Context context, final boolean dismiss) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		if (dismiss)
			builder.setMessage(R.string.message_notify_network_disconnected);
		builder.setTitle(R.string.network_disconnected);
		builder.setPositiveButton(R.string.close, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (dismiss)
					((Activity) context).finish();
			}

		});

		builder.show();
	}

	public static void bluetoothAlert(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.bluetooth_disconnected);
		builder.setMessage(R.string.message_notify_bluetooth_disconnected);
		builder.setPositiveButton(R.string.settings, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
				context.startActivity(intent);
			}

		});
		builder.setNegativeButton(android.R.string.ok, null);
		builder.show();
	}

	public static void bluetoothLEAlert(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.bluetooth_le_not_available);
		builder.setMessage(String.format(ResourceUtils.getString(R.string.message_notify_bluetooth_le), VERSION.RELEASE));
		builder.setPositiveButton(android.R.string.ok, null);
		builder.show();
	}

	public static void notifyShop(final Context context, final Shop shop) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("you are near " + shop.getName() + "!\nwant to check it out?");
		builder.setPositiveButton(R.string.yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// -- Tracking -- //
				Tracker.trackShopView(shop.getId(), 0);

				Intent intent = new Intent(context, ItemsActivity.class);
				intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP);
				intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
				context.startActivity(intent);
			}

		});
		builder.setNegativeButton(R.string.no, null);

		builder.show();
	}

	public static void showFeedbackDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.dialog_feedback_layout, null, false);

		if (!LocalUser.getUser().isGuest()) {
			EditText editEmail = (EditText) view.findViewById(R.id.edit_dialog_feedback_email);
			editEmail.setText(LocalUser.getUser().getEmail());
		}

		builder.setTitle(R.string.ask_dalshop);
		builder.setView(view);
		builder.setPositiveButton(R.string.send, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText editEmail = (EditText) view.findViewById(R.id.edit_dialog_feedback_email);
				EditText editMessage = (EditText) view.findViewById(R.id.edit_dialog_feedback_message);

				String email = editEmail.getEditableText().toString();
				String message = editMessage.getEditableText().toString();

				NetworkInter.sendFeedback(new ResponseHandler<Void>() {

					@Override
					protected void onResponse(Void response) {
						ToastUtils.show(R.string.message_notify_feedback_sent);
					}

				}, new FeedbackMessage(LocalUser.getUser().getId(), email, message));
			}

		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});

		builder.show();
	}

	public static void showSexPicker(FragmentManager fagmentManager, SexPickerFragment.OnSexItemClickListener listener) {
		SexPickerFragment sexPicker = new SexPickerFragment();
		sexPicker.setOnSexItemClickListener(listener);
		sexPicker.show(fagmentManager, "sexPicker");
	}

	public static void showJobPicker(FragmentManager fagmentManager, JobPickerFragment.OnJobItemClickListener listener) {
		JobPickerFragment jobPicker = new JobPickerFragment();
		jobPicker.setOnJobItemClickListener(listener);
		jobPicker.show(fagmentManager, "jobPicker");
	}

	public static void showDatePicker(FragmentManager fagmentManager, OnDateSetListener listener, long preset) {
		DatePickerFragment datePicker = new DatePickerFragment();
		datePicker.setOnDateSetListener(listener);
		datePicker.setPreset(preset);
		datePicker.show(fagmentManager, "datePicker");
	}

	public static void showRedeemFeedbackDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.dialog_redeem_feedback_layout, null, false);

		if (!LocalUser.getUser().isGuest()) {
			EditText editEmail = (EditText) view.findViewById(R.id.edit_dialog_feedback_email);
			editEmail.setText(LocalUser.getUser().getEmail());
		}

		builder.setTitle(R.string.redeem_feedback);
		builder.setView(view);
		builder.setPositiveButton(R.string.send, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText editEmail = (EditText) view.findViewById(R.id.edit_dialog_feedback_email);
				EditText editMessage = (EditText) view.findViewById(R.id.edit_dialog_feedback_message);

				String email = editEmail.getEditableText().toString();
				String message = editMessage.getEditableText().toString();

				NetworkInter.sendFeedback(new ResponseHandler<Void>() {

					@Override
					protected void onResponse(Void response) {
						ToastUtils.show(R.string.message_notify_feedback_sent);
					}

				}, new FeedbackMessage(LocalUser.getUser().getId(), email, message));
			}

		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});

		builder.show();
	}

	public static void showCheckinGuide(Context context) {
		new CheckinGuideDialog().show(((FragmentActivity) context).getSupportFragmentManager(), "checkinGuide");
	}

	public static void demandSignIn(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setMessage(R.string.message_guest_redeem_fail);
		builder.setPositiveButton(R.string.confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, LoginActivity.class);
				context.startActivity(intent);
			}

		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});

		builder.show();
	}

	public static void demandPhoneInput(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setMessage(R.string.message_redeem_fail_no_phone);
		builder.setPositiveButton(R.string.confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, PhoneInputActivity.class);
				intent.putExtra(PhoneInputActivity.EXTRA_PHONE_REQ, PhoneInputActivity.REQ_FROM_STORE);
				context.startActivity(intent);
			}

		});
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});

		builder.show();
	}

	public static void showRedeemDialog(final Context context, final UserInfo userInfo, final Redeem redeem) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final Dialog dialog = new Dialog(context);
		View view = inflater.inflate(R.layout.dialog_redeem_layout, null, false);

		ImageView image = (ImageView) view.findViewById(R.id.image_dialog_redeem_image);
		NetworkInter.getImage(null, image, redeem.getImageUrl(), 300, 300, false);

		TextView textName = (TextView) view.findViewById(R.id.text_dialog_redeem_item);
		textName.setText(redeem.getVendor() + "\n" + redeem.getName());

		TextView textValue = (TextView) view.findViewById(R.id.text_dialog_redeem_value);
		textValue.setText(redeem.getPrice() + ResourceUtils.getString(R.string.dal));

		Button buttonBuy = (Button) view.findViewById(R.id.button_dialog_redeem_buy);
		buttonBuy.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkInter.redeem(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(context)) {

					@Override
					protected void onResponse(User response) {
						super.onResponse(response);
						if (response == null) {
							ToastUtils.show(R.string.error_redeem_fail);
							return;
						}

						User user = response;
						user.setGuest(LocalUser.getGuestPref());
						LocalUser.setUser(user);

						dialog.dismiss();
						ToastUtils.showRedeemed(userInfo, redeem);
						((RewardActivity) context).refreshRewards();
					}

				}, new Redemption(userInfo.getUserId(), redeem.getId()));
			}

		});

		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.show();
	}
}
