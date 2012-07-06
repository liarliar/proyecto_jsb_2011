package es.uah.mat.sigueme.view;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.*;

import org.apache.commons.lang.math.RandomUtils;
import org.primefaces.component.chart.pie.*;
import org.primefaces.model.chart.*;

import es.uah.mat.sigueme.bean.Resultado;
import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.persistence.*;

@ManagedBean
@ViewScoped
public class PorEstanciaView implements Serializable {

	@ManagedProperty("#{estadisticaTiempoPorEstancia}")
	private EstadisticaPorEstancia estadisticaTiempoPorEstancia;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6010282186357781206L;
	private boolean verTabsResultados;
	private TipoTiempoEstancia tiempoEstancia;
	private List<TipoTiempoEstancia> tiposTiempoEstancia;
	private List<Resultado> resultados;
	private Date desde;
	private Date hasta;
	private PieChartModel chartModel;

	public PorEstanciaView () {
		verTabsResultados = false;
		tiposTiempoEstancia = Arrays.asList(TipoTiempoEstancia.values());
	}
	
	public void verResultados() {
		chartModel = new PieChartModel();
		resultados = new ArrayList<Resultado>();
		verTabsResultados = true;
		List<ZonaTiempoVisita> zonas = estadisticaTiempoPorEstancia.getTiempoPorSala(desde, hasta, tiempoEstancia);
		
		for (ZonaTiempoVisita zonaTiempoVisita : zonas) {
			resultados.add(new Resultado(zonaTiempoVisita.getZona(), zonaTiempoVisita.getTiempo()));
			chartModel.set(zonaTiempoVisita.getZona(), zonaTiempoVisita.getTiempo());
		}
		
	}

	public TipoTiempoEstancia getTiempoEstancia() {
		return tiempoEstancia;
	}

	public void setTiempoEstancia(TipoTiempoEstancia tiempoEstancia) {
		this.tiempoEstancia = tiempoEstancia;
	}

	public List<TipoTiempoEstancia> getTiposTiempoEstancia() {
		return tiposTiempoEstancia;
	}


	public List<Resultado> getResultados() {
		return resultados;
	}

	public boolean isVerTabsResultados() {
		return verTabsResultados;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public void setEstadisticaTiempoPorEstancia(
			EstadisticaPorEstancia estadisticaTiempoPorEstancia) {
		this.estadisticaTiempoPorEstancia = estadisticaTiempoPorEstancia;
	}

	public PieChartModel getChartModel() {
		return chartModel;
	}
	
	public String getCssZona(Resultado zona) {
		return zona.getSala().replaceAll(" ", "");
	}

}
