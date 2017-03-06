package org.javimelli.model;

public class App_platform {

	private int app_id;
	private int platform_id;
	private String url_download;
	
	public App_platform(){
		
	}

	public App_platform(int app_id, int platform_id, String url_download) {
		super();
		this.app_id = app_id;
		this.platform_id = platform_id;
		this.url_download = url_download;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public int getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(int platform_id) {
		this.platform_id = platform_id;
	}

	public String getUrl_download() {
		return url_download;
	}

	public void setUrl_download(String url_download) {
		this.url_download = url_download;
	}
	
	
}
