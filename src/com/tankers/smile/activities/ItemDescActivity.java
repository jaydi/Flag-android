package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.adapters.ItemAdapter;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.ItemCollection;
import com.tankers.smile.models.ItemParcelable;
import com.tankers.smile.models.Like;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Reward;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopCollection;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.models.User;
import com.tankers.smile.services.DBInter;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.LocationUtils;
import com.tankers.smile.utils.PushUtils;
import com.tankers.smile.utils.ResourceUtils;
import com.tankers.smile.utils.SocialUtils;
import com.tankers.smile.utils.ToastUtils;
import com.tankers.smile.utils.Tracker;

public class ItemDescActivity extends SubCategoryActivity implements ItemAdapter.ItemImageInter {
	public static final String EXTRA_ITEM_DESC_TYPE = "com.tankers.smile.extra.itemdesc.type";
	public static final int ITEM_DESC_TYPE_ITEM = 1;
	public static final int ITEM_DESC_TYPE_SHOP_ITEM = 2;
	public static final int ITEM_DESC_TYPE_ITEM_PUSH = 3;

	private static final int ITEM_SCAN_REQUEST = 100;

	private int itemDescType;
	private long itemId;
	private Item item;
	private long shopId;
	private Shop shop;

	private List<Item> recItems;
	private ListView listRecItems;
	private ItemAdapter recItemAdapter;
	private View itemDescHeaderView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_desc);
		viewId = Log.VIEW_ITEM_DESC;

		recItems = new ArrayList<Item>();
		listRecItems = (ListView) findViewById(R.id.list_item_desc_rec_items);
		recItemAdapter = new ItemAdapter(this, recItems);

		if (savedInstanceState == null) {
			itemDescType = getIntent().getIntExtra(EXTRA_ITEM_DESC_TYPE, 0);

			switch (itemDescType) {
			case ITEM_DESC_TYPE_ITEM:
				item = ((ItemParcelable) getIntent().getParcelableExtra(ItemParcelable.EXTRA_ITEM_PARCEL)).toItem();
				shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
				showItem();
				getShop();
				break;
			case ITEM_DESC_TYPE_SHOP_ITEM:
				item = ((ItemParcelable) getIntent().getParcelableExtra(ItemParcelable.EXTRA_ITEM_PARCEL)).toItem();
				shop = ((ShopParcelable) getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL)).toShop();
				showItem();
				getActionBar().setTitle(shop.getName());
				break;
			case ITEM_DESC_TYPE_ITEM_PUSH:
				itemId = getIntent().getLongExtra(Item.EXTRA_ITEM_ID, 0);
				shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
				getItem();
				getShop();
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
			}

		}, LocalUser.getUser().getId(), shopId);
	}

	private void getItem() {
		NetworkInter.getItem(new ResponseHandlerWithDialog<Item>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(Item response) {
				super.onResponse(response);
				if (response == null)
					return;

				item = response;
				showItem();
			}

		}, LocalUser.getUser().getId(), itemId);
	}

	private void showItem() {
		setItemDescHeaderView();
		setAdapter();
		getRecItems();

		// -- Tracking -- //
		Tracker.trackItemViewPair(item.getId());
	}

	private void setItemDescHeaderView() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		itemDescHeaderView = inflater.inflate(R.layout.adapted_item_header_item_desc, null, false);

		ImageView imageImage = (ImageView) itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_image);
		View loader = itemDescHeaderView.findViewById(R.id.progressbar_adapted_item_header_item_desc_image);
		NetworkInter.getImage(loader, imageImage, item.getThumbnailUrl(), 180, 225, false);
		NetworkInter.getImage(loader, imageImage, item.getThumbnailUrl(), 720, 840, false);

		TextView textName = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_name);
		textName.setText(item.getName());

		TextView textDesc = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_description);
		textDesc.setText(item.getDescription());

		TextView textLikes = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_likes);
		textLikes.setText("" + item.getLikes());

		if (item.isLiked())
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it).setVisibility(View.VISIBLE);
		else
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it_not).setVisibility(View.VISIBLE);

		if (item.getReward() > 0)
			if (item.isRewarded())
				itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_scanned).setVisibility(View.VISIBLE);
			else {
				itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_scannable).setVisibility(View.VISIBLE);
				itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_dal).setVisibility(View.VISIBLE);
				TextView textReward = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_reward);
				textReward.setText("" + item.getReward());
			}

		if (item.getOldPrice() != null && !item.getOldPrice().isEmpty()) {
			TextView textOldPrice = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_old_price);
			textOldPrice.setText(item.getOldPrice());
			textOldPrice.setPaintFlags(textOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}

		if (item.getSale() > 0) {
			TextView textSale = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_sale);
			textSale.setText(item.getSale() + "%");
		}

		TextView textPrice = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_price);
		textPrice.setText(item.getPrice());
		if (item.getSale() > 0 || (item.getOldPrice() != null && !item.getOldPrice().isEmpty()))
			textPrice.setTextColor(getResources().getColor(R.color.red));

		listRecItems.addHeaderView(itemDescHeaderView);
	}

	private void setAdapter() {
		listRecItems.setAdapter(recItemAdapter);
	}

	private void getRecItems() {
		System.out.println("getting rec items");
		NetworkInter.itemListByItem(new ResponseHandler<ItemCollection>() {

			@Override
			protected void onResponse(ItemCollection response) {
				if (response == null || response.getItems() == null)
					return;

				recItems.addAll(response.getItems());
				refreshRecItems();
			}

		}, LocalUser.getUser().getId(), item.getId());
	}

	private void refreshRecItems() {
		System.out.println("got rec items");
		recItemAdapter.notifyDataSetChanged();
	}

	public void toggleLikeItem(View view) {
		if (item == null)
			return;

		if (item.isLiked())
			NetworkInter.unlikeItem(new ResponseHandler<Void>() {

				@Override
				protected void onResponse(Void none) {
					// nothing to do
				}

			}, LocalUser.getUser().getId(), item.getId(), Like.TYPE_ITEM);
		else {
			ToastUtils.show(ResourceUtils.getString(R.string.message_like_it_guide));
			NetworkInter.likeItem(new ResponseHandler<Like>() {

				@Override
				protected void onResponse(Like response) {
					// nothing to do
				}

			}, new Like(LocalUser.getUser().getId(), item.getId(), Like.TYPE_ITEM));

			// -- Tracking -- //
			Tracker.trackItemLike(item.getId(), 0);
		}

		toggleLikeView();
	}

	private void toggleLikeView() {
		if (item.isLiked()) {
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it).setVisibility(View.GONE);
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it_not).setVisibility(View.VISIBLE);
			item.setLikes(item.getLikes() - 1);
			item.setLiked(false);
		} else {
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it_not).setVisibility(View.GONE);
			itemDescHeaderView.findViewById(R.id.image_adapted_item_header_item_desc_like_it).setVisibility(View.VISIBLE);
			item.setLikes(item.getLikes() + 1);
			item.setLiked(true);
		}

		TextView textLikes = (TextView) itemDescHeaderView.findViewById(R.id.text_adapted_item_header_item_desc_likes);
		textLikes.setText("" + item.getLikes());
	}

	public void shareItem(View view) {
		if (item == null || shop == null)
			return;

		SocialUtils.shareItemKakao(this, shop, item);

		// -- Tracking -- //
		Tracker.trackItemShare(item.getId(), 0);
	}

	public void showItemsOnMap(View view) {
		if (shop == null || item == null)
			return;

		Intent intent = new Intent(this, MapActivity.class);
		intent.putExtra(MapActivity.EXTRA_MAP_TYPE, MapActivity.MAP_TYPE_ITEM);
		intent.putExtra(ShopParcelable.EXTRA_SHOP_PARCEL, shop.toParcelable());
		intent.putExtra(Item.EXTRA_ITEM_ID, item.getId());
		startActivity(intent);

		// -- Tracking -- //
		Tracker.trackItemMap(item.getId(), 0);
	}

	public void scanItem(View view) {
		if (item == null || item.isRewarded())
			return;

		Intent intent = new Intent(this, CaptureActivity.class);
		intent.setAction("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, ITEM_SCAN_REQUEST);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == RESULT_OK && requestCode == ITEM_SCAN_REQUEST) {
			String contents = intent.getStringExtra("SCAN_RESULT");
			verifyBarcode(contents);
		}
	}

	private Dialog wait;

	private void showWaitingDialog() {
		wait = DialogUtils.getWaitingDialog(this);
	}

	private void hideWaitingDialog() {
		if (wait != null)
			wait.dismiss();
	}

	private void verifyBarcode(String barcode) {
		Location location = DalshopApplication.getInstance().getLocationUtils().getLastLocation();

		if (item.getBarcodeId() == null || location == null || !barcode.startsWith(item.getBarcodeId())) {
			ToastUtils.showScanRewardFail();
			return;
		}

		showWaitingDialog();
		getFlags(location);
	}

	private void getFlags(Location location) {
		DBInter.getFlags(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null) {
					hideWaitingDialog();
					ToastUtils.showScanRewardFail();
					return;
				}

				List<Flag> flags = response.getFlags();

				if (flags.isEmpty()) {
					hideWaitingDialog();
					ToastUtils.showScanRewardFail();
					return;
				}

				List<Long> shopIds = new ArrayList<Long>();
				for (Flag flag : flags)
					shopIds.add(flag.getShopId());

				getShops(shopIds);
			}

		}, location.getLatitude(), location.getLongitude(), LocationUtils.CLOSE_DISTANCE_DEGREE);
	}

	private void getShops(List<Long> shopIds) {
		NetworkInter.getShopList(new ResponseHandler<ShopCollection>() {

			@Override
			protected void onResponse(ShopCollection response) {
				if (response == null || response.getShops() == null) {
					hideWaitingDialog();
					ToastUtils.showScanRewardFail();
					return;
				}

				verifyLocation(response.getShops());
			}

		}, 0, shopIds);
	}

	private void verifyLocation(List<Shop> shops) {
		for (Shop shop : shops)
			if (shop.getId() == item.getShopId() || shop.getParentId() == item.getShopId()) {
				claimReward(new Reward(LocalUser.getUser().getId(), item.getId(), item.getName(), Reward.TYPE_ITEM, item.getReward()));
				return;
			}

		hideWaitingDialog();
		ToastUtils.showScanRewardFail();
	}

	private void claimReward(final Reward reward) {
		NetworkInter.claimReward(new ResponseHandler<User>() {

			@Override
			protected void onResponse(User response) {
				hideWaitingDialog();
				if (response == null) {
					ToastUtils.showScanRewardFail();
					return;
				}

				response.setGuest(LocalUser.getGuestPref());
				LocalUser.setUser(response);
				PushUtils.pushItemReward(item);

				// -- Tracking -- //
				Tracker.trackItemScan(item.getId(), reward.getReward());
			}

		}, reward);
	}

	@Override
	public void onClickItemImage(Item item) {
		// -- Tracking -- //
		Tracker.trackItemView(item.getId(), 0);

		Intent intent = new Intent(this, ItemDescActivity.class);
		intent.putExtra(ItemParcelable.EXTRA_ITEM_PARCEL, item.toParcelable());
		intent.putExtra(Shop.EXTRA_SHOP_ID, item.getShopId());
		intent.putExtra(ItemDescActivity.EXTRA_ITEM_DESC_TYPE, ItemDescActivity.ITEM_DESC_TYPE_ITEM);
		startActivity(intent);
	}

}
