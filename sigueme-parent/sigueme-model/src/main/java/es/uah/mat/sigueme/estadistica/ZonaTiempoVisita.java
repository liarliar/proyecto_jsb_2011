package es.uah.mat.sigueme.estadistica;

public class ZonaTiempoVisita {
	private final Long tiempo;
	private final String zona;

	public ZonaTiempoVisita (String zona, Long tiempo) {
		this.zona = zona;
		this.tiempo = tiempo;
	}

	public Long getTiempo() {
		return tiempo;
	}

	public String getZona() {
		return zona;
	}
}
