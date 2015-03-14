package ec.edu.epn.rrhh.servicio.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.rrhh.entities.Estemp;
import ec.edu.epn.rrhh.servicio.EstadoEmpleadoService;

@Stateless
public class EstadoEmpleadoServiceBean implements EstadoEmpleadoService {

	@PersistenceContext (unitName="ServiciosRRHHEPN")
	protected EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}

	@Override
	public Estemp findEstadoEmpleado(int id) {
		return getEntityManager().find(Estemp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Estemp> findAllEstadoEmpleado() {
		Query query = getEntityManager().createQuery("SELECT eemp FROM Estemp eemp");
		return query.getResultList();
	}
	
}
