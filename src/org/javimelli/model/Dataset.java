package org.javimelli.model;

public class Dataset {

	private int id;
	private int category_id;
	private int user_id;
	private String description;
	private String uri_dataset;
	private int institution_id;
	
	public Dataset(){
		
	}

	public Dataset(int id, int category_id, int user_id, String description, String uri_dataset, int institution_id) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.user_id = user_id;
		this.description = description;
		this.uri_dataset = uri_dataset;
		this.institution_id = institution_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUri_dataset() {
		return uri_dataset;
	}

	public void setUri_dataset(String uri_dataset) {
		this.uri_dataset = uri_dataset;
	}

	public int getInstitution_id() {
		return institution_id;
	}

	public void setInstitution_id(int institution_id) {
		this.institution_id = institution_id;
	}
	
	
}
