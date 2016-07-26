package com.yangfang.entiy;

import java.util.List;

public class MoviesDate extends BaseData {
	private String current_page;
	private String total_comments;
	private String page_count;
	private String count;
	private List<Comments> comments;

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

	public class Comments {
		private String comment_content;
		private String comment_approved;
		private String vote_positive;
		private String vote_negative;
		private List<videos> videos;

		public String getComment_content() {
			return comment_content;
		}

		public void setComment_content(String comment_content) {
			this.comment_content = comment_content;
		}

		public String getComment_approved() {
			return comment_approved;
		}

		public void setComment_approved(String comment_approved) {
			this.comment_approved = comment_approved;
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

		public List<videos> getVideos() {
			return videos;
		}

		public void setVideos(List<videos> videos) {
			this.videos = videos;
		}

		public class videos {
			private String title;
			private String thumbnail;
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getThumbnail() {
				return thumbnail;
			}
			public void setThumbnail(String thumbnail) {
				this.thumbnail = thumbnail;
			}
			
			
			
			
           
		}

	}

}
