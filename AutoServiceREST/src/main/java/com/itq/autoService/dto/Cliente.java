package com.itq.autoService.dto;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private String telefono;
	private Direccion direccion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		
		try {
			
			if (telefono.length() == 10 && telefono.matches("[0-9]+")) {
				this.telefono = telefono;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR: El telefono debe contener 10 digitos y solo valores numericos");
			this.telefono = null;
		}
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	

}
