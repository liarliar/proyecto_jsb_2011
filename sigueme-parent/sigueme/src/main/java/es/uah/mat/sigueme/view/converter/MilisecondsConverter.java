package es.uah.mat.sigueme.view.converter;

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

@FacesConverter("milisecondsConverter")
public class MilisecondsConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null)
			return null;
		
		Long miliseconds = (Long) value;
		
		int segundos = miliseconds.intValue() / 1000;
		int segundo = segundos % 60;   
		int minutos = segundos / 60;   
		int minuto = minutos % 60;   
		int hora = minutos / 60;   
		String hms = String.format ("%02d:%02d:%02d", hora, minuto, segundo);  
		return hms;
	}

}
