package es.uah.mat.sigueme.estadistica;

public enum TipoNumeroVisitas {
	MINIMO, MEDIO, MAXIMO;
	
	public String getLabel() {
		return this.getClass().getSimpleName() + "_" + this.name();
	}

}
