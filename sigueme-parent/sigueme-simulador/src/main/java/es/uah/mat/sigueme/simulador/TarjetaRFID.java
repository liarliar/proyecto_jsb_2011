package es.uah.mat.sigueme.simulador;

public class TarjetaRFID {
	private String direccionLarga;
	
	public TarjetaRFID(final String direccionLarga) {
		this.direccionLarga = direccionLarga;
	}
	public String getDireccionLarga() {
		return this.direccionLarga;		
	}
}
