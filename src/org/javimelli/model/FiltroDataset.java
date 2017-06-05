package org.javimelli.model;

public class FiltroDataset {
	
	private int categoria;
	private String pais;
	private String ciudad;
	
	public FiltroDataset(){}

	public FiltroDataset(int categoria, String pais, String ciudad) {
		super();
		this.categoria = categoria;
		this.pais = pais;
		this.ciudad = ciudad;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	

}
