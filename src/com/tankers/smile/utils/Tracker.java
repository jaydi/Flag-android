package com.tankers.smile.utils;

import java.util.Date;

import com.tankers.smile.models.ItemViewPair;
import com.tankers.smile.models.Log;
import com.tankers.smile.services.NetworkInter;

// DalShop Tracker
public class Tracker {
	public static void trackFirstUser(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_FIRST_USER, target, value));
	}

	public static void trackViewStart(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_VIEW_START, target, value));
	}

	public static void trackViewStop(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_VIEW_STOP, target, value));
	}

	public static void trackShopView(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_SHOP_VIEW, target, value));
	}

	public static void trackShopShare(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_SHOP_SHARE, target, value));
	}

	public static void trackShopLike(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_SHOP_LIKE, target, value));
	}

	public static void trackShopMap(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_SHOP_MAP, target, value));
	}

	public static void trackItemView(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_ITEM_VIEW, target, value));
	}

	public static void trackItemShare(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_ITEM_SHARE, target, value));
	}

	public static void trackItemLike(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_ITEM_LIKE, target, value));
	}

	public static void trackItemMap(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_ITEM_MAP, target, value));
	}

	public static void trackCheckIn(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_CHECK_IN, target, value));
	}

	public static void trackItemScan(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_ITEM_SCAN, target, value));
	}

	public static void trackLocalPush(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_LOCAL_PUSH, target, value));
	}

	public static void trackEventPush(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_EVENT_PUSH, target, value));
	}

	public static void trackAppShare(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_APP_SHARE, target, value));
	}
	
	public static void trackVisit(long target, long value) {
		NetworkInter.log(new Log(Log.CATEGORY_VISIT, target, value));
	}

	private static final long ITEM_PAIR_INTERVAL_MAX = 1000 * 60 * 3;
	private static long preItemId;
	private static long preTime;

	public static void trackItemViewPair(long itemId) {
		if (preItemId != 0 && preItemId != itemId && preTime > (new Date().getTime() - ITEM_PAIR_INTERVAL_MAX))
			NetworkInter.logItemViewPair(new ItemViewPair(preItemId, itemId, new Date().getTime()));

		preItemId = itemId;
		preTime = new Date().getTime();
	}
}
