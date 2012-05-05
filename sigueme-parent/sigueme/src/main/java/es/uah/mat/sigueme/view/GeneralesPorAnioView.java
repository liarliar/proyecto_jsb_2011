package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.faces.bean.*;

import org.apache.commons.lang.math.*;
import org.primefaces.model.chart.*;

@ManagedBean
@ViewScoped
public class GeneralesPorAnioView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	private Date fecha;
	private TipoGrafico tipoGrafico;
	private List<TipoGrafico> tiposGrafico;	
	private ChartModel chartModel;
	
	public GeneralesPorAnioView () {
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
				
				for (Mes mes : Mes.values()) {
					//TODO Internacionalizar el mes
					serie.set(mes.name(), RandomUtils.nextInt(1000));					
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
