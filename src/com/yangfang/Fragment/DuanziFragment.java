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
	private boolean isRefresh;//�ж�������״̬ ��������״̬
	private ProgressBar progress2;
	private boolean Tag=true;


	//����adapter
	private  Handler  mhander = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case com.yangfang.net.Constants.HAND_GET_DATA_SUCCESS:
				data=(DuanziDate) msg.obj;
				if(isRefresh){
					listDatas.clear();//��ԭ�е��������  ��ʾ��ʾ�µ�����
					listDatas.addAll(data.getComments());

				}else{
					listDatas.addAll(data.getComments());//ֱ����ʾ��Ԫ���ݵĺ���һҳ
				}

				duanziadapter.upData(listDatas);
				//����������֮��  �ر�header  ����
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
			Toast.makeText(getActivity(), "���ݼ���ʧ�ܣ���������", Toast.LENGTH_SHORT).show();
			return null;
		}

		//
		setView(view);
		//��������Դ
		setDate();
		return view;
	}


	/**
	 * ��������Դ
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
	 * ��ʼ���ؼ�
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

		//��XlistView���ü���
		listduanzi.setXListViewListener(this);
		listduanzi.setPullLoadEnable(true);
		listduanzi.setFooterLoadMoreEnabled(true);
		listduanzi.setOnScrollListener(this);
		
		
		
		//item�ĵ���¼�
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
	 * ����listView��״̬
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if(view.getLastVisiblePosition() == view.getCount() - 1){//��������
			page=page+1;
			setDate();

		}



	}


	/**
	 * ����listView��״̬
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {


	}


	@Override
	public void onRefresh() {//����ˢ��
		isRefresh=true;
		page=1;

		setDate();


	}


	@Override
	public void onLoadMore() {

	}


	public void onLoad() {//�ر���������������ʾ
		listduanzi.setRefreshTime(TimeUtils.getCurrentTimeInString());
		listduanzi.stopRefresh();
		listduanzi.stopLoadMore();
		progress2.setVisibility(View.GONE);

	}











}
