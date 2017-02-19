package org.javimelli.model;


public class User{
	
	private int id_user;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nombre_desarrollador;
	private String telefono;
	private String url_web;
	private String email;
	private String pais;
	private String url_foto;
	private String password;
	
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

	public User(){
		
	}

	public User(int id_user, String nombre, String apellido1, String apellido2, String nombre_desarrollador,
			String telefono, String url_web, String email, String pais) {
		super();
		this.id_user = id_user;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombre_desarrollador = nombre_desarrollador;
		this.telefono = telefono;
		this.url_web = url_web;
		this.email = email;
		this.pais = pais;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNombre_desarrollador() {
		return nombre_desarrollador;
	}

	public void setNombre_desarrollador(String nombre_desarrollador) {
		this.nombre_desarrollador = nombre_desarrollador;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
