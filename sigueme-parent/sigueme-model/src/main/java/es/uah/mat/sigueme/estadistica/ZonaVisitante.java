package es.uah.mat.sigueme.estadistica;

public class ZonaVisitante {

	private final Long visitantes;
	private final String zona;

	public ZonaVisitante (String zona, Long visitantes) {
		this.zona = zona;
		this.visitantes = visitantes;
	}

	public Long getVisitantes() {
		return visitantes;
	}

	public String getZona() {
		return zona;
	}
}
