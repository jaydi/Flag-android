package com.tankers.smile.app;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.kakao.GlobalApplication;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.proximity.ibeacon.powersave.BackgroundPowerSaver;
import com.radiusnetworks.proximity.ibeacon.startup.BootstrapNotifier;
import com.tankers.smile.services.DBInter;
import com.tankers.smile.utils.CheckInUtils;
import com.tankers.smile.utils.IBeaconUtils;
import com.tankers.smile.utils.LocationUtils;

public class DalshopApplication extends GlobalApplication implements BootstrapNotifier {
	public static final String FACEBOOK_APP_ID = "1375778686044438";

	private static DalshopApplication instance;

	@SuppressWarnings("unused")
	private BackgroundPowerSaver backgroundPowerSaver;
	private IBeaconUtils iBeaconUtils;
	private LocationUtils locationUtils;
	private KakaoLink kakaoLink;

	// application global methods

	@Override
	public void onCreate() {
		super.onCreate();
		initGlobalApplication();
		DBInter.initDB(this);
		setKakaoLink();
		backgroundPowerSaver = new BackgroundPowerSaver(this);
	}

	@Override
	public void onTerminate() {
		instance = null;
		super.onTerminate();
	}

	private void initGlobalApplication() {
		instance = this;
	}

	public final static DalshopApplication getInstance() {
		return instance;
	}

	public String getVersion() {
		PackageInfo packageInfo = null;
		try {
			packageInfo = getPackageInfo();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		if (packageInfo == null)
			return "null";
		else
			return packageInfo.versionName;
	}

	public int getAppVersionCode() {
		try {
			PackageInfo packageInfo = getPackageInfo();
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	private PackageInfo getPackageInfo() throws NameNotFoundException {
		return getPackageManager().getPackageInfo(getPackageName(), 0);
	}

	// kakao link

	private void setKakaoLink() {
		try {
			kakaoLink = KakaoLink.getKakaoLink();
		} catch (KakaoParameterException e) {
			e.printStackTrace();
		}
	}

	public KakaoLink getKakaoLink() {
		return kakaoLink;
	}

	// BLE check work

	@SuppressLint("HandlerLeak")
	private Handler bleCheckHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (IBeaconManager.getInstanceForApplication(DalshopApplication.this).checkAvailability())
				bindBeaconUtils();
			else
				checkBluetoothDelayed();
		}

	};

	public void checkBluetoothDelayed() {
		bleCheckHandler.sendEmptyMessageDelayed(0, 5000);
	}

	// location service binding

	public void initLocationUtils() {
		if (locationUtils == null) {
			Log.d("LOCUTIL", "INIT TRIGGER");
			locationUtils = new LocationUtils();
			locationUtils.init();
		} else if (!locationUtils.isConnected()) {
			Log.d("LOCUTIL", "INIT TRIGGER");
			locationUtils.init();
		} else
			locationUtils.searchCheck();
	}

	public void finLocationUtils() {
		if (locationUtils != null) {
			locationUtils.fin();
			locationUtils = null;
		}
	}

	public LocationUtils getLocationUtils() {
		if (locationUtils == null)
			initLocationUtils();
		return locationUtils;
	}

	// beacon utility binding

	public void bindBeaconUtils() {
		if (iBeaconUtils == null) {
			Log.d("BUTIL", "BIND TRIGGER");
			iBeaconUtils = new IBeaconUtils(this, new CheckInUtils(this));
		}
	}

	public void unbindBeaconUtils() {
		if (iBeaconUtils != null) {
			iBeaconUtils.unbind();
			iBeaconUtils = null;
		}
	}

	public IBeaconUtils getIBeaconUtils() {
		return iBeaconUtils;
	}

	// below for BootstrapNotifier

	@Override
	public void didEnterRegion(Region region) {
	}

	@Override
	public void didExitRegion(Region region) {
	}

	@Override
	public void didDetermineStateForRegion(int i, Region region) {
	}
}
