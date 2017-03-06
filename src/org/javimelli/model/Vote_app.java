package org.javimelli.model;

public class Vote_app {
	
	private int user_id;
	private int app_id;
	private int value;
	private boolean complaint;
	
	public Vote_app(){
		
	}

	public Vote_app(int user_id, int app_id, int value, boolean complaint) {
		super();
		this.user_id = user_id;
		this.app_id = app_id;
		this.value = value;
		this.complaint = complaint;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isComplaint() {
		return complaint;
	}

	public void setComplaint(boolean complaint) {
		this.complaint = complaint;
	}
	
}
