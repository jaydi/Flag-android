package com.tankers.smile.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagCollection;
import com.tankers.smile.models.Shop;
import com.tankers.smile.services.DBInter;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;

public class LocationUtils implements ConnectionCallbacks, OnConnectionFailedListener {
	private static final long EXPIRATION_TIME = 1000 * 3600 * 24 * 3;

	private static LocationClient locationClient;

	public void init() {
		locationClient = new LocationClient(DalshopApplication.getInstance(), this, this);
		locationClient.connect();
		Log.d("LOCUTIL", "INIT");
	}

	public void fin() {
		if (locationClient != null && locationClient.isConnected())
			locationClient.disconnect();
	}

	public boolean isConnected() {
		if (locationClient == null)
			return false;
		return locationClient.isConnected();
	}

	@Override
	public void onConnected(Bundle bundle) {
		search();
		Log.d("LOCUTIL", "ONCONNECT");
	}

	@Override
	public void onDisconnected() {
	}

	@Override
	public void onConnectionFailed(ConnectionResult res) {
		locationClient.connect();
	}

	private Location prePosition;

	private void search() {
		if (!properTime()) {
			Calendar cal = Calendar.getInstance(Locale.KOREA);
			cal.setTime(new Date());
			int h = cal.get(Calendar.HOUR_OF_DAY);

			int dh;
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
				if (h > 20)
					dh = 34 - h;
				else
					dh = 18 - h;
			else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				if (h > 20)
					dh = 34 - h;
				else
					dh = 10 - h;
			else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				if (h > 20)
					dh = 42 - h;
				else
					dh = 10 - h;
			else if (h > 20)
				dh = 42 - h;
			else
				dh = 18 - h;

			restartSearchDelayed((dh < 1) ? 60 : 60 * dh);
			Log.d("LOCSCAN", "not in right time, retry in " + ((dh < 1) ? 30 : 60 * dh) + " mins");
			return;
		}

		final Location location = getLastLocation();
		if (location == null) {
			restartSearchDelayed(1);
			Log.d("LOCSCAN", "location null, retry in 1 min");
			return;
		}

		if (!moved(location)) {
			prePosition = location;
			restartSearchDelayed(10);
			Log.d("LOCSCAN", "not moved, retry in 10mins");
			return;
		}

		DBInter.getFlags(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null)
					return;

				List<Long> shopIds = new ArrayList<Long>();
				for (Flag flag : response.getFlags())
					shopIds.add(flag.getShopId());

				Log.d("LOCSCAN", "scanned! retry in 1 min");
				notifyShopNearby(shopIds);
				prePosition = location;
				restartSearchDelayed(1);
			}

		}, location.getLatitude(), location.getLongitude(), CLOSE_DISTANCE_DEGREE);
	}

	private boolean properTime() {
		// Calendar cal = Calendar.getInstance(Locale.KOREA);
		// cal.setTime(new Date());
		// int h = cal.get(Calendar.HOUR_OF_DAY);
		//
		// if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		// if (h <= 20 && h >= 10)
		// return true;
		// else
		// return false;
		// else if (h <= 20 && h >= 18)
		// return true;
		// else
		// return false;
		return true;
	}

	public Location getLastLocation() {
		if (locationClient != null && locationClient.isConnected())
			return locationClient.getLastLocation();
		else
			return null;
	}

	private boolean moved(Location location) {
		if (prePosition == null)
			return true;
		else
			return !isClose(location.getLatitude(), location.getLongitude(), prePosition.getLatitude(), prePosition.getLongitude());
	}

	private void notifyShopNearby(List<Long> shopIds) {
		Log.d("LOCSCAN", "candidates: " + shopIds);
		NetworkInter.getShopRecommended(new ResponseHandler<Shop>() {

			@Override
			protected void onResponse(Shop response) {
				if (response == null) {
					Log.d("LOCSCAN", "no recommendable shop...");
					return;
				}

				if (isNoticedShop(response)) {
					Log.d("LOCSCAN", "noticed shop...");
					return;
				}

				PushUtils.pushShop(response);
				saveShopIdPref(response.getId());
				Log.d("LOCSCAN", "shop recommend! id is " + response.getId());

				// -- Tracking -- //
				Tracker.trackLocalPush(response.getId(), 0);
			}

		}, LocalUser.getUser().getId(), shopIds);
	}

	private boolean isNoticedShop(Shop shop) {
		return getShopIdPref(shop.getId()) > new Date().getTime() - EXPIRATION_TIME;
	}

	private long getShopIdPref(Long shopId) {
		SharedPreferences pref = getPref();
		return pref.getLong("" + shopId, 0);
	}

	private void saveShopIdPref(Long shopId) {
		SharedPreferences pref = getPref();
		SharedPreferences.Editor editor = pref.edit();
		editor.putLong("" + shopId, new Date().getTime());
		editor.commit();
	}

	private SharedPreferences getPref() {
		return DalshopApplication.getInstance().getSharedPreferences("NoticedShops" + LocalUser.getUser().getId(), Context.MODE_PRIVATE);
	}

	private long searchDue;

	@SuppressLint("HandlerLeak")
	private void restartSearchDelayed(int delayMin) {
		new Handler() {

			@Override
			public void handleMessage(Message msg) {
				search();
			}

		}.sendEmptyMessageDelayed(0, 1000 * 60 * delayMin);

		searchDue = new Date().getTime() + 1000 * 60 * delayMin;
	}

	public void searchCheck() {
		if (searchDue < new Date().getTime())
			search();
	}

	public static final double NEAR_DISTANCE_DEGREE = 0.05;
	public static final double CLOSE_DISTANCE_DEGREE = 0.001;

	public static boolean isNear(double latx, double lonx, double laty, double lony) {
		if (distance(latx, lonx, laty, lony) < NEAR_DISTANCE_DEGREE)
			return true;
		else
			return false;
	}

	public static boolean isClose(double latx, double lonx, double laty, double lony) {
		if (distance(latx, lonx, laty, lony) < CLOSE_DISTANCE_DEGREE)
			return true;
		else
			return false;
	}

	public static double distance(double latx, double lonx, double laty, double lony) {
		return Math.sqrt((latx - laty) * (latx - laty) + (lonx - lony) * (lonx - lony));
	}

	public static double metricDistance(double latx, double lonx, double laty, double lony) {
		double scale = 6400 * 1000 * (Math.PI / 180);
		double dX = scale * Math.abs((latx - laty));
		double dY = scale * Math.abs((lonx - lony));

		return Math.sqrt(dX * dX + dY * dY);
	}
}
