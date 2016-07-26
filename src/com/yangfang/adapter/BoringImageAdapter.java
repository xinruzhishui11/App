package com.yangfang.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.app.R;
import com.yangfang.entiy.BoringImageData.comments;
import com.yangfang.entiy.DuanziDate.Comments;
import com.yangfang.utils.Toosl;

public class BoringImageAdapter extends BaseAdapter {
	private Context context;
	private List<comments> list;
	private ImageLoader loader;

	

	public BoringImageAdapter(Context context, List<comments> list,ImageLoader loader) {
		super();
		this.context = context;
		this.list = list;
		this.loader=loader;
	}

	public void upData(List<comments> list) {
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
					R.layout.boringimgae_item, null);
			convertView.setTag(hold);
		} else {
			hold = (ViewHold) convertView.getTag();
		}

		hold.boringimage_item_title = (TextView) convertView
				.findViewById(R.id.boringimage_item_title);
		hold.boringimage_item=(ImageView)convertView.findViewById(R.id.boringimage_item);
		hold.boringimage_item_time=(TextView)convertView.findViewById(R.id.boringimage_item_time);
	
         
		
		hold.boringimage_item_title.getPaint().setFakeBoldText(true);// 给标题的字体加粗
		
		hold.boringimage_item_title.setText(list.get(position).getComment_author());
		//加载图片
		loader.displayImage(list.get(position).getPics()[0], hold.boringimage_item,Toosl.Imageloader());
			
     
		
		

	

		return convertView;
	}

	class ViewHold {
		public TextView boringimage_item_title;
		public ImageView boringimage_item ;
		public TextView  boringimage_item_time;
		
		
		
	}
	
	
	
	
		
		
		
		
		
		
	}
	
	

	


