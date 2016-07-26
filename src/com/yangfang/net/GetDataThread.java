package com.yangfang.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import com.google.gson.Gson;
import com.yangfang.entiy.BaseData;

/**
 * @author zoujian
 * @����ʱ�䣺2014-7-9����11:04:35 �����߳���ȡ����
 * 
 *��װ�����̵߳���   ����http����
 */
public class GetDataThread extends Thread {
    private static final boolean DEBUG = true ;
    private static final String TAG = "GetDataThread";
    private Context mContext;
    private Boolean isRun;
    private Handler mHandler;
    private String mUrl;
    private BaseData mData;
    private int mWhat = -1;//����handler


    public GetDataThread(Context context, Handler handler, String url, BaseData data, int what) {
        mContext = context.getApplicationContext();
        mHandler = handler;
        mUrl = url;
        mData = data;
        isRun = false;
        mWhat = what;
        if (DEBUG) {
            Log.d(TAG, "��ȡ���ݵ� URL��" + mUrl);
            Log.d(TAG, "mWhat��" + mWhat);
        }
    }

    public GetDataThread(Context context, Handler handler, String url, BaseData data) {
        mContext = context;
        mHandler = handler;
        mUrl = url;
        mData = data;
        isRun = false;
        if (DEBUG) {
            Log.d(TAG, "��ȡ���ݵ� URL��" + mUrl);
        }
    }

    /**
     * �Ƿ�������
     *
     * @return
     */
    public boolean isRunning() {
        return isRun;
    }

    @Override
    public void run() {
        super.run();

        isRun = true;
        //�����߳����ȼ�Ϊ��̨������������̲߳�����ܶ��޹ؽ�Ҫ�����̷߳����cpu�������
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        ProxyHttpClient httpClient = null;
        InputStream inputStream = null;
        try {
            httpClient = new ProxyHttpClient(mContext);
            HttpGet postRequest = new HttpGet(mUrl);
            HttpResponse httpResponse = httpClient.executeSafely(postRequest);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Message msg = Message.obtain();
                msg.what = Constants.MSG_EXCEPTION;
                msg.obj = "����������:" + statusCode;
                mHandler.sendMessage(msg);
                return;
            }

            inputStream = httpResponse.getEntity().getContent();
            byte[] data = readStream(inputStream);
            String res = new String(data, "GBK");
            if (DEBUG) {
                Log.d(TAG, res);
            }

            mData = new Gson().fromJson(res, mData.getClass());

//			HomeBTData homeData = (HomeBTData) mData;
//			
//			if(DEBUG) {
//				Log.d(TAG, "homeData.getBreeds().size() == " + homeData.getBreeds().size());
//			}

            Message msg = Message.obtain();
            if ("ok".equals(mData.getStatus())) {//������ǻ�ȡ���ݳɹ��ı�ʶ
                msg.what = Constants.HAND_GET_DATA_SUCCESS;
                msg.arg1 = mWhat;
                msg.obj = mData;
                mHandler.sendMessage(msg);
            } else {
                //��ȡ����ʧ��
                msg.what = Constants.HAND_GET_DATA_FAIL;
                msg.obj = "��ȡʧ��";
                mHandler.sendMessage(msg);
            }
        } catch (ClientProtocolException e) {
            mHandler.sendEmptyMessage(Constants.MSG_EXCEPTION);
        } catch (ConnectTimeoutException e) {
            Message msg = Message.obtain();
            msg.what = Constants.MSG_EXCEPTION;
            msg.obj = Constants.CONN_TIMEOUT;
            mHandler.sendMessage(msg);
        } catch (UnknownHostException e) {
            Message msg = Message.obtain();
            msg.what = Constants.MSG_EXCEPTION;
            msg.obj = Constants.UNKNOWN_HOST;
            mHandler.sendMessage(msg);
        } catch (IOException e) {
            Message msg = Message.obtain();
            msg.what = Constants.MSG_EXCEPTION;
            msg.obj = Constants.IO_ERR;
            mHandler.sendMessage(msg);
        } catch (Exception e) {
            Message msg = Message.obtain();
            e.printStackTrace();
            msg.what = Constants.MSG_EXCEPTION;
            msg.obj = Constants.EXCEPTION_ERR;
            mHandler.sendMessage(msg);
        } finally {
            isRun = false;
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                httpClient.close();
            }
        }
    }
    public static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();

    }

}
