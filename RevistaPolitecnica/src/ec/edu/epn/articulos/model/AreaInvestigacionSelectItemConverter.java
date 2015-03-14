package ec.edu.epn.articulos.model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import ec.edu.epn.articulos.entities.AreaInvestigacion;

@FacesConverter(forClass=AreaInvestigacion.class)
public class AreaInvestigacionSelectItemConverter extends SelectItemsConverter{
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String id = (value instanceof AreaInvestigacion) ? ((AreaInvestigacion) value).getIdAreainv() : null;
        return (id != null) ? id : null;
	}
	
	
	

}
