package es.uah.mat.sigueme.estadistica;

import java.util.*;

public class ZonaVisitantePorHora {

	private final String zona;
	private final List<HoraVisitante> horaVisitante;

	public ZonaVisitantePorHora(String zona, List<HoraVisitante> horaVisitante) {
		this.zona = zona;
		this.horaVisitante = horaVisitante;
	}

	public String getZona() {
		return zona;
	}

	public List<HoraVisitante> getHoraVisitante() {
		return horaVisitante;
	}

}
