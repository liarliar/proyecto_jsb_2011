package es.uah.mat.sigueme.listapaginada;

import java.util.*;

import org.primefaces.model.*;

import es.uah.mat.sigueme.commons.util.*;

public class ListaPaginadaDataModel<T> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8657520115011611231L;
	private List<T> lista;

	public ListaPaginadaDataModel(List<T> lista) {
		this.lista = lista;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField,
			boolean sortOrder, Map<String, String> filters) {
		if (lista instanceof ListaPaginada) {
			ListaPaginada<T> listaPaginada = (ListaPaginada<T>) lista;
			
			listaPaginada.setPageSize(pageSize);
		}
		return lista;
	}

}
