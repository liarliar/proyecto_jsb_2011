package es.uah.mat.sigueme.simulador;

public class Persona {

	private TarjetaRFID tarjeta;

	public Persona(RecepcionTarjetas recepcion) {
		tarjeta = recepcion.recogerTarjeta();
	}
	
	public TarjetaRFID getTarjeta() {
		return tarjeta;
	}
	
}
