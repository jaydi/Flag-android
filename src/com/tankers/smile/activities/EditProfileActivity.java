package com.tankers.smile.activities;

import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.fragments.JobPickerFragment.OnJobItemClickListener;
import com.tankers.smile.fragments.SexPickerFragment.OnSexItemClickListener;
import com.tankers.smile.models.Log;
import com.tankers.smile.models.UserInfo;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.services.ResponseHandlerWithDialog;
import com.tankers.smile.utils.DialogUtils;
import com.tankers.smile.utils.ResourceUtils;

public class EditProfileActivity extends BaseActivity implements OnSexItemClickListener, OnDateSetListener, OnJobItemClickListener {
	private UserInfo userInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		viewId = Log.VIEW_EDIT_PROFILE;

		userInfo = new UserInfo();
		userInfo.setUserId(LocalUser.getUser().getId());
	}

	public void pickSex(View view) {
		DialogUtils.showSexPicker(getFragmentManager(), this);
	}

	@Override
	public void onSexItemClick(int position) {
		TextView textSex = (TextView) findViewById(R.id.text_edit_profile_sex);
		String[] sexArray = ResourceUtils.getStringArray(R.array.sex);

		if (position == 0) {
			userInfo.setSex(1);
			textSex.setText(sexArray[0]);
		} else {
			userInfo.setSex(2);
			textSex.setText(sexArray[1]);
		}
	}

	public void pickBirthYear(View view) {
		DialogUtils.showDatePicker(getFragmentManager(), this, 0);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, monthOfYear, dayOfMonth);
		userInfo.setBirth(cal.getTimeInMillis());
		
		TextView textBirth = (TextView) findViewById(R.id.text_edit_profile_birth);
		textBirth.setText(DateUtils.getRelativeTimeSpanString(cal.getTimeInMillis()));
	}

	public void pickJob(View view) {
		DialogUtils.showJobPicker(getFragmentManager(), this);
	}

	@Override
	public void onJobItemClick(int position) {
		userInfo.setJob(position);

		String[] jobArray = ResourceUtils.getStringArray(R.array.job);
		TextView textJob = (TextView) findViewById(R.id.text_edit_profile_job);
		textJob.setText(jobArray[position]);
	}

	public void editProfile(View view) {
		if (!validUserInfo())
			return;
		
		NetworkInter.updateUserInfo(new ResponseHandlerWithDialog<UserInfo>(DialogUtils.getWaitingDialog(this)) {

			@Override
			protected void onResponse(UserInfo response) {
				super.onResponse(response);
				if (response == null)
					return;
				
				Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
			}
			
		}, userInfo);
	}

	private boolean validUserInfo() {
		if (userInfo.isEmpty())
			return false;
		
		return true;
	}

}