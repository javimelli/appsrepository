package org.javimelli.model;

public class App_category {

	private int app_id;
	private int category_id;
	
	public App_category(){
		
	}

	public App_category(int app_id, int category_id) {
		super();
		this.app_id = app_id;
		this.category_id = category_id;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	
}
