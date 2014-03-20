package com.training.myapp;

import android.net.Uri;
import android.provider.BaseColumns;

public class StatusContract {
	public final static String DB_NAME = "Yamba";
	public final static int DB_VERSION = 1;
	public final static String TABLE = "Status";
	
	public final static String ORDER_DEFAULT = Columns.ID + " DESC";
	
	public final static String AUTHORITY = "com.intel.myapp.provider";
	public final static Uri CONTENT_URI = 
			Uri.parse("content://" + AUTHORITY + "/" + TABLE);
	
	class Columns {
		public final static String ID = BaseColumns._ID;
		public final static String USER = "user";
		public final static String MESSAGE = "message";
		public final static String DATE = "date";
	}
}
