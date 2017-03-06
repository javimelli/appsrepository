package org.javimelli.model;

public class Dataset_app {

	private int app_id;
	private int dataset_id;
	
	public Dataset_app(){
		
	}

	public Dataset_app(int app_id, int dataset_id) {
		super();
		this.app_id = app_id;
		this.dataset_id = dataset_id;
	}
	
	public int getApp_id(){
		return this.app_id;
	}
	
	public void setApp_id(int app_id){
		this.app_id = app_id;
	}
	
	public int getDataset_id(){
		return this.dataset_id;
	}
	
	public void setDataset_id(int dataset_id){
		this.dataset_id = dataset_id;
	}
	
	
}
