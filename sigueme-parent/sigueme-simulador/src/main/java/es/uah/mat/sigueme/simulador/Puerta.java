package es.uah.mat.sigueme.simulador;

public class Puerta {
	private String direccionLarga;
	private String direccionCorta;
	
	public Puerta(String direccionLarga) {
		this.direccionLarga = direccionLarga;
		this.direccionCorta = direccionLarga.substring(direccionLarga.length() - 4);
	}

	public String getDireccionCorta() {
		return direccionCorta;
	}

	public String getDireccionLarga() {
		return direccionLarga;
	}
}
