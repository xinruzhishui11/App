package com.yangfang.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yangfang.app.R;
import com.yangfang.entiy.News;
import com.yangfang.utils.BitmapHelper;
import com.yangfang.utils.UIUtils;

public class TopHolder extends BaseHolder<News> {

	private BitmapUtils mBitmapUtils;
	ViewHolder holder;

	@Override
	public View initView() {
		//初始化化控件
		View view = UIUtils.inflate(R.layout.list_item_news);
		
		if(holder == null){
			holder = new ViewHolder();
			holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
			holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
			holder.ivPic = (ImageView) view.findViewById(R.id.iv_pic);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		
		mBitmapUtils = BitmapHelper.getBitmapUtils();
		mBitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);
		
		
		
		return view;
	}

	@Override
	public void refreshView(News data) {
		//刷新界面
		holder.tvTitle.setText(data.title);
		holder.tvDate.setText(data.date);
		mBitmapUtils.display(holder.ivPic,data.thumbnail_pic_s);
	}
	
	class ViewHolder {
		TextView tvTitle;
		TextView tvDate;
		ImageView ivPic;
	}
}
