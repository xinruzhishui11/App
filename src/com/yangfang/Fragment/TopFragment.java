package com.yangfang.Fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yangfang.activity.WebViewActivity;
import com.yangfang.adapter.MyBaseAdapter;
import com.yangfang.entiy.News;
import com.yangfang.holder.BaseHolder;
import com.yangfang.holder.TopHolder;
import com.yangfang.http.protocal.TopProtocol;
import com.yangfang.ui.LoadingPage.ResultState;
import com.yangfang.utils.UIUtils;

public class TopFragment extends BaseFragment{
	private ListView view;
	private ArrayList<News> mList;

	
	//�ɹ���ʾ�Ĳ���
	@Override
	public View onCreateSuccessView() {
		view = new ListView(UIUtils.getContext());
		NewsAdapter newsAdapter = new NewsAdapter(mList);
		view.setAdapter(newsAdapter);
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				News news = mList.get(position);
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("url", news.url);
				intent.putExtra("title", news.title);
				intent.putExtra("mtitle", "������Ѷ");
				startActivity(intent);
			}
		});
		return view;
	}

	//���������߳��У���������
	@Override
	public ResultState onLoad() {
		TopProtocol protocol = new TopProtocol();
		mList = protocol.getData();
		return check(mList);//���ݽ��м���
	}
	
	class NewsAdapter extends MyBaseAdapter<News>{

		public NewsAdapter(ArrayList<News> data) {
			super(data);
		}

		@Override
		public BaseHolder<News> getHolder(int position) {
			return new TopHolder();
		}

		@Override
		public ArrayList<News> onLoadMore() {
			return null;
		}
		
		@Override
		public boolean hasMore() {
			return false;//û�и�������
		}
		
	}
}
