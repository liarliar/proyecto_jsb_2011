package es.uah.mat.sigueme.listapaginada;

import java.util.*;

public interface ListaPaginaRepository<T> {

	public Tupla<List<T>, Long> ejecutarConsultaPaginada(String hsqlQuery,
			String hsqlCountQuery, int primerResultado, int numeroResultados,
			Map<String, Object> parametros);
	
	public Tupla<List<T>, Long> ejecutarConsultaPaginada(String hsqlQuery,
			String hsqlCountQuery, int primerResultado, int numeroResultados, Class<T> entityClass,
			Map<String, Object> parametros);
}
