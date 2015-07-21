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
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.StringUtils;
import com.tankers.smile.utils.ToastUtils;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		viewId = Log.VIEW_LOGIN;
	}

	public void signIn(View view) {
		EditText edit_email = (EditText) findViewById(R.id.edit_login_email);
		EditText edit_pw = (EditText) findViewById(R.id.edit_login_password);

		String email = edit_email.getText().toString();
		String pw = edit_pw.getText().toString();

		if (blankCheck(email, "email address") && blankCheck(pw, "password")) {
			UserForm userForm = new UserForm();
			userForm.setId(LocalUser.getUser().getId());
			userForm.setEmail(email);
			userForm.setPassword(StringUtils.encryptSHA256(pw));

			NetworkInter.getUser(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(this)) {
				@Override
				protected void onResponse(User response) {
					super.onResponse(response);
					if (response == null || response.getId() == 0) {
						ToastUtils.show(ResourceUtils.getString(R.string.message_notify_invalid_user));
						return;
					}

					LocalUser.setUser(response);

					Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
					finish();
				}
			}, userForm);
		}
	}

	public void goToSignUp(View view) {
		startActivity(new Intent(LoginActivity.this, JoinActivity.class));
		finish();
	}

	boolean blankCheck(String blank, String msg) {
		if (blank.equals("")) {
			ToastUtils.show("please provide your " + msg);
			return false;
		} else
			return true;
	}
}