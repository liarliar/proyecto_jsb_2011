package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

import org.primefaces.event.*;

import es.uah.mat.sigueme.estadistica.*;
import es.uah.mat.sigueme.persistence.*;

@ManagedBean
@ViewScoped
public class PorVisitasView implements Serializable {

	@ManagedProperty("#{estadisticaPorVisitas}")
	private EstadisticaPorVisitas estadisticaPorVisitas;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010282186357781206L;
	private boolean verTabsResultados;
	private Date desde;
	private Date hasta;
	private List<Ruta> rutas;
	private List<RecorridoSala> ruta;

	public PorVisitasView () {
		verTabsResultados = false;
	}
	
	public void verResultados() {
		verTabsResultados = true;
		rutas = estadisticaPorVisitas.getRutas(desde, hasta);
	}
	
    public void onRowToggle(ToggleEvent event) {
    	Ruta rutaSelected = (Ruta) event.getData();
    	ruta = estadisticaPorVisitas.getRecorrido(rutaSelected.getId());
    }


	public List<Ruta> getRutas() {
		return rutas;
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

	public void setEstadisticaPorVisitas(EstadisticaPorVisitas estadisticaPorVisitas) {
		this.estadisticaPorVisitas = estadisticaPorVisitas;
	}

	public List<RecorridoSala> getRuta() {
		return ruta;
	}

}
