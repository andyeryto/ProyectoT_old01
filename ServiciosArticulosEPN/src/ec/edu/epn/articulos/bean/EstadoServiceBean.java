package ec.edu.epn.articulos.bean;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.EstadoA;





/**
 * Session Bean implementation class EstadoServiceBean
 */
@Stateless
public class EstadoServiceBean implements EstadoService {
	
	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoA> findAllEstados() {
		
		StringBuilder queryString = new StringBuilder("SELECT est FROM EstadoA est");

		Query query = getEntityManager().createQuery(queryString.toString());

		return query.getResultList();

	}

	
}
