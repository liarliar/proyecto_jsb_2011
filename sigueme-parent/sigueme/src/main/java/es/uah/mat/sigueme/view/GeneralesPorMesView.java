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
public class GeneralesPorMesView implements Serializable {
	
	@ManagedProperty("#{estadisticaPorMesRepository}")
	EstadisticaPorMesRepository estadisticaPorMesRepository;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	private TipoGrafico tipoGrafico;
	private List<TipoGrafico> tiposGrafico;	
	private ChartModel chartModel;
	private Mes mes;
	private Integer anio;
	
	public GeneralesPorMesView () {
		Calendar hoy = Calendar.getInstance();
		mes = Mes.values()[hoy.get(Calendar.MONTH)];
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
			List<ZonaVisitante> zonas = estadisticaPorMesRepository.getVisitantesPorZona(mes, anio);
			for (ZonaVisitante zonaVisitante : zonas) {
				pieModel.set(zonaVisitante.getZona(), zonaVisitante.getVisitantes());
				
			}
			chartModel = pieModel;
			break;
		default:		
			CartesianChartModel cartesianModel = new CartesianChartModel();
			List<ZonaVisitantePorDiaMes> visitantes = estadisticaPorMesRepository.getVisitantesPorDiaMesEnCadaSala(mes, anio);
			
			for (ZonaVisitantePorDiaMes zonaVisitantePorDiaMes : visitantes) {
				ChartSeries serie = new ChartSeries(zonaVisitantePorDiaMes.getZona());
				
				for (MesVisitante mesVisitante : zonaVisitantePorDiaMes.getVisitantesMes()) {
					serie.set((tipoGrafico == TipoGrafico.LINEAS) ? mesVisitante.getDiaMes() : String.valueOf(mesVisitante.getDiaMes()), mesVisitante.getVisitantes());
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


	public void setEstadisticaPorMesRepository(
			EstadisticaPorMesRepository estadisticaPorMesRepository) {
		this.estadisticaPorMesRepository = estadisticaPorMesRepository;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	
	public List<Mes> getMeses() {
		return Arrays.asList(Mes.values());
	}
	
}
