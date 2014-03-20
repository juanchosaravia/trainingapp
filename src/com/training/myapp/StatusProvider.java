package com.training.myapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StatusProvider extends ContentProvider {

	private final static int TYPE_DIR = 10;
	private final static int TYPE_ITEM = 20;
	
	DBHelper dbHelper;
	private static final String TAG = "StatusProvider";
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insertWithOnConflict(StatusContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		Log.d(TAG, "Insertado: " + values.getAsLong(StatusContract.Columns.ID));

		return Uri.parse(StatusContract.CONTENT_URI + "/" + id);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String orderBy) {
		Cursor cursor = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(StatusContract.AUTHORITY, "Status", TYPE_DIR);
		uriMatcher.addURI(StatusContract.AUTHORITY, "Status/#", TYPE_ITEM);
		
		switch (uriMatcher.match(uri)) {
		case TYPE_DIR:
			cursor = db.query(StatusContract.TABLE, projection, selection, selectionArgs, 
					null, null, 
			orderBy == null ? StatusContract.ORDER_DEFAULT : orderBy);
			break;
		
		case TYPE_ITEM:
			String[] args = new String[] { uri.getLastPathSegment() };
			cursor = db.query(StatusContract.TABLE, projection,
					StatusContract.Columns.ID + "=?", 
					args, null, null, 
					orderBy == null ? StatusContract.ORDER_DEFAULT : orderBy);
			break;
		}
		
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
