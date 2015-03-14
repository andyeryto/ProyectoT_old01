package ec.edu.epn.rrhh.servicio.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.rrhh.entities.Emp;
import ec.edu.epn.rrhh.servicio.EmpleadoService;

@Stateless
public class EmpleadoServiceBean implements EmpleadoService {

	@PersistenceContext (unitName="ServiciosRRHHEPN")
	protected EntityManager em;
	
	public EntityManager getEntityManager(){
		return em;
	}

	@Override
	public Emp findEmpleado(int id) {
		return getEntityManager().find(Emp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emp> findAllEmpleado() {
		Query query = getEntityManager().createQuery("SELECT emp FROM Emp emp");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emp> findEmpleadoByParameters(String nced, String apellido) {
		System.out.println("CONSULTANDO EMPLEADOS");
		String estEmp="1";
		StringBuilder stringQuery = new StringBuilder("SELECT emp FROM Emp emp WHERE emp.estemp.codEst=?0");
		StringBuilder stringQueryAND = new StringBuilder(" AND ");
		boolean ncedB = nced!=null && !nced.equals("");
		if(ncedB){
			nced = nced.trim();
		}
		boolean apellidoB = apellido!=null && !apellido.equals("");
		if(apellidoB){
			apellido = apellido.trim();
		}

		String stringQueryNced = null;
		if(ncedB){
			stringQueryNced = new String(" emp.nced=?1 ");
		}
		String stringQueryApellido = null;
		if(apellidoB){
			stringQueryApellido = new String("UPPER(emp.apel) LIKE ?2 ");
		}
		
		if(ncedB & apellidoB){
			stringQuery.append(stringQueryAND+"("+stringQueryNced+"AND"+stringQueryApellido+")");
		}
		if(ncedB==true & apellidoB==false){
			stringQuery.append(stringQueryAND+stringQueryNced);
		}
		if(ncedB==false & apellidoB==true){
			stringQuery.append(stringQueryAND+stringQueryApellido);
		}
		
		Query query = getEntityManager().createQuery(stringQuery.toString());
		query.setParameter(0, estEmp);
		if(ncedB){
			query.setParameter(1, nced);
		}
		if(apellidoB){
			query.setParameter(2, "%"+apellido.toUpperCase()+"%");
		}

		System.out.println(stringQuery.toString());
		
		return query.getResultList();
	}
	
}
