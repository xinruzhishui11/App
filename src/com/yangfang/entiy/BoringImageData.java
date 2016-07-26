package com.yangfang.entiy;

import java.util.List;

public class BoringImageData  extends BaseData{
   private  String 	current_page;
   private  String  total_comments;
   private  String page_count;
   private  String  count;
   
   
   private List<comments>  comments;
   
   
   
   
   
   
   public String getCurrent_page() {
	return current_page;
}






public void setCurrent_page(String current_page) {
	this.current_page = current_page;
}






public String getTotal_comments() {
	return total_comments;
}






public void setTotal_comments(String total_comments) {
	this.total_comments = total_comments;
}






public String getPage_count() {
	return page_count;
}






public void setPage_count(String page_count) {
	this.page_count = page_count;
}






public String getCount() {
	return count;
}






public void setCount(String count) {
	this.count = count;
}






public List<comments> getComments() {
	return comments;
}






public void setComments(List<comments> comments) {
	this.comments = comments;
}






public  class comments{
	private String    comment_author;
	private String    comment_date;
    
	private String   comment_date_gmt;
	private String    comment_karma;
	private String   comment_approved;
	private String  comment_parent;
	private String   comment_subscribe;
	private String   vote_positive;
	private String   vote_negative;
	
	private  String []  pics;

	public String getComment_author() {
		return comment_author;
	}

	public void setComment_author(String comment_author) {
		this.comment_author = comment_author;
	}

	public String getComment_date() {
		return comment_date;
	}

	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}

	public String getComment_date_gmt() {
		return comment_date_gmt;
	}

	public void setComment_date_gmt(String comment_date_gmt) {
		this.comment_date_gmt = comment_date_gmt;
	}

	public String getComment_karma() {
		return comment_karma;
	}

	public void setComment_karma(String comment_karma) {
		this.comment_karma = comment_karma;
	}

	public String getComment_approved() {
		return comment_approved;
	}

	public void setComment_approved(String comment_approved) {
		this.comment_approved = comment_approved;
	}

	public String getComment_parent() {
		return comment_parent;
	}

	public void setComment_parent(String comment_parent) {
		this.comment_parent = comment_parent;
	}

	public String getComment_subscribe() {
		return comment_subscribe;
	}

	public void setComment_subscribe(String comment_subscribe) {
		this.comment_subscribe = comment_subscribe;
	}

	public String getVote_positive() {
		return vote_positive;
	}

	public void setVote_positive(String vote_positive) {
		this.vote_positive = vote_positive;
	}

	public String getVote_negative() {
		return vote_negative;
	}

	public void setVote_negative(String vote_negative) {
		this.vote_negative = vote_negative;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   }
   

	
	
	
	
	
	
	

}
