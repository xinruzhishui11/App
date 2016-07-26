package com.yangfang.utils;

import com.lidroid.xutils.BitmapUtils;
import com.yangfang.app.R;

/**
 */
public class BitmapHelper {

	private static BitmapUtils mBitmapUtils = null;

	public static BitmapUtils getBitmapUtils() {
		if (mBitmapUtils == null) {
			mBitmapUtils = new BitmapUtils(UIUtils.getContext());
		}

		mBitmapUtils.configDefaultLoadingImage(R.drawable.nothing);
		return mBitmapUtils;
	}
}
