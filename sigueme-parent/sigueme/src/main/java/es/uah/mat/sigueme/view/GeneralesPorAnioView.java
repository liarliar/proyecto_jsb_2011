package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.faces.bean.*;

import org.apache.commons.lang.math.*;
import org.primefaces.model.chart.*;

import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.persistence.*;

@ManagedBean
@ViewScoped
public class GeneralesPorAnioView implements Serializable {
	
	@ManagedProperty("#{estadisticaPorAnioRepository}")
	private EstadisticaPorAnioRepository estadisticaPorAnioRepository;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	private Integer anio;
	private TipoGrafico tipoGrafico;
	private List<TipoGrafico> tiposGrafico;	
	private ChartModel chartModel;
	
	public GeneralesPorAnioView () {
		Calendar hoy  = Calendar.getInstance();
		anio = hoy.get(Calendar.YEAR);
		tipoGrafico = TipoGrafico.PORCIONES;
		tiposGrafico = Arrays.asList(TipoGrafico.values());
	}

	@PostConstruct
	public void init() {
		generarChartModel();
	}

	public void cambioTipoGrafico () {						
		generarChartModel();		
	}

	private void generarChartModel() {
		switch (tipoGrafico) {
		case PORCIONES:
			PieChartModel pieModel = new PieChartModel();
			List<ZonaVisitante> zonas = estadisticaPorAnioRepository.getVisitantesPorZona(anio);
			for (ZonaVisitante zonaVisitante : zonas) {
				pieModel.set(zonaVisitante.getZona(), zonaVisitante.getVisitantes());
				
			}
			chartModel = pieModel;
			break;
		default:		
			CartesianChartModel cartesianModel = new CartesianChartModel();
			List<ZonaVisitantePorAnio> visitantes = estadisticaPorAnioRepository.getVisitantesPorAnioEnCadaSala(anio);
			
			for (ZonaVisitantePorAnio zonaVisitantePorAnio : visitantes) {
				ChartSeries serie = new ChartSeries(zonaVisitantePorAnio.getZona());
				
				for (AnioVisitante anioVisitante : zonaVisitantePorAnio.getVisitantesAnio()) {
					serie.set( anioVisitante.getMes().name(), anioVisitante.getVisitantes());
				}
				cartesianModel.addSeries(serie);				
			}
			 chartModel = cartesianModel;
			break;
		}
	}
	
	public TipoGrafico getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(TipoGrafico tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}

	public List<TipoGrafico> getTiposGrafico() {
		return tiposGrafico;
	}

	public ChartModel getChartModel() {
		return chartModel;
	}


	public void setEstadisticaPorAnioRepository(
			EstadisticaPorAnioRepository estadisticaPorAnioRepository) {
		this.estadisticaPorAnioRepository = estadisticaPorAnioRepository;
	}


	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	
}
