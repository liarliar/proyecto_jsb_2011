package es.uah.mat.sigueme.simulador.cargador;

import java.util.*;

import es.uah.mat.sigueme.simulador.*;
import es.uah.mat.sigueme.simulador.Puerta;

public class CargadorRecorrido {
	private List<Puerta> puertas;
	private Date fechaVisita;
	private Date momentoActual;
	private Random random;
	
	public CargadorRecorrido(List<Puerta> puertas, Date fechaVisita) {
		this.puertas = puertas;
		// Inicializo la hora de la visita
		this.fechaVisita = getHoraVisita(fechaVisita);
		this.momentoActual = this.fechaVisita;
		this.random = new Random();
	}
	public List<MensajeRFID> generaRecorrido (Persona persona){
		List <MensajeRFID> recorrido = new ArrayList<MensajeRFID>();
		// el recorrido comienza cuando atravesamos la p1 y entramos en la zona z2
		recorrido.add(generoPasoPuerta(puertas.get(0), persona, fechaVisita));
		int sala = -1;
	    momentoActual = calculaHora(momentoActual, getTiempoVisitaSala());		
		while (sala != 0 && getTiempoVisita()<120){
		   sala = accediendoaSala();

		   // mensaje de entrada
		   generoPasoPuerta(puertas.get(sala), persona, momentoActual);
		   // calculo el tiempo pasado en la sala
		   momentoActual = calculaHora(momentoActual, getTiempoVisitaSala());
		   // mensaje de salida
		   generoPasoPuerta(puertas.get(sala), persona, momentoActual);
		   
		}
		// generamos el registro de salida
		recorrido.add(generoPasoPuerta(puertas.get(0), persona, momentoActual));
		return recorrido;
	}
	private Date getHoraVisita(Date fechaVisita){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fechaVisita);
		calendar.set(Calendar.HOUR_OF_DAY,9);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		// Calcular hora de entrada el museo estara abierto de 9 a 8, solo se permite entrar hasta las 7 y media 
		long horaVisitaenSegundos=random.nextInt(630*60);
		horaVisitaenSegundos =+ calendar.getTime().getTime(); 
			
		return new Date(horaVisitaenSegundos);
	}

	public Integer getTiempoVisitaSala(){
		return random.nextInt(1200);
	}
	public Date calculaHora (Date fecha, Integer minutos)
	{
		long nuevaFecha = fecha.getTime()+(minutos*60);
		return new Date(nuevaFecha);
	}
	
	public int accediendoaSala (){
		int sala =random.nextInt(3);
		return sala;
	}
	public MensajeRFID generoPasoPuerta(Puerta puerta,Persona persona,Date momentoPaso)
	{
		MensajeRFID mensajeRFID = new MensajeRFID();
		mensajeRFID.setTipo(TipoMensaje.RM);
		mensajeRFID.setDireccionCorta(puerta.getDireccionCorta());
		mensajeRFID.setFecha(momentoPaso);
		mensajeRFID.setDireccionLarga(persona.getTarjeta().getDireccionLarga());

		return mensajeRFID;
	}
	public long getTiempoVisita(){
		long segundosVisita = momentoActual.getTime() - fechaVisita.getTime();
		return segundosVisita/60;
	}
}
