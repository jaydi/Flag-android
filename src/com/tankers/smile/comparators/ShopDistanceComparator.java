package com.tankers.smile.comparators;

import java.util.Comparator;
import java.util.Map;

import com.tankers.smile.models.Flag;
import com.tankers.smile.models.Shop;

public class ShopDistanceComparator implements Comparator<Shop> {
	private Map<Long, Flag> flagMap;

	public ShopDistanceComparator(Map<Long, Flag> flagMap) {
		super();
		this.flagMap = flagMap;
	}

	@Override
	public int compare(Shop lhs, Shop rhs) {
		double ld = Double.MAX_VALUE;
		if (flagMap.get(lhs.getId()) != null)
			ld = flagMap.get(lhs.getId()).getDistance();
		double rd = Double.MAX_VALUE;
		if (flagMap.get(rhs.getId()) != null)
			rd = flagMap.get(rhs.getId()).getDistance();

		if (ld > rd)
			return 1;
		else if (ld < rd)
			return -1;
		else
			return 0;
	}

}
