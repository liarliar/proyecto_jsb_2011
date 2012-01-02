package es.uah.mat.sigueme.view;

import java.io.*;
import java.util.*;

import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.servlet.*;
import javax.servlet.http.*;

import es.uah.mat.sigueme.simulador.*;
import es.uah.mat.sigueme.simulador.cargador.*;

@ManagedBean
@ViewScoped
public class SimuladorView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date desde;
	private Date hasta;
	private Integer horaInicio;
	private Integer minutoInicio;
	private Integer horaFin;
	private Integer minutoFin;
	private List<Integer> horas;
	private List<Integer> minutos;

	public SimuladorView () {
		horas = createRango(0, 23);
		minutos = createRango(0, 59);
	}
	

	public void simular() {
		if (desde.after(hasta)) {
			FacesContext.getCurrentInstance().addMessage("fechaDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha desde debe ser anterior a la fecha hasta", "La fecha desde debe ser anterior a la fecha hasta"));
		} else if (horaInicio > horaFin || (horaInicio == horaFin && minutoInicio > minutoFin)) {
			FacesContext.getCurrentInstance().addMessage("horaInicio", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La hora inicio debe ser anterior a la hora fin", "La hora inicio debe ser anterior a la hora fin"));
		} else {
		
			final CargadorRFID cargador = new CargadorRFID();
			final RecepcionTarjetas recepcion = new RecepcionTarjetas(cargador.getTarjetasRFID());
			final MapaBiblioteca mapa = new MapaBibliotecaMeco(cargador.getPuertasRFID());
			
			final Biblioteca biblioteca = new Biblioteca(new Horario(horaInicio, horaFin), mapa, recepcion);
			
			
			final Simulador simulador = new SimuladorRangoFechas(desde, hasta, biblioteca);
			final List<Recorrido> recorridos = simulador.simular(10, 25);
			
			try {
		          final String filename = "rutas_generadas.txt";
		          final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 
		          HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		          response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		          response.setHeader("Content-Transfer-Encoding", "Binary");
		          response.setHeader("Pragma", "private");
		          response.setHeader("cache-control", "private, must-revalidate");
		 
		          ServletOutputStream outs = response.getOutputStream();
		          for (Recorrido recorrido : recorridos) {
		        	  List<MensajeRFID> mensajes = recorrido.getMensajes();
		        	  for (MensajeRFID mensajeRFID : mensajes) {
		        		  outs.println(mensajeRFID.toString());
					}
				}
		          outs.flush();
		          outs.close();
		          response.flushBuffer();
		 
		          FacesContext.getCurrentInstance().responseComplete();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(Integer minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}

	public Integer getMinutoFin() {
		return minutoFin;
	}

	public void setMinutoFin(Integer minutoFin) {
		this.minutoFin = minutoFin;
	}

	private List<Integer> createRango(int inicio, int fin) {
		List<Integer> rango = new ArrayList<Integer>();
		
		for (int i = inicio; i <= fin; i++) {
			rango.add(i);
		}
		
		return rango;
	}


	public List<Integer> getHoras() {
		return horas;
	}


	public List<Integer> getMinutos() {
		return minutos;
	}
}
