package es.uah.mat.sigueme.simulador;

public class Persona {

	private TarjetaRFID tarjeta;

	public Persona(TarjetaRFID tarjetaRFID) {
		tarjeta = tarjetaRFID;
	}
	
	public TarjetaRFID getTarjeta() {
		return tarjeta;
	}
	
}
