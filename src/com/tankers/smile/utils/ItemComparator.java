package com.tankers.smile.utils;

import java.util.Comparator;

import com.tankers.smile.models.Item;

public class ItemComparator implements Comparator<Item> {

	@Override
	public int compare(Item lhs, Item rhs) {
		if (lhs.isLiked())
			return -1;
		else if (rhs.isLiked())
			return 0;
		else
			return 0;
	}

}
