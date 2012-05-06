package es.uah.mat.sigueme.persistence;

import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorMesRepository  {

	
	public List<ZonaVisitante> getVisitantesPorZona(Mes mes, int anio);

	public List<ZonaVisitantePorDiaMes> getVisitantesPorDiaMesEnCadaSala(Mes mes, int anio);
}
