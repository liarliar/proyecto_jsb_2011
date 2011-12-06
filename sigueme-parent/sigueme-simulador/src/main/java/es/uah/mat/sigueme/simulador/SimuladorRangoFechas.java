package es.uah.mat.sigueme.simulador;

import java.util.*;

public class SimuladorRangoFechas implements Simulador {

	private Biblioteca biblioteca;
	private Date hasta;
	private Date desde;

	public SimuladorRangoFechas(Date desde, Date hasta, Biblioteca biblioteca) {
		this.desde = desde;
		this.hasta = hasta;
		this.biblioteca = biblioteca;
	}

	@Override
	public List<Recorrido> simular(int personaMinimo, int personaMaximo) {
		final List<Recorrido> recorrido = new ArrayList<Recorrido>();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(desde);
		
		while (calendar.getTime().before(hasta)) {
			Simulador simuladorDia = new SimuladorDia(biblioteca, calendar.getTime());
			recorrido.addAll(simuladorDia.simular(personaMinimo, personaMaximo));
			
			calendar.add(Calendar.DAY_OF_MONTH, +1);
		}
		
		return recorrido;
	}

}
