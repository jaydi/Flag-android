package com.tankers.smile.comparators;

import java.util.Comparator;

import android.location.Location;

import com.tankers.smile.models.Flag;
import com.tankers.smile.utils.LocationUtils;

public class FlagComparator implements Comparator<Flag> {
	private Location l;

	public FlagComparator(Location l) {
		super();
		this.l = l;
	}

	@Override
	public int compare(Flag lhs, Flag rhs) {
		double ld = LocationUtils.metricDistance(l.getLatitude(), l.getLongitude(), lhs.getLat(), lhs.getLon());
		double rd = LocationUtils.metricDistance(l.getLatitude(), l.getLongitude(), rhs.getLat(), rhs.getLon());
		
		lhs.setDistance(ld);
		rhs.setDistance(rd);

		if (ld > rd)
			return 1;
		else if (ld < rd)
			return -1;
		else
			return 0;
	}

}
