package com.tankers.smile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;

public class MenuActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public void onResume() {
		super.onResume();

		TextView textReward = (TextView) findViewById(R.id.text_menu_dal);
		textReward.setText(LocalUser.getUser().getReward() + ResourceUtils.getString(R.string.dal));
	}

	public void goToDalSearch(View view) {
		Intent intent = new Intent(this, ShopsActivity.class);
		intent.putExtra(ShopsActivity.EXTRA_SHOPS_TYPE, ShopsActivity.SHOPS_TYPE_REWARD);
		startActivity(intent);
	}

	public void showGuide(View view) {
		DialogUtils.showCheckinGuide(this);
	}

	public void goToDalstore(View view) {
		Intent intent = new Intent(this, RewardActivity.class);
		startActivity(intent);
	}

	public void goToHistory(View view) {
		Intent intent = new Intent(this, RewardHistoryActivity.class);
		startActivity(intent);
	}

	public void goToAccount(View view) {
		if (LocalUser.getUser().isGuest()) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, MyPageActivity.class);
			startActivity(intent);
		}
	}

}
