package es.uah.mat.sigueme.estadistica;

import java.util.*;

public class ZonaVisitantePorAnio {

	private final List<AnioVisitante> visitantesAnio;
	private final String zona;

	public ZonaVisitantePorAnio(String zona, List<AnioVisitante> visitantesAnio) {
		this.zona = zona;
		this.visitantesAnio = visitantesAnio;
	}

	public List<AnioVisitante> getVisitantesAnio() {
		return visitantesAnio;
	}

	public String getZona() {
		return zona;
	}

}
