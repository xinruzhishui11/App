package com.yangfang.holder;

import android.util.Log;
import android.view.View;

public abstract class BaseHolder<T> {

	private View mRootView;// �?个item的根布局

	private T data;

	//当new这个对象�?, 就会加载布局, 初始化控�?,设置tag
	public BaseHolder() {
		Log.i("edu", "BaseHolder构�?�方�?");
		mRootView = initView();
		// 3. 打一个标记tag
		mRootView.setTag(this);
	}

	// 1. 加载布局文件
	// 2. 初始化控�? findViewById
	public abstract View initView();

	// 返回item的布�?对象
	public View getRootView() {
		return mRootView;
	}

	// 设置当前item的数�?
	public void setData(T data) {
		this.data = data;
		refreshView(data);
	}

	// 获取当前item的数�?
	public T getData() {
		return data;
	}

	// 4. 根据数据来刷新界�?
	public abstract void refreshView(T data);

}
