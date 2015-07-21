package com.tankers.smile.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.tankers.smile.R;

public class SexPickerFragment extends DialogFragment {
	protected OnSexItemClickListener listener;

	public interface OnSexItemClickListener {
		public abstract void onSexItemClick(int position);
	}

	public void setOnSexItemClickListener(OnSexItemClickListener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setItems(R.array.sex, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				listener.onSexItemClick(which);
			}

		});
		return builder.create();
	}
}
