package com.tankers.smile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.tankers.smile.R;
import com.tankers.smile.activities.ItemsActivity;
import com.tankers.smile.activities.LoginActivity;
import com.tankers.smile.activities.MyPageActivity;
import com.tankers.smile.activities.SettingsActivity;
import com.tankers.smile.adapters.MyMenuAdapter;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.SocialUtils;
import com.tankers.smile.utils.ToastUtils;
import com.tankers.smile.utils.Tracker;

public class MyMenuFragment extends Fragment implements OnItemClickListener {
	private FrameLayout myMenus;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		myMenus = (FrameLayout) inflater.inflate(R.layout.slide_my_menu, null, false);

		ListView menus = (ListView) myMenus.findViewById(R.id.list_slide_my_menu_menus);
		menus.setAdapter(new MyMenuAdapter(getActivity()));
		menus.setOnItemClickListener(this);

		return myMenus;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 1:
			if (LocalUser.getUser().isGuest()) {
				Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
				getActivity().startActivity(loginIntent);
			} else {
				Intent userInfoIntent = new Intent(getActivity(), MyPageActivity.class);
				getActivity().startActivity(userInfoIntent);
			}
			break;
		case 2:
			Intent myLikesIntent = new Intent(getActivity(), ItemsActivity.class);
			myLikesIntent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_MY_LIKES);
			getActivity().startActivity(myLikesIntent);
			break;
		case 3:
			ToastUtils.show(R.string.message_not_ready_yet);
			break;
		case 4:
			if (isFirstInvite()) {
				ToastUtils.showLong(R.string.guide_invitation);
				nowNotFirstInvite();
			}
			SocialUtils.inviteFriendKakao(getActivity());

			// -- Tracking -- //
			Tracker.trackAppShare(0, 0);
			break;
		case 5:
			Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
			getActivity().startActivity(settingsIntent);
			break;
		case 6:
			DialogUtils.showFeedbackDialog(getActivity());
			break;
		}
	}

	private boolean isFirstInvite() {
		SharedPreferences pref = getActivity().getSharedPreferences("invite_time" + LocalUser.getUser().getId(), Context.MODE_PRIVATE);
		return pref.getBoolean("isFirstTime", true);
	}

	private void nowNotFirstInvite() {
		SharedPreferences pref = getActivity().getSharedPreferences("invite_time" + LocalUser.getUser().getId(), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("isFirstTime", false);
		editor.commit();
	}
}
