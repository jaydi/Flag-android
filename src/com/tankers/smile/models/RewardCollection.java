package com.tankers.smile.models;

import java.util.List;

import com.google.api.client.util.Key;

public class RewardCollection extends BaseModel {
	@Key
	private List<Reward> rewards;

	public RewardCollection() {
		super();
	}

	public RewardCollection(List<Reward> rewards) {
		super();
		this.rewards = rewards;
	}

	public List<Reward> getRewards() {
		return rewards;
	}

	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
}
