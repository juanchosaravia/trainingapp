package com.training.myapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FetchReceiver extends BroadcastReceiver {

	public static long REFRESH_TIME = 10000;
	
	@Override
	public void onReceive(Context context, Intent intentParam) {
		Intent newIntent = new Intent(context, FetchService.class);
		context.startService(newIntent);
		
		PendingIntent operation = PendingIntent.getService(
				context, -1, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
		alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, REFRESH_TIME, REFRESH_TIME, operation);
	}

}
