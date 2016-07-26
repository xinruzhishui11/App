package com.yangfang.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yangfang.app.R;

public class WebViewActivity extends Activity {

	private WebView webview;
	private WebSettings settings;
	private String mUrl="";
	private String mtitle;
	private TextView newtvtitle;
	private ImageView newwebreturn;
	private String moviesurl;
	private String moviestitle;
	private ImageButton ibShare;
	private String title;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		

		setView();
		progressBar.setVisibility(View.VISIBLE);
		setDate();
		setLinsteners();
		
		
		
		
		webview.setWebChromeClient(new WebChromeClient() {
			 
		      public void onProgressChanged(WebView view, int progress) {
		           setTitle("ҳ������У����Ժ�..." + progress + "%");
		           setProgress(progress * 100);
		 
		           if(progress>90){
	        	   progressBar.setVisibility(view.INVISIBLE);
		           }
		           if (progress == 100) {
		                 setTitle(R.string.app_name);
		           }
		      }
		});


	}

	

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	private void setDate () {
		settings = webview.getSettings();

		settings.setJavaScriptEnabled(true); //������ʵ�ҳ������Javascript����WebView��������֧��Javascript
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setSupportZoom(true); //֧������
		settings.setBuiltInZoomControls(true); //֧����������
		settings.setDisplayZoomControls(false); //�Ƿ���ʾ���Ű�ť

		settings.setUseWideViewPort(true); //��ͼƬ�������ʺ�WebView�Ĵ�С
		settings.setLoadWithOverviewMode(true); //����Ӧ��Ļ
		settings.setDomStorageEnabled(true);
		settings.setSaveFormData(true);
		settings.setSupportMultipleWindows(true);
		settings.setAppCacheEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_DEFAULT); //����ʹ�û���

		webview.setHorizontalScrollbarOverlay(true);
		webview.setHorizontalScrollBarEnabled(false);
		webview.setOverScrollMode(View.OVER_SCROLL_NEVER); // ȡ��WebView�й������϶����������ײ�ʱ����Ӱ
		webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // ȡ���������ױ�Ч��
		webview.requestFocus();

		webview.loadUrl(mUrl);
		if(mUrl==getIntent().getStringExtra("url")){
			webview.loadUrl(mUrl);
			}else {
				webview.loadUrl(moviesurl);
			}
		webview.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	private void setView() {
		
		mUrl = getIntent().getStringExtra("url");
		mtitle=getIntent().getStringExtra("mtitle");
		title = getIntent().getStringExtra("title");
		moviesurl=getIntent().getStringExtra("murl");
		moviestitle=getIntent().getStringExtra("mtitle");
		
		
		
		newtvtitle=(TextView)findViewById(R.id.new_web_title);
		webview=(WebView)findViewById(R.id.web_view);
		newwebreturn=(ImageView)findViewById(R.id.new_web_return);
		ibShare = (ImageButton) findViewById(R.id.share);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		
		if(mtitle==getIntent().getStringExtra("title")){
			newtvtitle.setText(mtitle);
			
		}else {newtvtitle.setText(moviestitle);
			
		}
	}
	
	/**
	 * ��ҳ�ķ��ؼ�  �ر���ҳ
	 * 
	 */
    private void setLinsteners() {
    	//����
    	ibShare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, title+"\n"+mUrl);
				startActivity(Intent.createChooser(intent, "������"));
			}
		});
    	
    	newwebreturn.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
    
}
