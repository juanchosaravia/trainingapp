package com.training.myapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intel.myapp.R;
import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class StatusActivity extends Activity implements LocationListener {

	Button btn_click;
	TextView etiqueta;
	TextView txtChars;
	EditText txtMessage;
	private final int RESULT_CANNED_RESPONSE = 1;
	private final static String TAG = "StatusActivity";
	private Location location = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 50, this);
		
		txtChars = (TextView) findViewById(R.id.txtChars);
		txtChars.setText("140");
		
		btn_click = (Button) findViewById(R.id.btnSend);
		btn_click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("STATUS", "Pressed");
				if(txtMessage.length() <= 140) {
					String msg = txtMessage.getText().toString();
					new StatusPostTask().execute(msg);
				} else {
					Toast.makeText(getApplicationContext(), getString(R.string.msg_too_chars), Toast.LENGTH_LONG).show();
				}
			}
		});
		
		txtMessage = (EditText) findViewById(R.id.txtMessage);
		txtMessage.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}
			
			@Override
			public void afterTextChanged(Editable e) {
				int chars = 140 - e.length();
				txtChars.setText(Integer.toString(chars));
				
				if(chars < 50 && chars >= 0) {
					txtChars.setTextColor(Color.BLUE);
				} else if (chars < 0) {
					txtChars.setTextColor(Color.RED);
				} else {
					txtChars.setTextColor(Color.BLACK);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.status_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_status_tweets:
				startActivityForResult(new Intent(this, PresetMessagesActivity.class),
						RESULT_CANNED_RESPONSE);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_CANNED_RESPONSE:
			if(resultCode == RESULT_OK) {
				String cannedMessage = data.getStringExtra("text");
				txtMessage.setText(cannedMessage);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	class StatusPostTask extends AsyncTask<String, Void, String> {

		private ProgressDialog dialog;
		
		@Override
		protected String doInBackground(String... params) {
			String status = params[0];
			String result = getString(R.string.msg_success);
			
			SharedPreferences preferences = PreferenceManager.
					getDefaultSharedPreferences(StatusActivity.this);
			String user = preferences.getString("username", "");
			String pass = preferences.getString("password", "");
			
			YambaClient client = new YambaClient(user, pass);
			
			try {
				if(location != null) {
					client.postStatus(status, location.getLatitude(), location.getLongitude());
				} else {
					client.postStatus(status);
				}
				
			} catch (YambaClientException e) {
				e.printStackTrace();
				result = getString(R.string.msg_fail);
			}
			
			return result;
		}
		
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(StatusActivity.this, "Sending", "Please wait...");
		}
		
		@Override
		protected void onPostExecute(String result) {
			dialog.cancel();
			
			Toast.makeText(StatusActivity.this, result, 
					Toast.LENGTH_SHORT).show();
			StatusActivity.this.txtMessage.setText("");
			StatusActivity.this.finish();
		}
	}

	@Override
	public void onLocationChanged(Location l) {
		location = l;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
