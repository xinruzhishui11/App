package com.yangfang.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.yangfang.adapter.NewsAdapter.ViewHold;
import com.yangfang.app.R;
import com.yangfang.entiy.DuanziDate.Comments;
import com.yangfang.entiy.NewData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract.Data;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DuanziAdapter extends BaseAdapter {
	private Context context;
	private List<Comments> list;
	private long distanceTime;
	private long timelong;
	private Object startData;

	public DuanziAdapter(Context context, List<Comments> list) {
		super();
		this.context = context;
		this.list = list;
	}

	public void upData(List<Comments> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}

		return 0;
	}

	@Override
	public Object getItem(int position) {

		if (list != null) {
			return list.get(position);
		}
		return 0;
	}

	@Override
	public long getItemId(int position) {
		return 0;

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold hold;
		if (convertView == null) {
			hold = new ViewHold();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.duanzi_item, null);
			convertView.setTag(hold);
		} else {
			hold = (ViewHold) convertView.getTag();
		}

		hold.duanzi_item_title = (TextView) convertView
				.findViewById(R.id.duanzi_item_title);
		hold.duanzi_item_text = (TextView) convertView
				.findViewById(R.id.duanzi_item_text);
		hold.duanzi_item_time = (TextView) convertView
				.findViewById(R.id.duanzi_item_time);

		hold.duanzi_item_title.getPaint().setFakeBoldText(true);// 给标题的字体加粗

		hold.duanzi_item_title.setText(list.get(position).getComment_author());
		hold.duanzi_item_text.setText(list.get(position).getComment_content());
 
	
		

		
		

		return convertView;
	}

	class ViewHold {
		public TextView duanzi_item_title;
		public TextView duanzi_item_text;
		public TextView duanzi_item_time;
	}

	
	

}
