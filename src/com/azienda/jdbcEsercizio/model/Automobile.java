package com.azienda.jdbcEsercizio.model;

public class Automobile {
	private Integer id;
	private String marca;
	private String modello;
	private String targa;
	private String colore;
	private Float cilindrata;

	public Automobile(Integer id, String marca, String modello, String targa, String colore, Float cilindrata) {
		super();
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.colore = colore;
		this.cilindrata = cilindrata;
	}
	
	public Automobile(String marca, String modello, String targa, String colore, Float cilindrata) {
		super();
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.colore = colore;
		this.cilindrata = cilindrata;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public Float getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Float cilindrata) {
		this.cilindrata = cilindrata;
	}

	@Override
	public String toString() {
		return "Automobile [id=" + id + ", marca=" + marca + ", modello=" + modello + ", targa=" + targa + ", colore="
				+ colore + ", cilindrata=" + cilindrata + "]";
	}



}

