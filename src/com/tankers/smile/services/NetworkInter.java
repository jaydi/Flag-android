package com.tankers.smile.services;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.gson.GsonFactory;
import com.tankers.smile.R;
import com.tankers.smile.app.Cache;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.CheckinForm;
import com.tankers.smile.models.FeedbackMessage;
import com.tankers.smile.models.InvitationForm;
import com.tankers.smile.models.ItemViewPair;
import com.tankers.smile.models.Like;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Redemption;
import com.tankers.smile.models.RetainForm;
import com.tankers.smile.models.Reward;
import com.tankers.smile.models.UserForm;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.apis.FlagClient;

public class NetworkInter {
	private static FlagClient client;

	static {
		FlagClient.Builder builder = new FlagClient.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		client = builder.build();
	}

	public static boolean isNetworkOnline() {
		boolean status = false;

		try {
			ConnectivityManager cm = (ConnectivityManager) DalshopApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			} else {
				netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return status;
	}

	public static <T> void getImage(final View loader, final ImageView imageView, String url, int width, int height, final boolean animate) {
		if (url == null || url.isEmpty())
			return;

		StringBuilder sb = new StringBuilder(url);
		if (sb.indexOf("?") != -1)
			sb.append("&width=").append(width).append("&height=").append(height);
		url = sb.toString();
		Bitmap bitmap = Cache.getBitmapItem(url);

		if (imageView != null && bitmap != null) {
			imageView.setImageBitmap(bitmap);
			if (loader != null)
				loader.setVisibility(View.GONE);
		} else
			ThreadManager.execute(new ImageLoadWork(url, width, height), new ResponseHandler<Bitmap>() {

				@Override
				protected void onResponse(Bitmap response) {
					if (loader != null)
						loader.setVisibility(View.GONE);

					if (imageView != null && response != null) {
						if (animate) {
							Animation fadeIn = AnimationUtils.loadAnimation(DalshopApplication.getInstance(), R.anim.fade_in);
							imageView.startAnimation(fadeIn);
						}
						imageView.setImageBitmap(response);
					} else if (response == null)
						System.out.println("response null");
				}

			});
	}

	public static <T> void guestUser(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().guest().execute();
			}

		}, handler);
	}

	public static <T> void insertUser(ResponseHandler<T> handler, final UserForm userForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().insert(userForm).execute();
			}

		}, handler);
	}

	public static <T> void getUser(ResponseHandler<T> handler, final UserForm userForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().get(userForm).execute();
			}

		}, handler);
	}

	public static <T> void deleteUser(ResponseHandler<T> handler, final long userId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().delete(userId).execute();
			}

		}, handler);
	}

	public static <T> void retainUser(ResponseHandler<T> handler, final RetainForm retainForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().retain(retainForm).execute();
			}

		}, handler);
	}

	public static <T> void phoneTest(ResponseHandler<T> handler, final UserInfo userInfo) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.userInfos().phoneTest(userInfo).execute();
			}

		}, handler);
	}

	public static <T> void phoneInsert(ResponseHandler<T> handler, final UserInfo userInfo) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.userInfos().phoneInsert(userInfo).execute();
			}

		}, handler);
	}

	public static <T> void getUserInfo(ResponseHandler<T> handler, final long userId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.userInfos().get(userId).execute();
			}

		}, handler);
	}

	public static <T> void updateUserInfo(ResponseHandler<T> handler, final UserInfo userInfo) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.userInfos().update(userInfo).execute();
			}

		}, handler);
	}

	public static <T> void getFlagListAll(ResponseHandler<T> handler, final long tag) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.flags().listAll(tag).execute();
			}

		}, handler);
	}

	public static <T> void getFlagListByShop(ResponseHandler<T> handler, final long shopId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.flags().listByShop(shopId).execute();
			}

		}, handler);
	}

	public static <T> void getFlagListByItem(ResponseHandler<T> handler, final long itemId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.flags().listByItem(itemId).execute();
			}

		}, handler);
	}

	public static <T> void getFlagListByReward(ResponseHandler<T> handler, final long userId, final double lat, final double lon) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.flags().listByReward(userId, lat, lon).execute();
			}

		}, handler);
	}

	public static <T> void startShops(ResponseHandler<T> handler, final long userId, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().start(userId, mark).execute();
			}

		}, handler);
	}

	public static <T> void getShop(ResponseHandler<T> handler, final long userId, final long id) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().get(userId, id).execute();
			}

		}, handler);
	}

	public static <T> void getShopList(ResponseHandler<T> handler, final long userId, final List<Long> ids) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().list(userId, ids).execute();
			}

		}, handler);
	}

	public static <T> void getShopListByReward(ResponseHandler<T> handler, final long userId, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().listByReward(userId, mark).execute();
			}

		}, handler);
	}

	public static <T> void getShopRecommended(ResponseHandler<T> handler, final long userId, final List<Long> shopIds) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().recommend(userId, shopIds).execute();
			}

		}, handler);
	}

	public static <T> void getItem(ResponseHandler<T> handler, final long userId, final long itemId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().get(userId, itemId).execute();
			}

		}, handler);
	}

	public static <T> void initItems(ResponseHandler<T> handler, final long userId, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				int tryCount = 0;
				try {
					return (T) client.items().init(userId, mark).execute();
				} catch (GoogleJsonResponseException e) {
					if (tryCount < 3) {
						tryCount++;
						return (T) client.items().init(userId, mark).execute();
					} else
						return null;
				}
			}

		}, handler);
	}

	public static <T> void itemList(ResponseHandler<T> handler, final long userId, final long shopId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().list(userId, shopId).execute();
			}

		}, handler);
	}

	public static <T> void itemListByUser(ResponseHandler<T> handler, final long userId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().listByUser(userId).execute();
			}

		}, handler);
	}

	public static <T> void itemListByReward(ResponseHandler<T> handler, final long userId, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().listByReward(userId, mark).execute();
			}

		}, handler);
	}

	public static <T> void itemListByItem(ResponseHandler<T> handler, final long userId, final long itemId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().listByItem(userId, itemId).execute();
			}

		}, handler);
	}

	public static <T> void likeItem(ResponseHandler<T> handler, final Like like) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.likes().insert(like).execute();
			}

		}, handler);
	}

	public static <T> void unlikeItem(ResponseHandler<T> handler, final long userId, final long itemId, final int type) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.likes().delete(userId, itemId, type).execute();
			}

		}, handler);
	}

	public static <T> void claimReward(ResponseHandler<T> handler, final Reward reward) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().insert(reward).execute();
			}

		}, handler);
	}

	public static <T> void checkin(ResponseHandler<T> handler, final CheckinForm checkinForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().checkin(checkinForm).execute();
			}

		}, handler);
	}

	public static <T> void invited(ResponseHandler<T> handler, final InvitationForm invitationForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().invited(invitationForm).execute();
			}

		}, handler);
	}

	public static <T> void rewardList(ResponseHandler<T> handler, final long userId, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().list(userId, mark).execute();
			}

		}, handler);
	}

	public static <T> void getInviteRewardValue(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().getInviteRewardValue().execute();
			}

		}, handler);
	}

	public static <T> void redeemList(ResponseHandler<T> handler, final int mark) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.redeems().list(mark).execute();
			}

		}, handler);
	}

	public static <T> void redeem(ResponseHandler<T> handler, final Redemption redemption) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.rewards().redeem(redemption).execute();
			}

		}, handler);
	}

	public static <T> void getVersion(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.apps().getVersion().execute();
			}

		}, handler);
	}

	public static <T> void getNotice(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.apps().getNotice().execute();
			}

		}, handler);
	}

	public static <T> void getTOU(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.apps().getTOU().execute();
			}

		}, handler);
	}

	public static <T> void getPP(ResponseHandler<T> handler) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.apps().getPP().execute();
			}

		}, handler);
	}

	public static <T> void sendFeedback(ResponseHandler<T> handler, final FeedbackMessage feedbackMessage) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.apps().feedback(feedbackMessage).execute();
			}

		}, handler);
	}

	public static void log(final Log log) {
		ThreadManager.execute(new Work<Log>() {

			@Override
			public Log work() throws IOException {
				return (Log) client.logs().insert(log).execute();
			}

		}, null);
	}

	public static <T> void logItemViewPair(final ItemViewPair pair) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.logs().insertItemViewPair(pair).execute();
			}

		}, null);
	}
}
