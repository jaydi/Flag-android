package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;

public class MyPageActivity extends SubCategoryActivity {
	private UserInfo userInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_page);
		viewId = Log.VIEW_MY_PAGE;
	}

	@Override
	public void onResume() {
		super.onResume();
		getUserInfo();
	}

	private void getUserInfo() {
		NetworkInter.getUserInfo(new ResponseHandlerWithDialog<UserInfo>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(UserInfo response) {
				super.onResponse(response);
				if (response == null)
					return;

				userInfo = response;
				showUserInfo();
			}

		}, LocalUser.getUser().getId());
	}

	private void showUserInfo() {
		TextView textEmail = (TextView) findViewById(R.id.text_my_page_email);
		textEmail.setText(LocalUser.getUser().getEmail());

		TextView textPhone = (TextView) findViewById(R.id.text_my_page_phone);
		if (userInfo != null && userInfo.getPhone() != null && !userInfo.getPhone().isEmpty())
			textPhone.setText(userInfo.getPhone());
		else
			textPhone.setText(ResourceUtils.getString(R.string.no_phone));
	}

	public void goToPhoneInput(View view) {
		if (userInfo.getPhone() == null || userInfo.getPhone().isEmpty()) {
			Intent intent = new Intent(this, PhoneInputActivity.class);
			intent.putExtra(PhoneInputActivity.EXTRA_PHONE_REQ, PhoneInputActivity.REQ_FROM_STORE);
			startActivity(intent);
		}
	}

	public void logout(View view) {
		LocalUser.removeUser();

		Intent intent = new Intent(this, LoadingActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	public void byebye(View view) {
		NetworkInter.deleteUser(new ResponseHandlerWithDialog<Void>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(Void response) {
				logout(null);
			}

		}, LocalUser.getUser().getId());
	}

}
