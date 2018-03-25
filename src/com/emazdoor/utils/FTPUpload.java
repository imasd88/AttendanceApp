package com.emazdoor.utils;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.emazdoor.attendanceapp.R;

public class FTPUpload extends Activity implements OnClickListener {

	/********* work only for Dedicated IP ***********/
	static String FTP_HOST = "50.63.92.56";

	/********* FTP USERNAME ***********/
	static String FTP_USER = "XXXXXX";

	/********* FTP PASSWORD ***********/
	static String FTP_PASS = "XXXXXXX";

	CheckBox cBtn1, cBtn2, cBtn3, cBtn4;
	Button uploadBtn;
	Context context;
	SharedPreferences prefs;
	List<String> pathList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_ftpupload);
		Init();
	}

	private void Init() {
		context = this;
		pathList = new ArrayList<String>();
		cBtn1 = (CheckBox) findViewById(R.id.btn1CHK);
		cBtn2 = (CheckBox) findViewById(R.id.btn2CHK);
		cBtn3 = (CheckBox) findViewById(R.id.btn3CHK);
		cBtn4 = (CheckBox) findViewById(R.id.btn4CHK);
		uploadBtn = (Button) findViewById(R.id.upBtn);
		uploadBtn.setOnClickListener(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		cBtn1.setText(prefs.getString("button1", "No File Found"));
		cBtn1.setOnClickListener(this);
		cBtn2.setText(prefs.getString("button2", "No File Found"));
		cBtn2.setOnClickListener(this);
		cBtn3.setText(prefs.getString("button3", "No File Found"));
		cBtn3.setOnClickListener(this);
		cBtn4.setText(prefs.getString("button4", "No File Found"));
		cBtn4.setOnClickListener(this);
		FTP_HOST = prefs.getString("prefsFTPHost", "0.0.0.0");
		FTP_USER = prefs.getString("userName", "null");
		FTP_PASS = prefs.getString("password", "null");
	}

	@Override
	public void onClick(View v) {
		File sdCard = Environment.getExternalStorageDirectory();
		String temp = sdCard.getAbsolutePath() + "/data/test";
		File f = null;
		switch (v.getId()) {
		case R.id.upBtn:
			/********** Pick file from sdcard *******/
			if (!pathList.isEmpty()) {
				for (String a1 : pathList)
					try {
						uploadFile(a1);
						this.finish();
						Toast.makeText(context, "Files Uploaded Successfully!",
								Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			break;
		case R.id.btn1CHK:
			temp = sdCard.getAbsolutePath() + "/data/test/" + cBtn1.getText()
					+ ".xls";
			if (((CheckBox) v).isChecked()) {
				pathList.add(temp);
			} else {
				pathList.remove(pathList.get(pathList.indexOf(temp)));
			}
			break;
		case R.id.btn2CHK:
			temp = sdCard.getAbsolutePath() + "/data/test/" + cBtn2.getText()
					+ ".xls";
			if (((CheckBox) v).isChecked()) {
				pathList.add(temp);
			} else {
				pathList.remove(pathList.get(pathList.indexOf(temp)));
			}
			break;
		case R.id.btn3CHK:
			temp = sdCard.getAbsolutePath() + "/data/test/" + cBtn3.getText()
					+ ".xls";
			if (((CheckBox) v).isChecked()) {
				pathList.add(temp);
			} else {
				pathList.remove(pathList.get(pathList.indexOf(temp)));
			}
			break;
		case R.id.btn4CHK:
			temp = sdCard.getAbsolutePath() + "/data/test/" + cBtn4.getText()
					+ ".xls";
			if (((CheckBox) v).isChecked()) {
				pathList.add(temp);
			} else {
				pathList.remove(pathList.get(pathList.indexOf(temp)));
			}
			break;
		}
	}

	public void uploadFile(String fileName) throws Exception {
		DataUploader data = new DataUploader();
		data.execute(fileName);
	}

	private class DataUploader extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... fileName) {
			System.out.println("Start");
			FTPUploader ftpUploader;
			try {
				ftpUploader = new FTPUploader(
						FTP_HOST,
						FTP_USER,
						FTP_PASS,
						getApplicationContext(),
						fileName[0],
						fileName[0].substring(fileName[0].lastIndexOf("/") + 1),
						"/test/");
				// ftpUploader.uploadFile(fileName[0], "test-old.xls",
				// "/test/");
				// ftpUploader.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				// Toast.makeText(context, "Error Occurred",
				// Toast.LENGTH_LONG).show();
			}
			System.out.println("Done");
			return null;
		}
	}

	/******* Used to file upload and show progress **********/

	public class MyTransferListener implements FTPDataTransferListener {

		@Override
		public void aborted() {
			uploadBtn.setVisibility(View.VISIBLE);
			// Transfer aborted
			Toast.makeText(getBaseContext(),
					" transfer aborted , please try again...",
					Toast.LENGTH_SHORT).show();
			// System.out.println(" aborted ..." );
		}

		@Override
		public void completed() {
			uploadBtn.setVisibility(View.VISIBLE);
			// Transfer completed

			Toast.makeText(getBaseContext(), " completed ...",
					Toast.LENGTH_SHORT).show();
			// System.out.println(" completed ..." );
		}

		@Override
		public void failed() {
			uploadBtn.setVisibility(View.VISIBLE);
			// Transfer failed
			System.out.println(" failed ...");
		}

		@Override
		public void started() {
			uploadBtn.setVisibility(View.GONE);
			// Transfer started
			Toast.makeText(getBaseContext(), " Upload Started ...",
					Toast.LENGTH_SHORT).show();
			// System.out.println(" Upload Started ...");
		}

		@Override
		public void transferred(int length) {
			// Yet other length bytes has been transferred since the last time
			// this
			// method was called
			Toast.makeText(getBaseContext(), " transferred ..." + length,
					Toast.LENGTH_SHORT).show();
			// System.out.println(" transferred ..." + length);
		}
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(context, UsersOrefs.class));
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.settings, menu);
		return true;
	}
}
