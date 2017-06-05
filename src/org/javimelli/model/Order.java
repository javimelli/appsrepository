package org.javimelli.model;

public class Order {
	
	private boolean vistas;
	private boolean precio;
	private boolean media;
	private String order;
	
	public Order(){}

	public Order(boolean vistas, boolean precio, boolean media, String order) {
		super();
		this.vistas = vistas;
		this.precio = precio;
		this.media = media;
		this.order = order;
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
	
	
}
