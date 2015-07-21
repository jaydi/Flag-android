package com.tankers.smile.utils;

import java.util.Collection;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;
import com.tankers.smile.app.DalshopApplication;

public class IBeaconUtils implements IBeaconConsumer, RangeNotifier {
	private static final String DALCON_UUID = "7ED18560-4686-43C7-A8BB-7621E22B1CC8";

	private Context context;
	private CheckInUtils checkInUtils;
	private IBeaconManager iBeaconManager;

	public IBeaconUtils(Context context, CheckInUtils checkInUtils) {
		this.context = context;
		this.checkInUtils = checkInUtils;
		iBeaconManager = IBeaconManager.getInstanceForApplication(context);
		iBeaconManager.bind(this);
	}

	public static boolean verifyBluetooth(final Context context) {
		try {
			if (!IBeaconManager.getInstanceForApplication(context).checkAvailability()) {
				// DialogUtils.bluetoothAlert(context);
				DalshopApplication.getInstance().checkBluetoothDelayed();
				return false;
			}
		} catch (RuntimeException e) {
			// DialogUtils.bluetoothLEAlert(context);
			return false;
		}

		return true;
	}

	@Override
	public Context getApplicationContext() {
		return context;
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return context.bindService(service, conn, flags);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		context.unbindService(conn);
	}

	public void unbind() {
		iBeaconManager.unBind(this);
	}

	@Override
	public void onIBeaconServiceConnect() {
		Log.d("BUTIL", "ONCONNECT");
		Region region = new Region("ApplicationRanging", null, null, null);
		try {
			iBeaconManager.startRangingBeaconsInRegion(region);
			iBeaconManager.setRangeNotifier(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
		for (IBeacon iBeacon : iBeacons) {
			System.out.println(iBeacon.getProximityUuid() + " / " + iBeacon.getMajor() + " / " + iBeacon.getMinor() + " / " + iBeacon.getAccuracy());

			if (iBeacon.getProximityUuid().toUpperCase(Locale.ENGLISH).equals(DALCON_UUID) && iBeacon.getAccuracy() < iBeacon.getMinor())
				checkInUtils.checkIn(iBeacon.getMajor());
		}
	}
}
