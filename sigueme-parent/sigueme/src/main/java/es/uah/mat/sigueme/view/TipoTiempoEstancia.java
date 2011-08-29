package es.uah.mat.sigueme.view;

public enum TipoTiempoEstancia {
	MINIMO,MEDIO,MAXIMO;
	
	public String getLabel(){
		return this.getClass().getSimpleName() + "_" + this.name();
	}

}
