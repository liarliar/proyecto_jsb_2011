package es.uah.mat.sigueme.persistence;

public enum TipoTiempoEstancia {
	MINIMO,MEDIO,MAXIMO;
	
	public String getLabel(){
		return this.getClass().getSimpleName() + "_" + this.name();
	}


	String getSql() {
		switch (this) {
		case MINIMO:
			return "min";
		case MEDIO:
			return "avg";
		default:
			return "max";
		}
	}

}
