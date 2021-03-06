package es.uah.mat.sigueme.simulador;

import java.text.*;
import java.util.*;


public class MensajeRFID {

	private TipoMensaje tipo;
	private String direccionCorta;
	private Date fecha;
	private String direccionLarga;
	
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

	public String toString()
	{
		DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
		return tipo.toString()+getDireccionCorta()+df.format(fecha)+
				direccionLarga;
	}
}
