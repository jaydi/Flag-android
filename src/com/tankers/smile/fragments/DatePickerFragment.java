package com.tankers.smile.fragments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DatePickerFragment extends DialogFragment {
	private OnDateSetListener listener;
	private long preset;

	public void setOnDateSetListener(OnDateSetListener listener) {
		this.listener = listener;
	}

	public void setPreset(long preset) {
		this.preset = preset;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();

		if (preset != 0)
			c.setTimeInMillis(preset);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), listener, year, month, day);
	}

}
