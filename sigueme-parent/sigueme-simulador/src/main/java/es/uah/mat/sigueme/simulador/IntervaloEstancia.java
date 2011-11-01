package es.uah.mat.sigueme.simulador;

public class IntervaloEstancia {

	private int tiempoMinimo;

	private int tiempoMaximo;

	public IntervaloEstancia (final int tiempoMinimo, final int tiempoMaximo) {
		this.tiempoMinimo = tiempoMinimo;
		this.tiempoMaximo = tiempoMaximo;
	}
	
	public int getTiempoMinimo() {
		return tiempoMinimo;
	}
	
	public int getTiempoMaximo() {
		return tiempoMaximo;
	}
}
