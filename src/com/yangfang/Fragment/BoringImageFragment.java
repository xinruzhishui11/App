package com.yangfang.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.activity.BoringImageActivity;
import com.yangfang.adapter.BoringImageAdapter;
import com.yangfang.adapter.lixtview.XListView;
import com.yangfang.adapter.lixtview.XListView.IXListViewListener;
import com.yangfang.app.R;
import com.yangfang.entiy.BaseData;
import com.yangfang.entiy.BoringImageData;
import com.yangfang.entiy.BoringImageData.comments;
import com.yangfang.net.Constants;
import com.yangfang.net.GetDataThread;
import com.yangfang.utils.NetUtils;
import com.yangfang.utils.TimeUtils;

public class BoringImageFragment extends Fragment implements
		IXListViewListener, OnScrollListener {
	private GetDataThread mGetDataThread;
	private XListView boringimagexlistview;

	private List<comments> boringimagelist = new ArrayList<comments>();

	private BoringImageAdapter boringimageAdapter;

	private BoringImageAdapter adapter;
	private ProgressBar progressBar2;
	private boolean isRefresh = true;// 判断是上拉状态还是下拉刷新状态
	private boolean   istag=true; //判断刚点击图片时   标题的状态
	private int page = 1;
	private ImageLoader loader;

	private Handler mHandler = new Handler() {

		private BoringImageData data;

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case Constants.HAND_GET_DATA_SUCCESS:
				data = (BoringImageData) msg.obj;
				if (isRefresh) {
					boringimagelist.clear();
					boringimagelist.addAll(data.getComments());
				} else {
					boringimagelist.addAll(data.getComments());
				}
				adapter.upData(boringimagelist);
				onLoad();
				break;
 
			}

		}

	};
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_boringfigure_list, null);


		if (!NetUtils.haveNet(getActivity())){
			Toast.makeText(getActivity(), "数据加载失败，请检查网络", Toast.LENGTH_SHORT).show();
			return null;
		}
		setView(view);

		setData();

		return view;
	}

	// 得到数据
	private void setData() {

		BaseData mData = new BoringImageData();
		String url = "http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&size=20&page="+ page;
//		String url = "http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_pic_comments&page="+ page;
		if (null != mGetDataThread && mGetDataThread.isRunning())
			return;
		if (null != mGetDataThread && mGetDataThread.isAlive()) {
			mGetDataThread.interrupt();
			mGetDataThread = null;
		}
		// dialog=Tools.createProgressDialog(this);
		mGetDataThread = new GetDataThread(getActivity(), mHandler, url, mData);
		mGetDataThread.start();

	}

	// 初始化控件
	private void setView(View view) {
		
		loader = ImageLoader.getInstance();
		boringimagexlistview = (XListView) view
				.findViewById(R.id.xListViewimage);
		progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
//		imagetitlelayout=(RelativeLayout)view.findViewById(R.id.rl1);
		
		adapter = new BoringImageAdapter(getActivity(), boringimagelist,loader);
		boringimagexlistview.setAdapter(adapter);

		
		boringimagexlistview.setXListViewListener(this);
		boringimagexlistview.setPullLoadEnable(true);
		boringimagexlistview.setFooterLoadMoreEnabled(true);
		boringimagexlistview.setOnScrollListener(this);
		
		//item的点击事件
	boringimagexlistview.setOnItemClickListener(new  OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			
			 
			if(position>0){
				
				Intent  intent = new  Intent(getActivity(),BoringImageActivity.class);
				intent.putExtra("position", boringimagelist.get(position-1).getPics()[0]);
				startActivity(intent);

			}
			
			
}
		
	});
		
}	
		
		
		
@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		
		isRefresh = false;
		page = page + 1;
		setData();

	}
	

	@Override
	public void onRefresh() {
		isRefresh = true;// 判断是上拉状态还是下拉刷新状态
		page = 1;// 恢复最新的1页数据
		setData();

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	public void onLoad() {// 关闭下拉或者上拉显示
		boringimagexlistview.setRefreshTime(TimeUtils.getCurrentTimeInString());
		boringimagexlistview.stopRefresh();
		boringimagexlistview.stopLoadMore();
		progressBar2.setVisibility(View.GONE);
	}

}
