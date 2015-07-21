package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ToastUtils;

public class PhoneInputActivity extends BaseActivity {
	public static final String EXTRA_PHONE_REQ = "com.tankers.smile.extra.phone.req";
	public static final int REQ_FROM_STORE = 1;

	private int reqFrom;

	private EditText editLocale;
	private EditText editPhone1;
	private EditText editPhone2;
	private EditText editPhone3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_input);
		viewId = Log.VIEW_PHONE_INPUT;

		if (savedInstanceState == null) {
			reqFrom = getIntent().getIntExtra(EXTRA_PHONE_REQ, 0);
			if (reqFrom == REQ_FROM_STORE)
				findViewById(R.id.button_phone_input_skip).setVisibility(View.GONE);
		}

		editLocale = (EditText) findViewById(R.id.edit_phone_input_locale);
		editPhone1 = (EditText) findViewById(R.id.edit_phone_input_phone1);
		editPhone2 = (EditText) findViewById(R.id.edit_phone_input_phone2);
		editPhone3 = (EditText) findViewById(R.id.edit_phone_input_phone3);
	}

	@Override
	public void onResume() {
		super.onResume();
		editLocale.setText("+82");
		editPhone1.setText("010");
	}

	public void goToVerification(View view) {
		String locale = editLocale.getEditableText().toString();
		String phone1 = editPhone1.getEditableText().toString();
		String phone2 = editPhone2.getEditableText().toString();
		String phone3 = editPhone3.getEditableText().toString();

		if (locale.isEmpty() || !locale.startsWith("+"))
			return;

		if (phone1.isEmpty() || phone1.length() < 3)
			return;

		if (phone2.isEmpty() || phone2.length() < 3)
			return;

		if (phone3.isEmpty() || phone3.length() < 4)
			return;

		if (phone1.startsWith("0"))
			phone1 = phone1.substring(1);

		String phone = locale + phone1 + phone2 + phone3;
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(LocalUser.getUser().getId());
		userInfo.setPhone(phone);
		sendPhone(userInfo);
	}

	private void sendPhone(final UserInfo userInfo) {
		NetworkInter.phoneTest(new ResponseHandlerWithDialog<UserInfo>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(UserInfo response) {
				super.onResponse(response);
				if (response == null)
					return;

				if (response.getPhone() != null && response.getPhone().equals(userInfo.getPhone())) {
					ToastUtils.show(R.string.error_duplicate_phone_number);
					return;
				}

				Intent intent = new Intent(PhoneInputActivity.this, VerificationInputActivity.class);
				intent.putExtra(EXTRA_PHONE_REQ, reqFrom);
				startActivity(intent);
				finish();
			}

		}, userInfo);
	}

	public void skipPhoneInput(View view) {
		Intent intent = new Intent(this, BGuideActivity.class);
		startActivity(intent);
		finish();
	}
}