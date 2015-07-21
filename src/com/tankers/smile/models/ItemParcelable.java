package com.tankers.smile.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemParcelable implements Parcelable {
	public static final String EXTRA_ITEM_PARCEL = "com.tankers.smile.models.item.parcel";

	private long id;
	private long shopId;
	private String name;
	private String thumbnailUrl;
	private String description;
	private int sale;
	private String oldPrice;
	private String price;
	private String barcodeId;
	private int reward;
	private boolean rewarded;
	private int likes;
	private boolean liked;

	public ItemParcelable() {
		super();
	}

	public ItemParcelable(Item item) {
		super();
		this.id = item.getId();
		this.shopId = item.getShopId();
		this.name = item.getName();
		this.thumbnailUrl = item.getThumbnailUrl();
		this.description = item.getDescription();
		this.sale = item.getSale();
		this.oldPrice = item.getOldPrice();
		this.price = item.getPrice();
		this.barcodeId = item.getBarcodeId();
		this.reward = item.getReward();
		this.rewarded = item.isRewarded();
		this.likes = item.getLikes();
		this.liked = item.isLiked();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public boolean isRewarded() {
		return rewarded;
	}

	public void setRewarded(boolean rewarded) {
		this.rewarded = rewarded;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public Item toItem() {
		return new Item(this);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<ItemParcelable> CREATOR = new Parcelable.Creator<ItemParcelable>() {

		@Override
		public ItemParcelable createFromParcel(Parcel source) {
			return new ItemParcelable(source);
		}

		@Override
		public ItemParcelable[] newArray(int size) {
			return new ItemParcelable[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(shopId);
		dest.writeString(name);
		dest.writeString(thumbnailUrl);
		dest.writeString(description);
		dest.writeInt(sale);
		dest.writeString(oldPrice);
		dest.writeString(price);
		dest.writeString(barcodeId);
		dest.writeInt(reward);
		dest.writeInt(likes);
		dest.writeBooleanArray(new boolean[] { rewarded, liked });
	}

	public ItemParcelable(Parcel source) {
		id = source.readLong();
		shopId = source.readLong();
		name = source.readString();
		thumbnailUrl = source.readString();
		description = source.readString();
		sale = source.readInt();
		oldPrice = source.readString();
		price = source.readString();
		barcodeId = source.readString();
		reward = source.readInt();
		likes = source.readInt();
		boolean[] boolParam = new boolean[2];
		source.readBooleanArray(boolParam);
		rewarded = boolParam[0];
		liked = boolParam[1];
	}
}
