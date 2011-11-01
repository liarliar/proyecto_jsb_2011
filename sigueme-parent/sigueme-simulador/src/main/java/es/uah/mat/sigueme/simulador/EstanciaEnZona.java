package es.uah.mat.sigueme.simulador;

public class EstanciaEnZona {

	private Puerta puerta;
	private IntervaloEstancia intervalo;
	private Probabilidad probabilidad;
	private Zona zona;

	public EstanciaEnZona (final Zona zona, final Puerta puerta, final IntervaloEstancia intervalo, final Probabilidad probabilidad) {
		this.puerta = puerta;
		this.intervalo = intervalo;
		this.probabilidad = probabilidad;
		this.zona = zona;
	}
	
	public EstanciaEnZona (final Zona zona, final Puerta puerta, final Probabilidad probabilidad) {
		this(zona, puerta, new IntervaloEstancia(0, 0), probabilidad);
	}

	public Puerta getPuerta() {
		return puerta;
	}

	public IntervaloEstancia getIntervalo() {
		return intervalo;
	}

	public Probabilidad getProbabilidad() {
		return probabilidad;
	}

	public Zona getZona() {
		return zona;
	}
}
