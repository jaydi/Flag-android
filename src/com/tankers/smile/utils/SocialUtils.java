package com.tankers.smile.utils;

import android.app.Activity;

import com.kakao.AppActionBuilder;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoTalkLinkMessageBuilder;
import com.kakao.internal.Action;
import com.tankers.smile.R;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Item;
import com.tankers.smile.models.Shop;

public class SocialUtils {
	public static void inviteFriendKakao(Activity activity) {
		KakaoLink kakaoLink = DalshopApplication.getInstance().getKakaoLink();
		KakaoTalkLinkMessageBuilder mBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

		try {
			mBuilder.addText(ResourceUtils.getString(R.string.message_invitation));
			mBuilder.addAppButton(ResourceUtils.getString(R.string.move_to_app));
			kakaoLink.sendMessage(mBuilder.build(), activity);
		} catch (KakaoParameterException e) {
			e.printStackTrace();
		}
	}

	public static void shareShopKakao(Activity activity, Shop shop) {
		KakaoLink kakaoLink = DalshopApplication.getInstance().getKakaoLink();
		KakaoTalkLinkMessageBuilder mBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

		try {
			mBuilder.addText(shop.getName() + "\n" + StringUtils.shortify(shop.getDescription()));
			Action action = new AppActionBuilder().setAndroidExecuteURLParam("action=shop&shopId=" + shop.getId())
					.setIOSExecuteURLParam("action=shop&shopId=" + shop.getId()).build();
			mBuilder.addAppButton(ResourceUtils.getString(R.string.move_to_app), action);
			kakaoLink.sendMessage(mBuilder.build(), activity);
		} catch (KakaoParameterException e) {
			e.printStackTrace();
		}
	}

	public static void shareItemKakao(Activity activity, Shop shop, Item item) {
		KakaoLink kakaoLink = DalshopApplication.getInstance().getKakaoLink();
		KakaoTalkLinkMessageBuilder mBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

		try {
			mBuilder.addText("[" + shop.getName() + "]" + "\n" + item.getName());
			Action action = new AppActionBuilder().setAndroidExecuteURLParam("action=item&shopId=" + shop.getId() + "&itemId=" + item.getId())
					.setIOSExecuteURLParam("action=item&shopId=" + shop.getId() + "&itemId=" + item.getId()).build();
			mBuilder.addAppButton(ResourceUtils.getString(R.string.move_to_app), action);
			kakaoLink.sendMessage(mBuilder.build(), activity);
		} catch (KakaoParameterException e) {
			e.printStackTrace();
		}
	}
}
