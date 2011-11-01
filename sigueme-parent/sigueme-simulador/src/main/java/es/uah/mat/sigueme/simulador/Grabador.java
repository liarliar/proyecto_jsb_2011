package es.uah.mat.sigueme.simulador;

import java.io.*;
import java.util.*;

import org.apache.commons.io.*;

public class Grabador {

	public static void grabar(String rutaToFile, List<Recorrido> recorridos) throws IOException {
		List<MensajeRFID> mensajes = new ArrayList<MensajeRFID>();
		
		for (Recorrido recorrido : recorridos) {
			mensajes.addAll(recorrido.getMensajes());
		}
		FileUtils.writeLines(new File(rutaToFile), mensajes);
		
	}

}
