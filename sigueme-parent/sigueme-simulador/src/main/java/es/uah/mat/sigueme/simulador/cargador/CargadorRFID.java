package es.uah.mat.sigueme.simulador.cargador;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

import es.uah.mat.sigueme.simulador.*;

public class CargadorRFID {

	protected File getFicheroDireccionesTarjetasRFID() throws URISyntaxException {
		return new File(CargadorRFID.class.getResource("/TarjetasSistema.txt").toURI());
	}

	public List<TarjetaRFID> getTarjetasRFID() {
		List<TarjetaRFID> tarjetas = new ArrayList<TarjetaRFID>();
		try {
			File file = getFicheroDireccionesTarjetasRFID();
			List<String> lines = FileUtils.readLines(file);
			for (String direccionLarga : lines) {
				tarjetas.add(new TarjetaRFID(direccionLarga));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tarjetas;
	}

	public List<Puerta> getPuertasRFID() {
		List<Puerta> puertas = new ArrayList<Puerta>();
		
		puertas.add(new Puerta("00124B00011F6E26"));
		puertas.add(new Puerta("00124B00011F6E27"));
		puertas.add(new Puerta("00124B00011F6E28"));
		puertas.add(new Puerta("00124B00011F6E29"));
		
		return puertas;
	}
}
