package com.tankers.smile.services.apis.rewards;

import java.io.IOException;

import com.tankers.smile.models.CheckinForm;
import com.tankers.smile.models.InvitationForm;
import com.tankers.smile.models.Redemption;
import com.tankers.smile.models.Reward;
import com.tankers.smile.services.apis.FlagClient;

public class Rewards {
	private FlagClient client;

	public Rewards(FlagClient client) {
		super();
		this.client = client;
	}

	public Insert insert(Reward reward) throws IOException {
		Insert insert = new Insert(client, reward);
		client.initialize(insert);
		return insert;
	}

	public Checkin checkin(CheckinForm checkinForm) throws IOException {
		Checkin checkin = new Checkin(client, checkinForm);
		client.initialize(checkin);
		return checkin;
	}

	public Invited invited(InvitationForm invitationForm) throws IOException {
		Invited invited = new Invited(client, invitationForm);
		client.initialize(invited);
		return invited;
	}

	public List list(long userId, int mark) throws IOException {
		List list = new List(client);
		list.setUserId(userId);
		list.setMark(mark);
		client.initialize(list);
		return list;
	}

	public Redeem redeem(Redemption redemption) throws IOException {
		Redeem redeem = new Redeem(client, redemption);
		client.initialize(redeem);
		return redeem;
	}

	public GetInviteRewardValue getInviteRewardValue() throws IOException {
		GetInviteRewardValue getValue = new GetInviteRewardValue(client);
		client.initialize(getValue);
		return getValue;
	}
}
