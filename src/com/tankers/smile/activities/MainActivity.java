package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tankers.smile.R;
import com.tankers.smile.adapters.ItemAdapter;
import com.tankers.smile.adapters.ShopAdapter;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.fragments.MyMenuFragment;
import com.tankers.smile.fragments.RewardMenuFragment;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.models.ItemParcelable;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopCollection;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.IBeaconUtils;
import com.tankers.smile.utils.Tracker;

public class MainActivity extends SlidingFragmentActivity implements TabListener, ItemAdapter.ItemImageInter, OnItemClickListener {
	private static final int POSITION_SHOP = 0;
	private static final int POSITION_ITEM = 1;

	private long viewId;
	private long previewId;
	private EasyTracker easyTracker;

	private List<Item> items;
	private ItemAdapter itemAdapter;
	private PullToRefreshListView listItems;

	private int itemMarker = 0;
	private long itemRefreshMarker = 0;

	private List<Shop> shops;
	private ShopAdapter shopAdapter;
	private PullToRefreshListView listShops;

	private int shopMarker = 0;
	private long shopRefreshMarker = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewId = Log.VIEW_MAIN;

		items = new ArrayList<Item>();
		itemAdapter = new ItemAdapter(this, items);
		listItems = (PullToRefreshListView) findViewById(R.id.grid_main_items);
		addItemsDummyHeader();
		listItems.setAdapter(itemAdapter);
		listItems.setMode(Mode.GOOGLE_STYLE);
		listItems.setFriction(8.0f);
		listItems.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				itemMarker = 0;
				itemRefreshMarker = 0;
				items.clear();
				loadItems();
			}

		});
		listItems.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				loadItems();
			}

		});

		shops = new ArrayList<Shop>();
		shopAdapter = new ShopAdapter(this, shops, true);
		listShops = (PullToRefreshListView) findViewById(R.id.list_main_shops);
		listShops.setAdapter(shopAdapter);
		listShops.setMode(Mode.GOOGLE_STYLE);
		listShops.setFriction(8.0f);
		listShops.setOnItemClickListener(this);
		listShops.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				shopMarker = 0;
				shopRefreshMarker = 0;
				shops.clear();
				loadShops();
			}

		});
		listShops.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				loadShops();
			}

		});

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setLogo(R.drawable.ic_slide);
		setUpMenu();
		setUpTabs();
	}

	private void addItemsDummyHeader() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View headerView = inflater.inflate(R.layout.adapted_dummy_header_layout, null, false);
		ListView lv = listItems.getRefreshableView();
		lv.addHeaderView(headerView);
	}

	private void setUpMenu() {
		setBehindContentView(R.layout.frame_my_menu);
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_my_menu_frame, new MyMenuFragment()).commit();

		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		menu.setSecondaryMenu(R.layout.frame_reward_menu);
		menu.setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction().replace(R.id.frame_reward_menu_frame, new RewardMenuFragment()).commit();

		menu.setOnOpenListener(new OnOpenListener() {

			@Override
			public void onOpen() {
				// -- Tracking -- //
				Tracker.trackViewStop(viewId, 0);
				previewId = viewId;
				viewId = Log.VIEW_MAIN_MY_MENU;
				Tracker.trackViewStart(viewId, 0);
			}

		});
		menu.setSecondaryOnOpenListner(new OnOpenListener() {

			@Override
			public void onOpen() {
				// -- Tracking -- //
				Tracker.trackViewStop(viewId, 0);
				previewId = viewId;
				viewId = Log.VIEW_MAIN_REWARD_MENU;
				Tracker.trackViewStart(viewId, 0);
			}

		});
		menu.setOnCloseListener(new OnCloseListener() {

			@Override
			public void onClose() {
				// -- Tracking -- //
				Tracker.trackViewStop(viewId, 0);
				viewId = previewId;
				Tracker.trackViewStart(viewId, 0);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getSlidingMenu().toggle();
			return true;
		case R.id.action_reward:
			if (getSlidingMenu().isSecondaryMenuShowing())
				getSlidingMenu().showContent();
			else
				getSlidingMenu().showSecondaryMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void setUpTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText(R.string.shop).setTabListener(this), false);
		actionBar.addTab(actionBar.newTab().setText(R.string.item).setTabListener(this), true);
		actionBar.setSelectedNavigationItem(0);
	}

	private int resumeCount = 0;

	@Override
	public void onResume() {
		super.onResume();
		bindLocationUtils();
		bindBeaconUtils();

		// -- Tracking -- //
		if (resumeCount > 0)
			Tracker.trackViewStart(viewId, 0);
		resumeCount++;
	}

	private void bindLocationUtils() {
		DalshopApplication.getInstance().initLocationUtils();
	}

	private void bindBeaconUtils() {
		if (IBeaconUtils.verifyBluetooth(this))
			DalshopApplication.getInstance().bindBeaconUtils();
	}

	@Override
	public void onPause() {
		super.onPause();

		// -- Tracking - //
		Tracker.trackViewStop(viewId, 0);
	}

	@Override
	public void onStart() {
		super.onStart();
		easyTracker = EasyTracker.getInstance(this);
		easyTracker.activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (easyTracker != null)
			easyTracker.activityStop(this);
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		switch (tab.getPosition()) {
		case POSITION_ITEM:
			showItemLoader();
			findViewById(R.id.grid_main_items).setVisibility(View.VISIBLE);
			if (itemRefreshMarker < new Date().getTime() - 1000 * 60 * 30)
				loadItems();

			// -- Tracking -- //
			viewId = Log.VIEW_MAIN_ITEMS;
			Tracker.trackViewStart(viewId, 0);
			break;
		case POSITION_SHOP:
			showShopLoader();
			findViewById(R.id.list_main_shops).setVisibility(View.VISIBLE);
			if (shopRefreshMarker < new Date().getTime() - 1000 * 60 * 30)
				loadShops();

			// -- Tracking -- //
			viewId = Log.VIEW_MAIN_SHOPS;
			Tracker.trackViewStart(viewId, 0);
			break;
		}
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		switch (tab.getPosition()) {
		case POSITION_ITEM:
			hideItemLoader();
			findViewById(R.id.grid_main_items).setVisibility(View.GONE);

			// -- Tracking -- //
			Tracker.trackViewStop(Log.VIEW_MAIN_ITEMS, 0);
			break;
		case POSITION_SHOP:
			hideShopLoader();
			findViewById(R.id.list_main_shops).setVisibility(View.GONE);

			// -- Tracking -- //
			Tracker.trackViewStop(Log.VIEW_MAIN_SHOPS, 0);
			break;
		}
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	}

	public void showItemLoader() {
		if (items == null || items.isEmpty())
			findViewById(R.id.linear_main_loading_items).setVisibility(View.VISIBLE);
	}

	public void hideItemLoader() {
		findViewById(R.id.linear_main_loading_items).setVisibility(View.GONE);
	}

	public void showShopLoader() {
		if (shops == null || shops.isEmpty())
			findViewById(R.id.linear_main_loading_shops).setVisibility(View.VISIBLE);
	}

	public void hideShopLoader() {
		findViewById(R.id.linear_main_loading_shops).setVisibility(View.GONE);
	}

	private boolean itemLoadSign = false;
	private Item loadingItemDummy = new Item();

	private void loadItems() {
		if (!itemLoadSign) {
			itemLoadSign = true;

			if (items.size() > 21) {
				for (int i = 0; i < 20; i++) {
					try {
						items.remove(0);
					} catch (IndexOutOfBoundsException e) {
						break;
					}
				}
				refreshItems();
			}

			NetworkInter.initItems(new ResponseHandler<ItemCollection>() {

				@Override
				protected void onResponse(ItemCollection response) {
					hideItemLoader();
					itemLoadSign = false;
					listItems.onRefreshComplete();
					items.remove(loadingItemDummy);

					if (response == null || response.getItems() == null)
						return;

					items.addAll(response.getItems());
					if (!response.getItems().isEmpty())
						items.add(loadingItemDummy);
					refreshItems();
				}

			}, LocalUser.getUser().getId(), itemMarker);
		}
	}

	private void refreshItems() {
		itemAdapter.notifyDataSetChanged();
		itemMarker++;
		itemRefreshMarker = new Date().getTime();
	}

	@Override
	public void onClickItemImage(Item item) {
		// -- Tracking -- //
		Tracker.trackItemView(item.getId(), 0);

		Intent intent = new Intent(this, ItemDescActivity.class);
		intent.putExtra(ItemDescActivity.EXTRA_ITEM_DESC_TYPE, ItemDescActivity.ITEM_DESC_TYPE_ITEM);
		intent.putExtra(ItemParcelable.EXTRA_ITEM_PARCEL, item.toParcelable());
		intent.putExtra(Shop.EXTRA_SHOP_ID, item.getShopId());
		startActivity(intent);
	}

	private boolean shopLoadSign = false;
	private Shop loadingShopDummy = new Shop();

	private void loadShops() {
		if (!shopLoadSign) {
			shopLoadSign = true;

			NetworkInter.startShops(new ResponseHandler<ShopCollection>() {

				@Override
				protected void onResponse(ShopCollection response) {
					hideShopLoader();
					shopLoadSign = false;
					listShops.onRefreshComplete();
					shops.remove(loadingShopDummy);

					if (response == null || response.getShops() == null)
						return;

					shops.addAll(response.getShops());

					final Map<String, Integer> pointMap = response.getPointMap();
					if (pointMap != null)
						Collections.sort(shops, new Comparator<Shop>() {

							@Override
							public int compare(Shop lhs, Shop rhs) {
								int lp = 0;
								int rp = 0;

								if (pointMap.get(String.valueOf(lhs.getId())) != null)
									lp = pointMap.get(String.valueOf(lhs.getId()));
								if (pointMap.get(String.valueOf(rhs.getId())) != null)
									rp = pointMap.get(String.valueOf(rhs.getId()));

								if (lp > rp)
									return -1;
								else if (lp < rp)
									return 1;
								else
									return 0;
							}

						});

					if (!response.getShops().isEmpty())
						shops.add(loadingShopDummy);
					refreshShops();
				}

			}, LocalUser.getUser().getId(), shopMarker);
		}
	}

	private void refreshShops() {
		shopAdapter.notifyDataSetChanged();
		shopMarker++;
		shopRefreshMarker = new Date().getTime();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Shop shop = (Shop) shopAdapter.getItem(position - 1);

		// -- Tracking -- //
		Tracker.trackShopView(shop.getId(), 0);

		Intent intent = new Intent(this, ItemsActivity.class);
		intent.putExtra(ItemsActivity.EXTRA_ITEMS_TYPE, ItemsActivity.ITEMS_TYPE_SHOP);
		intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		startActivity(intent);
	}
}
