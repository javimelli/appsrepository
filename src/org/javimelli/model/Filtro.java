package org.javimelli.model;

public class Filtro {

	private int categoria;
	private String pais;
	private boolean vistas;
	private boolean precio;
	private boolean media;
	private String order;
	private int num;
	private int init;
	
	public Filtro(){}

	

	public Filtro(int categoria, String pais, boolean vistas, boolean precio, boolean media, String order, int num,
			int init) {
		super();
		this.categoria = categoria;
		this.pais = pais;
		this.vistas = vistas;
		this.precio = precio;
		this.media = media;
		this.order = order;
		this.num = num;
		this.init = init;
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

	public boolean isVistas() {
		return vistas;
	}

	public void setVistas(boolean vistas) {
		this.vistas = vistas;
	}

	public boolean isPrecio() {
		return precio;
	}

	public void setPrecio(boolean precio) {
		this.precio = precio;
	}

	public boolean isMedia() {
		return media;
	}

	public void setMedia(boolean media) {
		this.media = media;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}
	
	
	
}
