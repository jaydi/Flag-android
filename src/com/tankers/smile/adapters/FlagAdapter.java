package com.tankers.smile.adapters;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.Shop;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.utils.StringUtils;

public class FlagAdapter extends BaseAdapter {
	private Context context;
	private List<Flag> flags;
	private Map<Long, Shop> shopMap;

	public FlagAdapter(Context context, List<Flag> flags, Map<Long, Shop> shopMap) {
		super();
		this.context = context;
		this.flags = flags;
		this.shopMap = shopMap;
	}

	@Override
	public int getCount() {
		return flags.size();
	}

	@Override
	public Object getItem(int position) {
		return flags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return flags.get(position).getId();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Flag flag = (Flag) getItem(position);
		Shop shop = shopMap.get(flag.getShopId());
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_flag_layout, parent, false);

		if (flag == null || shop == null)
			return view;

		ImageView imageLogo = (ImageView) view.findViewById(R.id.image_flag_logo);
		NetworkInter.getImage(null, imageLogo, shop.getLogoUrl(), 0, 0, true);

		TextView textName = (TextView) view.findViewById(R.id.text_flag_name);
		textName.setText(flag.getShopName());

		if (shop.getReward() > 0) {
			View viewRewardBox = view.findViewById(R.id.linear_flag_reward_box);
			viewRewardBox.setVisibility(View.VISIBLE);
			TextView textReward = (TextView) view.findViewById(R.id.text_flag_reward);
			textReward.setText("" + shop.getReward());
		}

		if (shop.isOnSale())
			view.findViewById(R.id.image_flag_sale).setVisibility(View.VISIBLE);

		TextView textDescription = (TextView) view.findViewById(R.id.text_flag_description);
		textDescription.setText(StringUtils.shortifyCleverly(shop.getDescription()));

		TextView textDistance = (TextView) view.findViewById(R.id.text_flag_distance);
		textDistance.setText(flag.getDistanceString());

		return view;
	}

}
