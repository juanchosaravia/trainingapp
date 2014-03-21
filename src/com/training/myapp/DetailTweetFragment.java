package com.training.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.intel.myapp.R;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailTweetFragment extends Fragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if(getActivity().getIntent().getExtras() != null
				&& getActivity().getIntent().getExtras().containsKey("id")) {
			long id = getActivity().getIntent().getLongExtra("id", 0);
			showDetails(id);
		}
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweet_detail, container);
	}
	
	public void showDetails(long id) {
		if(id > 0) {
			Uri uri = Uri.parse(StatusContract.CONTENT_URI + "/" + id);
			Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
			cursor.moveToFirst();
			
			String user = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.USER));
			String message = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.MESSAGE ));
			String date = cursor.getString(cursor.getColumnIndex(StatusContract.Columns.DATE));
			
			TextView userTextView = (TextView) getActivity().findViewById(R.id.detail_user);
			userTextView.setText(user);
			
			TextView msgTextView = (TextView) getActivity().findViewById(R.id.detail_msg);
			msgTextView.setText(message);
			
			TextView dateTextView = (TextView) getActivity().findViewById(R.id.detail_date);
			
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
