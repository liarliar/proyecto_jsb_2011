package es.uah.mat.sigueme.estadistica;

public class AnioVisitante {

	private final Mes mes;
	private final long visitantes;

	public AnioVisitante(Mes mes, long visitantes) {
		this.mes = mes;
		this.visitantes = visitantes;
	}

	public Mes getMes() {
		return mes;
	}

	public long getVisitantes() {
		return visitantes;
	}

}
