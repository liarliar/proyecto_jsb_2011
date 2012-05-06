package es.uah.mat.sigueme.estadistica;

import java.util.*;

public class ZonaVisitantePorDiaSemana {

	private final String zona;
	private final List<SemanaVisitante> semanaVisitantes;

	public ZonaVisitantePorDiaSemana(String zona, List<SemanaVisitante> semanaVisitantes) {
		this.zona = zona;
		this.semanaVisitantes = semanaVisitantes;
	}

	public String getZona() {
		return zona;
	}

	public List<SemanaVisitante> getSemanaVisitantes() {
		return semanaVisitantes;
	}

}
