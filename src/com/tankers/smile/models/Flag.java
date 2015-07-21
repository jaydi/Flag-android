package com.tankers.smile.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.util.Key;
import com.kakao.GlobalApplication;
import com.tankers.smile.R;
import com.tankers.smile.utils.LocationUtils;
import com.tankers.smile.utils.ResourceUtils;

public class Flag extends BaseModel {
	public static final String EXTRA_FLAG_ID = "com.flag.models.extra.flag.id";
	public static final String EXTRA_LATLNG = "com.flag.models.extra.latlng";

	@Key
	private long id;

	@Key
	private double lat;

	@Key
	private double lon;

	@Key
	private long createdAt;

	@Key
	private long shopId;

	@Key
	private String shopName;

	@Key
	private int shopType;

	@Key
	private int reward;

	private double distance;

	public Flag() {
		super();
	}

	public Flag(FlagParcelable flagParcelable) {
		super();
		id = flagParcelable.getId();
		lat = flagParcelable.getLat();
		lon = flagParcelable.getLon();
		shopId = flagParcelable.getShopId();
		shopName = flagParcelable.getShopName();
		shopType = flagParcelable.getShopType();
		reward = flagParcelable.getReward();
		distance = flagParcelable.getDistance();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double calDistance(double x, double y) {
		distance = LocationUtils.metricDistance(lat, lon, x, y);
		return distance;
	}

	public String getDistanceString() {
		String distanceString;
		if (distance > 1000) {
			long d = Math.round(distance / 1000);
			distanceString = d + "km";
		} else {
			long d = Math.round(distance / 10) * 10;
			distanceString = d + "m";
		}

		return distanceString;
	}

	public MarkerOptions toMarkerOptions() {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(lat, lon));
		markerOptions.title(shopName);
		markerOptions.draggable(false);
		markerOptions.icon(getIcon(false));

		return markerOptions;
	}

	public BitmapDescriptor getIcon(boolean on) {
		int resId;
		switch (shopType) {
		case Shop.TYPE_TANKERS:
			if (on)
				resId = R.drawable.ic_marker_tankers_on;
			else
				resId = R.drawable.ic_marker_tankers_off;
			break;
		case Shop.TYPE_CLOTH:
			if (on)
				resId = R.drawable.ic_marker_cloth_on;
			else
				resId = R.drawable.ic_marker_cloth_off;
			break;
		case Shop.TYPE_SHOES:
			if (on)
				resId = R.drawable.ic_marker_shoes_on;
			else
				resId = R.drawable.ic_marker_shoes_off;
			break;
		case Shop.TYPE_COSMETIC:
			if (on)
				resId = R.drawable.ic_marker_cosmetic_on;
			else
				resId = R.drawable.ic_marker_cosmetic_off;
			break;
		case Shop.TYPE_ACCESSORY:
			if (on)
				resId = R.drawable.ic_marker_accessory_on;
			else
				resId = R.drawable.ic_marker_accessory_off;
			break;
		case Shop.TYPE_APPLIANCE:
			if (on)
				resId = R.drawable.ic_marker_appliance_on;
			else
				resId = R.drawable.ic_marker_appliance_off;
			break;
		case Shop.TYPE_BEAUTY:
			if (on)
				resId = R.drawable.ic_marker_beauty_on;
			else
				resId = R.drawable.ic_marker_beauty_off;
			break;
		case Shop.TYPE_RETAILLER:
			if (on)
				resId = R.drawable.ic_marker_retailler_on;
			else
				resId = R.drawable.ic_marker_retailler_off;
			break;
		default:
			if (on)
				resId = R.drawable.ic_marker_etc_on;
			else
				resId = R.drawable.ic_marker_etc_off;
			break;
		}

		if (reward > 0)
			return getRewardFlagView(resId);
		else
			return BitmapDescriptorFactory.fromResource(resId);
	}

	private BitmapDescriptor getRewardFlagView(int resId) {
		LayoutInflater inflater = (LayoutInflater) GlobalApplication.getGlobalApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.reward_flag_layout, null, false);

		TextView textReward = (TextView) view.findViewById(R.id.text_reward_flag_reward);
		textReward.setText("" + reward + ResourceUtils.getString(R.string.dal));

		ImageView imageReward = (ImageView) view.findViewById(R.id.image_reward_flag_image);
		imageReward.setImageResource(resId);

		view.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.draw(c);

		return BitmapDescriptorFactory.fromBitmap(b);
	}

	public FlagParcelable toParcelable() {
		return new FlagParcelable(this);
	}

	@Override
	public boolean equals(Object o) {
		try {
			Flag f = (Flag) o;
			if (id == f.getId())
				return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}
}
