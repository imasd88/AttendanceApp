package com.emazdoor.attendanceapp;

import jxl.write.Label;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.emazdoor.attendanceapp.customlistview.CustomClass;
import com.emazdoor.utils.FTPUpload;
import com.emazdoor.utils.FileChooser;

public class MainActivity extends Activity implements OnClickListener {

	Button btn1, btn2, btn3, btn4, btn5;
	TextView lblDesc;
	Context context;
	SharedPreferences prefs;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		Init();
	}

	@Override
	protected void onResume() {
		super.onResume();
		btn1.setText(prefs.getString("button1", "No File Found"));
		btn2.setText(prefs.getString("button2", "No File Found"));
		btn3.setText(prefs.getString("button3", "No File Found"));
		btn4.setText(prefs.getString("button4", "No File Found"));
	}

	private void Init() {
		context = this;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		btn5 = (Button) findViewById(R.id.button5);
		lblDesc = (TextView) findViewById(R.id.tvDesc);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn1.setText(prefs.getString("button1", "No File Found"));
		btn2.setText(prefs.getString("button2", "No File Found"));
		btn3.setText(prefs.getString("button3", "No File Found"));
		btn4.setText(prefs.getString("button4", "No File Found"));
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.buttons:
			startActivity(new Intent(context, FileChooser.class));
			return true;
		case R.id.logout:
			finish();
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.buttons, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent(context, CustomClass.class);
			intent.putExtra("button", getPreferences("button1"));
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(context, CustomClass.class);
			intent.putExtra("button", getPreferences("button2"));
			startActivity(intent);
			break;
		case R.id.button3:
			intent = new Intent(context, CustomClass.class);
			intent.putExtra("button", getPreferences("button3"));
			startActivity(intent);
			break;
		case R.id.button4:
			intent = new Intent(context, CustomClass.class);
			intent.putExtra("button", getPreferences("button4"));
			startActivity(intent);
			break;
		case R.id.button5:
			startActivity(new Intent(context, FTPUpload.class));
			break;
		}

	}

	private String getPreferences(String prefString) {
		String temp = prefs.getString(prefString, "");
		return temp;
	}

}