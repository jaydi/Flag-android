package com.tankers.smile.models;

import java.util.Calendar;
import java.util.Date;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;
import com.tankers.smile.R;
import com.tankers.smile.app.LocalUser;
import com.tankers.smile.utils.ResourceUtils;

public class UserInfo extends BaseModel {
	@Key
	@JsonString
	private Long userId;

	@Key
	private String pushToken;

	@Key
	private String phone;

	@Key
	private int sex;

	@Key
	private long birth;

	@Key
	private int job;

	@Key
	private int os;

	@Key
	private long lastTime;

	@Key
	private String recoEmail;

	public UserInfo() {
		super();
	}

	public UserInfo(long userId, String pushToken) {
		super();
		this.userId = userId;
		this.pushToken = pushToken;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSexString() {
		String[] sexArray = ResourceUtils.getStringArray(R.array.sex);
		if (sex == 2)
			return sexArray[1];
		else
			return sexArray[0];
	}

	public long getBirth() {
		return birth;
	}

	public void setBirth(long birth) {
		this.birth = birth;
	}

	public int getAge() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(birth));
		return Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public String getJobString() {
		String[] jobArray = ResourceUtils.getStringArray(R.array.job);
		return jobArray[job];
	}

	public int getOs() {
		return os;
	}

	public void setOs(int os) {
		this.os = os;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public String getRecoEmail() {
		return recoEmail;
	}

	public void setRecoEmail(String recoEmail) {
		this.recoEmail = recoEmail;
	}

	@Override
	public boolean isEmpty() {
		if (sex == 0 || birth == 0 || job == 0)
			return true;
		else
			return super.isEmpty();
	}

	public static UserInfo checkingInstance() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(LocalUser.getUser().getId());
		userInfo.setOs(Integer.valueOf("1" + android.os.Build.VERSION.SDK_INT));
		userInfo.setLastTime(new Date().getTime());
		return userInfo;
	}
}
