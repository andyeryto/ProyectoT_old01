package ec.edu.epn.gestionDocente.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import ec.edu.ec.gestionDocente.entities.Publicacione;

/**
 * Session Bean implementation class PublicacionesServiceBean
 */
@Stateless
public class PublicacionesServiceBean implements PublicacionesService {

	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	private EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void insertPublicacionDocente(Publicacione publicaciones) {

		getEntityManager().persist(publicaciones);

	}

}
