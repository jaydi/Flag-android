package com.tankers.smile.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tankers.smile.R;
import com.tankers.smile.adapters.FlagAdapter;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagParcelable;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Shop;
import com.tankers.smile.models.ShopParcelable;
import com.tankers.smile.utils.FlagDistanceComparator;

public class FlagListActivity extends SubCategoryActivity implements OnItemClickListener {
	private List<Flag> flags;
	private List<Shop> shops;
	private ListView listFlags;
	private FlagAdapter flagAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flag_list);
		viewId = Log.VIEW_FLAG_LIST;

		List<FlagParcelable> flagParcelables = getIntent().getParcelableArrayListExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST);
		flags = new ArrayList<Flag>();
		for (FlagParcelable flagParcelable : flagParcelables)
			flags.add(flagParcelable.toFlag());

		shops = new ArrayList<Shop>();
		List<ShopParcelable> shopParcelables = getIntent().getParcelableArrayListExtra(ShopParcelable.EXTRA_SHOP_PARCEL_LIST);
		if (shopParcelables != null)
			for (ShopParcelable shopParcelable : shopParcelables)
				shops.add(shopParcelable.toShop());
		ShopParcelable shopParcelable = getIntent().getParcelableExtra(ShopParcelable.EXTRA_SHOP_PARCEL);
		if (shopParcelable != null)
			shops.add(shopParcelable.toShop());

		listFlags = (ListView) findViewById(R.id.list_flag_list_flag_list);
		listFlags.setOnItemClickListener(this);
		getShopMap();
	}

	private void getShopMap() {
		Map<Long, Shop> shopMap = new HashMap<Long, Shop>();
		for (Shop shop : shops)
			shopMap.put(shop.getId(), shop);

		sort();
		setAdapter(flags, shopMap);
	}

	private void sort() {
		Location location = DalshopApplication.getInstance().getLocationUtils().getLastLocation();
		if (location == null)
			return;
		Collections.sort(flags, new FlagDistanceComparator(location));
	}

	private void setAdapter(List<Flag> flags, Map<Long, Shop> shopMap) {
		flagAdapter = new FlagAdapter(this, flags, shopMap);
		listFlags.setAdapter(flagAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		long flagId = flagAdapter.getItemId(position);
		Intent returnIntent = new Intent();
		returnIntent.putExtra(Flag.EXTRA_FLAG_ID, flagId);
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
