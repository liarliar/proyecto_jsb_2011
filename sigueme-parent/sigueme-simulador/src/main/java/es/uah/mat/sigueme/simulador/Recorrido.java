package es.uah.mat.sigueme.simulador;

import java.util.*;

public class Recorrido {
	private List<MensajeRFID> recorrido;
	
	
	public Recorrido() {
		recorrido = new ArrayList<MensajeRFID>();
	}
	
	public List<MensajeRFID> getRecorrido() {
		return recorrido;
	}

	public void addMensaje(TarjetaRFID tarjeta, Puerta puerta, Date fecha) {
		final MensajeRFID mensaje = new MensajeRFID();
		mensaje.setDireccionCorta(puerta.getDireccionCorta());
		mensaje.setDireccionLarga(tarjeta.getDireccionLarga());
		mensaje.setFecha(fecha);
		mensaje.setTipo(TipoMensaje.RM);
		
		if (!puerta.isInicializada()) {
			final MensajeRFID mensajeResetPuerta = new MensajeRFID();
			mensajeResetPuerta.setDireccionCorta(puerta.getDireccionCorta());
			mensajeResetPuerta.setDireccionLarga(puerta.getDireccionLarga());
			mensajeResetPuerta.setFecha(fecha);
			mensajeResetPuerta.setTipo(TipoMensaje.NW);
			
			recorrido.add(mensajeResetPuerta);
			puerta.inicializar();
			
		}
		
		recorrido.add(mensaje);
	}

	public List<MensajeRFID> getMensajes() {
		return recorrido;
	}
}
