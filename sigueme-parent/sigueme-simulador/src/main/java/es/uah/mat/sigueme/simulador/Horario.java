package es.uah.mat.sigueme.simulador;

public class Horario {
	
	private int horaApertura;
	private int horaCierre;

	public Horario (final int horaApertura, final int horaCierre) {
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}

	public int getHoraApertura() {
		return horaApertura;
	}

	public int getHoraCierre() {
		return horaCierre;
	}
}
