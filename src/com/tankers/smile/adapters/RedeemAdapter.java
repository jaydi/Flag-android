package com.tankers.smile.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tankers.smile.R;
import com.tankers.smile.models.Redeem;
import com.tankers.smile.services.NetworkInter;
import com.tankers.smile.utils.ResourceUtils;

public class RedeemAdapter extends BaseAdapter {
	private Context context;
	private RedeemClickInter redeemClickInter;
	private List<Redeem> redeems;

	public interface RedeemClickInter {
		public abstract void onClickRedeem(Redeem redeem);
	}

	public RedeemAdapter(Context context, List<Redeem> redeems) {
		super();
		this.context = context;
		this.redeemClickInter = (RedeemClickInter) context;
		this.redeems = redeems;
	}

	@Override
	public int getCount() {
		return redeems.size();
	}

	@Override
	public Object getItem(int position) {
		return redeems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return redeems.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Redeem redeem = (Redeem) getItem(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_redeem_layout, parent, false);

		View box = view.findViewById(R.id.linear_adapted_redeem_box);
		box.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				redeemClickInter.onClickRedeem(redeem);
			}

		});

		ImageView image = (ImageView) view.findViewById(R.id.image_adapted_redeem_image);
		NetworkInter.getImage(null, image, redeem.getImageUrl(), 120, 120, true);

		TextView vendor = (TextView) view.findViewById(R.id.text_adapted_redeem_vendor);
		vendor.setText(redeem.getVendor());

		TextView name = (TextView) view.findViewById(R.id.text_adapted_redeem_name);
		name.setText(redeem.getName());

		TextView dal = (TextView) view.findViewById(R.id.text_adapted_redeem_price);
		dal.setText(redeem.getPrice() + ResourceUtils.getString(R.string.dal));

		return view;
	}

}
