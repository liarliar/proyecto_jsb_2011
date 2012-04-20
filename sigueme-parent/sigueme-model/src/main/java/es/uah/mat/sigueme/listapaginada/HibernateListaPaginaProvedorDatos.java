package es.uah.mat.sigueme.listapaginada;

import java.util.*;

public class HibernateListaPaginaProvedorDatos<T> implements
		ListaPaginadaProvedorDatos<T> {

	private final ListaPaginaRepository<T> listaPaginadaRepository;
	private final String query;
	private final String countQuery;
	private final Map<String, Object> parametros;

	public HibernateListaPaginaProvedorDatos(
			ListaPaginaRepository<T> listaPaginadaRepository, String query,
			String countQuery,
			Map<String, Object> parametros) {
		this.listaPaginadaRepository = listaPaginadaRepository;
		this.query = query;
		this.countQuery = countQuery;
		this.parametros = parametros;

	}

	@Override
	public Tupla<List<T>, Long> getPage(int primeraFila, int tamanoPagina) {
		return listaPaginadaRepository.ejecutarConsultaPaginada(query,
				countQuery, primeraFila, tamanoPagina, parametros);
	}

}
