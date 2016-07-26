package com.yangfang.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yangfang.app.R;
import com.yangfang.entiy.MoviesDate.Comments;
import com.yangfang.utils.Toosl;

public class MoviesAdapter extends BaseAdapter {
	private Context context;
	private List<Comments> list;
	private ImageLoader loader;

	

	public MoviesAdapter(Context context, List<Comments> list,ImageLoader loader) {
		super();
		this.loader=loader;
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
					R.layout.movies_item, null);
			convertView.setTag(hold);
		} else {
			hold = (ViewHold) convertView.getTag();
		}
       
		hold.item_movies_image=(ImageView) convertView.findViewById(R.id.item_movies_image);
		hold.item_movies_text=(TextView)convertView.findViewById(R.id.item_movies_text);
		
		
	    hold.item_movies_text.setText(list.get(position).getVideos().get(0).getTitle());
		loader.displayImage(list.get(position).getVideos().get(0).getThumbnail(), hold.item_movies_image,Toosl.Imageloader());
	
      return convertView;
	}
	
	

	class ViewHold {
	
		public ImageView item_movies_image;
		public TextView  item_movies_text;
		
		
		
	}
	
	
	
	
		
		
		
		
		
		
	}
	
	

	


