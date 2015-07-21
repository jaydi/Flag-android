package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.tankers.smile.R;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.models.FlagParcelable;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.Tracker;

public class MapActivity extends SubCategoryActivity implements OnMarkerClickListener, OnMapClickListener {
	public static final String EXTRA_MAP_TYPE = "com.tankres.smile.extra.map.type";
	public static final int MAP_TYPE_SHOP = 1;
	public static final int MAP_TYPE_ITEM = 2;
	public static final int MAP_TYPE_REWARD = 3;

	public static final int FLAG_LIST_REQUEST = 1;

	private int mapType;
	private List<Shop> shops;
	private Shop shop;
	private long itemId;

	private GoogleMap map;
	private Map<Marker, Flag> markerMap;
	private List<Flag> flags;
	private Flag targetFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		viewId = Log.VIEW_MAP;

		shops = new ArrayList<Shop>();
		markerMap = new HashMap<Marker, Flag>();
		flags = new ArrayList<Flag>();

		if (savedInstanceState == null) {
			mapType = getIntent().getIntExtra(EXTRA_MAP_TYPE, 0);

			switch (mapType) {
			case MAP_TYPE_SHOP:
				shop = ((ShopParcelable) getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL)).toShop();

				viewId = Log.VIEW_MAP_SHOP;
				getActionBar().setTitle(shop.getName());
				break;

			case MAP_TYPE_ITEM:
				shop = ((ShopParcelable) getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL)).toShop();
				itemId = getIntent().getLongExtra(Item.EXTRA_ITEM_ID, 0);

				viewId = Log.VIEW_MAP_ITEM;
				getActionBar().setTitle(shop.getName());
				break;

			case MAP_TYPE_REWARD:
				List<ShopParcelable> shopParcelables = getIntent().getParcelableArrayListExtra(ShopParcelable.EXTRA_SHOP_PARCEL_LIST);
				for (ShopParcelable shopParcelable : shopParcelables)
					shops.add(shopParcelable.toShop());

