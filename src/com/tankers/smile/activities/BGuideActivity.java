package com.tankers.smile.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.radiusnetworks.ibeacon.IBeaconManager;
import com.tankers.smile.R;
import com.tankers.smile.models.Log;
import com.tankers.smile.utils.ResourceUtils;

public class BGuideActivity extends BaseActivity {
	private boolean able;
	private boolean on;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bguide);
		viewId = Log.VIEW_BGUIDE;

		try {
			if (IBeaconManager.getInstanceForApplication(this).checkAvailability())
				on = true;
			else
				on = false;

			able = true;
		} catch (RuntimeException e) {
			able = false;
		}

		showMessage();
	}

	private void showMessage() {
		TextView textGuide = (TextView) findViewById(R.id.text_bguide_guide);
		ImageView imageIcon = (ImageView) findViewById(R.id.image_bguide_icon);
		TextView textStatus = (TextView) findViewById(R.id.text_bguide_status);
		TextView textMessage = (TextView) findViewById(R.id.text_bguide_message);

		if (able && on) {
			textGuide.setText(ResourceUtils.getString(R.string.message_bluetooth_why));
			imageIcon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_ble_on));
			textStatus.setText(ResourceUtils.getString(R.string.message_bluetooth_status) + " ON");
			textMessage.setText(ResourceUtils.getString(R.string.message_bluetooth_no_worry));
		} else if (able && !on) {
			textGuide.setText(ResourceUtils.getString(R.string.message_bluetooth_why));
			imageIcon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_ble_off));
			textStatus.setText(ResourceUtils.getString(R.string.message_bluetooth_status) + " OFF");
			textMessage.setText(ResourceUtils.getString(R.string.message_bluetooth_no_worry));
		} else {
			textGuide.setText(ResourceUtils.getString(R.string.error_ble_not_supported));
			imageIcon.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_ble_no));
			textStatus.setText(ResourceUtils.getString(R.string.error_need_4_3));
			textMessage.setText(ResourceUtils.getString(R.string.error_but_scan_possible));
		}
	}

	public void turnOnBluetooth(View view) {
		if (able && !on) {
			BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
			on = adapter.enable();
			showMessage();
		}
	}

	public void goToMenu(View view) {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
		finish();
	}
}