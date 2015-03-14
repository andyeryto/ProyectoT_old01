package ec.edu.epn.articulos.model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import ec.edu.epn.articulos.entities.Volumen;


@FacesConverter(forClass=Volumen.class)
public class VolumenSelectItemConverter extends SelectItemsConverter{
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
       
        Integer id = (value instanceof Volumen) ? ((Volumen) value).getIdVol() : null;
        return (id != null) ? String.valueOf(id) : null;
        
	}

}
