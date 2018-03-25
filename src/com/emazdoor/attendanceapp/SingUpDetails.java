package com.emazdoor.attendanceapp;

import java.util.ArrayList;
import java.util.List;

import com.emazdoor.db.Dbase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SingUpDetails extends Activity {

	EditText name, subject, _class, _password;
	Spinner department, title;
	Button done;
	SignUpPOJO pojo;
	Dbase db;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		Init();
		prepareDepartmentSpinner();
		prepareTitleSPinner();
	}

	private void prepareTitleSPinner() {
		List<String> list = new ArrayList<String>();
		list.add("Mr");
		list.add("Mrs");
		list.add("Miss");
		list.add("Doctor");
		list.add("Professor");
		for (String abc : list) {
			System.out.println("Items in list are: " + abc);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		title.setAdapter(dataAdapter);

	}

	private void prepareDepartmentSpinner() {
		List<String> list = new ArrayList<String>();
		list.add("Sciences");
		list.add("Arts");
		list.add("Humanities");
		for (String abc : list) {
			System.out.println("Items in list are: " + abc);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		department.setAdapter(dataAdapter);

	}

	private void Init() {
		db = new Dbase(this);
		context = this;
		name = (EditText) findViewById(R.id.nameET);
		subject = (EditText) findViewById(R.id.subjectET);
		_class = (EditText) findViewById(R.id.classET);
		department = (Spinner) findViewById(R.id.spinner2);
		title = (Spinner) findViewById(R.id.spinner1);
		_password = (EditText) findViewById(R.id.passET);
		done = (Button) findViewById(R.id.doneBtn);
		done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pojo = new SignUpPOJO(name.getText().toString(), subject
						.getText().toString(), _class.getText().toString(),
						department.getSelectedItem().toString(), _password
								.getText().toString());
				db.open();
				db.insertSignUpDetails(pojo);
				finish();
			}
	});
	}
}
