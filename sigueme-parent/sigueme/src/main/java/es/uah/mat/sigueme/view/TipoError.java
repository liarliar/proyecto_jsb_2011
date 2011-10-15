package es.uah.mat.sigueme.view;

public enum TipoError {
	TODOS, TIPO1, TIPO2;
	
	public String getLabel(){
		return this.getClass().getSimpleName() + "_" + this.name();
	}

}
