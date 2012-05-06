package es.uah.mat.sigueme.estadistica;

public class SemanaVisitante {

	private final DiaSemana diaSemana;
	private final long visitantes;

	public SemanaVisitante(DiaSemana diaSemana, long visitantes) {
		this.diaSemana = diaSemana;
		this.visitantes = visitantes;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public long getVisitantes() {
		return visitantes;
	}

}
