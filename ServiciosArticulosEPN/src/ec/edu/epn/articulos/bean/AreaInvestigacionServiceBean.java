package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.AreaInvestigacion;

/**
 * Session Bean implementation class AreaInvestigacionServiceBean
 */
@Stateless
public class AreaInvestigacionServiceBean implements AreaInvestigacionService {
	
	@PersistenceContext(unitName= "ServiciosArticulosEPN")
	protected EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}

  	@SuppressWarnings("unchecked")
	@Override
	public List<AreaInvestigacion> findAllAreasInv() {
  		StringBuilder queryString = new StringBuilder("SELECT ai from AreaInvestigacion ai");
  		Query query = getEntityManager().createQuery(queryString.toString());
  		return query.getResultList();

	}

}
