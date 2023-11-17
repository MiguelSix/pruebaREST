package com.itq.autoService.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Auto {
	
	@JsonProperty("id")
	//@Pattern(regexp="[A-Z]{2}[0-9]{3}")
	private int idAuto;
	@Min(1995)
	@Max(2026)
	@NotNull
	private int modelo;
	//@Pattern(regexp="[Chevrolet, Mercedes]")
	private String marca;
	@NotNull
	@NotEmpty
	private String color;
	
	public int getIdAuto() {
		return idAuto;
	}
	public void setIdAuto(int idAuto) {
		this.idAuto = idAuto;
	}
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
