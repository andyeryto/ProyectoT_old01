package ec.edu.epn.articulos.model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import ec.edu.epn.articulos.entities.AreaInvestigacion;
import ec.edu.epn.articulos.entities.Convocatoria;

@FacesConverter (forClass=Convocatoria.class)
public class ConvocatoriaSelectItemConverter extends SelectItemsConverter{
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String id = (value instanceof Convocatoria) ? ((Convocatoria) value).getIdConv() : null;
        return (id != null) ? id : null;
	}
		
}
