package es.uah.mat.sigueme.persistence;

import java.io.*;
import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorSemanaRepository extends Serializable {

	List<ZonaVisitante> getVisitantesPorZona(Date fecha);
	List<ZonaVisitantePorDiaSemana> getVisitantesPorDiaSemanaEnCadaSala(Date fecha);
}
