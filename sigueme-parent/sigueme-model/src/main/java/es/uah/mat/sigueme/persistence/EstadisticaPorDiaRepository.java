package es.uah.mat.sigueme.persistence;

import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorDiaRepository {

	List<ZonaVisitante> getVisitantesPorSala(Date fecha, Rango rangoHoras);
	List<ZonaVisitantePorHora> getVisitantesPorHoraEnCadaSala(Date fecha, Rango rangoHoras);
}
