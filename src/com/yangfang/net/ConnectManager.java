package com.yangfang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.util.Log;

/**
 * �����жϵ�ǰ�����������ͣ�����㣬�����������Ϣ�� 
 * @author longpeng
 */
public class ConnectManager {
    
    /** log tag. */
    private static final String TAG = ConnectManager.class.getSimpleName();
    
    /** log debug on/off .*/
    private static final boolean DEBUG = false;
    
    /** current apn. */
    private String mApn;
    
    /** apn proxy. */
    private String mProxy;
    
    /** proxy port. */
    private String mPort;
    
    /** ��ǰ�����Ƿ�ʹ��wap��
     *  wifi ���� cmnet��Ϊ��ʹ�� wap.
     */
    private boolean mUseWap;
    
    /** 
     * ���������ַ���.wifi���緵�� wifi�� mobile���緵�ض�Ӧ��apn name:  cmnet, uninet, ctnet, cmwap
     * �п���Ϊ�ա�
     * */
    private String mNetType; //add by fujiaxing 20120323
    
    /**
     * constructor.
     * @param context  context
     */
    public ConnectManager(Context context) {
        checkNetworkType(context);
    }
    
    /**
     * ��鵱ǰϵͳapn ���� ״̬.
     * @param context context
     * @param networkInfo NetworkInfo instance
     */
    private void checkApn(Context context, NetworkInfo networkInfo) {
        
        if (networkInfo.getExtraInfo() != null) {
            String info = networkInfo.getExtraInfo().toLowerCase(); //3gnet/3gwap/uninet/uniwap/cmnet/cmwap/ctnet/ctwap 
            // �ȸ�������apn��Ϣ�ж�,������ proxy �Զ�����
            if (info != null) {
                if (info.startsWith("cmwap") || info.startsWith("uniwap") || info.startsWith("3gwap")) {
                    mUseWap = true;
                    mApn = info;
                    mProxy = "10.0.0.172";
                    mPort = "80";
                    
                    return;
                } else if (info.startsWith("ctwap")) {
                    mUseWap = true;
                    mApn = info;
                    mProxy = "10.0.0.200";
                    mPort = "80";
                    
                    return;
                } else if (info.startsWith("cmnet") || info.startsWith("uninet") || info.startsWith("ctnet")
                        || info.startsWith("3gnet")) {
                    mUseWap = false;
                    mApn = info;
                    // mProxy  mPort ������ʼֵ
                    
                    return;
                } // else fall through
            } // else fall through
        }
        

        
        // ���û�� apn ��Ϣ������� proxy�����жϡ�
        // ����android 4.2 �� "content://telephony/carriers/preferapn" ��ȡ���������ƣ�����ͨ��ϵͳ�ӿڻ�ȡ��
        
		String defaultProxyHost = Proxy.getDefaultHost();
		int defaultProxyPort = Proxy.getDefaultPort();
        
        if (defaultProxyHost != null && defaultProxyHost.length() > 0) {
            
            // ���� mProxy  mPort
            mProxy = defaultProxyHost;
            /*
             * �޷�����  proxy host ��ԭ apn ���� ���ﲻ����  mApn
             */
            if ("10.0.0.172".equals(mProxy.trim())) {
                // ��ǰ������������Ϊcmwap || uniwap
                mUseWap = true;
                mPort = "80";
            } else if ("10.0.0.200".equals(mProxy.trim())) {
                mUseWap = true;
                mPort = "80";
            } else {
                // ����Ϊnet
                mUseWap = false;
                mPort = Integer.toString(defaultProxyPort);
            }
        } else {
            // �������綼������net
            mUseWap = false;
        }
    }
    
    /**
     * ��鵱ǰ�������͡�
     * @param context context
     */
    private void checkNetworkType(Context context) {
        
        ConnectivityManager connectivityManager = 
            (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            
            if (DEBUG) {
                Log.d(TAG, "network type : " + activeNetInfo.getTypeName().toLowerCase());
            }
            
            if ("wifi".equals(activeNetInfo.getTypeName().toLowerCase())) {
                mNetType = "wifi";
                mUseWap = false;
            } else { // mobile
                checkApn(context, activeNetInfo);
                
                mNetType = mApn; // �ƶ������Ӧapn name���п���Ϊ�ա�
            }
        }
        
        if (DEBUG) {
            Log.d(TAG, "checkApn: " + " mApn = " + mApn + "�� mProxy = "
                    + mProxy + " ��mPort = " + mPort + " , mUseWap = " + mUseWap + " , mNetType = " + mNetType);
        }
    }
    
    /**
     * ��ǰ�����Ƿ��������ˡ�
     * 
     * @param context Context
     * @return activeNetInfo.isConnected();
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = 
            (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            // �޸������ֻ���ʵ�����Ѿ����ӣ����� activeNetInfo.isConnected���� ����false��bug����ʱ״̬Ϊ connecting.
            //return activeNetInfo.isConnected();
            return activeNetInfo.isConnectedOrConnecting();
        }
        return false;
    }
    
    /**
     * ��ǰ���������Ƿ���wap���硣
     * @return  cmwap 3gwap ctwap ����true
     * 
     * 
     * 
     */
    public boolean isWapNetwork() {
        return mUseWap;
    }
    
    /**
     * ��ȡ��ǰ��������apn.
     * @return apn
     */
    public String getApn() {
        return mApn;
    }
    
    /**
     * ��ȡ��ǰ�������ӵĴ����������ַ������ cmwap ���������10.0.0.172.
     * @return ��ȡ��ǰ�������ӵĴ����������ַ������ cmwap ���������10.0.0.172
     */
    public String getProxy() {
        return mProxy;
    }
    
    /**
     * ��ȡ��ǰ�������ӵĴ���������˿ڣ����� cmwap ����������˿� 80.
     * @return ��ȡ��ǰ�������ӵĴ���������˿ڣ����� cmwap ����������˿� 80
     */
    public String getProxyPort() {
        return mPort;
    }
    
    /**
     * ��õ�ǰ��������
     * @return wifi, cmnet, uninet, ctnet, cmwap ����
     */
    public String getNetType() {
        return mNetType;
    }
}
