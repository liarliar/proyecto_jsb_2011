package es.uah.mat.sigueme.simulador.cargador;

import java.util.*;

import es.uah.mat.sigueme.simulador.*;

public class CargadorFicheroVisitas {
	private CargadorRFID cargadorRFID;
	private List<Puerta> puertas;
	private List<TarjetaRFID> tarjetasRFID;
	
	private Integer numVisitas;
	private Date fechaVisita;
	private List<Recorrido> listaRecorridoVisitas;
	
	public CargadorFicheroVisitas(Integer numVisitas, Date fechaVisita) {
		this.puertas = cargadorRFID.getPuertasRFID();
		this.tarjetasRFID = cargadorRFID.getTarjetasRFID();
		this.numVisitas =numVisitas;
		this.fechaVisita=fechaVisita;
		this.listaRecorridoVisitas= new ArrayList<Recorrido>();
		
	}
	public List<Recorrido> getRecorridosVisitas(List<TarjetaRFID> listaTarjetas,List<Puerta> listaPuertas,Integer numVisitas,Date fechaVisita){
		RecepcionTarjetas recepcionTarjetas = new RecepcionTarjetas(listaTarjetas);
		CargadorRecorrido cargaRecorrido = new CargadorRecorrido(puertas,fechaVisita);

		for (int i=0;i<numVisitas;i++){
			final Persona persona = new Persona(recepcionTarjetas);
			final Recorrido recorrido = new Recorrido(cargaRecorrido,persona);
			listaRecorridoVisitas.add(recorrido);
			recepcionTarjetas.devolverTarjeta(persona.getTarjeta());
			
		}
		return listaRecorridoVisitas;
	}

	
}
