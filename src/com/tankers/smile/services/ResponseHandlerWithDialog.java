package com.tankers.smile.services;

import android.app.Dialog;
import android.os.Looper;

public abstract class ResponseHandlerWithDialog<T> extends ResponseHandler<T> {
	private Dialog dialog;

	public ResponseHandlerWithDialog(Dialog dialog) {
		super();
		this.dialog = dialog;
	}
	
	public ResponseHandlerWithDialog(Looper looper, Dialog dialog) {
		super(looper);
		this.dialog = dialog;
	}
	
	@Override
	protected void onResponse(T response) {
		if (dialog != null)
			dialog.dismiss();
	}
}
