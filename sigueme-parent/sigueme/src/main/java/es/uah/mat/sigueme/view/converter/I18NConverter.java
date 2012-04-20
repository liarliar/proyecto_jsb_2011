package es.uah.mat.sigueme.view.converter;

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import org.springframework.context.support.*;
import org.springframework.web.context.*;
import org.springframework.web.jsf.*;



public class I18NConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		return getMessage(context, value.toString());
	}

	protected String getMessage(FacesContext context, String key) {
		final MessageSourceAccessor messageSourceAccessor = getMessageSourceAccessor(context);
		return messageSourceAccessor.getMessage(key);
	}

	MessageSourceAccessor getMessageSourceAccessor(FacesContext context) {		
		return getBean(context, MessageSourceAccessor.class,
				"messageSourceAccessor");
	}

	public <T> T getBean(FacesContext context, Class<T> beanClass, String beanName) {
		final WebApplicationContext webApplicationContext = FacesContextUtils.getRequiredWebApplicationContext(context);
		return (T) webApplicationContext.getBean(beanName, beanClass);
	}
}
