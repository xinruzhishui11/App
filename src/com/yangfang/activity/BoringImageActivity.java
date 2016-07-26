package com.yangfang.activity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.app.R;
import com.yangfang.utils.Toosl;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class BoringImageActivity extends BaseActivity {

	private ImageView boringimageiv;
	private String boringimagekey;
	private ImageView boringimagereturn;
	private ImageLoader loader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boring_image);
		setViews();
	}

	private void setViews() {
		boringimageiv=(ImageView)findViewById(R.id.boringimage_iv);
		boringimagereturn=(ImageView)findViewById(R.id.boringimage_return);
		
	//接受數據
     boringimagekey= getIntent().getStringExtra("position");
     loader = ImageLoader.getInstance();
     loader.displayImage(boringimagekey, boringimageiv,Toosl.Imageloader());
     Log.i("image", "图片的值" +boringimagekey);
     
     //返回键的监听
     boringimagereturn.setOnClickListener(new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			finish();
			
		}
	});
 
	}
}
