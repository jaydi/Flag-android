package com.tankers.smile.services;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class ResponseHandler<T> extends Handler {
	protected abstract void onResponse(T response);
	
	public ResponseHandler() {
		super();
	}
	public ResponseHandler(Looper looper) {
		super(looper);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final void handleMessage(Message m) {
		onResponse((T) m.obj);
	}
}
