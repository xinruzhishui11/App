package com.yangfang.entiy;

import java.util.List;


/**
 * ÐÂ
 * @author Administrator
 *
 */
public class NewData extends BaseData{
	
	
	
	private int count;
	private int count_total; 
	private int pages;
	
	public  List<Posts> posts;
	
	public class Posts {
		private int id;
		private String url;
		private String title;
		private String date;
		
		private custom_fields custom_fields;
		
		
		public custom_fields getCustom_fields() {
			return custom_fields;
		}
		public void setCustom_fields(custom_fields custom_fields) {
			this.custom_fields = custom_fields;
		}
		public class custom_fields{
			private String [] thumb_c ;

			public String[] getThumb_c() {
				return thumb_c;
			}

			public void setThumb_c(String[] thumb_c) {
				this.thumb_c = thumb_c;
			}

			
			
		}
		
		
		private  List<Tags>tags;
		public class  Tags{
			private String title;

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}
			
		}
		
		private Author author;
		
		public class Author{
			private String name;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
			
		}
		
		
		
		
		
		public Author getAuthor() {
			return author;
		}
		public void setAuthor(Author author) {
			this.author = author;
		}
		public List<Tags> getTags() {
			return tags;
		}
		public void setTags(List<Tags> tags) {
			this.tags = tags;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
		
		
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount_total() {
		return count_total;
	}

	public void setCount_total(int count_total) {
		this.count_total = count_total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}


	
	
	
}
