package es.uah.mat.sigueme.estadistica;

public class MesVisitante {

	private final long visitantes;
	private final int diaMes;

	public MesVisitante(int diaMes, long visitantes) {
		this.diaMes = diaMes;
		this.visitantes = visitantes;
	}

	public long getVisitantes() {
		return visitantes;
	}

	public int getDiaMes() {
		return diaMes;
	}

}
