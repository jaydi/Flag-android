package com.tankers.smile.activities;

import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.tankers.smile.R;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.Tracker;

public class BaseActivity extends FragmentActivity {
	protected long viewId = 1;
	protected EasyTracker easyTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!NetworkInter.isNetworkOnline())
			networkAlert();
		setTitlePadding();
	}

	protected void networkAlert() {
		DialogUtils.networkAlert(this, false);
	}

	private void setTitlePadding() {
		View view = findViewById(android.R.id.home);
		if (view != null)
			view.setPadding(0, 0, 20, 0);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// -- Tracking -- //
		Tracker.trackViewStart(viewId, 0);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		// -- Tracking -- //
		Tracker.trackViewStop(viewId, 0);
	}

	@Override
	public void onStart() {
		super.onStart();
		initTracking();
	}

	private void initTracking() {
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_ask:
			makeFeedBack();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void makeFeedBack() {
		DialogUtils.showFeedbackDialog(this);
	}
	
	// -- GA Tracking Methods -- //
	
	protected void track(Map<String, String> params) {
		easyTracker.send(params);
	}
	
	protected void trackEvent(String category, String action, String label, long value) {
		easyTracker.send(MapBuilder.createEvent(category, action, label, value).build());
	}

	protected void trackTiming(String category, long time, String name, String label) {
		easyTracker.send(MapBuilder.createTiming(category, time, name, label).build());
	}

	protected void trackSocial(String network, String action, String target) {
		easyTracker.send(MapBuilder.createSocial(network, action, target).build());
	}

	protected void trackTransaction(String transactionId, String affiliation, double revenue, double tax, double shipping, String currencyCode) {
		easyTracker.send(MapBuilder.createTransaction(transactionId, affiliation, revenue, tax, shipping, currencyCode).build());
	}

	protected void trackItem(String transactionId, String name, String sku, String category, double price, long quantity, String currencyCode) {
		easyTracker.send(MapBuilder.createItem(transactionId, name, sku, category, price, quantity, currencyCode).build());
	}

}
