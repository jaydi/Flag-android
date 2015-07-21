package com.tankers.smile.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.activities.JoinActivity;
import com.tankers.smile.activities.LoginActivity;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.InviteRewardValue;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.ResourceUtils;

public class MyMenuAdapter extends BaseAdapter {
	private static final int POSITION_HEADER = 0;
	private Context context;

	public MyMenuAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 7;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = null;

		if (position == POSITION_HEADER)
			view = getHeaderView(inflater, parent);
		else
			view = getMenuItemView(inflater, parent, position);

		return view;
	}

	private View getHeaderView(LayoutInflater inflater, ViewGroup parent) {
		View view = inflater.inflate(R.layout.adapted_my_menu_header, parent, false);

		if (LocalUser.getUser().isGuest()) {
			view.findViewById(R.id.linear_my_menu_header_sign).setVisibility(View.VISIBLE);
			view.findViewById(R.id.text_my_menu_header_sign_up).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					goToSignUp(v);
				}

			});
			view.findViewById(R.id.text_my_menu_header_sign_in).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					goToSignIn(v);
				}

			});
		}

		return view;
	}

	public void goToSignIn(View view) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	public void goToSignUp(View view) {
		Intent intent = new Intent(context, JoinActivity.class);
		context.startActivity(intent);
	}

	private View getMenuItemView(LayoutInflater inflater, ViewGroup parent, int position) {
		View view = inflater.inflate(R.layout.adapted_menu_layout, parent, false);
		ImageView icon = (ImageView) view.findViewById(R.id.image_adapted_menu_icon);
		TextView name = (TextView) view.findViewById(R.id.text_adapted_menu_name);

		switch (position) {
		case 1:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_my_profile));
			name.setText(R.string.menu_my_profile);
			break;
		case 2:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_my_likes));
			name.setText(R.string.menu_my_likes);
			break;
		case 3:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_my_messages));
			name.setText(R.string.menu_my_messages);
			break;
		case 4:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_invite));
			name.setText(R.string.menu_invite_friends);

			final TextView reward = (TextView) view.findViewById(R.id.text_adapted_menu_reward);
			NetworkInter.getInviteRewardValue(new ResponseHandler<InviteRewardValue>() {

				@Override
				protected void onResponse(InviteRewardValue response) {
					if (response == null)
						return;

					reward.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dal_small, 0, 0, 0);
					reward.setText(response.getValue() + ResourceUtils.getString(R.string.dal));
				}

			});
			break;
		case 5:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_settings));
			name.setText(R.string.menu_settings);
			break;
		case 6:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_help));
			name.setText(R.string.menu_help);
			break;
		}

		return view;
	}

}
