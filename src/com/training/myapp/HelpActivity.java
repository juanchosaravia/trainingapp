package com.training.myapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.intel.myapp.R;

public class HelpActivity extends Activity {

	WebView webview = null;
	
	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		webview = (WebView) findViewById(R.id.web_view);
		webview.setWebChromeClient(new WebChromeClient());
		webview.setWebViewClient(new WebViewClient());
		
		webview.getSettings().setJavaScriptEnabled(true);
		
		webview.loadUrl("file:///android_asset/index.html");
		webview.addJavascriptInterface(new JavaScriptInterface(this), "myapp");
		
		Button button = (Button) findViewById(R.id.web_view_button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				webview.loadUrl("javascript:alert('Hi !!!! :)')");
			}
		});
		
		//webview.loadUrl("http://intel.com");
	}

}
