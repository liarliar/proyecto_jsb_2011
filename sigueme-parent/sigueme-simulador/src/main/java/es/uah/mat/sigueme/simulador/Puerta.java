package es.uah.mat.sigueme.simulador;

import javax.swing.text.StyledEditorKit.*;

public class Puerta {
	private String direccionLarga;
	private String direccionCorta;
	private boolean inicializada;
	
	public Puerta(String direccionLarga) {
		this.direccionLarga = direccionLarga;
		this.direccionCorta = direccionLarga.substring(direccionLarga.length() - 4);
		inicializada = false;
	}

	public String getDireccionCorta() {
		return direccionCorta;
	}

	public String getDireccionLarga() {
		return direccionLarga;
	}
	
	public boolean isInicializada () {
		return inicializada;
	}
	
	public void inicializar() {
		inicializada = true;
	}
}
