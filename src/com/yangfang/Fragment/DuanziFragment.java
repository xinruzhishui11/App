package com.yangfang.Fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yangfang.adapter.DuanziAdapter;
import com.yangfang.adapter.lixtview.XListView;
import com.yangfang.adapter.lixtview.XListView.IXListViewListener;
import com.yangfang.app.R;
import com.yangfang.entiy.BaseData;
import com.yangfang.entiy.DuanziDate;
import com.yangfang.entiy.DuanziDate.Comments;
import com.yangfang.net.GetDataThread;
import com.yangfang.utils.NetUtils;
import com.yangfang.utils.TimeUtils;

public class DuanziFragment   extends  Fragment implements  IXListViewListener ,OnScrollListener{

	private GetDataThread  getdatethread;
	private XListView listduanzi;
	private DuanziDate data;
	private DuanziAdapter duanziadapter;
	private List<Comments>  listDatas = new  ArrayList<DuanziDate.Comments>();
	private int page;
	private View duziview;
	private boolean isRefresh;//判断是上拉状态 还是下拉状态
	private ProgressBar progress2;
	private boolean Tag=true;


	//更新adapter
	private  Handler  mhander = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case com.yangfang.net.Constants.HAND_GET_DATA_SUCCESS:
				data=(DuanziDate) msg.obj;
				if(isRefresh){
					listDatas.clear();//将原有的数据清空  显示显示新的数据
					listDatas.addAll(data.getComments());

				}else{
					listDatas.addAll(data.getComments());//直接显示在元数据的后面一页
				}

				duanziadapter.upData(listDatas);
				//适配器更新之后  关闭header  调用
				onLoad();
				break;
			}  
		}

	};
	private TextView duanzititle;
	private TextView duanzitext;





	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_duanzi_list, null);
		
		if (!NetUtils.haveNet(getActivity())){
			Toast.makeText(getActivity(), "数据加载失败，请检查网络", Toast.LENGTH_SHORT).show();
			return null;
		}

		//
		setView(view);
		//请求数据源
		setDate();
		return view;
	}


	/**
	 * 请求数据源
	 */
	private void setDate() {
		BaseData mdata = new DuanziDate();
		String url = "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page="+page;
		if (null != getdatethread && getdatethread.isRunning())
			return;
		if (null != getdatethread && getdatethread.isAlive()) {
			getdatethread.interrupt();
			getdatethread = null;
		}

		getdatethread = new GetDataThread(getActivity(), mhander, url, mdata);
		getdatethread.start();



	}



	/**
	 * 初始化控件
	 * @param view
	 */
	private void setView(View view) {
		progress2=(ProgressBar)view.findViewById(R.id.progressBar2);
		listduanzi=(XListView)view.findViewById(R.id.list_duanzi);
		duanzititle=(TextView)view.findViewById(R.id.duanzi_item_title);
		duanzitext=(TextView)view.findViewById(R.id.duanzi_item_text);
		
		duanziadapter = new DuanziAdapter(getActivity(), listDatas);
		listduanzi.setAdapter(duanziadapter);
		duziview=(View)view.findViewById(R.id.duziview);

		//给XlistView配置监听
		listduanzi.setXListViewListener(this);
		listduanzi.setPullLoadEnable(true);
		listduanzi.setFooterLoadMoreEnabled(true);
		listduanzi.setOnScrollListener(this);
		
		
		
		//item的点击事件
		listduanzi.setOnItemClickListener(new OnItemClickListener() {

						@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2>0){
					if(Tag){
						duziview.setVisibility(View.VISIBLE);
						duanzititle.setText(listDatas.get(arg2-1).getComment_author());
						duanzitext.setText(listDatas.get(arg2-1).getComment_content());
						Tag=false;
						
					}else{
						duziview.setVisibility(View.GONE);
						Tag=true;
					}
					
					}
				
			}
		});


	}


	/**
	 * 监听listView的状态
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if(view.getLastVisiblePosition() == view.getCount() - 1){//上拉加载
			page=page+1;
			setDate();

		}



	}


	/**
	 * 监听listView的状态
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {


	}


	@Override
	public void onRefresh() {//下拉刷新
		isRefresh=true;
		page=1;

		setDate();


	}


	@Override
	public void onLoadMore() {

	}


	public void onLoad() {//关闭下拉或者上拉显示
		listduanzi.setRefreshTime(TimeUtils.getCurrentTimeInString());
		listduanzi.stopRefresh();
		listduanzi.stopLoadMore();
		progress2.setVisibility(View.GONE);

	}











}
