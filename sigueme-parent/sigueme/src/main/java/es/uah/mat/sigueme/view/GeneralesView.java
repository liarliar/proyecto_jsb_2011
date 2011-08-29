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
public class GeneralesView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010282186357781206L;
	private List<Resultado> resultados;

	public GeneralesView () {
		resultados = new ArrayList<Resultado>();
		int indexAleatorio = RandomUtils.nextInt(6);
		
		for (int i = 0; i < indexAleatorio; i++) {
			resultados.add(new Resultado("Sala " + i, RandomUtils.nextLong()));
		}
		
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

}