package com.emazdoor.attendanceapp.customlistview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.emazdoor.attendanceapp.R;
import com.emazdoor.utils.JExcelClass;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class CustomClass extends Activity {

	ListView listView;
	List<ListModel> listModel;
	AttendanceCustomListView attendanceCustomListView;
	JExcelClass jXl;
	Bundle bundle;
	DataLoader data;
	String temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_listview);
		listView = (ListView) findViewById(R.id.list);
		data = new DataLoader();
		String extras = "";
		bundle = getIntent().getExtras();
		if (bundle != null) {
			extras = bundle.getString("button");
		}
		listModel = new ArrayList<ListModel>();
		jXl = new JExcelClass();
		List<String> abc = jXl.readExcelSheet(extras + ".xls", 0);
//		String date = this.getDate();
		List<String> roll = jXl.readExcelSheet(extras+".xls", 1);
		Object[] arr = roll.toArray();
		System.out.println(arr.length);
		int i=0;
		for (String temp : abc) {
			listModel.add(new ListModel(temp, arr[i].toString()));
			i++;
		}
		attendanceCustomListView = new AttendanceCustomListView(this,
				R.layout.layout_list_content, listModel);
		listView.setAdapter(attendanceCustomListView);
		temp = extras + ".xls";
	}

	public String getDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.submit, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Map<Integer, ListModel> map = new LinkedHashMap<Integer, ListModel>();
		switch (item.getItemId()) {
		case R.id.submit:
			// for (ListModel list : AttendanceCustomListView.attenList) {
			// System.out.println(list.getName() + " " + list.getAttenStatus()
			// + " <<");
			// }
			for (ListModel list : AttendanceCustomListView.attenList) {
				map.put(list.getPosition(), list);
			}
			AttendanceCustomListView.attenList.clear();
			AttendanceCustomListView.attenList.addAll(map.values());
			data.execute(new JExcelClass());
			this.finish();
			return true;
		}
		return false;
	}

	private class DataLoader extends AsyncTask<JExcelClass, Void, Void> {

		@Override
		protected Void doInBackground(JExcelClass... params) {
			for (ListModel list : AttendanceCustomListView.attenList) {
				System.out.println(list.getName() + " " + list.getAttenStatus()
						+ " >>");
				params[0].writeToExisting(temp, list.getPosition() + 1,
						params[0].findEmptyRow(list.getPosition() + 1,
								temp), list.getAttenStatus());
			}
			return null;
		}

	}
}
