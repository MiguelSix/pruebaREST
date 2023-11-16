package com.itq.autoService.dto;

import java.util.List;

public class Servicio {
	
	private String fechaIngreso; 
	private String fechaSalidaProgramada;
	private String fechaSalidaReal;
	private String tipoServicio;
	private List<String> refacciones;
	private List<String> procedimientosRealizados;
	private String observaciones;
	
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		
		try {
			
			if(fechaIngreso.matches("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/")) {
				this.fechaIngreso = fechaIngreso;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error en el formato de fecha");
			this.fechaIngreso = null;
		}
	}
	public String getFechaSalidaProgramada() {
		return fechaSalidaProgramada;
	}
	public void setFechaSalidaProgramada(String fechaSalidaProgramada) {
		
		try {
			
			if(fechaSalidaProgramada.matches("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/")) {
				this.fechaSalidaProgramada = fechaSalidaProgramada;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error en el formato de fecha");
			this.fechaSalidaProgramada = null;
		}
	}
	public String getFechaSalidaReal() {
		return fechaSalidaReal;
	}
	public void setFechaSalidaReal(String fechaSalidaReal) {
		
		try {

			if(fechaSalidaReal.matches("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/")) {
				this.fechaSalidaReal = fechaSalidaReal;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error en el formato de fecha");
			this.fechaSalidaReal = null;
		}
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public List<String> getRefacciones() {
		return refacciones;
	}
	public void setRefacciones(List<String> refacciones) {
		this.refacciones = refacciones;
	}
	public List<String> getProcedimientosRealizados() {
		return procedimientosRealizados;
	}
	public void setProcedimientosRealizados(List<String> procedimientosRealizados) {
		this.procedimientosRealizados = procedimientosRealizados;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
