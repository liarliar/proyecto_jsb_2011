package es.uah.mat.sigueme.commons.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.uah.mat.sigueme.listapaginada.*;

public class ListaPaginada<T> extends AbstractList<T> {

	private static final Log log = LogFactory.getLog(ListaPaginada.class);

	public static final int DEFAULT_PAGE_SIZE = 10;

	protected List<T> elementosCargados;

	private int tamanoPagina;

	protected int numeroFilas = -1;

	protected int primeraFila = 0;

	private int ultimaFila;

	private final ListaPaginadaProvedorDatos<T> provedorDatos;

	public ListaPaginada(ListaPaginadaProvedorDatos<T> provedorDatos) {
		this(provedorDatos, DEFAULT_PAGE_SIZE);
	}

	public ListaPaginada(ListaPaginadaProvedorDatos<T> provedorDatos, int tamanoPagina) {
		this.provedorDatos = provedorDatos;
		this.tamanoPagina = tamanoPagina;
	}

	@Override
	public boolean add(T e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		elementosCargados = null;
	}

	void checkLoadPage(int index) {
		if (elementosCargados == null || index < primeraFila || index > ultimaFila) {
			cargarPagina(index);
		}
	}

	protected void cargarPagina(int index) {
		final int pagesBefore = index / tamanoPagina;
		primeraFila = pagesBefore * tamanoPagina;

		if (log.isDebugEnabled()) {
			log.debug("Loading new page: index=" + index + ", firstRow="
					+ primeraFila + ", pageSize=" + tamanoPagina);
		}

		final Tupla<List<T>, Long> pair = provedorDatos.getPage(primeraFila,
				tamanoPagina);
		elementosCargados = pair.getIzquierda();
		numeroFilas = pair.getDerecha().intValue();
		ultimaFila = primeraFila + elementosCargados.size() - 1;
	}

	@Override
	public T get(int index) {
		checkLoadPage(index);
		return elementosCargados.get(index - primeraFila);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		if (elementosCargados == null) {
			checkLoadPage(primeraFila);
		}
		return numeroFilas;
	}

	public int getPageSize() {
		return tamanoPagina;
	}

	public void setPageSize(int pageSize) {
		this.tamanoPagina = pageSize;
		clear();
	}

}
