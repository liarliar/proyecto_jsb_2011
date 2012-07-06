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
	private List<Integer> dias;
	private Integer diaInicio;
	private Integer diaFin;
	private boolean renderChart;
	
	public GeneralesPorMesView () {
		Calendar hoy = Calendar.getInstance();
		mes = Mes.values()[hoy.get(Calendar.MONTH)];
		anio = hoy.get(Calendar.YEAR);
		tipoGrafico = TipoGrafico.PORCIONES;
		tiposGrafico = Arrays.asList(TipoGrafico.values());
		
		dias = new ArrayList<Integer>();
		
		for (int i = 1; i < 31; i++) {
			dias.add(i);
		}
	}

	@PostConstruct
	public void init() {
		generarChartModel();
	}

	public void cambioTipoGrafico () {						
		generarChartModel();		
	}
	
	public void cambioDias () {						
		generarChartModel();		
	}

	private void generarChartModel() {
		switch (tipoGrafico) {
		case PORCIONES:
			PieChartModel pieModel = new PieChartModel();
			List<ZonaVisitante> zonas = estadisticaPorMesRepository.getVisitantesPorZona(mes, anio, new Rango(diaInicio, diaFin));
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
			List<ZonaVisitantePorDiaMes> visitantes = estadisticaPorMesRepository.getVisitantesPorDiaMesEnCadaSala(mes, anio, new Rango(diaInicio, diaFin));
			
			if (visitantes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				ChartSeries serie = new ChartSeries("");
				serie.set("", 0);
				cartesianModel.addSeries(serie);
				renderChart = false;
			} else {
				renderChart = true;
				for (ZonaVisitantePorDiaMes zonaVisitantePorDiaMes : visitantes) {
					ChartSeries serie = new ChartSeries(zonaVisitantePorDiaMes.getZona());
					
					for (MesVisitante mesVisitante : zonaVisitantePorDiaMes.getVisitantesMes()) {
						serie.set((tipoGrafico == TipoGrafico.LINEAS) ? mesVisitante.getDiaMes() : String.valueOf(mesVisitante.getDiaMes()), mesVisitante.getVisitantes());
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
	
	public Integer getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(Integer diaInicio) {
		this.diaInicio = diaInicio;
	}

	public Integer getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(Integer diaFin) {
		this.diaFin = diaFin;
	}

	public List<Integer> getDias() {
		return dias;
	}

	public boolean isRenderChart() {
		return renderChart;
	}
	
}
