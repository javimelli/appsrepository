package org.javimelli.model;

public class Institution {

	private int id;
	private String name;
	private String country;
	private String city;
	private String province;
	
	public Institution(){
		
	}

	public Institution(int id, String name, String country, String city, String province) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.province = province;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
