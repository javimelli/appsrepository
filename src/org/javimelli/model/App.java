package org.javimelli.model;

public class App {

	private int id;
	private int user_id;
	private String url_web;
	private String title;
	private String description;
	private String url_icon;
	private int price;
	private int version;
	private String url_video;
	private String language;
	private String country;
	
	public App(){
		
	}

	public App(int id, int user_id, String url_web, String title, String description, String url_icon, int price,
			int version, String url_video, String language, String country) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.url_web = url_web;
		this.title = title;
		this.description = description;
		this.url_icon = url_icon;
		this.price = price;
		this.version = version;
		this.url_video = url_video;
		this.language = language;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUrl_web() {
		return url_web;
	}

	public void setUrl_web(String url_web) {
		this.url_web = url_web;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl_icon() {
		return url_icon;
	}

	public void setUrl_icon(String url_icon) {
		this.url_icon = url_icon;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUrl_video() {
		return url_video;
	}

	public void setUrl_video(String url_video) {
		this.url_video = url_video;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String conuntry) {
		this.country = conuntry;
	}
	
	
}
