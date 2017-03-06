package org.javimelli.model;

public class Screenshot {

	private int id;
	private String url_capture;
	private int app_id;
	
	public Screenshot(){
		
	}

	public Screenshot(int id, String url_capture, int app_id) {
		super();
		this.id = id;
		this.url_capture = url_capture;
		this.app_id = app_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_capture() {
		return url_capture;
	}

	public void setUrl_capture(String url_capture) {
		this.url_capture = url_capture;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	
	
}
