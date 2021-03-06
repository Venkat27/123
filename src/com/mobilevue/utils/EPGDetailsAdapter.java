package com.mobilevue.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobilevue.data.ProgramGuideData;
import com.mobilevue.vod.R;

public class EPGDetailsAdapter extends BaseAdapter {
	private List<ProgramGuideData> data;
	private static LayoutInflater inflater = null;

	public EPGDetailsAdapter(Activity activity, List<ProgramGuideData> data) {
		this.data = data;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.fragment_epg_details_list_row, null);

		final ProgramGuideData pData = data.get(position);

		TextView prog_title = (TextView) vi
				.findViewById(R.id.epg_details_list_row_new_tv_prog_title);
		TextView start_time = (TextView) vi
				.findViewById(R.id.epg_details_list_row_new_tv_start_time);
		prog_title.setText(pData.getProgramTitle());
		SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
		Date sTime= null;
		try {
			sTime = tf.parse(pData.getStartTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start_time.setText(tf.format(sTime));
		return vi;
	}
}
