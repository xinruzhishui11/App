package com.yangfang.Fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangfang.ui.LoadingPage;
import com.yangfang.ui.LoadingPage.ResultState;
import com.yangfang.utils.UIUtils;

public abstract class BaseFragment extends Fragment{

	private LoadingPage mLoadingPage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.i("edu", "Fragent ��ʼ��");
		
		
		mLoadingPage = new LoadingPage(UIUtils.getContext()) {

			@Override
			public View onCreateSuccessView() {
				return BaseFragment.this.onCreateSuccessView();
			}

			@Override
			public ResultState onLoad() {
				
				Log.i("edu", "baseFragment  ����������");
				return BaseFragment.this.onLoad();
			}

		};

		return mLoadingPage;
	}

	// ���سɹ��Ĳ���, ������������ʵ��
	public abstract View onCreateSuccessView();

	// ������������, ������������ʵ��
	public abstract ResultState onLoad();

	// ��ʼ��������
	public void loadData() {
		if (mLoadingPage != null) {
			mLoadingPage.loadData();
		}
	}

	// �����緵�����ݵĺϷ��Խ���У��
	public ResultState check(Object obj) {
		if (obj != null) {
			if (obj instanceof ArrayList) {// �ж��Ƿ��Ǽ���
				ArrayList list = (ArrayList) obj;

				if (list.isEmpty()) {
					return ResultState.STATE_EMPTY;
				} else {
					return ResultState.STATE_SUCCESS;
				}
			}
		}

		return ResultState.STATE_ERROR;
	}}
