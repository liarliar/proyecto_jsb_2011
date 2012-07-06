package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

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
	private boolean renderChart;
	
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
			if (zonas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				pieModel.set("", 0);
				renderChart = false;
			} else {
				renderChart = true;

				for (ZonaVisitante zonaVisitante : zonas) {
					pieModel.set(zonaVisitante.getZona(), zonaVisitante.getVisitantes());
					
				}
			}
			chartModel = pieModel;
			break;
		default:		
			CartesianChartModel cartesianModel = new CartesianChartModel();
			List<ZonaVisitantePorAnio> visitantes = estadisticaPorAnioRepository.getVisitantesPorAnioEnCadaSala(anio);
			
			if (visitantes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				ChartSeries serie = new ChartSeries("");
				serie.set("", 0);
				cartesianModel.addSeries(serie);
				renderChart = false;
			} else {
				renderChart = true;
				for (ZonaVisitantePorAnio zonaVisitantePorAnio : visitantes) {
					ChartSeries serie = new ChartSeries(zonaVisitantePorAnio.getZona());
					
					for (AnioVisitante anioVisitante : zonaVisitantePorAnio.getVisitantesAnio()) {
						serie.set( anioVisitante.getMes().name(), anioVisitante.getVisitantes());
					}
					cartesianModel.addSeries(serie);				
				}
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

	public boolean isRenderChart() {
		return renderChart;
	}
	
	
}
