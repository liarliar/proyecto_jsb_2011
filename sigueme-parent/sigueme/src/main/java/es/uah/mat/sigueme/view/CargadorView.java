package es.uah.mat.sigueme.view;

import java.io.*;

import javax.faces.bean.*;

import org.primefaces.event.*;
import org.primefaces.model.*;

@ManagedBean
@ViewScoped
public class CargadorView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UploadedFile ficheroSubido;
	
	public void ficheroSubido(FileUploadEvent event) {
		ficheroSubido = event.getFile();
	}
}
