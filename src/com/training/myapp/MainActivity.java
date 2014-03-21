package com.training.myapp;

import com.intel.myapp.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_new:
				startActivity(new Intent(this, StatusActivity.class));
				break;
			
			case R.id.menu_about:
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/?q=cordoba, argentina")));
				break;
				
			case R.id.menu_setting:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
				
			case R.id.menu_refresh:
				startService(new Intent(this, FetchService.class));
				break;
				
			case R.id.menu_web_view:
				startActivity(new Intent(this, HelpActivity.class));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
