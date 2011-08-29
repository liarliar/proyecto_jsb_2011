package es.uah.mat.sigueme.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.math.RandomUtils;

import es.uah.mat.sigueme.bean.Resultado;

@ManagedBean
@ViewScoped
public class EstadisticasView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010282186357781206L;
	private boolean componenteTiempoEstanciaHabilitado;
	private boolean componenteNumeroVisitasHabilitado;
	private boolean verTabsResultados;
	private TipoTiempoEstancia tiempoEstancia;
	private TipoNumeroVisitas numeroVisitas;
	private List<TipoTiempoEstancia> tiposTiempoEstancia;
	private List<TipoNumeroVisitas> tiposNumeroVisitas;
	private List<Resultado> resultados;
	private Date desde;
	private Date hasta;

	public EstadisticasView () {
		componenteNumeroVisitasHabilitado = true;
		componenteTiempoEstanciaHabilitado = true;		
		verTabsResultados = false;
		tiposTiempoEstancia = Arrays.asList(TipoTiempoEstancia.values());
		tiposNumeroVisitas = Arrays.asList(TipoNumeroVisitas.values());
	}
	
	public void tiempoEstanciaChangeListener() {

		if (tiempoEstancia == null) {
			habilitarComponenteNumeroVisitas();
		} else {
			deshabilitarComponenteNumeroVisitas();
		}
	}

	public void numeroVisitasChangeListener() {

		if (numeroVisitas == null) {
			habilitarComponenteTiempoEstancia();
		} else {
			deshabilitarComponenteTiempoEntancia();
		}
	}
	
	public void verResultados() {
		resultados = new ArrayList<Resultado>();
		verTabsResultados = true;
		int indexAleatorio = RandomUtils.nextInt(6);
		
		for (int i = 1; i < indexAleatorio; i++) {
			resultados.add(new Resultado("Sala " + i, RandomUtils.nextLong()));
		}
		
	}

	public TipoTiempoEstancia getTiempoEstancia() {
		return tiempoEstancia;
	}

	public void setTiempoEstancia(TipoTiempoEstancia tiempoEstancia) {
		this.tiempoEstancia = tiempoEstancia;
	}

	public TipoNumeroVisitas getNumeroVisitas() {
		return numeroVisitas;
	}

	public void setNumeroVisitas(TipoNumeroVisitas numeroVisitas) {
		this.numeroVisitas = numeroVisitas;
	}

	public boolean isComponenteTiempoEstanciaHabilitado() {
		return componenteTiempoEstanciaHabilitado;
	}

	public boolean isComponenteNumeroVisitasHabilitado() {
		return componenteNumeroVisitasHabilitado;
	}

	private void deshabilitarComponenteNumeroVisitas() {
		componenteNumeroVisitasHabilitado = false;
	}

	private void habilitarComponenteNumeroVisitas() {
		componenteNumeroVisitasHabilitado = true;
	}

	private void deshabilitarComponenteTiempoEntancia() {
		componenteTiempoEstanciaHabilitado = false;
	}

	private void habilitarComponenteTiempoEstancia() {
		componenteTiempoEstanciaHabilitado = true;
	}
	
	public List<TipoTiempoEstancia> getTiposTiempoEstancia() {
		return tiposTiempoEstancia;
	}

	public List<TipoNumeroVisitas> getTiposNumeroVisitas() {
		return tiposNumeroVisitas;
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

}
