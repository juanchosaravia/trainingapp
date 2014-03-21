package com.training.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.intel.myapp.R;

@SuppressLint("NewApi")
public class DetailTweetActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_tweet);
		
		int version = android.os.Build.VERSION.SDK_INT;
		int minimo = android.os.Build.VERSION_CODES.HONEYCOMB;
		
		if(version >= minimo) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// lo infla con el setDisplayShowHomeEnable solo.
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Buscamos el id de este nuevo elemento en:
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
