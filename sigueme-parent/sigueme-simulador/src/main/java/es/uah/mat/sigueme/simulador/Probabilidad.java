package es.uah.mat.sigueme.simulador;

public class Probabilidad {

	private int valorMinimo;
	private int valorMaximo;

	public Probabilidad (final int valorMinimo,final int valorMaximo) {
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}

	public int getValorMinimo() {
		return valorMinimo;
	}

	public int getValorMaximo() {
		return valorMaximo;
	}

	public boolean isRango(int valor) {
		return valorMinimo <= valor && valorMaximo >= valor;
	}
}
