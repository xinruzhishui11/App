//package com.yangfang.utils;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;
//import com.yangfang.app.BaseApplication;
//
//
//
///**
// * Created by lcy on 2016/4/15.
// */
//public class RequestManager {
//    public static RequestQueue mQueue= Volley.newRequestQueue(BaseApplication.getContext());
//
//    public static void  addRequest(Request<?> request,Object tag){
//        if (tag!=null){
//            request.setTag(tag);
//        }
//        mQueue.add(request);
//    }
//
//    public static  void cancleAll(Object tag){
//        mQueue.cancelAll(tag);
//    }
//}
