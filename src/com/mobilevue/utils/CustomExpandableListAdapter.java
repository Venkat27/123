package com.mobilevue.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mobilevue.data.PlansData;
import com.mobilevue.vod.PlanActivity;
import com.mobilevue.vod.R;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<PlansData> _planData;
	private ArrayList<RadioButton> _arrRadioButton;
	private int rb_seed =9001;

	public CustomExpandableListAdapter(Context context, List<PlansData> planData) {
		this._context = context;
		this._planData = planData;
		this._arrRadioButton = new ArrayList<RadioButton>();
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._planData.get(groupPosition).getServiceData()
				.get(childPosititon).getserviceDescription();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.plan_list_item, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.plan_list_item_tv);

		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._planData.get(groupPosition).getServiceData().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._planData.get(groupPosition).getPlanDescription();
	}

	@Override
	public int getGroupCount() {
		return this._planData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.plan_list_group, null);
		}
		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.plan_list_plan_tv);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		RadioButton rb1 = (RadioButton) convertView
				.findViewById(R.id.plan_list_plan_rb);
		rb1.setTag(rb_seed+groupPosition);
		_arrRadioButton.add(rb1);
		rb1.setFocusable(false);
		
		rb1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for(RadioButton rb: _arrRadioButton){
					if(rb.getTag()!= v.getTag()){
						rb.setChecked(false);
		         }
				}
				PlanActivity.selectedGroupItem = groupPosition;
			}
		});
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	class ViewHolder {
		TextView textView;
		RadioButton radioButton;
	}
}
