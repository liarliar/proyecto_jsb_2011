package es.uah.mat.sigueme.persistence;

import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorAnioRepository  {

	
	public List<ZonaVisitante> getVisitantesPorZona(int anio);

	public List<ZonaVisitantePorAnio> getVisitantesPorAnioEnCadaSala(int anio);
}
