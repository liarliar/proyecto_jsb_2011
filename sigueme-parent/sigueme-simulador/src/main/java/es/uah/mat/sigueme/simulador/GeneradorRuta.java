package es.uah.mat.sigueme.simulador;

import java.util.*;

import org.apache.commons.lang.math.*;

public class GeneradorRuta  {

	private Date fechaComienzoRuta;
	private RecepcionTarjetas recepcion;
	private MapaBiblioteca mapa;

	public GeneradorRuta (Date fecha, int hora, RecepcionTarjetas recepcion, MapaBiblioteca mapa ) {
		this.fechaComienzoRuta = calcularFecha(fecha, hora);
		this.recepcion = recepcion;
		this.mapa = mapa;
		
	}
	
	private Date calcularFecha(Date fecha, int hora) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, RandomUtils.nextInt(59));
		
		return calendar.getTime();
	}

	public Recorrido simular() {
		final Recorrido recorrido = new Recorrido();
		Date fechaEscaneoTarjeta = fechaComienzoRuta;
		Persona persona = new Persona(recepcion.recogerTarjeta());
		
		EstanciaEnZona estancia = mapa.getProximaEstancia(Zona.UNA);
		IntervaloEstancia intervalor = estancia.getIntervalo();
		
		do  {
			recorrido.addMensaje(persona.getTarjeta(), estancia.getPuerta(), fechaEscaneoTarjeta);
			fechaEscaneoTarjeta = calcularFechaEscaneo(fechaEscaneoTarjeta, intervalor);
			intervalor = estancia.getIntervalo();
		} while ((estancia = mapa.getProximaEstancia(estancia.getZona())).getZona() != Zona.UNA);
		
		recorrido.addMensaje(persona.getTarjeta(), estancia.getPuerta(), calcularFechaEscaneo(fechaEscaneoTarjeta, intervalor));
		
		recepcion.devolverTarjeta(persona.getTarjeta());
		
		return recorrido;
	}

	private Date calcularFechaEscaneo(Date fechaEscaneoTarjeta,
			IntervaloEstancia intervalor) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaEscaneoTarjeta);
		int diferencia = intervalor.getTiempoMaximo() - intervalor.getTiempoMinimo();
		int minutos = intervalor.getTiempoMinimo() + RandomUtils.nextInt((diferencia <= 0) ? 1 : diferencia);
		calendar.add(Calendar.MINUTE, minutos);
		
		return calendar.getTime();
	}
}
