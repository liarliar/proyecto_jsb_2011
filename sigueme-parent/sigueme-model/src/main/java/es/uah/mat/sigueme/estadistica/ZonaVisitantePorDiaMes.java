package es.uah.mat.sigueme.estadistica;

import java.util.*;

public class ZonaVisitantePorDiaMes {

	private final List<MesVisitante> visitantesMes;
	private final String zona;

	public ZonaVisitantePorDiaMes(String zona, List<MesVisitante> visitantesMes) {
		this.zona = zona;
		this.visitantesMes = visitantesMes;
	}

	public List<MesVisitante> getVisitantesMes() {
		return visitantesMes;
	}

	public String getZona() {
		return zona;
	}

}
