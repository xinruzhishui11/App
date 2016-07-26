//package com.yangfang.utils;
//
//import java.io.UnsupportedEncodingException;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.ParseError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.google.gson.Gson;
//
///**
// * Created by lcy on 2016/4/15.
// */
////public class GsonRequest<T> extends Request<T> {
////
////    private final Response.Listener<T> mListener;
////
////    private Gson mGson;
////
////    private Class<T> mClass;
////    
////    private Context mContext;
////
//////	private ProgressDialog createProgressDialog;
////
////    public GsonRequest(int method, Context context, String url, Class<T> clazz, Response.Listener<T> listener,
////                       Response.ErrorListener errorListener) {
////        super(method, url, errorListener);
////        mContext = context;
////        mGson = new Gson();
////        mClass = clazz;
////        mListener = listener;
//////        createProgressDialog = Utils.showProgressDialog(mContext);
////    }
////
////    public GsonRequest(Context context, String url, Class<T> clazz, Response.Listener<T> listener,
////                       Response.ErrorListener errorListener) {
////        this(Method.GET, context, url, clazz, listener, errorListener);
////    }
////
////    @Override
////    protected Response<T> parseNetworkResponse(NetworkResponse response) {
////        try {
////            String jsonString = new String(response.data, 
////            		HttpHeaderParser.parseCharset(response.headers));
////            return Response.success(mGson.fromJson(jsonString, mClass),
////                    HttpHeaderParser.parseCacheHeaders(response));
////        } catch (UnsupportedEncodingException e) {
////            return Response.error(new ParseError(e));
////        }
////    }
////
////    @Override
////    protected void deliverResponse(T response) {
//////    	if (createProgressDialog.isShowing()) {
//////			createProgressDialog.dismiss();
//////		}
////        mListener.onResponse(response);
////    }
////    
////    @Override
////    public void deliverError(VolleyError error) {
////    	super.deliverError(error);
//////    	if (createProgressDialog.isShowing()) {
//////			createProgressDialog.dismiss();
//////		}
////    }
//}
