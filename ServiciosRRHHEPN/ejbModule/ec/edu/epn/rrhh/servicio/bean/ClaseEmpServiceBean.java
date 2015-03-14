package ec.edu.epn.rrhh.servicio.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.rrhh.entities.Clasemp;
import ec.edu.epn.rrhh.servicio.ClaseEmpService;

@Stateless
public class ClaseEmpServiceBean implements ClaseEmpService{

	@PersistenceContext (unitName="ServiciosRRHHEPN")
	protected EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}
	
	@Override
	public Clasemp findClaseEmp(int id) {
		return getEntityManager().find(Clasemp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clasemp> findAllClaseEmp() {
		Query query = getEntityManager().createQuery("SELECT ce FROM Clasemp ce");
		return query.getResultList();
	}
}
