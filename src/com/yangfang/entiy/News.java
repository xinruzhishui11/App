package com.yangfang.entiy;

public class News {
	public String title;
	public String date;
	public String author_name;
	public String thumbnail_pic_s;
	public String thumbnail_pic_s02;
	public String thumbnail_pic_s03;
	public String url;
	public String uniquekey;
	public String type;
	public String realtype;
	@Override
	public String toString() {
		return "News [title=" + title + ", date=" + date + ", author_name="
				+ author_name + ", thumbnail_pic_s=" + thumbnail_pic_s
				+ ", thumbnail_pic_s02=" + thumbnail_pic_s02
				+ ", thumbnail_pic_s03=" + thumbnail_pic_s03 + ", url=" + url
				+ ", uniquekey=" + uniquekey + ", type=" + type + ", realtype="
				+ realtype + "]";
	}
	
	
	
}
