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
public class PorErroresView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010282186357781206L;
	private boolean verTabsResultados;
	private TipoError tipoError;
	private List<TipoError> tipoErrorLista;
	private List<Resultado> resultados;
	private Date desde;
	private Date hasta;
	
	public TipoError getTipoError() {
		return tipoError;
	}

	public void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	public PorErroresView () {
		verTabsResultados = false;
		tipoErrorLista = Arrays.asList(TipoError.values());
	}
	
	public void verResultados() {
		resultados = new ArrayList<Resultado>();
		verTabsResultados = true;
		int indexAleatorio = RandomUtils.nextInt(6);
		
		for (int i = 1; i < indexAleatorio; i++) {
			resultados.add(new Resultado("Sala " + i, RandomUtils.nextLong()));
		}
		
	}

	public List<TipoError> getTipoErrorLista() {
		return tipoErrorLista;
	}

	public void setTipoErrorLista(List<TipoError> tipoErrorLista) {
		this.tipoErrorLista = tipoErrorLista;
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
