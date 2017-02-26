package org.javimelli.model;


public class Commentary {
	
	private int id;
	private int user_id;
	private int app_id;
	private String date;
	private String time;
	private String text;
	
	public Commentary(){
		
	}

	public Commentary(int id, int user_id, int app_id, String date, String time, String text) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.app_id = app_id;
		this.date = date;
		this.time = time;
		this.text = text;
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

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
