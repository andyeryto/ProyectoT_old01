package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.Convocatoria;
import ec.edu.epn.articulos.entities.Volumen;

@Local
public interface ConvocatoriaService {
	
	List<Convocatoria> findAllConvocatorias();
	
	List<Convocatoria> loadFechaLlamada(Convocatoria conv);

	List<Volumen> findAllVolumenes();

	Volumen findVolumenselect(Integer idvol);
	

}
