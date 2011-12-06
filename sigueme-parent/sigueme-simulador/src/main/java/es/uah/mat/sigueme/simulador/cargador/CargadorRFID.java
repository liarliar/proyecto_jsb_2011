package es.uah.mat.sigueme.simulador.cargador;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.*;

import es.uah.mat.sigueme.simulador.*;

public class CargadorRFID {

	protected InputStream getFicheroDireccionesTarjetasRFID() throws URISyntaxException {
		return CargadorRFID.class.getResourceAsStream("/TarjetasSistema.txt");
	}

	public List<TarjetaRFID> getTarjetasRFID() {
		List<TarjetaRFID> tarjetas = new ArrayList<TarjetaRFID>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(getFicheroDireccionesTarjetasRFID()));
			
			String direccionLarga = null;
			while ( (direccionLarga = br.readLine()) != null) {
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
