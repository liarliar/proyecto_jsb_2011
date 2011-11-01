package es.uah.mat.sigueme.simulador;

import java.io.*;
import java.util.*;

import es.uah.mat.sigueme.simulador.cargador.*;

public class LanzadorSimulador {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		final CargadorRFID cargador = new CargadorRFID();
		final RecepcionTarjetas recepcion = new RecepcionTarjetas(cargador.getTarjetasRFID());
		final MapaBiblioteca mapa = new MapaBibliotecaMeco(cargador.getPuertasRFID());
		
		final Biblioteca biblioteca = new Biblioteca(new Horario(10, 20), mapa, recepcion);
		
		
		final Simulador simulador = new SimuladorDia(biblioteca, new Date());
		final List<Recorrido> recorridos = simulador.simular(10, 25);
		
		Grabador.grabar("c:/temp/fichero.txt", recorridos);
	}

}
