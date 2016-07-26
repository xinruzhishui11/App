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

	
	//成功显示的布局
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
				intent.putExtra("mtitle", "新闻资讯");
				startActivity(intent);
			}
		});
		return view;
	}

	//工作在子线程中，加载数据
	@Override
	public ResultState onLoad() {
		TopProtocol protocol = new TopProtocol();
		mList = protocol.getData();
		return check(mList);//数据进行检验
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
			return false;//没有更多数据
		}
		
	}
}
