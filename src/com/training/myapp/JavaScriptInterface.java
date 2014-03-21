package com.training.myapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
	Context context;
	
	public JavaScriptInterface(Context c) {
		this.context = c;
	}
	
	@JavascriptInterface
	public void toast(String s) {
		Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
	}
}
