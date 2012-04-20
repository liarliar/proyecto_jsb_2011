package es.uah.mat.sigueme.simulador;


public class Biblioteca {

	private RecepcionTarjetas recepcion;
	private MapaBiblioteca mapa;
	private Horario horario;

	public Biblioteca (Horario horario, MapaBiblioteca mapa, RecepcionTarjetas recepcion) {
		this.mapa = mapa;
		this.recepcion = recepcion;
		this.horario = horario;
	}

	public Horario getHorario() {
		return horario;
	}

	public RecepcionTarjetas getRecepcion() {
		return recepcion;
	}

	public MapaBiblioteca getMapa() {
		return mapa;
	}
}
