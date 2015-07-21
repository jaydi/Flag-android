package com.tankers.smile.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
	public static final String EXTRA_EVENT = "com.tankers.smile.extra.event";
	public static final String EXTRA_EVENT_PUSH_REWARD = "com.tankers.smile.extra.event.pushreward";

	private long shopId;
	private String title;
	private String imageUrl;
	private String message;
	private int reward;

	public Event() {
		super();
	}

	public Event(long shopId, String title, String imageUrl, String message, int reward) {
		super();
		this.shopId = shopId;
		this.title = title;
		this.imageUrl = imageUrl;
		this.message = message;
		this.reward = reward;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {

		@Override
		public Event createFromParcel(Parcel source) {
			return new Event(source);
		}

		@Override
		public Event[] newArray(int size) {
			return new Event[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(shopId);
		dest.writeString(title);
		dest.writeString(imageUrl);
		dest.writeString(message);
		dest.writeInt(reward);
	}

	public Event(Parcel source) {
		shopId = source.readLong();
		title = source.readString();
		imageUrl = source.readString();
		message = source.readString();
		reward = source.readInt();
	}
}
