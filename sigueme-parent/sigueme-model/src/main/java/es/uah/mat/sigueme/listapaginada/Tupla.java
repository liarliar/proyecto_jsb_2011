package es.uah.mat.sigueme.listapaginada;


public class Tupla<T, E> {

	private T izquierda;
	private E derecha;

	public Tupla() {
	}

	public T getIzquierda() {
		return izquierda;
	}

	public E getDerecha() {
		return derecha;
	}

	public void setTamano(E derecha) {
		this.derecha = derecha;
	}

	public void setLista(T izquierda) {
		this.izquierda = izquierda;
	}
	public E getTamano() {
		return derecha;
	}

}
