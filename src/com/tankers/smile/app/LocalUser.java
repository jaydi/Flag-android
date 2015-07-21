package com.tankers.smile.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.tankers.smile.models.User;

public class LocalUser {
	private static User user;

	public static User getUser() {
		if (user == null)
			setUser(new User(getIdPref(), getGuestPref()));
		return user;
	}

	public static void setUser(User user) {
		LocalUser.user = user;
		setIdPref(user.getId());
		setGuestPref(user.isGuest());
	}

	public static void removeUser() {
		LocalUser.user = null;
		removeIdPref();
		removeGuestPref();
	}
	
	public static void setIdPref(long id) {
		SharedPreferences prefs = getPreference();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong("id", id);
		editor.commit();
	}
	
	public static long getIdPref() {
		SharedPreferences prefs = getPreference();
		return prefs.getLong("id", 0);
	}
	
	public static void removeIdPref() {
		SharedPreferences prefs = getPreference();
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove("id");
		editor.commit();
	}
	
	public static void setGuestPref(boolean isGuest) {
		SharedPreferences prefs = getPreference();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("isGuest", isGuest);
		editor.commit();
	}
	
	public static boolean getGuestPref() {
		SharedPreferences prefs = getPreference();
		return prefs.getBoolean("isGuest", true);
	}
	
	public static void removeGuestPref() {
		SharedPreferences prefs = getPreference();
		SharedPreferences.Editor editor = prefs.edit();
		editor.remove("isGuest");
		editor.commit();
	}
	
	public static SharedPreferences getPreference() {
		return DalshopApplication.getInstance().getSharedPreferences("DalShopUser", Context.MODE_PRIVATE);
	}
}
