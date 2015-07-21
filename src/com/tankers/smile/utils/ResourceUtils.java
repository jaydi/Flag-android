package com.tankers.smile.utils;

import android.graphics.drawable.Drawable;

import com.tankers.smile.app.DalshopApplication;

public class ResourceUtils {
	public static String getString(int resId) {
		return DalshopApplication.getInstance().getResources().getString(resId);
	}
	
	public static Drawable getDrawable(int resId) {
		return DalshopApplication.getInstance().getResources().getDrawable(resId);
	}

	public static String[] getStringArray(int resId) {
		return DalshopApplication.getInstance().getResources().getStringArray(resId);
	}

	public static int getColor(int resId) {
		return DalshopApplication.getInstance().getResources().getColor(resId);
	}
}
