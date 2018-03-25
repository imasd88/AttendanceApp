package com.emazdoor.attendanceapp;

import java.util.List;

import com.emazdoor.db.Dbase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	EditText user, password;
	Button login;
	List<SignUpPOJO> pojo;
	TextView signUp, copyRight;
	Dbase db;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Init();
	}

	private void Init() {
		user = (EditText) findViewById(R.id.loginET);
		password = (EditText) findViewById(R.id.passwordET);
		login = (Button) findViewById(R.id.loginBtn);
		signUp = (TextView) findViewById(R.id.tvSignUp);
		signUp.setTextColor(Color.WHITE);
		copyRight = (TextView) findViewById(R.id.tvCopyRight);
		copyRight.setTextColor(Color.WHITE);
		context = getApplicationContext();
		signUp.setOnClickListener(this);
		login.setOnClickListener(this);

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.login, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.signup:
//			startActivity(new Intent(context, SingUpDetails.class));
//			return true;
//		}
//		return false;
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvSignUp:
			startActivity(new Intent(context, SingUpDetails.class));
			break;
		case R.id.loginBtn:
			db = new Dbase(context);
			String tUser = user.getText().toString();
			String tPass = password.getText().toString();
			pojo = db.fetchLoginDetails(tUser);
			for (SignUpPOJO a : pojo) {
				if (a.getName().equals(tUser)) {
					if (a.getPassword().equals(tPass))
						startActivity(new Intent(context, MainActivity.class));
					Toast.makeText(context, "Login Successfully!",
							Toast.LENGTH_LONG).show();
				} else
					Toast.makeText(context,
							"Try again, wrong user or password",
							Toast.LENGTH_LONG).show();
				user.setText("");
				password.setText("");
				break;
			}

		}
	}
}
