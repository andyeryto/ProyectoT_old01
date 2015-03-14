package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.Articulo;

@Stateless
public class ArticulosDocenteServiceBean implements ArticulosDocenteService {

	@PersistenceContext(unitName="ServiciosArticulosEPN")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> getArticulosPorDocente(Integer idUser) {
		Query qUsuario = getEntityManager().createQuery("Select user.cedula FROM Usuario user WHERE user.idUsuario = ?1");
		qUsuario.setParameter(1, idUser);
		String cedula = (String) qUsuario.getSingleResult();
		System.out.println("Cedula Usuario: " + cedula);
		
		Query qArticulos = getEntityManager().createQuery("Select a FROM Articulo a WHERE a.ncedArticulista = ?1");
		qArticulos.setParameter(1, cedula);
		return qArticulos.getResultList();
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	

}
