package es.uah.mat.sigueme.estadistica;

public class HoraVisitante {

	private final long visitantes;
	private final long hora;

	public HoraVisitante(long hora, long visitantes) {
		this.hora = hora;
		this.visitantes = visitantes;
	}

	public long getVisitantes() {
		return visitantes;
	}

	public long getHora() {
		return hora;
	}

}
