package es.uah.mat.sigueme.simulador;

import java.util.*;

import org.apache.commons.lang.math.*;

public class MapaBibliotecaMeco implements MapaBiblioteca {

	private EstanciaEnZona [][] estanciasEnZona = new EstanciaEnZona [5][5];
	
	public MapaBibliotecaMeco (final List<Puerta> puertas) {
		estanciasEnZona[Zona.UNA.ordinal()][Zona.UNA.ordinal()] = new EstanciaEnZona(Zona.UNA, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.UNA.ordinal()][Zona.DOS.ordinal()] = new EstanciaEnZona(Zona.DOS, puertas.get(0), new IntervaloEstancia(5, 15), new Probabilidad(0, 100));
		estanciasEnZona[Zona.UNA.ordinal()][Zona.TRES.ordinal()] = new EstanciaEnZona(Zona.TRES, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.UNA.ordinal()][Zona.CUATRO.ordinal()] = new EstanciaEnZona(Zona.CUATRO, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.UNA.ordinal()][Zona.CINCO.ordinal()] = new EstanciaEnZona(Zona.CINCO, puertas.get(0),  new Probabilidad(101, 102));

		estanciasEnZona[Zona.DOS.ordinal()][Zona.UNA.ordinal()] = new EstanciaEnZona(Zona.UNA, puertas.get(0), new Probabilidad(0, 25));
		estanciasEnZona[Zona.DOS.ordinal()][Zona.DOS.ordinal()] = new EstanciaEnZona(Zona.DOS, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.DOS.ordinal()][Zona.TRES.ordinal()] = new EstanciaEnZona(Zona.TRES, puertas.get(1), new IntervaloEstancia(5, 15), new Probabilidad(25, 50));
		estanciasEnZona[Zona.DOS.ordinal()][Zona.CUATRO.ordinal()] = new EstanciaEnZona(Zona.CUATRO, puertas.get(2), new IntervaloEstancia(5, 15), new Probabilidad(50, 75));
		estanciasEnZona[Zona.DOS.ordinal()][Zona.CINCO.ordinal()] = new EstanciaEnZona(Zona.CINCO, puertas.get(3), new IntervaloEstancia(5, 15), new Probabilidad(75, 100));

		estanciasEnZona[Zona.TRES.ordinal()][Zona.UNA.ordinal()] = new EstanciaEnZona(Zona.UNA, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.TRES.ordinal()][Zona.DOS.ordinal()] = new EstanciaEnZona(Zona.DOS, puertas.get(1), new IntervaloEstancia(5, 15), new Probabilidad(0, 100));
		estanciasEnZona[Zona.TRES.ordinal()][Zona.TRES.ordinal()] = new EstanciaEnZona(Zona.TRES, puertas.get(1), new Probabilidad(101, 102));
		estanciasEnZona[Zona.TRES.ordinal()][Zona.CUATRO.ordinal()] = new EstanciaEnZona(Zona.CUATRO, puertas.get(2), new Probabilidad(101, 102));
		estanciasEnZona[Zona.TRES.ordinal()][Zona.CINCO.ordinal()] = new EstanciaEnZona(Zona.CINCO, puertas.get(3),  new Probabilidad(101, 102));
		
		estanciasEnZona[Zona.CUATRO.ordinal()][Zona.UNA.ordinal()] = new EstanciaEnZona(Zona.UNA, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CUATRO.ordinal()][Zona.DOS.ordinal()] = new EstanciaEnZona(Zona.DOS, puertas.get(2), new IntervaloEstancia(5, 15), new Probabilidad(0, 100));
		estanciasEnZona[Zona.CUATRO.ordinal()][Zona.TRES.ordinal()] = new EstanciaEnZona(Zona.TRES, puertas.get(1), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CUATRO.ordinal()][Zona.CUATRO.ordinal()] = new EstanciaEnZona(Zona.CUATRO, puertas.get(2), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CUATRO.ordinal()][Zona.CINCO.ordinal()] = new EstanciaEnZona(Zona.CINCO, puertas.get(3), new Probabilidad(101, 102));

		estanciasEnZona[Zona.CINCO.ordinal()][Zona.UNA.ordinal()] = new EstanciaEnZona(Zona.UNA, puertas.get(0), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CINCO.ordinal()][Zona.DOS.ordinal()] = new EstanciaEnZona(Zona.DOS, puertas.get(3), new IntervaloEstancia(5, 15), new Probabilidad(0, 100));
		estanciasEnZona[Zona.CINCO.ordinal()][Zona.TRES.ordinal()] = new EstanciaEnZona(Zona.TRES, puertas.get(1), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CINCO.ordinal()][Zona.CUATRO.ordinal()] = new EstanciaEnZona(Zona.CUATRO, puertas.get(2), new Probabilidad(101, 102));
		estanciasEnZona[Zona.CINCO.ordinal()][Zona.CINCO.ordinal()] = new EstanciaEnZona(Zona.CINCO, puertas.get(3), new Probabilidad(101, 102));
	}

	@Override
	public EstanciaEnZona getProximaEstancia(Zona zona) {
		int random = RandomUtils.nextInt(100);
		EstanciaEnZona [] estancias = estanciasEnZona[zona.ordinal()];
		
		for (EstanciaEnZona estanciaEnZona : estancias) {
			if (estanciaEnZona.getProbabilidad().isRango(random)) {
				return estanciaEnZona;
			}
		}
		
		return null;
	}
}
