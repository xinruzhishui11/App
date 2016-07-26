package com.yangfang.adapter;

import java.util.List;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.app.R;
import com.yangfang.entiy.BaseData;
import com.yangfang.entiy.NewData;
import com.yangfang.utils.Toosl;

public class NewsAdapter extends BaseAdapter{
	
	private Context context;
	private List<NewData.Posts> list;
	private ImageLoader loader;
	public NewsAdapter(Context context, List<NewData.Posts> list,ImageLoader loader) {
		super();
		this.context = context;
		this.loader = loader;
		this.list = list;
	}
	
	public void upData( List<NewData.Posts> list){
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		return list.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold hold;
		if (convertView == null) {
			hold = new ViewHold();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.news_item, null);
			convertView.setTag(hold);
		} else {
			hold = (ViewHold) convertView.getTag();
		}

		hold.item_news_text = (TextView) convertView.findViewById(R.id.item_news_text);
		hold.item_news_from = (TextView) convertView.findViewById(R.id.item_news_from);
		hold.item_news_image = (ImageView) convertView.findViewById(R.id.item_news_image);
		

//		hold.imageView.setImageResource(list.get(position).getImageview());
		hold.item_news_text.setText(list.get(position).getTitle());
		hold.item_news_from.setText(list.get(position).getAuthor().getName()+"@"+list.get(position).getTags().get(0).getTitle());
		
		
//		loader = ImageLoader.getInstance();
//		loader.displayImage("http://img3.duitang.com/uploads/item/201605/25/20160525093455_Qa2yR.thumb.700_0.jpeg", hold.item_news_image,options);
		loader.displayImage(list.get(position).getCustom_fields().getThumb_c()[0], hold.item_news_image,Toosl.Imageloader());
		return convertView;
		
	}

	class ViewHold {
		public TextView item_news_text;
		public TextView item_news_from;
		public ImageView item_news_image;
	}

	

}
