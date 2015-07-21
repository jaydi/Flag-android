package com.tankers.smile.utils;

import java.util.Comparator;

import android.location.Location;

import com.tankers.smile.models.Flag;

public class FlagDistanceComparator implements Comparator<Flag> {
	private Location location;

	public FlagDistanceComparator(Location location) {
		super();
		this.location = location;
	}

	@Override
	public int compare(Flag lhs, Flag rhs) {
		if (location == null || lhs == null || rhs == null)
			return 0;
		
		double distanceL = LocationUtils.metricDistance(location.getLatitude(), location.getLongitude(), lhs.getLat(), lhs.getLon());
		double distanceR = LocationUtils.metricDistance(location.getLatitude(), location.getLongitude(), rhs.getLat(), rhs.getLon());
		
		lhs.setDistance(distanceL);
		rhs.setDistance(distanceR);
		
		return (distanceL < distanceR)? -1 : 0;
	}

}
