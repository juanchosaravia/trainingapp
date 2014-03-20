package com.training.myapp;

import java.util.List;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import com.marakana.android.yamba.clientlib.YambaClientException;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class FetchService extends IntentService {
	
	public FetchService() {
		super("FetchService");
	}

	private static final String TAG = "FetchService";
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.d(TAG, "OnCreate");
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		Log.d(TAG, "OnDestroy");
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// Se ejecuta en un worker thread.
		Log.d(TAG, "OnStart");
		
		SharedPreferences preferences = PreferenceManager.
				getDefaultSharedPreferences(this);
		String user = preferences.getString("username", "");
		String pass = preferences.getString("password", "");
		
		YambaClient client = new YambaClient(user, pass);
		
		try {
			List<Status> list = client.getTimeline(20);
			for(Status status : list) {
				// Log.d(TAG, status.getUser() + ": " + status.getMessage());
				ContentValues values = new ContentValues();
				values.put(StatusContract.Columns.ID, status.getId());
				values.put(StatusContract.Columns.USER, status.getUser());
				values.put(StatusContract.Columns.MESSAGE, status.getMessage());
				values.put(StatusContract.Columns.DATE, status.getCreatedAt().toString());
				
				getContentResolver().insert(StatusContract.CONTENT_URI, values);
				
			}
		} catch (YambaClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
