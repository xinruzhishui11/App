package com.yangfang.Fragment;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.activity.WebViewActivity;
import com.yangfang.adapter.MoviesAdapter;
import com.yangfang.adapter.lixtview.XListView;
import com.yangfang.adapter.lixtview.XListView.IXListViewListener;
import com.yangfang.app.R;
import com.yangfang.entiy.BaseData;
import com.yangfang.entiy.MoviesDate;

import com.yangfang.entiy.MoviesDate.Comments;
import com.yangfang.net.Constants;
import com.yangfang.net.GetDataThread;
import com.yangfang.utils.NetUtils;
import com.yangfang.utils.TimeUtils;

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

public class MoviesFragment   extends Fragment implements  IXListViewListener,OnScrollListener{
	private XListView xlistmovies;
	private  List<Comments>  movieslist = new  ArrayList<Comments>();
	private   GetDataThread mGetDataThread;
	private MoviesAdapter moviesadapter;
	private MoviesDate data;
	private int page=1;
	private ProgressBar moviesprogress;
	private ImageLoader loader;
	private boolean isRefresh=true;//判断是上拉状态还是下拉刷新状态
	
	private Handler  mHandler = new  Handler(){

		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case Constants.HAND_GET_DATA_SUCCESS:
				data=(MoviesDate) msg.obj;

				if(isRefresh){
					movieslist.clear();
					movieslist.addAll(data.getComments());
				}else{
					movieslist.addAll(data.getComments());
				}
                moviesadapter.upData(movieslist);
				onLoad();
				break;
				
			}
			
			
		}
		
	};
	
	
	

		@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_movies_list, null);

		if (!NetUtils.haveNet(getActivity())){
			Toast.makeText(getActivity(), "数据加载失败，请检查网络", Toast.LENGTH_SHORT).show();
			return null;
		}
		setViews(view);
		setDate();
		
		return  view;
	}
	
	//得到数据
   private void setDate() {
	   
	   BaseData mData = new MoviesDate();
		String url = "http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_video_comments&page="+page;
		if (null != mGetDataThread && mGetDataThread.isRunning())
			return;
		if (null != mGetDataThread && mGetDataThread.isAlive()) {
			mGetDataThread.interrupt();
			mGetDataThread = null;
		}
		//dialog=Tools.createProgressDialog(this);
		mGetDataThread = new GetDataThread(getActivity(), mHandler, url, mData);
		mGetDataThread.start();

	   
	   
		
	}
   
   
   
	//初始化
	private void setViews(View view) {
		loader = ImageLoader.getInstance();
		
		xlistmovies=(XListView)view.findViewById(R.id.xList_movies);
		moviesprogress=(ProgressBar)view.findViewById(R.id.progressBar3);
		moviesadapter= new  MoviesAdapter(getActivity(), movieslist,loader);
		xlistmovies.setAdapter(moviesadapter);
		
		xlistmovies.setXListViewListener(this);
		xlistmovies.setPullLoadEnable(true);
		xlistmovies.setFooterLoadMoreEnabled(true);
		xlistmovies.setOnScrollListener(this);

		//item的监听
		xlistmovies.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent  intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("murl", movieslist.get(position).getComment_content());
				intent.putExtra("mtitle", movieslist.get(position-1).getVideos().get(0).getTitle());
				startActivity(intent);
				
				
				
				
			}
			
			
		});
		
		
		
		
}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (view.getLastVisiblePosition() == view.getCount() - 1) {
			isRefresh=false;
			page = page + 1;
		     setDate();
	}
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		isRefresh=true;//判断是上拉状态还是下拉刷新状态
		page=1;//恢复最新的1页数据
		setDate();
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}
	
	public void onLoad() {//关闭下拉或者上拉显示
		xlistmovies.setRefreshTime(TimeUtils.getCurrentTimeInString());
		xlistmovies.stopRefresh();
		xlistmovies.stopLoadMore();
		moviesprogress.setVisibility(View.GONE);
	}
	
	
	
	
	

}
