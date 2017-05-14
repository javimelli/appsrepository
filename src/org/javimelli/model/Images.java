package org.javimelli.model;

public class Images {
	
	private int id;
	private String id_fotos;
	private String url;
	private String type;
	
	public Images(){}

	public Images(int id, String id_fotos, String url, String type) {
		super();
		this.id = id;
		this.id_fotos = id_fotos;
		this.url = url;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_fotos() {
		return id_fotos;
	}

	public void setId_fotos(String id_fotos) {
		this.id_fotos = id_fotos;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