				List<FlagParcelable> flagParcelables = getIntent().getParcelableArrayListExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST);
				for (FlagParcelable flagParcelable : flagParcelables)
					flags.add(flagParcelable.toFlag());

				FlagParcelable flagParcelable = getIntent().getParcelableExtra(FlagParcelable.EXTRA_FLAGPARCELABLE);
				if (flagParcelable != null)
					targetFlag = flagParcelable.toFlag();

				viewId = Log.VIEW_MAP_REWARD;
				getActionBar().setTitle(ResourceUtils.getString(R.string.title_activity_map_reward));
				break;
			}

			setUpMap();
		}
	}

	@Override
	public void onBackPressed() {
		if (targetFlag != null)
			onMapClick(null);
		else
			super.onBackPressed();
	}

	private void setUpMap() {
		if (map == null)
			map = getMap();

		map.setMyLocationEnabled(true);
		map.setOnMarkerClickListener(this);
		map.setOnMapClickListener(this);

		initialize();
	}

	private GoogleMap getMap() {
		return ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map_map)).getMap();
	}

	private void initialize() {
		Location location = DalshopApplication.getInstance().getLocationUtils().getLastLocation();
		if (location != null)
			setCenter(location.getLatitude(), location.getLongitude());

		switch (mapType) {
		case MAP_TYPE_SHOP:
			getShopFlags();
			break;
		case MAP_TYPE_ITEM:
			getItemFlags();
			break;
		case MAP_TYPE_REWARD:
			showFlags(flags);
			if (targetFlag != null) {
				Marker marker = focusFlag(targetFlag.getId());
				map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 17));
			}
			break;
		}
	}

	private void setCenter(double lat, double lon) {
		if (mapType == MAP_TYPE_REWARD)
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 10));
		else
			map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
	}

	private void getShopFlags() {
		NetworkInter.getFlagListByShop(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null)
					return;

				removeFlags();
				showFlags(response.getFlags());
			}

		}, shop.getId());
	}

	private void getItemFlags() {
		NetworkInter.getFlagListByItem(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null)
					return;

				removeFlags();
				showFlags(response.getFlags());
			}
		}, itemId);
	}

	private void removeFlags() {
		markerMap.clear();
		map.clear();
	}

	private void showFlags(List<Flag> flags) {
		for (Flag flag : flags)
			markerMap.put(map.addMarker(flag.toMarkerOptions()), flag);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		unfocusMarker();
		focusMarker(marker);
		showDetails(marker);
		return true;
	}

	private void unfocusMarker() {
		Marker marker = null;
		if (targetFlag != null)
			marker = getMarkerWithFlagId(targetFlag.getId());
		if (marker != null)
			marker.setIcon(markerMap.get(marker).getIcon(false));
	}

	private Marker getMarkerWithFlagId(long flagId) {
		for (Entry<Marker, Flag> entry : markerMap.entrySet())
			if (entry.getValue().getId() == flagId)
				return entry.getKey();
		return null;
	}

	private void focusMarker(Marker marker) {
		marker.setIcon(markerMap.get(marker).getIcon(true));
	}

	private Marker focusFlag(long flagId) {
		Marker marker = getMarkerWithFlagId(flagId);
		if (marker != null)
			onMarkerClick(marker);
		return marker;
	}

	private void showDetails(Marker marker) {
		final Flag flag = markerMap.get(marker);
		targetFlag = flag;

		View infoView = findViewById(R.id.linear_map_flaginfo);
		infoView.setVisibility(View.VISIBLE);

		ImageView imageLogo = (ImageView) findViewById(R.id.image_map_flag_logo);
		imageLogo.setImageBitmap(null);

		View loader = findViewById(R.id.progressbar_map_flag_logo);
		loader.setVisibility(View.VISIBLE);

		switch (mapType) {
		case MAP_TYPE_SHOP:
			showShopDetails(shop);
			break;
		case MAP_TYPE_ITEM:
			showShopDetails(shop);
			break;
		case MAP_TYPE_REWARD:
			showShopDetails(getShopFromList());
			break;
		}
	}

	private Shop getShopFromList() {
		if (shops == null || targetFlag == null)
			return null;

		for (Shop shop : shops)
			if (shop.getId() == targetFlag.getShopId())
				return shop;

		return null;
	}

	private void showShopDetails(Shop shop) {
		if (shop == null)
			return;

		ImageView imageLogo = (ImageView) findViewById(R.id.image_map_flag_logo);
		View loader = findViewById(R.id.progressbar_map_flag_logo);
		NetworkInter.getImage(loader, imageLogo, shop.getLogoUrl(), 0, 0, true);

		TextView textName = (TextView) findViewById(R.id.text_map_flag_name);
		if (targetFlag != null)
			textName.setText(targetFlag.getShopName());
		else
			textName.setText(shop.getName());

		View viewRewardBox = findViewById(R.id.linear_map_flag_reward_box);
		if (shop.getReward() > 0 && !shop.isRewarded()) {
			viewRewardBox.setVisibility(View.VISIBLE);
			TextView textReward = (TextView) findViewById(R.id.text_map_flag_reward);
			textReward.setText("" + shop.getReward());
		} else
			viewRewardBox.setVisibility(View.GONE);

		View viewSale = findViewById(R.id.text_map_flag_sale);
		if (shop.isOnSale())
			viewSale.setVisibility(View.VISIBLE);
		else
			viewSale.setVisibility(View.GONE);
	}

	@Override
	public void onMapClick(LatLng latLng) {
		unfocusMarker();
		hideDetails();
	}

	private void hideDetails() {
		View infoView = findViewById(R.id.linear_map_flaginfo);
		infoView.setVisibility(View.GONE);
		targetFlag = null;
	}

	public void goToFlagList(View view) {
		if (mapType == MAP_TYPE_REWARD)
			super.onBackPressed();
		else {
			Intent intent = new Intent(this, FlagListActivity.class);
			intent.putParcelableArrayListExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST, getFlagParcelables());
			intent.putParcelableArrayListExtra(ShopParcelable.EXTRA_SHOP_PARCEL_LIST, getShopParcelables());
			if (shop != null)
				intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
			startActivityForResult(intent, FLAG_LIST_REQUEST);
		}
	}

	private ArrayList<FlagParcelable> getFlagParcelables() {
		ArrayList<FlagParcelable> flagParcelables = new ArrayList<FlagParcelable>();
		for (Flag flag : markerMap.values())
			flagParcelables.add(flag.toParcelable());
		return flagParcelables;
	}

	private ArrayList<ShopParcelable> getShopParcelables() {
		ArrayList<ShopParcelable> shopParcelables = new ArrayList<ShopParcelable>();
		for (Shop shop : shops)
			shopParcelables.add(shop.toParcelable());
		return shopParcelables;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == FLAG_LIST_REQUEST) {
			long flagId = data.getLongExtra(Flag.EXTRA_FLAG_ID, 0);
			if (flagId != 0)
				focusFlag(flagId);
		}
	}

	public void goToShop(View view) {
		Shop shop = null;

		switch (mapType) {
		case MAP_TYPE_SHOP:
			return;
		case MAP_TYPE_ITEM:
			shop = this.shop;
			break;
		case MAP_TYPE_REWARD:
			DialogUtils.showCheckinGuide(this);
			return;
		}

		if (shop == null)
			return;

		// -- Tracking -- //
		Tracker.trackShopView(shop.getId(), 0);

		Intent intent = new Intent(this, ItemsActivity.class);
		intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP);
		intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		startActivity(intent);
	}
}
