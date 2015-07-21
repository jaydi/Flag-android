package com.tankers.smile.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.tankers.smile.R;

public class JobPickerFragment extends DialogFragment {
	protected OnJobItemClickListener listener;

	public interface OnJobItemClickListener {
		public abstract void onJobItemClick(int position);
	}

	public void setOnJobItemClickListener(OnJobItemClickListener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setItems(R.array.job, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				listener.onJobItemClick(which);
			}

		});
		return builder.create();
	}
}
