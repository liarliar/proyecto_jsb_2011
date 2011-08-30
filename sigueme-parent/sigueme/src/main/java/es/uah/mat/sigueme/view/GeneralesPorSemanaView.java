package es.uah.mat.sigueme.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.math.RandomUtils;
import org.primefaces.component.chart.series.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class GeneralesPorSemanaView implements Serializable {
	
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
		generarChartModel();
	}


	public void cambioTipoGrafico () {						
		generarChartModel();		
	}

	private void generarChartModel() {
		switch (tipoGrafico) {
		case PORCIONES:
			PieChartModel pieModel = new PieChartModel();
			for (int j = 1; j < 6; j++) {
				pieModel.set("Sala " + j, RandomUtils.nextInt(1000));
			}
			
			chartModel = pieModel;
			break;
		default:		
			CartesianChartModel cartesinaModel = new CartesianChartModel();
			
			for (int j = 1; j < 6; j++) {
				ChartSeries serie = new ChartSeries("Sala " + j);
				
				for (DiaSemana diaSemana : DiaSemana.values()) {
					//TODO Internacionalizar el dia de la semana
					serie.set(diaSemana.name(), RandomUtils.nextInt(1000));					
				}
				cartesinaModel.addSeries(serie);				
			}
			 chartModel = cartesinaModel;
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
	
	
}
