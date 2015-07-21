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
import com.tankers.smile.activities.ShopsActivity;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.utils.ResourceUtils;

public class RewardMenuAdapter extends BaseAdapter {
	private static final int POSITION_HEADER = 0;
	private Context context;

	public RewardMenuAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return 3;
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
		View view = inflater.inflate(R.layout.adapted_reward_menu_header, parent, false);

		TextView textReward = (TextView) view.findViewById(R.id.text_adapted_reward_info_dal);
		textReward.setText(LocalUser.getUser().getReward() + ResourceUtils.getString(R.string.dal));

		view.findViewById(R.id.image_adapted_reward_menu_header_checkin).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToCheckinRewardSearch(v);
			}

		});

		return view;
	}

	private void goToCheckinRewardSearch(View view) {
		Intent intent = new Intent(context, ShopsActivity.class);
		intent.putExtra(ShopsActivity.EXTRA_SHOPS_TYPE, ShopsActivity.SHOPS_TYPE_REWARD);
		context.startActivity(intent);
	}

	// private void goToRewardList(View view) {
	// Intent intent = new Intent(context, ItemsActivity.class);
	// intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_REWARD);
	// context.startActivity(intent);
	// }

	private View getMenuItemView(LayoutInflater inflater, ViewGroup parent, int position) {
		View view = inflater.inflate(R.layout.adapted_menu_layout, parent, false);
		ImageView icon = (ImageView) view.findViewById(R.id.image_adapted_menu_icon);
		TextView name = (TextView) view.findViewById(R.id.text_adapted_menu_name);

		switch (position) {
		case 1:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_dalstore));
			name.setText(R.string.menu_dalstore);
			break;
		case 2:
			icon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_menu_history));
			name.setText(R.string.menu_history);
			break;
		}

		return view;
	}

}
