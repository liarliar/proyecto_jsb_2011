package es.uah.mat.sigueme.simulador;

import java.util.*;

import es.uah.mat.sigueme.simulador.cargador.*;

public class Recorrido {
	private List<MensajeRFID> recorrido;
	
	
	public Recorrido(CargadorRecorrido cargaRecorrido,Persona persona) {
		recorrido = cargaRecorrido.generaRecorrido(persona);
	}
	
	public List<MensajeRFID> getRecorrido() {
		return recorrido;
	}
	
}
