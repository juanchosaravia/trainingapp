package com.training.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = String.format(
				"CREATE TABLE %s (%s int primary key, %s text, %s text, %s text)",
				StatusContract.TABLE, 
				StatusContract.Columns.ID, 
				StatusContract.Columns.USER, 
				StatusContract.Columns.MESSAGE,
				StatusContract.Columns.DATE);
		
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
