package com.tankers.smile.models;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class FlagCollection extends BaseModel {
	@Key
	private List<Flag> flags;

	@Key
	@JsonString
	private List<Long> deletedIds;

	public FlagCollection() {
		super();
	}

	public FlagCollection(List<Flag> flags) {
		super();
		this.flags = flags;
	}

	public FlagCollection(List<Flag> flags, List<Long> deletedIds) {
		super();
		this.flags = flags;
		this.deletedIds = deletedIds;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public List<Long> getDeletedIds() {
		return deletedIds;
	}

	public void setDeletedIds(List<Long> deletedIds) {
		this.deletedIds = deletedIds;
	}

	public List<MarkerOptions> toMarkerOptionsList() {
		List<MarkerOptions> markerOptionsList = new ArrayList<MarkerOptions>();

		for (Flag flag : flags) {
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(new LatLng(flag.getLat(), flag.getLon()));
			markerOptions.title(String.valueOf(flag.getShopId()));
			markerOptions.draggable(false);
			markerOptionsList.add(markerOptions);
		}

		return markerOptionsList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Flag flag : flags)
			sb.append(" id:" + flag.getId());

		return sb.toString();
	}
}
