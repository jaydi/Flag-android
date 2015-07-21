package com.tankers.smile.models;

import java.util.Date;

import com.google.api.client.util.Key;
import com.tankers.smile.app.LocalUser;

public class Log extends BaseModel {
	@Key
	private long id;

	@Key
	private long subject;

	@Key
	private int category;

	@Key
	private long target;

	@Key
	private long value;

	@Key
	private long createdAt;

	public Log() {
		super();
	}

	public Log(int category, long target, long value) {
		super();
		this.subject = LocalUser.getUser().getId();
		this.category = category;
		this.target = target;
		this.value = value;
		this.createdAt = new Date().getTime();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSubject() {
		return subject;
	}

	public void setSubject(long subject) {
		this.subject = subject;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public long getTarget() {
		return target;
	}

	public void setTarget(long target) {
		this.target = target;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "log: " + category + " / " + target + " / " + value;
	}

	public static final long VIEW_LOADING = 100;

	public static final long VIEW_LOGIN = 200;
	public static final long VIEW_JOIN = 210;
	public static final long VIEW_PHONE_INPUT = 220;
	public static final long VIEW_VERIFICATION_INPUT = 230;
	public static final long VIEW_BGUIDE = 240;
	public static final long VIEW_EDIT_PROFILE = 250;

	public static final long VIEW_MAIN = 300;
	public static final long VIEW_MAIN_ITEMS = 301;
	public static final long VIEW_MAIN_SHOPS = 302;
	public static final long VIEW_MAIN_MY_MENU = 303;
	public static final long VIEW_MAIN_REWARD_MENU = 304;
	public static final long VIEW_SHOPS = 310;
	public static final long VIEW_SHOPS_REWARD = 311;
	public static final long VIEW_ITEMS = 320;
	public static final long VIEW_ITEMS_MY_LIKES = 321;
	public static final long VIEW_ITEMS_REWARD = 322;
	public static final long VIEW_ITEM_DESC = 330;
	public static final long VIEW_MAP = 340;
	public static final long VIEW_MAP_SHOP = 341;
	public static final long VIEW_MAP_ITEM = 342;
	public static final long VIEW_MAP_REWARD = 343;
	public static final long VIEW_FLAG_LIST = 350;

	public static final long VIEW_MY_PAGE = 400;
	public static final long VIEW_REWARD = 410;
	public static final long VIEW_REWARD_HISTORY = 420;
	public static final long VIEW_SETTINGS = 430;
	public static final long VIEW_NOTICE = 440;
	public static final long VIEW_VERSION = 450;

	public static final long VIEW_IMAGE = 500;
	public static final long VIEW_POPUP = 510;
	public static final long VIEW_SIMPLE_TEXT = 520;

	public static final int CATEGORY_FIRST_USER = 100;

	public static final int CATEGORY_VIEW_START = 200;
	public static final int CATEGORY_VIEW_STOP = 201;

	public static final int CATEGORY_SHOP_VIEW = 300;
	public static final int CATEGORY_SHOP_SHARE = 301;
	public static final int CATEGORY_SHOP_LIKE = 302;
	public static final int CATEGORY_SHOP_MAP = 303;

	public static final int CATEGORY_ITEM_VIEW = 400;
	public static final int CATEGORY_ITEM_SHARE = 401;
	public static final int CATEGORY_ITEM_LIKE = 402;
	public static final int CATEGORY_ITEM_MAP = 403;

	public static final int CATEGORY_CHECK_IN = 500;
	public static final int CATEGORY_ITEM_SCAN = 501;
	public static final int CATEGORY_BUY = 502;

	public static final int CATEGORY_LOCAL_PUSH = 600;
	public static final int CATEGORY_EVENT_PUSH = 601;

	public static final int CATEGORY_APP_SHARE = 700;
	
	public static final int CATEGORY_VISIT = 800;
}
