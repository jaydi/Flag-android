package com.tankers.smile.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tankers.smile.R;
import com.tankers.smile.models.Log;
import com.tankers.smile.services.NetworkInter;

public class ImageActivity extends BaseActivity {
	public static final String EXTRA_URL = "com.tankers.smile.extra.url";
	
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		viewId = Log.VIEW_IMAGE;
			
		if (savedInstanceState == null)
			url = getIntent().getStringExtra(EXTRA_URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		loadImage();
	}

	private void loadImage() {
		ImageView imageView = (ImageView) findViewById(R.id.image_image_image);
		View loader = findViewById(R.id.progressbar_image_image);

		NetworkInter.getImage(loader, imageView, url, 0, 0, true);
	}
}
