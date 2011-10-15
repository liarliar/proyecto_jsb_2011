package es.uah.mat.sigueme.simulador;

import java.util.*;


public class MensajeRFID {

	public TipoMensaje getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	public String getDireccionCorta() {
		return direccionCorta;
	}
	public void setDireccionCorta(String direccionCorta) {
		this.direccionCorta = direccionCorta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDireccionLarga() {
		return direccionLarga;
	}
	public void setDireccionLarga(String direccionLarga) {
		this.direccionLarga = direccionLarga;
	}
	private TipoMensaje tipo;
	private String direccionCorta;
	private Date fecha;
	private String direccionLarga;

	public String toString()
	{
		// TODO formatear la fecha
		return this.tipo.toString()+this.getDireccionCorta()+this.fecha.toString()+
				this.direccionLarga;
	}
}
