package org.javimelli.model;


public class User{
	
	private int id;
	private String name;
	private String Last_name1;
	private String Last_name2;
	private String username;
	private String tlf;
	private String url_web;
	private String email;
	private String country;
	private String url_foto;
	private String password;
	
	public User() {
	}
	
	public User(int id, String name, String last_name1, String last_name2, String username, String tlf, String url_web,
			String email, String country, String url_foto, String password) {
		super();
		this.id = id;
		this.name = name;
		Last_name1 = last_name1;
		Last_name2 = last_name2;
		this.username = username;
		this.tlf = tlf;
		this.url_web = url_web;
		this.email = email;
		this.country = country;
		this.url_foto = url_foto;
		this.password = password;
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
	public String getLast_name1() {
		return Last_name1;
	}
	public void setLast_name1(String last_name1) {
		Last_name1 = last_name1;
	}
	public String getLast_name2() {
		return Last_name2;
	}
	public void setLast_name2(String last_name2) {
		Last_name2 = last_name2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public String getUrl_web() {
		return url_web;
	}
	public void setUrl_web(String url_web) {
		this.url_web = url_web;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
