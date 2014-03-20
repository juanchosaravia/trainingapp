package com.training.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.intel.myapp.R;

public class DetailTweetActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_tweet);
		
		long id = getIntent().getLongExtra("id", 0);
		if(id > 0) {
			Uri uri = Uri.parse(StatusContract.CONTENT_URI + "/" + id);
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			cursor.moveToFirst();
			
			String user = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.USER));
			String message = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.MESSAGE ));
			String date = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.DATE));
			
			TextView userTextView = (TextView) findViewById(R.id.detail_user);
			userTextView.setText(user);
			
			TextView msgTextView = (TextView) findViewById(R.id.detail_msg);
			msgTextView.setText(message);
			
			TextView dateTextView = (TextView) findViewById(R.id.detail_date);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
			try {
				Date d = dateFormat.parse(date);
				long dateLong = d.getTime();
				
				dateTextView.setText(DateUtils.getRelativeTimeSpanString(dateLong));
				
			} catch (ParseException e) {
				e.printStackTrace();
				dateTextView.setText(date);
			}
			
			
		}
	}
}
