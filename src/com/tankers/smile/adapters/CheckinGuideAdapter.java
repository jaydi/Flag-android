package com.tankers.smile.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tankers.smile.fragments.CheckinGuideFragment;

public class CheckinGuideAdapter extends FragmentPagerAdapter {
	private static final int NUM_PAGES = 3;

	public CheckinGuideAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		CheckinGuideFragment f = new CheckinGuideFragment();
		f.setPosition(position);
		return f;
	}

	@Override
	public int getCount() {
		return NUM_PAGES;
	}

}
