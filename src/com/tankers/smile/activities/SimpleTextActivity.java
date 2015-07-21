package com.tankers.smile.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.PP;
import com.tankers.smile.models.TOU;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;
import com.tankers.smile.utils.ResourceUtils;

public class SimpleTextActivity extends SubCategoryActivity {
	public static final int REQUEST_TOU = 100;
	public static final int REQUEST_PP = 200;
	public static final String EXTRA_REQUEST_CODE_TEXT = "com.tankers.smile.extra.request.text";
	public static final String EXTRA_SIMPLE_TEXT = "com.tankers.smile.extra.simple.text";

	private int requestCode;
	private String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_text);
		viewId = Log.VIEW_SIMPLE_TEXT;

		if (savedInstanceState == null) {
			requestCode = getIntent().getIntExtra(EXTRA_REQUEST_CODE_TEXT, 0);
			text = getIntent().getStringExtra(EXTRA_SIMPLE_TEXT);
			if (text != null)
				showText();
			else
				getText();
		}
	}

	private void getText() {
		switch (requestCode) {
		case REQUEST_TOU:
			getActionBar().setTitle(ResourceUtils.getString(R.string.tou));
			getTOU();
			break;
		case REQUEST_PP:
			getActionBar().setTitle(ResourceUtils.getString(R.string.pp));
			getPP();
			break;
		}
	}

	private void getTOU() {
		NetworkInter.getTOU(new ResponseHandler<TOU>() {

			@Override
			protected void onResponse(TOU response) {
				if (response == null)
					return;

				text = response.getTou().getValue();
				showText();
			}

		});
	}

	private void getPP() {
		NetworkInter.getPP(new ResponseHandler<PP>() {

			@Override
			protected void onResponse(PP response) {
				if (response == null)
					return;

				text = response.getPp().getValue();
				showText();
			}

		});
	}

	private void showText() {
		TextView textView = (TextView) findViewById(R.id.text_simple_text_text);
		textView.setText(text);
	}

}
