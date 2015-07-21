package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tankers.smile.R;
import com.tankers.smile.adapters.ShopAdapter;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.comparators.ShopDistanceComparator;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagParcelable;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopCollection;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.ResourceUtils;

public class ShopsActivity extends SubCategoryActivity implements ShopAdapter.ShopFlagClickInterface {
	public static final String EXTRA_SHOPS_TYPE = "com.tankers.smile.extra.shops.type";
	public static final int SHOPS_TYPE_REWARD = 1;

	private int shopsType;

	private List<Shop> shops;
	private List<Flag> flags;
	private Map<Long, Flag> flagMap;
	private PullToRefreshListView listShops;
	private ShopAdapter shopAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shops);
		viewId = Log.VIEW_SHOPS;

		shops = new ArrayList<Shop>();
		flags = new ArrayList<Flag>();
		flagMap = new HashMap<Long, Flag>();
		listShops = (PullToRefreshListView) findViewById(R.id.list_shops_shops);
		shopAdapter = new ShopAdapter(this, shops, flagMap, false);
		listShops.setAdapter(shopAdapter);
		listShops.setMode(Mode.GOOGLE_STYLE);
		listShops.setFriction(8.0f);
//		listShops.setOnItemClickListener(this);

		if (savedInstanceState == null) {
			shopsType = getIntent().getIntExtra(EXTRA_SHOPS_TYPE, 0);

			switch (shopsType) {
			case SHOPS_TYPE_REWARD:
				getActionBar().setTitle(ResourceUtils.getString(R.string.title_activity_map_reward));
				getRewardShops();
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		switch (shopsType) {
		case SHOPS_TYPE_REWARD:
			getMenuInflater().inflate(R.menu.shops_reward, menu);
			return true;
		default:
			return super.onCreateOptionsMenu(menu);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_reward_map:
			Intent intent = new Intent(this, MapActivity.class);
			intent.putExtra(MapActivity.EXTRA_MAP_TYPE, MapActivity.MAP_TYPE_REWARD);
			intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL_LIST, getShopParcelables());
			intent.putExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST, getFlagParcelables());
			startActivity(intent);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private int rewardShopMarker = 0;

	private void getRewardShops() {
		NetworkInter.getShopListByReward(new ResponseHandler<ShopCollection>() {

			@Override
			protected void onResponse(ShopCollection response) {
				hideLoader();
				if (response == null || response.getShops() == null || response.getFlags() == null)
					return;

				shops.addAll(response.getShops());
				flags.addAll(response.getFlags());
				arrangeData();

				refreshRewardShops();
			}

		}, LocalUser.getUser().getId(), rewardShopMarker);
	}

	private void hideLoader() {
		findViewById(R.id.progressbar_shops_loading).setVisibility(View.GONE);
	}

	private void arrangeData() {
		final Location location = DalshopApplication.getInstance().getLocationUtils().getLastLocation();

		if (location != null)
			for (Flag flag : flags)
				flag.calDistance(location.getLatitude(), location.getLongitude());

		for (Shop shop : shops)
			for (Flag flag : flags)
				if (shop.getId() == flag.getShopId()) {
					flagMap.put(shop.getId(), flag);
					break;
				}

		List<Shop> branches = new ArrayList<Shop>();
		for (Shop shop : shops)
			if (shop.getType() == Shop.TYPE_HQ) {
				for (Shop branch : shops)
					if (branch.getParentId() == shop.getId()) {
						shop.addBranch(branch);
						branches.add(branch);
					}

				if (shop.getBranches() != null)
					flagMap.put(shop.getId(), shop.nearestBranchFlag(flagMap));
			}
		shops.removeAll(branches);

		Collections.sort(shops, new ShopDistanceComparator(flagMap));
	}

	private void refreshRewardShops() {
		shopAdapter.notifyDataSetChanged();
		rewardShopMarker++;
	}

	@Override
	public void onShopFlagClick(Flag flag) {
		Intent intent = new Intent(this, MapActivity.class);
		intent.putExtra(MapActivity.EXTRA_MAP_TYPE, MapActivity.MAP_TYPE_REWARD);
		intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL_LIST, getShopParcelables());
		intent.putExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST, getFlagParcelables());
		intent.putExtra(FlagParcelable.EXTRA_FLAGPARCELABLE, flag.toParcelable());
		startActivity(intent);
	}

	private ArrayList<ShopParcelable> getShopParcelables() {
		ArrayList<ShopParcelable> shopParcelables = new ArrayList<ShopParcelable>();

		for (Shop shop : shops)
			if (shop.getType() == Shop.TYPE_HQ && shop.getBranches() != null)
				for (Shop branch : shop.getBranches())
					shopParcelables.add(branch.toParcelable());
			else
				shopParcelables.add(shop.toParcelable());

		return shopParcelables;
	}

	private ArrayList<FlagParcelable> getFlagParcelables() {
		ArrayList<FlagParcelable> flagParcelables = new ArrayList<FlagParcelable>();
		for (Flag flag : flags)
			flagParcelables.add(flag.toParcelable());
		return flagParcelables;
	}

	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// Shop shop = (Shop) shopAdapter.getItem(position - 1);
	//
	// // -- Tracking -- //
	// Tracker.trackShopView(shop.getId(), 0);
	//
	// Intent intent = new Intent(this, ItemsActivity.class);
	// intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP);
	// intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
	// startActivity(intent);
	// }

}
