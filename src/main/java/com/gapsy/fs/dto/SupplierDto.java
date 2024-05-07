package com.gapsy.fs.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupplierDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6835443627995852650L;
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	private String razonSocial;
	private String direccion;
	
	
	public SupplierDto() {
		super();
	}
	
	public SupplierDto(String name, String razonSocial, String direccion) {
		super();
		this.name = name;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
}
