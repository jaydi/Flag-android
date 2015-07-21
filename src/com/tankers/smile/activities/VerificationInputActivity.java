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

public class VerificationInputActivity extends BaseActivity {
	private int reqFrom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verification_input);
		viewId = Log.VIEW_VERIFICATION_INPUT;

		if (savedInstanceState == null)
			reqFrom = getIntent().getIntExtra(PhoneInputActivity.EXTRA_PHONE_REQ, 0);
	}

	public void verifyCode(View view) {
		EditText editVerCode = (EditText) findViewById(R.id.edit_verification_input_code);
		String verCode = editVerCode.getEditableText().toString();

		if (verCode.isEmpty())
			return;

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(LocalUser.getUser().getId());
		userInfo.setPhone(verCode);

		NetworkInter.phoneInsert(new ResponseHandlerWithDialog<UserInfo>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(UserInfo response) {
				super.onResponse(response);
				if (response == null)
					return;

				Intent intent = null;
				if (reqFrom != PhoneInputActivity.REQ_FROM_STORE) {
					intent = new Intent(VerificationInputActivity.this, BGuideActivity.class);
					startActivity(intent);
				}
				finish();
			}

		}, userInfo);
	}
	
	// public void regetVerCode(View view) {
	//
	// }

}
