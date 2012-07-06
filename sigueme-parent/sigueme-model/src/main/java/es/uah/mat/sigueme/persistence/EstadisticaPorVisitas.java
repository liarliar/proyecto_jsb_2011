package es.uah.mat.sigueme.persistence;

import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorVisitas {

	public List<Ruta> getRutas(Date fechaInicio, Date fechaFin);
	public List<RecorridoSala> getRecorrido(Integer id);
}

