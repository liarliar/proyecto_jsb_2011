package es.uah.mat.sigueme.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class GeneralesPorDiaView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6680945021215084566L;
	public Date fecha;
	public TipoGrafico tipoGrafico;
	public List<TipoGrafico> tiposGrafico;
	
	public GeneralesPorDiaView () {
		fecha = new Date();
		tipoGrafico = TipoGrafico.PORCIONES;
		tiposGrafico = Arrays.asList(TipoGrafico.values());
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
	
	
}
