package ec.edu.epn.gestionDocente.beans;

import javax.ejb.Local;

import ec.edu.ec.gestionDocente.entities.Publicacione;


@Local
public interface PublicacionesService {
	
	void insertPublicacionDocente(Publicacione publicaciones);

}
