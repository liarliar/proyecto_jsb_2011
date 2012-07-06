package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

import org.apache.commons.lang.*;
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
	private boolean renderChart;
	
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
			List<ZonaVisitantePorDiaSemana> visitantes = estadisticaPorSemanaRepository.getVisitantesPorDiaSemanaEnCadaSala(fecha);
			
			if (visitantes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				final ChartSeries serie = new ChartSeries("-");
				serie.set("-", 0);
				cartesianModel.addSeries(serie);
				renderChart = false;
			} else {
				renderChart = true;
				for (ZonaVisitantePorDiaSemana zonaVisitantePorDiaSemaan : visitantes) {
					ChartSeries serie = new ChartSeries(zonaVisitantePorDiaSemaan.getZona());
					Collections.sort(zonaVisitantePorDiaSemaan.getSemanaVisitantes(), new Comparator<SemanaVisitante>() {

						@Override
						public int compare(SemanaVisitante o1,
								SemanaVisitante o2) {
							return o1.getDiaSemana().compareTo(o2.getDiaSemana());
						}
					});
					for (SemanaVisitante diaSemanaVisitante : zonaVisitantePorDiaSemaan.getSemanaVisitantes()) {
						serie.set(diaSemanaVisitante.getDiaSemana().name(), diaSemanaVisitante.getVisitantes());
					}
					cartesianModel.addSeries(serie);				
				}
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

	public boolean isRenderChart() {
		return renderChart;
	}
	
}
