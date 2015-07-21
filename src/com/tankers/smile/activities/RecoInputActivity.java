package com.tankers.smile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.InvitationForm;
import com.tankers.smile.models.User;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ToastUtils;

public class RecoInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reco_input);
	}

	public void claimInvited(View view) {
		EditText editRecoEmail = (EditText) findViewById(R.id.edit_reco_input_email);
		String recoEmail = editRecoEmail.getEditableText().toString();

		NetworkInter.invited(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(User response) {
				super.onResponse(response);
				if (response == null) {
					ToastUtils.show("email address doesn't exist");
					return;
				}
				
				// User user = response;
				// user.setGuest(LocalUser.getGuestPref());
				// LocalUser.setUser(user);
				// PushUtils.pushInvitedReward();

				Intent intent = new Intent(RecoInputActivity.this, BGuideActivity.class);
				startActivity(intent);
				finish();
			}

		}, new InvitationForm(LocalUser.getUser().getId(), recoEmail));
	}

	public void skip(View view) {
		Intent intent = new Intent(this, BGuideActivity.class);
		startActivity(intent);
		finish();
	}

}
