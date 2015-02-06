package com.example.bean;

public class Good {

	private String title;
	private String url;
	private String price;
	private String level;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Good() {
		super();
	}
	@Override
	public String toString() {
		return "Good [title=" + title + ", url=" + url + ", price=" + price
				+ ", level=" + level + "]";
	}
	
}
