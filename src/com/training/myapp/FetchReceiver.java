package com.training.myapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

public class FetchReceiver extends BroadcastReceiver {

	public static long REFRESH_TIME = 15000;
	
	@Override
	public void onReceive(Context context, Intent intentParam) {
		long refreshTime = PreferenceManager.getDefaultSharedPreferences(context).getLong("menu_refresh", REFRESH_TIME);
		refreshTime = refreshTime * 60000;
		
		Intent newIntent = new Intent(context, FetchService.class);
		context.startService(newIntent);
		
		PendingIntent operation = PendingIntent.getService(
				context, -1, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		
		// Uncomment this to execute this every 15 second.
		//alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, REFRESH_TIME, refreshTime, operation);
	}

}
