package es.uah.mat.sigueme.persistence;

import java.util.*;

import es.uah.mat.sigueme.estadistica.*;

public interface EstadisticaPorEstancia {

	public List<ZonaTiempoVisita> getTiempoPorSala(Date fechaInicio, Date fechaFin, TipoTiempoEstancia tipoTiempoEstancia);
}
