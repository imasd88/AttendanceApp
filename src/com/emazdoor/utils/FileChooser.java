package com.emazdoor.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.emazdoor.attendanceapp.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FileChooser extends Activity {

	private File currentFile;
	Spinner sp1, sp2, sp3, sp4;
	Button dBtn;
	Context context;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_filechooser);
		Init();
		getFiles();
	}

	private void Init() {
		context = this;
		sp1 = (Spinner) findViewById(R.id.sp1);
		sp2 = (Spinner) findViewById(R.id.sp2);
		sp3 = (Spinner) findViewById(R.id.sp3);
		sp4 = (Spinner) findViewById(R.id.sp4);
		dBtn = (Button) findViewById(R.id.doneBtnBtn);
		dBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				prefs = PreferenceManager.getDefaultSharedPreferences(context);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("button1", sp1.getSelectedItem().toString());
				editor.putString("button2", sp2.getSelectedItem().toString());
				editor.putString("button3", sp3.getSelectedItem().toString());
				editor.putString("button4", sp4.getSelectedItem().toString());
				editor.apply();
				finish();
			}
		});
	}

	public String nameGetter(String string) {
		int dot1 = string.indexOf(".");
		int dot2 = string.indexOf(".", dot1 - 1);
		String subString = string.substring(0, dot2);
		return subString;
	}

	public void getFiles() {
		File sdCard = Environment.getExternalStorageDirectory();
		currentFile = new File(sdCard.getAbsolutePath() + "/data/test");
		File[] dirs = currentFile.listFiles();
		List<String> dir = new ArrayList<String>();
		List<String> files = new ArrayList<String>();
		try {
			for (File ff : dirs) {
				if (ff.isFile()) {
					files.add(nameGetter(ff.getName()));
					System.out.println(">>" + ff);
				}
			}
		} catch (Exception e) {
		}
		Collections.sort(dir);
		Collections.sort(files);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, files);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(dataAdapter);
		sp2.setAdapter(dataAdapter);
		sp3.setAdapter(dataAdapter);
		sp4.setAdapter(dataAdapter);
	}
}
