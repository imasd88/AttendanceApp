package com.emazdoor.attendanceapp.customlistview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.emazdoor.attendanceapp.R;
import com.emazdoor.utils.JExcelClass;

public class AttendanceCustomListView extends ArrayAdapter<ListModel> {

	Context context;
	public List<ListModel> studentsList;
	ListModel listModel;
	LayoutInflater inflater;
	public static List<ListModel> attenList = new ArrayList<ListModel>();
	final JExcelClass jXl = new JExcelClass();
	int radioButtonCounter, radioButtonInitialized;

	public AttendanceCustomListView(Context context, int resource,
			List<ListModel> studentsList) {
		super(context, resource, studentsList);
		this.context = context;
		this.studentsList = studentsList;
	}

	private class ViewHolder {
		TextView name, date;
		RadioGroup radioGroup;
		RadioButton presentRDOButton, absentRDOButton, leaveRDOButton;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final int pos = position + 1;

		if (convertView == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.layout_list_content,
					parent, false);
			holder = new ViewHolder();

			// initialization here
			holder.name = (TextView) convertView.findViewById(R.id.nameListTV);
			holder.date = (TextView) convertView.findViewById(R.id.dateTV);
			holder.radioGroup = (RadioGroup) convertView
					.findViewById(R.id.attendanceRDG);
			holder.presentRDOButton = (RadioButton) convertView
					.findViewById(R.id.presentRDO);
			holder.absentRDOButton = (RadioButton) convertView
					.findViewById(R.id.absentRDO);
			holder.leaveRDOButton = (RadioButton) convertView
					.findViewById(R.id.leaveRDO);
			radioButtonCounter = 0;
			radioButtonInitialized = 0;
			holder.radioGroup
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							Integer getPosition = (Integer) group.getTag();
							ListModel listModel = studentsList.get(getPosition);

							switch (checkedId) {

							case R.id.presentRDO:
								System.out.println(getPosition);
								listModel.current = ListModel.PRESENT;
								// jXl.writeToExisting("test.xls",
								// getPosition + 1, jXl.findEmptyRow(
								// getPosition + 1, "test.xls"),
								// "P");
								attenList.add(new ListModel(studentsList.get(
										getPosition).getName(), getPosition,
										"P"));
								break;
							case R.id.absentRDO:
								System.out.println(getPosition);
								listModel.current = ListModel.ABSENT;
								// jXl.writeToExisting("test.xls",
								// getPosition + 1, jXl.findEmptyRow(
								// getPosition + 1, "test.xls"),
								// "A");
								attenList.add(new ListModel(studentsList.get(
										getPosition).getName(), getPosition,
										"A"));
								break;
							case R.id.leaveRDO:
								System.out.println(getPosition);
								listModel.current = ListModel.LEAVE;
								attenList.add(new ListModel(studentsList.get(
										getPosition).getName(), getPosition,
										"L"));
								// jXl.writeToExisting("test.xls",
								// getPosition + 1, jXl.findEmptyRow(
								// getPosition + 1, "test.xls"),
								// "L");
								break;
							default:
								listModel.current = -1;
							}
						}
					});
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// setting values here
		holder.radioGroup.setTag(new Integer(position));
		holder.name.setText(studentsList.get(position).getName());
		holder.date.setText(studentsList.get(position).getDate());

		if (studentsList.get(position).current != -1) {
			RadioButton r = (RadioButton) holder.radioGroup
					.getChildAt(studentsList.get(position).current);
			r.setChecked(true);
		} else {
			holder.radioGroup.clearCheck();
		}
		return convertView;
	}
}
