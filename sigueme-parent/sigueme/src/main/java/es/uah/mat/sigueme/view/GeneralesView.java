package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.faces.bean.*;

import org.apache.commons.lang.math.*;

import es.uah.mat.sigueme.bean.*;

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