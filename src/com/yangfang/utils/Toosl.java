package com.yangfang.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.yangfang.app.R;

public class Toosl {

public static final	DisplayImageOptions Imageloader(){
	
	return  new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.abc_ic_commit_search_api_mtrl_alpha)
	// ���ڼ���
	.showImageForEmptyUri(R.drawable.abc_ic_commit_search_api_mtrl_alpha)
	// ��ͼƬ
	.showImageOnFail(R.drawable.abc_ic_commit_search_api_mtrl_alpha)
	// ����ͼƬ
	.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
	.bitmapConfig(Bitmap.Config.RGB_565).build();
	
	
	
}



	
}
