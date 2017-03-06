package org.javimelli.model;

public class Platform {
	
	private int id;
	private String name;
	private int version;
	
	public Platform(){
		
	}

	public Platform(int id, String name, int version) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
