package com.tankers.smile.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopParcelable implements Parcelable {
	public static final String EXTRA_SHOP_PARCEL = "com.tankers.smile.models.extra.shop.parcel";
	public static final String EXTRA_SHOP_PARCEL_LIST = "com.tankers.smile.models.extra.shop.parcel.list";

	private long id;
	private long parentId;
	private String name;
	private String logoUrl;
	private String imageUrl;
	private String description;
	private boolean onSale;
	private int type;
	private int reward;
	private boolean rewarded;
	private int likes;
	private boolean liked;

	public ShopParcelable() {
		super();
	}

	public ShopParcelable(Shop shop) {
		super();
		this.id = shop.getId();
		this.parentId = shop.getParentId();
		this.name = shop.getName();
		this.logoUrl = shop.getLogoUrl();
		this.imageUrl = shop.getImageUrl();
		this.description = shop.getDescription();
		this.onSale = shop.isOnSale();
		this.type = shop.getType();
		this.reward = shop.getReward();
		this.rewarded = shop.isRewarded();
		this.likes = shop.getLikes();
		this.liked = shop.isLiked();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<ShopParcelable> CREATOR = new Parcelable.Creator<ShopParcelable>() {

		@Override
		public ShopParcelable createFromParcel(Parcel source) {
			return new ShopParcelable(source);
		}

		@Override
		public ShopParcelable[] newArray(int size) {
			return new ShopParcelable[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(parentId);
		dest.writeString(name);
		dest.writeString(logoUrl);
		dest.writeString(imageUrl);
		dest.writeString(description);
		dest.writeInt(type);
		dest.writeInt(reward);
		dest.writeInt(likes);
		dest.writeBooleanArray(new boolean[] { onSale, rewarded, liked });
	}

	public ShopParcelable(Parcel source) {
		this.id = source.readLong();
		this.parentId = source.readLong();
		this.name = source.readString();
		this.logoUrl = source.readString();
		this.imageUrl = source.readString();
		this.description = source.readString();
		this.type = source.readInt();
		this.reward = source.readInt();
		this.likes = source.readInt();
		boolean[] boolParam = new boolean[3];
		source.readBooleanArray(boolParam);
		this.onSale = boolParam[0];
		this.rewarded = boolParam[1];
		this.liked = boolParam[2];
	}

	public Shop toShop() {
		return new Shop(this);
	}
}
