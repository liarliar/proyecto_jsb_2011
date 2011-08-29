package es.uah.mat.sigueme.bean;

public class Resultado {

	private String sala;
	private Long valor;
	
	public Resultado (String sala, Long valor) {
		this.sala = sala;
		this.valor = valor;
	}
	
	public String getSala() {
		return sala;
	}
	public Long getValor() {
		return valor;
	}
	
	
}
