package es.uah.mat.sigueme.simulador;

import java.util.*;

public class RecepcionTarjetas {

	private List<TarjetaRFID> tarjetasLibres;
	private List<TarjetaRFID> tarjetasUsandose;

	public RecepcionTarjetas(List<TarjetaRFID> tarjetas) {
		this.tarjetasLibres = tarjetas;
		this.tarjetasUsandose = new ArrayList<TarjetaRFID>();
	}

	public TarjetaRFID recogerTarjeta() {
		
		if (tarjetasLibres.isEmpty()) {
			return null;
		}
		
		final TarjetaRFID tarjeta = tarjetasLibres.remove(0);
		
		tarjetasUsandose.add(tarjeta);
		
		return tarjeta;
	}

	public int getNumeroTarjetasLibres() {
		return tarjetasLibres.size();
	}

	public void devolverTarjeta(TarjetaRFID tarjeta) {
		if (tarjetasUsandose.remove(tarjeta)) {
			tarjetasLibres.add(tarjeta);
		}
	}

	public int getNumeroTarjetasUsandose() {
		return tarjetasUsandose.size();
	}

}
