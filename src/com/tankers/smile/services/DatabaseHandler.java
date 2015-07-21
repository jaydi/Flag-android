package com.tankers.smile.services;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tankers.smile.models.Flag;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "FlagDB";

	private static final String TABLE_FLAGS = "flags";
	private static final String COL_ID = "id";
	private static final String COL_LAT = "lat";
	private static final String COL_LON = "lon";
	private static final String COL_SHOP_ID = "shop_id";
	private static final String COL_SHOP_NAME = "shop_name";
	private static final String COL_SHOP_TYPE = "shop_type";
	private static final String COL_CREATED_AT = "created_at";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createFlagTable = "CREATE TABLE " + TABLE_FLAGS + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_LAT + " REAL, " + COL_LON + " REAL, "
				+ COL_SHOP_ID + " INTEGER, " + COL_SHOP_NAME + " TEXT, " + COL_SHOP_TYPE + " INTEGER, " + COL_CREATED_AT + " INTEGER)";

		db.execSQL(createFlagTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLAGS);
			onCreate(db);
		}
	}

	public void addFlags(List<Flag> flags) {
		SQLiteDatabase db = this.getWritableDatabase();

		for (Flag flag : flags) {
			ContentValues values = new ContentValues();
			values.put(COL_ID, flag.getId());
			values.put(COL_LAT, flag.getLat());
			values.put(COL_LON, flag.getLon());
			values.put(COL_SHOP_ID, flag.getShopId());
			values.put(COL_SHOP_NAME, flag.getShopName());
			values.put(COL_SHOP_TYPE, flag.getShopType());
			values.put(COL_CREATED_AT, flag.getCreatedAt());
			db.insert(TABLE_FLAGS, null, values);
		}

		db.close();
	}

	public void deleteFlags(List<Long> flagIds) {
		SQLiteDatabase db = this.getWritableDatabase();

		for (long flagId : flagIds)
			db.delete(TABLE_FLAGS, COL_ID + " = ?", new String[] { String.valueOf(flagId) });

		db.close();
	}

	public Flag getFlag(long flagId) {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] cols = new String[] { COL_ID, COL_LAT, COL_LON, COL_SHOP_ID, COL_SHOP_NAME, COL_SHOP_TYPE, COL_CREATED_AT };
		String selection = COL_ID + " = ?";
		String[] args = new String[] { String.valueOf(flagId) };

		Cursor cursor = db.query(TABLE_FLAGS, cols, selection, args, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		if (cursor.isAfterLast())
			return null;

		Flag flag = new Flag();
		flag.setId(cursor.getLong(0));
		flag.setLat(cursor.getDouble(1));
		flag.setLon(cursor.getDouble(2));
		flag.setShopId(cursor.getInt(3));
		flag.setShopName(cursor.getString(4));
		flag.setShopType(cursor.getInt(5));
		flag.setCreatedAt(cursor.getLong(6));

		return flag;
	}

	public List<Flag> getFlags(double lat, double lon, double range) {
		SQLiteDatabase db = this.getReadableDatabase();
		List<Flag> flags = new ArrayList<Flag>();

		String[] cols = new String[] { COL_ID, COL_LAT, COL_LON, COL_SHOP_ID, COL_SHOP_NAME, COL_SHOP_TYPE, COL_CREATED_AT };
		String selection = COL_LAT + " > ? and " + COL_LAT + " < ? and " + COL_LON + " > ? and " + COL_LON + " < ?";
		String[] args = new String[] { String.valueOf(lat - range), String.valueOf(lat + range), String.valueOf(lon - range),
				String.valueOf(lon + range) };

		Cursor cursor = db.query(TABLE_FLAGS, cols, selection, args, null, null, null);
		if (cursor == null)
			return flags;

		if (cursor.isAfterLast())
			return flags;

		if (cursor.moveToFirst()) {
			do {
				Flag flag = new Flag();
				flag.setId(cursor.getLong(0));
				flag.setLat(cursor.getDouble(1));
				flag.setLon(cursor.getDouble(2));
				flag.setShopId(cursor.getInt(3));
				flag.setShopName(cursor.getString(4));
				flag.setShopType(cursor.getInt(5));
				flag.setCreatedAt(cursor.getLong(6));
				flags.add(flag);
			} while (cursor.moveToNext());
		}

		return flags;
	}

}
