package com.tankers.smile.adapters;

import static com.tankers.smile.utils.ResourceUtils.getString;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Reward;
import com.tankers.smile.utils.ResourceUtils;

public class RewardAdapter extends BaseAdapter {
	private Context context;
	private List<Reward> rewards;

	public RewardAdapter(Context context, List<Reward> rewards) {
		super();
		this.context = context;
		this.rewards = rewards;
	}

	@Override
	public int getCount() {
		return rewards.size();
	}

	@Override
	public Object getItem(int position) {
		return rewards.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rewards.get(position).getTargetId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Reward reward = (Reward) getItem(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_reward_layout, parent, false);
		
		TextView rewardDate = (TextView) view.findViewById(R.id.text_adapted_reward_date);
		String dateString = DateUtils.getRelativeTimeSpanString(reward.getCreatedAt()).toString();

		if (position == 0) {
			rewardDate.setText(dateString);
			LinearLayout rewardDateLayout = (LinearLayout) view.findViewById(R.id.linear_adapted_reward_date);
			rewardDateLayout.setVisibility(View.VISIBLE);
		} else {
			Reward exReward = (Reward) getItem(position - 1);
			String exDateString = DateUtils.getRelativeTimeSpanString(exReward.getCreatedAt()).toString();
			if (!exDateString.equals(dateString)) {
				rewardDate.setText(dateString);
				LinearLayout rewardDateLayout = (LinearLayout) view.findViewById(R.id.linear_adapted_reward_date);
				rewardDateLayout.setVisibility(View.VISIBLE);
			}
		}

		TextView rewardName = (TextView) view.findViewById(R.id.text_adapted_reward_name);
		rewardName.setText(reward.getTargetName());

		TextView typeName = (TextView) view.findViewById(R.id.text_adapted_reward_type);
		if (reward.getType() == Reward.TYPE_SHOP)
			typeName.setText(getString(R.string.check_in));
		else if (reward.getType() == Reward.TYPE_ITEM)
			typeName.setText(getString(R.string.scan));
		else if (reward.getType() == Reward.TYPE_INVITATION)
			typeName.setText(getString(R.string.invitation));
		else if (reward.getType() == Reward.TYPE_INVITED)
			typeName.setText(getString(R.string.invited));
		else if (reward.getType() == Reward.TYPE_REDEMPTION)
			typeName.setText(getString(R.string.redemption));

		TextView textReward = (TextView) view.findViewById(R.id.text_adapted_reward_money);
		DecimalFormat format = new DecimalFormat("###,###,###,###");
		String rewardString = format.format(reward.getReward());
		textReward.setText(rewardString);
		
		if (reward.getType() == Reward.TYPE_REDEMPTION) {
			TextView textDal = (TextView) view.findViewById(R.id.text_adapted_reward_dal);
			
			textReward.setTextColor(ResourceUtils.getColor(R.color.red));
			textDal.setTextColor(ResourceUtils.getColor(R.color.red));
		}

		return view;
	}

}
