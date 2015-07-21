package com.tankers.smile.services;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLPeerUnverifiedException;

import android.os.Handler;
import android.os.Message;

public class ThreadManager<T> implements Runnable {
	private static ExecutorService executorService = Executors.newCachedThreadPool();

	public static <T> void execute(final Work<T> work, final ResponseHandler<T> handler) {
		executorService.execute(new ThreadManager<T>(work, handler));
	}

	private Work<T> work;
	private Handler handler;

	public ThreadManager(Work<T> work, Handler handler) {
		super();
		this.work = work;
		this.handler = handler;
	}

	private int runCount = 0;

	@Override
	public void run() {
		try {
			T response = (T) work.work();
			if (handler != null) {
				Message m = new Message();
				m.obj = response;
				handler.sendMessage(m);
			}
			runCount = 0;
		} catch (SSLPeerUnverifiedException e) {
			retry();
		} catch (SocketTimeoutException e) {
			retry();
		} catch (IOException e) {
			e.printStackTrace();
			if (handler != null)
				handler.sendEmptyMessage(0);
		}
	}

	private void retry() {
		if (runCount < 3) {
			runCount++;
			run();
		} else
			runCount = 0;
	}
}
