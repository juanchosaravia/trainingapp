package com.training.myapp;

import java.util.ArrayList;
import java.util.List;

import com.intel.myapp.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PresetMessagesActivity extends ListActivity {
	private static final String TAG = "PresetMessagesActivity";
	private String[] cannedResponses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preset_messages);
		
		cannedResponses = getResources().getStringArray(R.array.responses_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, cannedResponses);
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent datos = new Intent();
		datos.putExtra("text", cannedResponses[position]);
		setResult(RESULT_OK, datos);
		finish();
	}
}
