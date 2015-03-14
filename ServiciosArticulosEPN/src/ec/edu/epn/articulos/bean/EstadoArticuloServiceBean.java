package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.EstadoA;
import ec.edu.epn.articulos.entities.TipoRev;

/**
 * Session Bean implementation class EstadoArticuloServiceBean
 */
@Stateless
public class EstadoArticuloServiceBean implements EstadoArticuloService {
   @PersistenceContext (unitName= "ServiciosArticulosEPN")
   protected EntityManager em;
   
   public EntityManager getEntityManager(){
	   return em;
   }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoA> findAllEstadoA() {
		StringBuilder queryString = new StringBuilder("SELECT ea FROM EstadoA ea");
		Query query = getEntityManager().createQuery(queryString.toString());
		return query.getResultList();
	}

	@Override
	public void insertEstadoA(EstadoA estA) {
		Query q = getEntityManager().createQuery("SELECT MAX(ea.idEstadoa) FROM EstadoA ea");
		Integer idEstadoa = null;
		try {
			idEstadoa = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			idEstadoa = null;
		}
		if (idEstadoa == null) {
			idEstadoa = 1;
		} else {
			++idEstadoa;
		}
		
		estA.setIdEstadoa(idEstadoa);
		getEntityManager().persist(estA);
		
	}

	@Override
	public void updateEstadoA(EstadoA estA) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEstadoA(EstadoA estA) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoA> findEstadoAIngresado() {
		System.out.println("Estado INGRESADO");
		StringBuilder queryString = new StringBuilder("SELECT ea FROM EstadoA ea WHERE ea.idEstadoa = 1 ");
		Query query = getEntityManager().createQuery(queryString.toString());
		return query.getResultList();
	}
	
	
	
	
	@Override
	public EstadoA findTipoEstado(int idEstadoa) {
		return getEntityManager().find(EstadoA.class, idEstadoa);
	}

}
