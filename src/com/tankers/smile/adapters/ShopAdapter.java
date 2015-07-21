package com.tankers.smile.adapters;

import java.util.List;
import java.util.Map;

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
import com.tankers.smile.activities.MapActivity;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.Tracker;

public class ShopAdapter extends BaseAdapter {
	private Context context;
	private List<Shop> shops;
	private Map<Long, Flag> flagMap;
	private ShopFlagClickInterface flagClickInter;

	public interface ShopFlagClickInterface {
		public abstract void onShopFlagClick(Flag flag);
	}

	public ShopAdapter(Context context, List<Shop> shops, boolean loading) {
		super();
		this.context = context;
		this.shops = shops;
	}

	public ShopAdapter(Context context, List<Shop> shops, Map<Long, Flag> flagMap, boolean loading) {
		super();
		this.context = context;
		this.shops = shops;
		this.flagMap = flagMap;
		flagClickInter = (ShopFlagClickInterface) context;
	}

	@Override
	public int getCount() {
		return shops.size();
	}

	@Override
	public Object getItem(int position) {
		return shops.get(position);
	}

	@Override
	public long getItemId(int position) {
		return shops.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_shop_layout, parent, false);
		final Shop shop = (Shop) getItem(position);
		if (shop == null)
			return view;
		else if (shop.isDummy())
			return loadingFooterView(inflater, convertView, parent);

		ImageView imageImage = (ImageView) view.findViewById(R.id.image_adapted_shop_image);
		NetworkInter.getImage(null, imageImage, shop.getImageUrl(), 720, 400, true);

		ImageView imageLogo = (ImageView) view.findViewById(R.id.image_adapted_shop_logo);
		NetworkInter.getImage(null, imageLogo, shop.getLogoUrl(), 0, 0, true);

		TextView textName = (TextView) view.findViewById(R.id.text_adapted_shop_name);
		textName.setText(shop.getName());

		// if (shop.isOnSale())
		// view.findViewById(R.id.text_adapted_shop_sale).setVisibility(View.VISIBLE);

		// final ImageView imageLikeIt = (ImageView) view.findViewById(R.id.image_adapted_shop_like_it);
		// final ImageView imageLikeItNot = (ImageView) view.findViewById(R.id.image_adapted_shop_like_it_not);
		// final TextView textLike = (TextView) view.findViewById(R.id.text_adapted_shop_likes);

		// if (shop.isLiked())
		// imageLikeIt.setVisibility(View.VISIBLE);
		// else
		// imageLikeItNot.setVisibility(View.VISIBLE);
		// textLike.setText("" + shop.getLikes());

		if (shop.getReward() > 0)
			if (!shop.isRewarded()) {
				view.findViewById(R.id.button_adapted_shop_checkin_reward).setVisibility(View.VISIBLE);
				TextView textReward = (TextView) view.findViewById(R.id.text_adapted_shop_reward);
				textReward.setText(shop.getReward() + ResourceUtils.getString(R.string.dal));
			} else
				view.findViewById(R.id.linear_adapted_shop_checkin_rewarded).setVisibility(View.VISIBLE);
		else
			view.findViewById(R.id.linear_adapted_shop_checkin_reward_not).setVisibility(View.VISIBLE);

		if (flagMap != null) {
			Flag flag = flagMap.get(shop.getId());
			if (flag != null) {
				TextView textDistance = (TextView) view.findViewById(R.id.text_adapted_shop_distance);
				textDistance.setText(flag.getDistanceString());
			}
		}

		// view.findViewById(R.id.button_adapted_shop_like).setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// toggleShopLike(shop);
		// toggleShopLikeView(shop, imageLikeIt, imageLikeItNot, textLike);
		// }
		//
		// });

		view.findViewById(R.id.button_adapted_shop_checkin_reward).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showCheckinGuide();
			}

		});
		view.findViewById(R.id.button_adapted_shop_map).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (flagMap != null && flagMap.get(shop.getId()) != null)
					flagClickInter.onShopFlagClick(flagMap.get(shop.getId()));
				else
					goToMap(shop);
			}

		});

		return view;
	}

	private View loadingFooterView(LayoutInflater inflater, View convertView, ViewGroup parent) {
		return inflater.inflate(R.layout.adapted_loading_footer, parent, false);
	}

	// private void toggleShopLike(Shop shop) {
	// if (shop == null)
	// return;
	//
	// if (shop.isLiked()) {
	// NetworkInter.unlikeItem(new ResponseHandler<Void>() {
	//
	// @Override
	// protected void onResponse(Void none) {
	// // nothing to do
	// }
	//
	// }, LocalUser.getUser().getId(), shop.getId(), Like.TYPE_SHOP);
	//
	// shop.setLiked(false);
	// shop.setLikes(shop.getLikes() - 1);
	// } else {
	// NetworkInter.likeItem(new ResponseHandler<Like>() {
	//
	// @Override
	// protected void onResponse(Like response) {
	// // nothing to do
	// }
	//
	// }, new Like(LocalUser.getUser().getId(), shop.getId(), Like.TYPE_SHOP));
	//
	// shop.setLiked(true);
	// shop.setLikes(shop.getLikes() + 1);
	//
	// // -- Tracking -- //
	// Tracker.trackShopLike(shop.getId(), 0);
	// }
	// }

	// private void toggleShopLikeView(Shop shop, ImageView imageLikeIt, ImageView imageLikeItNot, TextView textLike) {
	// if (shop.isLiked()) {
	// imageLikeIt.setVisibility(View.VISIBLE);
	// imageLikeItNot.setVisibility(View.GONE);
	// } else {
	// imageLikeIt.setVisibility(View.GONE);
	// imageLikeItNot.setVisibility(View.VISIBLE);
	// }
	// textLike.setText("" + shop.getLikes());
	// }

	private void showCheckinGuide() {
		DialogUtils.showCheckinGuide(context);
	}

	private void goToMap(Shop shop) {
		if (shop == null)
			return;

		// -- Tracking -- //
		Tracker.trackShopMap(shop.getId(), 0);

		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra(MapActivity.EXTRA_MAP_TYPE, MapActivity.MAP_TYPE_SHOP);
		intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		context.startActivity(intent);
	}
}
