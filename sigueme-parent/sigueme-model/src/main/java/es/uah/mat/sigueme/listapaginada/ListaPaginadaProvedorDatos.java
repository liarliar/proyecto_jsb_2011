package es.uah.mat.sigueme.listapaginada;

import java.util.*;

import es.uah.mat.sigueme.commons.util.*;

public interface ListaPaginadaProvedorDatos<T> {

	Tupla<List<T>, Long> getPage(int primeraFila, int tamanoPagina);

}
