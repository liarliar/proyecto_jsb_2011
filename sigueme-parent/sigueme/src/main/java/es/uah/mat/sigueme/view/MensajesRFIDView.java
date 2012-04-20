package es.uah.mat.sigueme.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import javax.annotation.*;

import es.uah.mat.sigueme.persistence.MensajeRFID;
import es.uah.mat.sigueme.persistence.MensajeRFIDRepository;

@ManagedBean
@ViewScoped
public class MensajesRFIDView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5148059215422280559L;
	@ManagedProperty("#{mensajeRFIDRepository}")
	private transient MensajeRFIDRepository mensajeRFIDRepository;
	private List<MensajeRFID> mensajes;

	@PostConstruct
	public void init() {
		mensajes = mensajeRFIDRepository.getMensajesRFID();
	}

	public List<MensajeRFID> getMensajesRFID() {
		return mensajes;
	}
	
	public void setMensajeRFIDRepository(
			MensajeRFIDRepository mensajeRFIDRepository) {
		this.mensajeRFIDRepository = mensajeRFIDRepository;
	}

}
