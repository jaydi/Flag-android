package com.tankers.smile.activities;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.Notice;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandler;

public class NoticeActivity extends SubCategoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		viewId = Log.VIEW_NOTICE;
		
		getNotice();
	}

	private void getNotice() {
		NetworkInter.getNotice(new ResponseHandler<Notice>() {

			@Override
			protected void onResponse(Notice response) {
				if (response == null)
					return;
				
				showNotice(response);
			}
			
		});
	}

	private void showNotice(Notice notice) {
		TextView textDate = (TextView) findViewById(R.id.text_notice_date);
		textDate.setText(DateUtils.getRelativeTimeSpanString(notice.getCreatedAt()));
		
		TextView textMessage = (TextView) findViewById(R.id.text_notice_message);
		textMessage.setText(notice.getMessage());
	}

}
