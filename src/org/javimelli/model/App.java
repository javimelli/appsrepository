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
	private String id_fotos;
	private String time;
	private String date;
	private int visits;
	
	public App(){
		
	}

	public App(int id, int user_id, String url_web, String title, String description, String url_icon, int price,
			int version, String url_video, String language, String country, String id_fotos, String time, String date,
			int visits) {
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
		this.id_fotos = id_fotos;
		this.time = time;
		this.date = date;
		this.visits = visits;
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

	public String getId_fotos() {
		return id_fotos;
	}

	public void setId_fotos(String id_fotos) {
		this.id_fotos = id_fotos;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getVisitas() {
		return visits;
	}

	public void setVisitas(int visits) {
		this.visits = visits;
	}
	
	
	
}
