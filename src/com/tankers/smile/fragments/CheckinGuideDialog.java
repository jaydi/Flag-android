package com.tankers.smile.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.tankers.smile.R;
import com.tankers.smile.adapters.CheckinGuideAdapter;

public class CheckinGuideDialog extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewPager view = (ViewPager) inflater.inflate(R.layout.dialog_checkin_guide_layout, container, false);
		view.setAdapter(new CheckinGuideAdapter(getChildFragmentManager()));
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		return view;
	}
}
