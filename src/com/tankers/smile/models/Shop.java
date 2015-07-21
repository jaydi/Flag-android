package com.tankers.smile.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.api.client.util.Key;
import com.tankers.smile.R;
import com.tankers.smile.utils.ResourceUtils;

public class Shop extends BaseModel {
	public static final String EXTRA_SHOP_ID = "com.flag.models.extra.shop.id";
	public static final String EXTRA_SHOP_NAME = "com.flag.models.extra.shop.name";

	public static final int TYPE_HQ = 1;
	public static final int TYPE_BR = 2;

	public static final int TYPE_TANKERS = 0;
	public static final int TYPE_CLOTH = 1;
	public static final int TYPE_SHOES = 2;
	public static final int TYPE_COSMETIC = 3;
	public static final int TYPE_ACCESSORY = 4;
	public static final int TYPE_APPLIANCE = 5;
	public static final int TYPE_BEAUTY = 6;
	public static final int TYPE_ETC = 7;
	public static final int TYPE_RETAILLER = 100;

	@Key
	private long id;

	@Key
	private long parentId;

	@Key
	private String name;

	@Key
	private String logoUrl;

	@Key
	private String imageUrl;

	@Key
	private String description;

	@Key
	private int type;

	@Key
	private int reward;

	@Key
	private boolean onSale;

	@Key
	private boolean rewarded;

	@Key
	private int likes;

	@Key
	private boolean liked;

	private List<Shop> branches;

	public Shop() {
		super();
	}

	public Shop(ShopParcelable parcel) {
		super();
		this.id = parcel.getId();
		this.parentId = parcel.getParentId();
		this.name = parcel.getName();
		this.logoUrl = parcel.getLogoUrl();
		this.imageUrl = parcel.getImageUrl();
		this.description = parcel.getDescription();
		this.onSale = parcel.isOnSale();
		this.type = parcel.getType();
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

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
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

	public List<Shop> getBranches() {
		return branches;
	}

	public void setBranches(List<Shop> branches) {
		this.branches = branches;
	}

	public void addBranch(Shop branch) {
		if (branches == null)
			branches = new ArrayList<Shop>();
		branches.add(branch);
	}

	public ShopParcelable toParcelable() {
		return new ShopParcelable(this);
	}

	public Event toEvent() {
		String message;
		if (!rewarded && reward > 0)
			message = String.format(ResourceUtils.getString(R.string.message_notify_shop_rewardable), name, reward);
		else
			message = String.format(ResourceUtils.getString(R.string.message_notify_shop), name);

		Event event = new Event();
		event.setShopId(id);
		event.setTitle(name);
		event.setImageUrl(imageUrl);
		event.setMessage(message);

		return event;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Shop o = (Shop) obj;
			if (id == o.getId())
				return true;
			else
				return false;
		} catch (ClassCastException e) {
			return false;
		}
	}

	public Flag nearestBranchFlag(Map<Long, Flag> flagMap) {
		if (branches == null || flagMap == null)
			return null;

		Flag flag = null;
		for (Shop branch : branches)
			if (flag == null)
				flag = flagMap.get(branch.getId());
			else if (flag.getDistance() > flagMap.get(branch.getId()).getDistance())
				flag = flagMap.get(branch.getId());

		return flag;
	}

	public boolean isDummy() {
		return id == 0;
	}
}
