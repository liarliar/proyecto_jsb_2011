package es.uah.mat.sigueme.simulador;

import java.util.*;

import org.apache.commons.lang.math.*;

public class SimuladorDia implements Simulador {

	private Biblioteca biblioteca;
	private Date fecha;
	private List<Recorrido> recorridos;

	public SimuladorDia(Biblioteca biblioteca, Date fecha) {
		this.biblioteca = biblioteca;
		this.fecha = fecha;
		this.recorridos = new ArrayList<Recorrido>();
	}

	@Override
	public List<Recorrido> simular(int personaMinimo, int personaMaximo) {
		final Horario horario = biblioteca.getHorario();
		
		for (int i = horario.getHoraApertura(); i < horario.getHoraCierre(); i++) {
			
			final int personasEnHora = personaMinimo + RandomUtils.nextInt(personaMaximo - personaMinimo);
			
			for (int j = 0; j < personasEnHora; j++) {
				final GeneradorRuta ruta = new GeneradorRuta(fecha, i, biblioteca.getRecepcion(), biblioteca.getMapa());
				
				recorridos.add(ruta.simular());
			}
		}
		
		return recorridos;
	}
}
