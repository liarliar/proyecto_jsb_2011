package es.uah.mat.sigueme.view;

public enum TipoGrafico {
	PORCIONES,LINEAS,BARRAS;
	
	public String getLabel() {
		return this.getClass().getSimpleName() + "_" + this.name();
	}
}
