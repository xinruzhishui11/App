package com.yangfang.app;

import java.util.HashMap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 
 * @author 2015-12-11  qijiahai
 *
 */
public class BaseApplication extends Application{
	/**
	 * 全局开关，app发布时一定要置为false.
	 */
	public static final boolean GLOBAL_DEBUG = true;
	public static final boolean DEBUG = true & GLOBAL_DEBUG;
	private Handler hanlder;
	private static Handler handler;
	private static int mainThreadId;
	/**
	 * 是否登录
	 */
	public static boolean sIsLoging = false;

	/**
	 * SessionId的保存
	 */
	public static HashMap<String, String> SessionMap = new HashMap<String, String>();

	private static Context mContext;

	public static Context getContext(){
		return mContext;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
		//注意该方法要再setContentView方法之前实现  
//		initImageLoader();
		mContext = this;
		initImageLoader(getApplicationContext());
		handler = new Handler();
		mainThreadId = android.os.Process.myTid();
	}
	/**
     * 初始化ImageLoader
     */

	public static void initImageLoader(Context context) {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}
	
	public static Handler getHandler() {
		return handler;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}
	

}
