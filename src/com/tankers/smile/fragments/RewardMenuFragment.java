package com.tankers.smile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tankers.smile.R;
import com.tankers.smile.activities.RewardActivity;
import com.tankers.smile.activities.RewardHistoryActivity;
import com.tankers.smile.adapters.RewardMenuAdapter;

public class RewardMenuFragment extends Fragment implements OnItemClickListener {
	private ListView menus;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		menus = (ListView) inflater.inflate(R.layout.slide_reward_menu, null, false);
		return menus;
	}

	@Override
	public void onResume() {
		super.onResume();
		menus.setAdapter(new RewardMenuAdapter(getActivity()));
		menus.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 1:
			Intent rewardIntent = new Intent(getActivity(), RewardActivity.class);
			getActivity().startActivity(rewardIntent);
			break;
		case 2:
			Intent historyIntent = new Intent(getActivity(), RewardHistoryActivity.class);
			getActivity().startActivity(historyIntent);
			break;
		}
	}
}
