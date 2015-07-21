package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.adapters.RedeemAdapter;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Redeem;
import com.tankers.smile.models.RedeemCollection;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.ToastUtils;

public class RewardActivity extends SubCategoryActivity implements RedeemAdapter.RedeemClickInter {
	private List<Redeem> redeems;
	private RedeemAdapter redeemAdapter;
	private GridView gridRedeems;

	private int mark;

	private UserInfo userInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
		viewId = Log.VIEW_REWARD;

		redeems = new ArrayList<Redeem>();
		redeemAdapter = new RedeemAdapter(this, redeems);
		gridRedeems = (GridView) findViewById(R.id.grid_reward_redeems);
		gridRedeems.setAdapter(redeemAdapter);

		loadRedeems();
	}

	@Override
	public void onResume() {
		super.onResume();
		userInfo = null;
		loadUserInfo();
		refreshRewards();
	}

	public void refreshRewards() {
		TextView textReward = (TextView) findViewById(R.id.text_reward_dal);
		textReward.setText("" + LocalUser.getUser().getReward() + ResourceUtils.getString(R.string.dal));
	}

	private void loadRedeems() {
		NetworkInter.redeemList(new ResponseHandler<RedeemCollection>() {

			@Override
			protected void onResponse(RedeemCollection response) {
				if (response == null || response.getRedeems() == null)
					return;

				redeems.addAll(response.getRedeems());
				refreshRedeems();
			}

		}, mark);
	}

	private void refreshRedeems() {
		redeemAdapter.notifyDataSetChanged();
		mark++;
	}

	private void loadUserInfo() {
		NetworkInter.getUserInfo(new ResponseHandler<UserInfo>() {

			@Override
			protected void onResponse(UserInfo response) {
				userInfo = response;
			}

		}, LocalUser.getUser().getId());
	}

	@Override
	public void onClickRedeem(Redeem redeem) {
		if (LocalUser.getUser().isGuest()) {
			DialogUtils.demandSignIn(this);
			return;
		}

		if (userInfo == null)
			return;

		if (userInfo.getPhone() == null || userInfo.getPhone().isEmpty()) {
			DialogUtils.demandPhoneInput(this);
			return;
		}

		if (LocalUser.getUser().getReward() < redeem.getPrice()) {
			ToastUtils.show(ResourceUtils.getString(R.string.message_not_enough_reward));
			return;
		}

		DialogUtils.showRedeemDialog(this, userInfo, redeem);
	}
}
