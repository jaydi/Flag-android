package com.tankers.smile.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.app.DalshopApplication;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Version;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;

public class VersionActivity extends SubCategoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version);
		viewId = Log.VIEW_VERSION;
		
		getVersion();
	}

	private void getVersion() {
		NetworkInter.getVersion(new ResponseHandler<Version>() {

			@Override
			protected void onResponse(Version response) {
				if (response == null)
					return;
				
				showVersion(response);
			}
			
		});
	}

	private void showVersion(Version version) {
		TextView textNewest = (TextView) findViewById(R.id.text_version_newest);
		textNewest.setText(R.string.newest_version);
		textNewest.append(": " + version.getVersion());
		
		TextView textCurrent = (TextView) findViewById(R.id.text_version_current);
		textCurrent.setText(R.string.current_version);
		textCurrent.append(": " + DalshopApplication.getInstance().getVersion());
	}

}
