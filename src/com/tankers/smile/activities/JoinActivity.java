package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.User;
import com.tankers.smile.models.UserForm;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.StringUtils;
import com.tankers.smile.utils.ToastUtils;

public class JoinActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		viewId = Log.VIEW_JOIN;
	}

	public void signUp(View view) {
		EditText edit_email = (EditText) findViewById(R.id.edit_join_email);
		EditText edit_pw = (EditText) findViewById(R.id.edit_join_password);
		EditText edit_pwcheck = (EditText) findViewById(R.id.edit_join_password_check);

		String email = edit_email.getText().toString();
		String pw = edit_pw.getText().toString();
		String pwcheck = edit_pwcheck.getText().toString();

		if (emailCheck(email) && pwCheck(pw, pwcheck)) {
			UserForm userForm = new UserForm();
			userForm.setId(LocalUser.getUser().getId());
			userForm.setEmail(email);
			userForm.setPassword(StringUtils.encryptSHA256(pw));

			NetworkInter.insertUser(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(this)) {
				@Override
				protected void onResponse(User response) {
					super.onResponse(response);
					if (response == null || response.getId() == 0)
						return;

					LocalUser.setUser(response);
					Intent intent = new Intent(JoinActivity.this, PhoneInputActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
					finish();
				}
			}, userForm);

		} else
			return;
	}

	private boolean emailCheck(String email) {
		if (email.equals("")) {
			ToastUtils.show("please enter your e-mail address");
			return false;
		} else if (email.length() < 5 || !email.contains("@")) {
			ToastUtils.show("please enter a valid e-mail address");
			return false;
		} else
			return true;
	}

	private boolean pwCheck(String pw, String pw2) {
		if (pw.equals("")) {
			ToastUtils.show("please enter a password");
			return false;
		} else if (!pw.equals(pw2)) {
			ToastUtils.show("passwords not match");
			return false;
		} else
			return true;
	}

	public void goToTOU(View view) {
		Intent intent = new Intent(this, SimpleTextActivity.class);
		intent.putExtra(SimpleTextActivity.EXTRA_REQUEST_CODE_TEXT, SimpleTextActivity.REQUEST_TOU);
		startActivity(intent);
	}

	public void goToPP(View view) {
		Intent intent = new Intent(this, SimpleTextActivity.class);
		intent.putExtra(SimpleTextActivity.EXTRA_REQUEST_CODE_TEXT, SimpleTextActivity.REQUEST_PP);
		startActivity(intent);
	}
}