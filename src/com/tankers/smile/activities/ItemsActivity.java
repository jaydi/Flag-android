package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.adapters.ItemAdapter;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.models.ItemParcelable;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.ItemComparator;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.SocialUtils;
import com.tankers.smile.utils.Tracker;

public class ItemsActivity extends SubCategoryActivity implements ItemAdapter.ItemImageInter {
	public static final String EXTRA_ITEMS_TYPE = "com.tankers.smile.extra.items.type";
	public static final int ITEMS_TYPE_SHOP = 1;
	public static final int ITEMS_TYPE_SHOP_PUSH = 2;
	public static final int ITEMS_TYPE_MY_LIKES = 3;
	public static final int ITEMS_TYPE_REWARD = 4;

	private int itemsType;
	private long shopId;
	private Shop shop;

	private List<Item> items;
	private ListView listItems;
	private ItemAdapter itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);
		viewId = Log.VIEW_ITEMS;

		items = new ArrayList<Item>();
		listItems = (ListView) findViewById(R.id.list_items_items);
		itemAdapter = new ItemAdapter(this, items);

		if (savedInstanceState == null) {
			itemsType = getIntent().getIntExtra(EXTRA_ITEMS_TYPE, 0);

			switch (itemsType) {
			case ITEMS_TYPE_SHOP:
				ShopParcelable shopParcelable = getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL);
				shop = shopParcelable.toShop();
				getActionBar().setTitle(shop.getName());
				setShopHeader();
				setAdapter();
				getItems(shop.getId());
				break;
			case ITEMS_TYPE_SHOP_PUSH:
				shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
				getShop();
				break;
			case ITEMS_TYPE_MY_LIKES:
				getActionBar().setTitle(ResourceUtils.getString(R.string.menu_my_likes));
				setDummyHeader();
				setAdapter();
				getItemsByUser();
				viewId = Log.VIEW_ITEMS_MY_LIKES;
				break;
			case ITEMS_TYPE_REWARD:
				getActionBar().setTitle(ResourceUtils.getString(R.string.title_activity_items_reward));
				setDummyHeader();
				setAdapter();
				getItemsByReward();
				viewId = Log.VIEW_ITEMS_REWARD;
				break;
			}
		}
	}

	private void getShop() {
		NetworkInter.getShop(new ResponseHandler<Shop>() {

			@Override
			protected void onResponse(Shop response) {
				if (response == null)
					return;

				shop = response;
				getActionBar().setTitle(shop.getName());
				setShopHeader();
				setAdapter();
				getItems(shopId);
			}

		}, LocalUser.getUser().getId(), shopId);
	}

	private void setShopHeader() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View headerView = inflater.inflate(R.layout.adapted_item_header_shop, null, false);

		ImageView imageImage = (ImageView) headerView.findViewById(R.id.image_adapted_item_header_shop_image);
		NetworkInter.getImage(null, imageImage, shop.getImageUrl(), 540, 290, true);

		TextView textDesc = (TextView) headerView.findViewById(R.id.text_adapted_item_header_shop_desc);
		textDesc.setText(""); // TODO

		listItems.addHeaderView(headerView);
	}

	private void setDummyHeader() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View headerView = inflater.inflate(R.layout.adapted_dummy_header_layout, null, false);
		listItems.addHeaderView(headerView);
	}

	private void setAdapter() {
		listItems.setAdapter(itemAdapter);
	}

	private void getItems(long shopId) {
		NetworkInter.itemList(new ResponseHandler<ItemCollection>() {

			@Override
			protected void onResponse(ItemCollection response) {
				hideLoader();

				if (response != null && response.getItems() != null) {
					items.clear();
					items.addAll(response.getItems());
				}
				refresh();
			}

		}, LocalUser.getUser().getId(), shopId);
	}

	private void getItemsByUser() {
		NetworkInter.itemListByUser(new ResponseHandler<ItemCollection>() {

			@Override
			protected void onResponse(ItemCollection response) {
				hideLoader();

				if (response != null && response.getItems() != null) {
					items.clear();
					items.addAll(response.getItems());
				}
				refresh();
			}

		}, LocalUser.getUser().getId());
	}

	private int mark = 0;

	private void getItemsByReward() {
		NetworkInter.itemListByReward(new ResponseHandler<ItemCollection>() {

			@Override
			protected void onResponse(ItemCollection response) {
				hideLoader();

				if (response != null && response.getItems() != null) {
					items.clear();
					items.addAll(response.getItems());
				}
				refresh();
				mark++;
			}

		}, LocalUser.getUser().getId(), mark);
	}

	private void hideLoader() {
		findViewById(R.id.progressbar_items_loading).setVisibility(View.GONE);
	}

	private void refresh() {
		Collections.sort(items, new ItemComparator());
		itemAdapter.notifyDataSetChanged();

		if (items.isEmpty())
			showGuides();
		else
			hideGuides();
	}

	private void showGuides() {
		if (itemsType == ITEMS_TYPE_MY_LIKES)
			findViewById(R.id.text_items_guide_no_likes).setVisibility(View.VISIBLE);
		else
			findViewById(R.id.text_items_guide_no_contents).setVisibility(View.VISIBLE);
	}

	private void hideGuides() {
		findViewById(R.id.text_items_guide_no_likes).setVisibility(View.GONE);
		findViewById(R.id.text_items_guide_no_contents).setVisibility(View.GONE);
	}

	public void shareShop(View view) {
		if (shop == null)
			return;

		SocialUtils.shareShopKakao(this, shop);

		// -- Tracking -- //
		Tracker.trackShopShare(shop.getId(), 0);
	}

	@Override
	public void onClickItemImage(Item item) {
		// -- Tracking -- //
		Tracker.trackItemView(item.getId(), 0);

		Intent intent = new Intent(this, ItemDescActivity.class);
		intent.putExtra(ItemParcelable.EXTRA_ITEM_PARCEL, item.toParcelable());

		if (itemsType == ITEMS_TYPE_SHOP || itemsType == ITEMS_TYPE_SHOP_PUSH) {
			intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
			intent.putExtra(ItemDescActivity.EXTRA_ITEM_DESC_TYPE, ItemDescActivity.ITEM_DESC_TYPE_SHOP_ITEM);
		} else if (itemsType == ITEMS_TYPE_MY_LIKES || itemsType == ITEMS_TYPE_REWARD) {
			intent.putExtra(Shop.EXTRA_SHOP_ID, item.getShopId());
			intent.putExtra(ItemDescActivity.EXTRA_ITEM_DESC_TYPE, ItemDescActivity.ITEM_DESC_TYPE_ITEM);
		} else
			return;

		startActivity(intent);
	}
}
