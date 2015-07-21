package com.tankers.smile.models;

import com.google.api.client.util.Key;

public class Item extends BaseModel {
	public static final String EXTRA_ITEM_ID = "com.tankers.smile.models.item.id";

	@Key
	private long id;

	@Key
	private long shopId;

	@Key
	private String name;

	@Key
	private String thumbnailUrl;

	@Key
	private String description;

	@Key
	private int sale;

	@Key
	private String oldPrice;

	@Key
	private String price;

	@Key
	private String barcodeId;

	@Key
	private int reward;

	@Key
	private boolean rewardable;

	@Key
	private boolean rewarded;

	@Key
	private int likes;

	@Key
	private boolean liked;

	public Item() {
		super();
	}

	public Item(ItemParcelable parcel) {
		super();
		this.id = parcel.getId();
		this.shopId = parcel.getShopId();
		this.name = parcel.getName();
		this.thumbnailUrl = parcel.getThumbnailUrl();
		this.description = parcel.getDescription();
		this.sale = parcel.getSale();
		this.oldPrice = parcel.getOldPrice();
		this.price = parcel.getPrice();
		this.barcodeId = parcel.getBarcodeId();
		this.reward = parcel.getReward();
		this.rewarded = parcel.isRewarded();
		this.likes = parcel.getLikes();
		this.liked = parcel.isLiked();
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

	public boolean isRewardable() {
		return rewardable;
	}

	public void setRewardable(boolean rewardable) {
		this.rewardable = rewardable;
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

	public ItemParcelable toParcelable() {
		return new ItemParcelable(this);
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Item o = (Item) obj;
			if (id == o.getId())
				return true;
			else
				return false;
		} catch (ClassCastException e) {
			return false;
		}
	}

	public boolean isDummy() {
		return id == 0;
	}
}
