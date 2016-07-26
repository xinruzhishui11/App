package com.yangfang.net;


import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 *  *�ṩһ��anroidƽ̨�Զ����apn proxy�� HttpClient.
 * 
 * �Զ���� proxy ���ã�����Ҫʹ�����ٴι���wap net�����
 *@author longpeng
 *@2014��5��21������9:02:54
 */
public class ProxyHttpClient extends DefaultHttpClient {
    
    /** log tag. */
    private static final String TAG = ProxyHttpClient.class.getSimpleName();
    
    /** log debug on/off .*/
    private static final boolean DEBUG = false;
    
    /** apn proxy. */
    private String mProxy;
    
    /** proxy port. */
    private String mPort;
    
    /** ��ǰ�����Ƿ�ʹ��wap��
     *  wifi ���� cmnet��Ϊ��ʹ�� wap.
     */
    private boolean mUseWap;
    
    /** http ��ʱ�� */
    private static final int HTTP_TIMEOUT_MS = 15000;

    /** ���ڼ���Ƿ���û���ͷ�(close)�����. */
    private RuntimeException mLeakedException = new IllegalStateException(
                                                        "ProxyHttpClient created and never closed");
    
    /**
     * constructor.
     * @param context  context
     */
    public ProxyHttpClient(Context context) {
        this(context, null, null);
    }
    
    /**
     * ���캯��.
     * @param context application context
     * @param userAgent  useragent you want to set 
     */
    public ProxyHttpClient(Context context, String userAgent) {
        this(context, userAgent, null);
    }
    
    /**
     * ���캯��.
     * @param context application context
     * @param connectManager ConnectManager
     */
    public ProxyHttpClient(Context context, ConnectManager connectManager) {
        this(context, null, connectManager);
    }
    
    /**
     * ���캯��.
     * @param context application context
     * @param userAgent  useragent you want to set 
     * @param connectManager ConnectManager
     */
    public ProxyHttpClient(Context context, String userAgent, ConnectManager connectManager) {
        
        // ����������� wifi /mobile & apn proxy
        ConnectManager cm = connectManager;
        if (cm == null) {
            cm = new ConnectManager(context);
        }
        
        mUseWap = cm.isWapNetwork();
        mProxy = cm.getProxy();
        mPort = cm.getProxyPort();
        
        if (DEBUG) {
            Log.d(TAG, "wap :" + mUseWap + " " + mProxy + " " + mPort);
        }
        
        if (mProxy != null && mProxy.length() > 0) {
            
            HttpHost proxy = new HttpHost(mProxy, Integer.valueOf(mPort));
            getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
        }

        // Default connection and socket timeout of 30 seconds. 
        HttpConnectionParams.setConnectionTimeout(getParams(), HTTP_TIMEOUT_MS);
        HttpConnectionParams.setSoTimeout(getParams(), HTTP_TIMEOUT_MS);
        /*
         * http://stackoverflow.com/questions/5358014/android-httpclient-oom-on-4g-lte-htc-thunderbolt
         * https://android-review.googlesource.com/#/c/22852/
         * ��������ֻ�(android 2.x ����) httpClient OOM ����
         */
        HttpConnectionParams.setSocketBufferSize(getParams(), 8192); // SUPPRESS CHECKSTYLE
        
        if (!TextUtils.isEmpty(userAgent)) {
            HttpProtocolParams.setUserAgent(getParams(), userAgent);
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (mLeakedException != null) {
            Log.e(TAG, "Leak found", mLeakedException);
        }
    }
    
    /**
     * Release resources associated with this client.  You must call this,
     * or significant resources (sockets and memory) may be leaked.
     */
    public void close() {
        if (mLeakedException != null) {
            getConnectionManager().shutdown();
            mLeakedException = null;
        }
    }

    /**
     * ��ȡ�Ƿ�ǰ������wap���硣
     * 
     * @return true ��ʾ��wap���磬false��ʾ����
     */
    public boolean isWap() {
        return mUseWap;
    }

    @Override
    protected HttpParams createHttpParams() {
        HttpParams params = super.createHttpParams();
        // ���� Expect: 100-continue����ͨ uniwap���ز�֧�֣�post���ݷ�������
        HttpProtocolParams.setUseExpectContinue(params, false);
        return params;
    }
    
    /**
     * �滻{@link #execute(HttpUriRequest)} ���Խ��2.3ϵͳ�Ϸ�������ʱ������������<br>
     * Ҫ�����ָ���쳣�������
     * ע�⣺����ֱ��ʹ��{@link #execute(HttpUriRequest)}
     * �ο��� ����HttpClient��NullPointer https://code.google.com/p/android/issues/detail?id=5255
     * @param request request
     * @throws ClientProtocolException ClientProtocolException
     * @throws IOException IOException
     * @return HttpResponse
     */
    public HttpResponse executeSafely(HttpUriRequest request) 
            throws ClientProtocolException, IOException, ConnectTimeoutException, UnknownHostException{
        try {
            return execute(request);
        } catch (NullPointerException e) {
            throw new ClientProtocolException(e);
        }
    }
}
