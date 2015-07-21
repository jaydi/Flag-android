package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;

public class SettingsActivity extends SubCategoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		viewId = Log.VIEW_SETTINGS;
		
		if (!LocalUser.getUser().isGuest())
			findViewById(R.id.text_settings_logout).setVisibility(View.VISIBLE);
	}

	public void goToNotice(View view) {
		Intent intent = new Intent(this, NoticeActivity.class);
		startActivity(intent);
	}

	public void goToVersion(View view) {
		Intent intent = new Intent(this, VersionActivity.class);
		startActivity(intent);
	}

	public void logout(View view) {
		LocalUser.removeUser();

		Intent intent = new Intent(this, LoadingActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

}
