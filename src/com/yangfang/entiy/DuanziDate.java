package com.yangfang.entiy;

import java.util.List;

public class DuanziDate  extends  BaseData {
	private String  current_page;
    private  String  total_comments;
    private String   page_count ;
    private  String   count;
    public  List<Comments> comments;
    
    public  class  Comments{
    	
    	private  String  comment_author;
    	private  String   comment_date ;
    	private  String    comment_date_gmt;
    	private  String    comment_content;
    	private   String   comment_kar;
    	private   String    comment_approved;
    	private   String     comment_parent ;
         private  String  user_id ;
       private  String  comment_subscribe ;
       private  String  comment_reply_ID;
       private String  vote_positive ;
       private String  vote_negative  ;
       private String  vote_ip_pool ;
      private String   text_content ;
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_kar() {
		return comment_kar;
	}
	public void setComment_kar(String comment_kar) {
		this.comment_kar = comment_kar;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComment_subscribe() {
		return comment_subscribe;
	}
	public void setComment_subscribe(String comment_subscribe) {
		this.comment_subscribe = comment_subscribe;
	}
	public String getComment_reply_ID() {
		return comment_reply_ID;
	}
	public void setComment_reply_ID(String comment_reply_ID) {
		this.comment_reply_ID = comment_reply_ID;
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
	public String getVote_ip_pool() {
		return vote_ip_pool;
	}
	public void setVote_ip_pool(String vote_ip_pool) {
		this.vote_ip_pool = vote_ip_pool;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
      
      
    }

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

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
    
    
    
    
	
	

}
