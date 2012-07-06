package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.annotation.*;
import javax.faces.application.*;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.*;
import javax.faces.context.*;

import org.apache.commons.lang.math.*;
import org.jfree.util.*;
import org.primefaces.event.*;
import org.primefaces.model.chart.*;
import org.slf4j.*;

import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.persistence.*;

@ManagedBean
@ViewScoped
public class GeneralesPorDiaView implements Serializable {
	
	@ManagedProperty("#{estadisticaPorDiaRepository}")
	private transient EstadisticaPorDiaRepository estadisticaPorDiaRepository;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	private Date fecha;
	private TipoGrafico tipoGrafico;
	private List<TipoGrafico> tiposGrafico;	
	private ChartModel chartModel;
	private Logger log = LoggerFactory.getLogger(GeneralesPorDiaView.class);
	private List<Integer> horas;
	private Integer horaInicio;
	private Integer horaFin;
	private boolean renderChart;
	
	public GeneralesPorDiaView () {
		fecha = new Date();
		tipoGrafico = TipoGrafico.PORCIONES;
		tiposGrafico = Arrays.asList(TipoGrafico.values());
		horas = new ArrayList<Integer>();
		for (int i = 9; i < 24; i++) {
			horas.add(i);
		}
	}

	@PostConstruct
	public void init() {
		generarChartModel();
	}

	public void cambioTipoGrafico () {						
		generarChartModel();		
	}
	
	public void cambioHoras () {						
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
			List<ZonaVisitante> zonas = estadisticaPorDiaRepository.getVisitantesPorSala(fecha, new Rango(horaInicio, horaFin));
			if (zonas.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				renderChart = false;
				pieModel.set("", 0);
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
			List<ZonaVisitantePorHora> visitantes = estadisticaPorDiaRepository.getVisitantesPorHoraEnCadaSala(fecha, new Rango(horaInicio, horaFin));
			
			if (visitantes.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para mostrar.", ""));
				ChartSeries serie = new ChartSeries("");
				serie.set("", 0);
				cartesianModel.addSeries(serie);
				renderChart = false;
			} else {
				renderChart = true;
				for (ZonaVisitantePorHora zonaVisitantePorHora : visitantes) {
					ChartSeries serie = new ChartSeries(zonaVisitantePorHora.getZona());
					
					for (HoraVisitante horaVisitante : zonaVisitantePorHora.getHoraVisitante()) {
						serie.set((tipoGrafico == TipoGrafico.LINEAS) ? horaVisitante.getHora() : String.valueOf(horaVisitante.getHora()), horaVisitante.getVisitantes());
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


	public void setEstadisticaPorDiaRepository(
			EstadisticaPorDiaRepository estadisticaPorDiaRepository) {
		this.estadisticaPorDiaRepository = estadisticaPorDiaRepository;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}

	public List<Integer> getHoras() {
		return horas;
	}

	public boolean isRenderChart() {
		return renderChart;
	}
}
