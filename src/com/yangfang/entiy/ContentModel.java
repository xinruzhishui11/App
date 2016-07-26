package com.yangfang.entiy;


public class ContentModel {
	private  String  text;
	private  int  imageview;
	private int id;
	public ContentModel(String text, int imageview, int id) {
		super();
		this.text = text;
		this.imageview = imageview;
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getImageview() {
		return imageview;
	}
	public void setImageview(int imageview) {
		this.imageview = imageview;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
