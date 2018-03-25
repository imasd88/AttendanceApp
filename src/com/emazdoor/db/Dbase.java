package com.emazdoor.db;

import java.util.ArrayList;
import java.util.List;
import com.emazdoor.attendanceapp.SignUpPOJO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Dbase extends SQLiteOpenHelper {

	SQLiteDatabase db;
	Context context;
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "ATTENDANCEDB";

	private static final String CREATE_SIGN_UP_TABLE = "CREATE TABLE [SIgnUpTable] ("
			+ "[_id] INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "[_name] CHAR, "
			+ "[_deptt] CHAR, "
			+ "[_class] CHAR,"
			+ "[_subject] CHAR, [_password] CHAR);";
	private static final String SIGN_UP_TABLE_NAME = "SIgnUpTable";
	private static final String GET_TEACHER_DETAILS = "SELECT _id, _name, _deptt,_class, _subject FROM "
			+ SIGN_UP_TABLE_NAME + " WHERE _id=";
	private static final String GET_USER_PASS = "SELECT _name, _password FROM "
			+ SIGN_UP_TABLE_NAME + " WHERE _name =";

	public Dbase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	public Dbase open() throws SQLException {
		db = getWritableDatabase();
		return this;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SIGN_UP_TABLE);
		Log.d("tag", "Table Created!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SIGN_UP_TABLE_NAME);
		Log.d("tag", "Table Re-Created!");
		this.onCreate(db);
	}

	public void insertSignUpDetails(SignUpPOJO pojo) {
		ContentValues cv = new ContentValues();
		cv.put("_name", pojo.getName());
		cv.put("_class", pojo.getClass_());
		cv.put("_subject", pojo.getSubject());
		cv.put("_deptt", pojo.getDepartment());
		cv.put("_password", pojo.getPassword());
		db.insert(SIGN_UP_TABLE_NAME, null, cv);
		db.close();
	}

	public List<SignUpPOJO> fetchSignUpDetails(int value) {
		this.open();
		Cursor cursor = db.rawQuery(GET_TEACHER_DETAILS + value, null);
		List<SignUpPOJO> list = new ArrayList<SignUpPOJO>();
		while (cursor.moveToFirst()) {
			do {
				SignUpPOJO pojo = new SignUpPOJO();
				pojo.setName(cursor.getString(0));
				pojo.setDepartment(cursor.getString(1));
				pojo.setClass_(cursor.getString(2));
				pojo.setSubject(cursor.getString(3));
				list.add(pojo);
			} while (cursor.moveToNext());
			cursor.close();
			db.close();
		}
		return list;
	}

	public List<SignUpPOJO> fetchLoginDetails(String value) {
		this.open();
		db = getReadableDatabase();
		Cursor cursor = db.rawQuery(GET_USER_PASS + "'" + value + "'", null);
		List<SignUpPOJO> list = new ArrayList<SignUpPOJO>();
		SignUpPOJO pojo = new SignUpPOJO();
		int count = cursor.getCount();
		if (count > 0) {
			cursor.moveToNext();
			pojo.setName(cursor.getString(0));
			pojo.setPassword(cursor.getString(1));
			list.add(pojo);
		} else {
			pojo.setName("null");
			pojo.setPassword("null");
			list.add(pojo);
		}
		cursor.close();
		db.close();
		return list;
	}

}
