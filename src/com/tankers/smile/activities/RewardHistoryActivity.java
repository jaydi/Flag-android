package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.adapters.RewardAdapter;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Reward;
import com.tankers.smile.models.RewardCollection;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.ResourceUtils;

public class RewardHistoryActivity extends SubCategoryActivity {
	private ListView listRewards;
	private RewardAdapter rewardAdapter;
	private List<Reward> rewards;
	
	private int mark = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward_history);
		viewId = Log.VIEW_REWARD_HISTORY;
		
		TextView textReward = (TextView) findViewById(R.id.text_reward_history_dal);
		textReward.setText("" + LocalUser.getUser().getReward() + ResourceUtils.getString(R.string.dal));

		listRewards = (ListView) findViewById(R.id.list_reward_history_rewards);
		rewards = new ArrayList<Reward>();
		rewardAdapter = new RewardAdapter(this, rewards);
		listRewards.setAdapter(rewardAdapter);
		
		loadRewards();
	}

	private void loadRewards() {
		NetworkInter.rewardList(new ResponseHandler<RewardCollection>() {

			@Override
			protected void onResponse(RewardCollection response) {
				hideLoader();
				if (response == null || response.getRewards() == null)
					return;

				rewards.clear();
				rewards.addAll(response.getRewards());
				refreshRewards();
			}

		}, LocalUser.getUser().getId(), mark);
	}

	private void hideLoader() {
		findViewById(R.id.progressbar_reward_history_loading).setVisibility(View.GONE);
	}

	private void refreshRewards() {
		sortRewards();
		rewardAdapter.notifyDataSetChanged();
		mark++;
	}

	private void sortRewards() {
		Collections.sort(rewards, new Comparator<Reward>() {

			@Override
			public int compare(Reward r1, Reward r2) {
				return (r1.getCreatedAt() < r2.getCreatedAt()) ? 0 : -1;
			}

		});
	}

}
