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
import com.yangfang.holder.InlandHolder;
import com.yangfang.http.protocal.EntertainmentProtocol;
import com.yangfang.ui.LoadingPage.ResultState;
import com.yangfang.utils.UIUtils;

public class EntertainmentFragment extends BaseFragment{
	private ArrayList<News> data;

	@Override
	public View onCreateSuccessView() {
		ListView view = new ListView(UIUtils.getContext());
		view.setAdapter(new EntertainmentAdapter(data));
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				News news = data.get(position);
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("url", news.url);
				intent.putExtra("title", news.title);
				intent.putExtra("mtitle", "新闻资讯");
				startActivity(intent);
			}
		});
		
		return view;
	}

	@Override
	public ResultState onLoad() {
		EntertainmentProtocol protocol = new EntertainmentProtocol();
		data = protocol.getData();
		return check(data);
	}
	
	class EntertainmentAdapter extends MyBaseAdapter<News>{

		public EntertainmentAdapter(ArrayList<News> data) {
			super(data);
		}

		@Override
		public BaseHolder<News> getHolder(int position) {
			// TODO Auto-generated method stub
			return new InlandHolder();
		}

		@Override
		public ArrayList<News> onLoadMore() {
			return null;
		}
		
		@Override
		public boolean hasMore() {
			return false;
		}
		
	}

}
