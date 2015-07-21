package com.tankers.smile.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.Like;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.Tracker;

public class ItemAdapter extends BaseAdapter {
	private Context context;
	private ItemImageInter itemImageInter;
	private List<Item> items;

	public interface ItemImageInter {
		public abstract void onClickItemImage(Item item);
	}

	public ItemAdapter(Context context, List<Item> items) {
		super();
		this.context = context;
		this.itemImageInter = (ItemImageInter) context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return Math.round(((float) items.size() / 2f));
	}

	@Override
	public Object getItem(int position) {
		return items.get((position) * 2);
	}

	@Override
	public long getItemId(int position) {
		return items.get((position) * 2).getId();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = null;

		if (items.get(position * 2).isDummy())
			return loadingFooterView(inflater, convertView, parent);

		view = inflater.inflate(R.layout.adapted_item_layout, parent, false);

		view = setFirstItemView(view, items.get((position) * 2));
		if (position != getCount() - 1 || items.size() % 2 == 0)
			view = setSecondItemView(view, items.get((position) * 2 + 1));

		return view;
	}

	private View loadingFooterView(LayoutInflater inflater, View convertView, ViewGroup parent) {
		return inflater.inflate(R.layout.adapted_loading_footer, parent, false);
	}

	private View setFirstItemView(View view, final Item item) {
		ImageView imageProfile = (ImageView) view.findViewById(R.id.image_item_profile_1);
		NetworkInter.getImage(null, imageProfile, item.getThumbnailUrl(), 180, 225, true);

		imageProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				itemImageInter.onClickItemImage(item);
			}

		});

		if (item.getSale() > 0)
			view.findViewById(R.id.image_item_sale_1).setVisibility(View.VISIBLE);

		final TextView textLikeIt = (TextView) view.findViewById(R.id.text_item_like_it_1);
		final View viewLikeIt = view.findViewById(R.id.image_item_like_it_1);
		final View viewLikeItNot = view.findViewById(R.id.image_item_like_it_not_1);
		if (item.isLiked())
			viewLikeIt.setVisibility(View.VISIBLE);
		else
			viewLikeItNot.setVisibility(View.VISIBLE);
		textLikeIt.setText("" + item.getLikes());

		View viewLikeItBox = view.findViewById(R.id.linear_item_like_it_box_1);
		viewLikeItBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleItemLike(item);
				toggleItemLikeView(item, viewLikeIt, viewLikeItNot, textLikeIt);

			}

		});

		if (item.getReward() > 0)
			if (item.isRewarded()) {
				View viewScanned = view.findViewById(R.id.image_item_scanned_1);
				viewScanned.setVisibility(View.VISIBLE);
			} else {
				View viewScannable = view.findViewById(R.id.image_item_scannable_1);
				viewScannable.setVisibility(View.VISIBLE);
			}

		return view;
	}

	private View setSecondItemView(View view, final Item item) {
		view.findViewById(R.id.linear_adapted_item_layout_second).setVisibility(View.VISIBLE);

		ImageView imageProfile = (ImageView) view.findViewById(R.id.image_item_profile_2);
		NetworkInter.getImage(null, imageProfile, item.getThumbnailUrl(), 240, 300, true);

		imageProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				itemImageInter.onClickItemImage(item);
			}

		});

		if (item.getSale() > 0)
			view.findViewById(R.id.image_item_sale_2).setVisibility(View.VISIBLE);

		final TextView textLikeIt = (TextView) view.findViewById(R.id.text_item_like_it_2);
		final View viewLikeIt = view.findViewById(R.id.image_item_like_it_2);
		final View viewLikeItNot = view.findViewById(R.id.image_item_like_it_not_2);
		if (item.isLiked())
			viewLikeIt.setVisibility(View.VISIBLE);
		else
			viewLikeItNot.setVisibility(View.VISIBLE);
		textLikeIt.setText("" + item.getLikes());

		View viewLikeItBox = view.findViewById(R.id.linear_item_like_it_box_2);
		viewLikeItBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleItemLike(item);
				toggleItemLikeView(item, viewLikeIt, viewLikeItNot, textLikeIt);

			}

		});

		if (item.getReward() > 0)
			if (item.isRewarded()) {
				View viewScanned = view.findViewById(R.id.image_item_scanned_2);
				viewScanned.setVisibility(View.VISIBLE);
			} else {
				View viewScannable = view.findViewById(R.id.image_item_scannable_2);
				viewScannable.setVisibility(View.VISIBLE);
			}

		return view;
	}

	private void toggleItemLike(Item item) {
		if (item.isLiked()) {
			NetworkInter.unlikeItem(new ResponseHandler<Void>() {

				@Override
				protected void onResponse(Void none) {
					// nothing to do
				}

			}, LocalUser.getUser().getId(), item.getId(), Like.TYPE_ITEM);

			item.setLiked(false);
			item.setLikes(item.getLikes() - 1);
		} else {
			NetworkInter.likeItem(new ResponseHandler<Like>() {

				@Override
				protected void onResponse(Like response) {
					// nothing to do
				}

			}, new Like(LocalUser.getUser().getId(), item.getId(), Like.TYPE_ITEM));

			item.setLiked(true);
			item.setLikes(item.getLikes() + 1);

			// -- Tracking -- //
			Tracker.trackItemLike(item.getId(), 0);
		}
	}

	protected void toggleItemLikeView(Item item, View viewLikeIt, View viewLikeItNot, TextView textLikeIt) {
		if (item.isLiked()) {
			viewLikeIt.setVisibility(View.VISIBLE);
			viewLikeItNot.setVisibility(View.GONE);
		} else {
			viewLikeIt.setVisibility(View.GONE);
			viewLikeItNot.setVisibility(View.VISIBLE);
		}
		textLikeIt.setText("" + item.getLikes());
	}

}
