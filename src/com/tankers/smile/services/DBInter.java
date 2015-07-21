package com.tankers.smile.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;

import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Flag;
import com.tankers.smile.models.FlagCollection;

public class DBInter {
	private static final String PREFERENCE_DB_TAG = "preference_db_tag";
	private static final String PROPERTY_FLAG_LIST_TAG = "property_flag_list_tag";

	private static DatabaseHandler dbHandler;

	public static void initDB(Context context) {
		dbHandler = new DatabaseHandler(context);
	}

	public static DatabaseHandler getDatabaseHandler() {
		return dbHandler;
	}

	public static void storeFlagsData(FlagCollection response) {
		new AsyncTask<FlagCollection, Void, Void>() {

			@Override
			protected Void doInBackground(FlagCollection... params) {
				if (params == null || params.length < 1)
					return null;

				FlagCollection response = params[0];
				if (response == null)
					return null;

				if (response.getDeletedIds() != null && !response.getDeletedIds().isEmpty())
					dbHandler.deleteFlags(response.getDeletedIds());

				if (response.getFlags() != null && !response.getFlags().isEmpty()) {
					List<Long> insertingIds = new ArrayList<Long>();
					for (Flag flag : response.getFlags())
						insertingIds.add(flag.getId());

					dbHandler.deleteFlags(insertingIds);
					dbHandler.addFlags(response.getFlags());
				}

				setFlagListTag(new Date().getTime());

				return null;
			}

		}.execute(response);
	}

	public static void getFlag(final ResponseHandler<Flag> handler, long flagId) {
		new AsyncTask<Long, Void, Flag>() {

			@Override
			protected Flag doInBackground(Long... params) {
				if (params == null || params.length < 1)
					return null;

				return dbHandler.getFlag(params[0]);
			}

			@Override
			protected void onPostExecute(Flag result) {
				Message m = new Message();
				m.obj = result;
				handler.sendMessage(m);
			}

		}.execute(flagId);
	}

	public static void getFlags(final ResponseHandler<FlagCollection> handler, double lat, double lon, double range) {
		new AsyncTask<Double, Void, FlagCollection>() {

			@Override
			protected FlagCollection doInBackground(Double... params) {
				if (params == null || params.length < 3)
					return null;

				return new FlagCollection(dbHandler.getFlags(params[0], params[1], params[2]));
			}

			@Override
			protected void onPostExecute(FlagCollection result) {
				Message m = new Message();
				m.obj = result;
				handler.sendMessage(m);
			}

		}.execute(lat, lon, range);
	}

	public static long getFlagListTag() {
		SharedPreferences pref = getFlagListTagPreference();
		return pref.getLong(PROPERTY_FLAG_LIST_TAG, 0);
	}

	public static void setFlagListTag(long tag) {
		SharedPreferences pref = getFlagListTagPreference();
		SharedPreferences.Editor editor = pref.edit();
		editor.putLong(PROPERTY_FLAG_LIST_TAG, tag);
		editor.commit();
	}

	private static SharedPreferences getFlagListTagPreference() {
		return DalshopApplication.getInstance().getSharedPreferences(PREFERENCE_DB_TAG, Context.MODE_PRIVATE);
	}

}
