package es.uah.mat.sigueme.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;


@FacesConverter("i18NEnumConverter")
public class I18NEnumConverter extends I18NConverter {

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass().isEnum()) {
			return getMessage(context, value.getClass().getSimpleName() + "_" + value.toString());
		} else {
			return value.toString();
		}
	}

}