package com.tankers.smile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tankers.smile.R;

public class CheckinGuideFragment extends Fragment {
	private int position;

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;

		switch (position) {
		case 0:
			view = inflater.inflate(R.layout.fragment_checkin_guide_shop, null, false);
			break;
		case 1:
			view = inflater.inflate(R.layout.fragment_checkin_guide_ble, null, false);
			break;
		case 2:
			view = inflater.inflate(R.layout.fragment_checkin_guide_visit, null, false);
			break;
		default:
			view = inflater.inflate(R.layout.fragment_checkin_guide_kinds, null, false);
			break;
		}

		return view;
	}
}
