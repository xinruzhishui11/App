package com.yangfang.net;

import android.R.string;

/**
 * 闈欐?甯搁噺鍊肩殑瀛樺偍绫?
 * 
 * @author qijiahai
 * @鍒涘缓鏃堕棿锛?015-12-10
 */
public class Constants {

	/**
	 * 取数据线程mssage.
	 */
	public static final int MSG_NOTIFY_UPDATE = 0;
	public static final int MSG_NET_ERROR = 1;
	public static final int MSG_EXCEPTION = 2;
	public static final int MSG_NOTIFY_RELOAD = 3;
	public static final int MSG_NET_NOT_CONNECTED = 4;
	public static final int MSG_NOTIFY_UPDATE_CONTINUE = 5;
	public static final int UPLOAD_IMG_SUCCESS = 6;
	public static final int UPLOAD_IMG_FAIL = 7;

	/**
	 * 数据返回结果
	 */
	public static String DATA_OK = "SUCCESS!";

	public static String DATA_ERR = "网络状态异常";
	public static String CONN_TIMEOUT = "连接超时";
	public static String UNKNOWN_HOST = "网络未连接，请连接网络";
	public static String IO_ERR = "请检查网络状态";
	public static String EXCEPTION_ERR = "请检查网络状态";
	public static String NET_NOT_CONNECTED = "网络未连接，请连接网络";

	public static final String ACCOUNT_ACTIVE = "account_active";

	public static final int CLEAN_DATA = 1010;
	/** 关闭页面 */
	public static final String EVENT_FINISH = "event_finish";
	/**
	 * 获取数据成功
	 */
	public static final int HAND_GET_DATA_SUCCESS = 1001;

	/**
	 * 获取数据失败
	 */
	public static final int HAND_GET_DATA_FAIL = 1002;

	/**
	 * 上拉加载的数据
	 */
	public static final int LOADMORETYPE = 13;

	/**
	 * 下拉刷新的数据
	 */

	public static final int REFRESHINGTYPE = 12;

	// DIALOG标签
	public static final int DILOG_SURE = -7;// 确定按钮
	public static final int DILOG_CANCEL = -8;// 取消按钮
	/********************* 接口标签 ***************************/
	public static final int URL_VERSION_UPDATE = 001;// 版本更新
	public static final int URL_UPLOAD_IMAGE = 002;// 上传图片

	/*** 所有事件的都放在这里 **/
	public static final String EXIT_LOGIN = "退出登录com.example.lazycatbusiness.tuichudenglu";//
	public static final String POS_TONGZHI = "poscom.example.lazycatbusiness.tuichudenglu";//
	public static final String POS_POSENT = "POS_posetn.example.lazycatbusiness.tuichudenglu";//
	public static final String EV_ORDER = "EV_order";// 有新订单推送过来，通知首页更新数据
	public static final String EV_UPDATA = "EV_";// 有新订单推送过来，通知首页更新数据
	public static final String EV_LOGO = "EV_LOGOgengxingtouxiang";// 更新头像

	/**
	 * 用户传来的优惠券的信息控制界面变换
	 */
	public static final String PERSION_TYPE = "persion_type";

	/**
	 * 跳转到搜索界面的判断来源的key
	 */
	public static final String SERARCH_TYPE = "search_type";
	public static final int URL_POT = 1000;
	public static final int URL_POT_WEB = 2000;
	public static final String ORDER_KEYWORDS = "OrderkeyWords";
	public static final String SCHOOL_BUSINSS_KEYWORDS = "SchoolBusiness";
	public static final String SCHOOL_KEHU_KEYWORDS = "SchoolBusinessKEhu";//客户管理搜索

	
	
	/**网络不好获java.lang.String得服务器指令为空的情况下的提示*/
	public static final String WEB_FAIL = "网络不给力";
	public static final String WEB_CLOSE = "web_close";

}
