package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.faces.bean.*;

import org.apache.commons.lang.math.*;
import org.primefaces.event.*;
import org.primefaces.model.chart.*;

import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.persistence.*;

@ManagedBean
@ViewScoped
public class GeneralesPorSemanaView implements Serializable {
	
	@ManagedProperty("#{estadisticaPorSemanaRepository}")
	private EstadisticaPorSemanaRepository estadisticaPorSemanaRepository;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	private Date fecha;
	private TipoGrafico tipoGrafico;
	private List<TipoGrafico> tiposGrafico;	
	private ChartModel chartModel;
	
	public GeneralesPorSemanaView () {
		fecha = new Date();
		tipoGrafico = TipoGrafico.PORCIONES;
		tiposGrafico = Arrays.asList(TipoGrafico.values());
	}

	@PostConstruct
	public void init () {
		generarChartModel();
	}

	public void cambioTipoGrafico () {						
		generarChartModel();		
	}
	
	public void cambioFecha(DateSelectEvent event) {
		this.fecha = event.getDate();
		generarChartModel();
	}


	private void generarChartModel() {
		switch (tipoGrafico) {
		case PORCIONES:
			PieChartModel pieModel = new PieChartModel();
			List<ZonaVisitante> zonas = estadisticaPorSemanaRepository.getVisitantesPorZona(fecha);
			for (ZonaVisitante zonaVisitante : zonas) {
				pieModel.set(zonaVisitante.getZona(), zonaVisitante.getVisitantes());
				
			}
			chartModel = pieModel;
			break;
		default:		
			CartesianChartModel cartesianModel = new CartesianChartModel();
			List<ZonaVisitantePorDiaSemana> visitantes = estadisticaPorSemanaRepository.getVisitantesPorDiaSemanaEnCadaSala(fecha);
			
			for (ZonaVisitantePorDiaSemana zonaVisitantePorDiaSemaan : visitantes) {
				ChartSeries serie = new ChartSeries(zonaVisitantePorDiaSemaan.getZona());
				
				for (SemanaVisitante diaSemanaVisitante : zonaVisitantePorDiaSemaan.getSemanaVisitantes()) {
					serie.set(diaSemanaVisitante.getDiaSemana().name(), diaSemanaVisitante.getVisitantes());
				}
				cartesianModel.addSeries(serie);				
			}
			 chartModel = cartesianModel;
			break;
		}
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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


	public void setEstadisticaPorSemanaRepository(
			EstadisticaPorSemanaRepository estadisticaPorSemanaRepository) {
		this.estadisticaPorSemanaRepository = estadisticaPorSemanaRepository;
	}
	
}
