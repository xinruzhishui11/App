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
	 * ȫ�ֿ��أ�app����ʱһ��Ҫ��Ϊfalse.
	 */
	public static final boolean GLOBAL_DEBUG = true;
	public static final boolean DEBUG = true & GLOBAL_DEBUG;
	private Handler hanlder;
	private static Handler handler;
	private static int mainThreadId;
	/**
	 * �Ƿ��¼
	 */
	public static boolean sIsLoging = false;

	/**
	 * SessionId�ı���
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
		//��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
		//ע��÷���Ҫ��setContentView����֮ǰʵ��  
//		initImageLoader();
		mContext = this;
		initImageLoader(getApplicationContext());
		handler = new Handler();
		mainThreadId = android.os.Process.myTid();
	}
	/**
     * ��ʼ��ImageLoader
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
