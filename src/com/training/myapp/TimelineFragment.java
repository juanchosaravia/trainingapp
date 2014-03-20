package com.training.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.intel.myapp.R;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TimelineFragment extends ListFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		inflater.inflate(R.layout.fragment_timeline, 
				container);
		
		// pongo todo null para no filtrar por nada y me traiga todo.
		Cursor cursor = getActivity().getContentResolver().query(
				StatusContract.CONTENT_URI,	null, null, null, null);
		
		// from/to: mapeo origen de datos a ID de la View de la celda.
		String[] from = {StatusContract.Columns.USER, StatusContract.Columns.MESSAGE, StatusContract.Columns.DATE};
		int[] to = {R.id.msg_header, R.id.msg_body, R.id.msg_time};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getActivity(), 
				//android.R.layout.simple_list_item_2, // has title and subtitle.
				R.layout.timeline_detail,
				cursor, from, to, 0);
		
		adapter.setViewBinder(new ViewBinder() {
			
			@Override
			public boolean setViewValue(View view, Cursor cursor, int index) {
				if(view.getId() == R.id.msg_time) {
					int col = cursor.getColumnIndex(StatusContract.Columns.DATE);
					String dateText = cursor.getString(col);
					
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
					try {
						Date date = dateFormat.parse(dateText);
						long dateLong = date.getTime();
						
						((TextView)view).setText(DateUtils.getRelativeTimeSpanString(dateLong));
						return true;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				} else {
					return false;
				}
			}
		});
		
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
