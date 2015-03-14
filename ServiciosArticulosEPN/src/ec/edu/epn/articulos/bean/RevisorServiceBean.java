package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.Revisor;
import ec.edu.epn.articulos.entities.RevisorObservacione;
import ec.edu.epn.articulos.entities.TipoRev;


/**
 * Session Bean implementation class RevisorServiceBean
 */
@Stateless
public class RevisorServiceBean implements RevisorService {

	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RevisorObservacione> getRevisorObservaciones(Integer id) {

		// RevisorObservacione listaRevisores;
		//
		// StringBuilder queryString = new
		// StringBuilder("SELECT art.revisorObservaciones FROM Articulo art where art.idArticulo = ?0");
		//
		// Query query = getEntityManager().createQuery(queryString.toString());
		// query.setParameter(0,id.trim());
		//
		// = (RevisorObservacione) query.getSingleResult();
		//
		// Integer idobser = listaRevisores.getIdRevisorart();
		//
		//
		//
		// System.out.println("Id del revisor Observaciones"+idobser);

		StringBuilder queryString = new StringBuilder(
				"SELECT obsr FROM RevisorObservacione obsr WHERE obsr.articulo.idArticulo = ?0");
		Query query = getEntityManager().createQuery(queryString.toString());
		query.setParameter(0, id); //query.setParameter(0, id.trim());
		return query.getResultList();

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RevisorObservacione> getRevisorArticuloExiste(Integer IdArtc, Integer idrev) {

		StringBuilder queryString = new StringBuilder(
				"SELECT obsr FROM RevisorObservacione obsr WHERE obsr.articulo.idArticulo = ?0 and obsr.revisor.idRev =?1");
		Query query = getEntityManager().createQuery(queryString.toString());
		query.setParameter(0, IdArtc);//query.setParameter(0, IdArtc.trim());
		query.setParameter(1, idrev);
		return query.getResultList();

	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Revisor> findRevisorbyParams(String cedula, String nombres,
			String apellido) {
		//List<Revisor> listarevisor;

		
		System.out.println("Cedula" + cedula + "nombre" + nombres + "apellido"
				+ apellido);
		StringBuilder queryString = new StringBuilder("SELECT rev FROM Revisor rev WHERE rev.nidentificacion like ?0");
		
		Query query = null;
			
		if(cedula == null & nombres == null & apellido == null){
			
			query = getEntityManager().createQuery(queryString.toString());
			query.setParameter(0,"%");
			return query.getResultList();
		}
		
		
			
		
		if (nombres != null) {
				queryString.append(" AND rev.nombres LIKE ?1");
			}

			if (apellido != null) {
				queryString.append(" AND  rev.apellidos LIKE ?2");
			}

			

			 query = getEntityManager().createQuery(queryString.toString());

			 if (cedula == null) {
					query.setParameter(0, "%");
				}
			if (cedula != null) {
				query.setParameter(0, "%" + cedula + "%");
			}
			if (nombres != null) {
				query.setParameter(1, "%" + nombres + "%");
			}
			if (apellido != null) {
				query.setParameter(2, "%" + apellido + "%");
			}

			
		
		return  query.getResultList();

	}
	
	
	@Override
	public void guardarRevisorObservaciones(RevisorObservacione revObs) {

		Query q = getEntityManager().createQuery(
				"SELECT MAX(revobsrva.idRevisorart) FROM RevisorObservacione revobsrva");

		Integer idrevObserva = null;
		try {
			idrevObserva = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			idrevObserva = null;
		}
		if (idrevObserva == null) {
			idrevObserva = 1;
		} else {
			++idrevObserva;
		}
		 revObs.setIdRevisorart(idrevObserva);
		
		getEntityManager().persist(revObs);
	}

	
	@Override
	public void guardarRevisor(Revisor revisor) {
		Query q = getEntityManager().createQuery(
				"SELECT MAX(rev.idRev) from Revisor rev");
		Integer idRev = null;
		try {
			idRev = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			idRev = null;
		}
		if (idRev == null) {
			idRev = 1;
		} else {
			++idRev;
		}
		revisor.setIdRev(idRev);
		getEntityManager().persist(revisor);

	}

	@Override
	public void updateRevisor(Revisor revisor) {

		getEntityManager().merge(revisor);

	}

	@Override
	public Revisor findRevisor(int idRevisor) {
		// TODO Auto-generated method stub
		return getEntityManager().find(Revisor.class, idRevisor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Revisor> findAllRevisor() {
		Query q = getEntityManager().createQuery(
				"SELECT rev from Revisor rev ORDER by rev.apellidos");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoRev> finfAllTiposRevisor() {
		Query q = getEntityManager().createQuery("SELECT tr from TipoRev tr");
		return q.getResultList();
	}

	@Override
	public TipoRev findTipoRevisor(int idTipoRev) {

		return getEntityManager().find(TipoRev.class, idTipoRev);
	}

	@Override
	public boolean isRevisorRegistered(String nIdentificacion) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT rev FROM Revisor rev WHERE rev.nidentificacion LIKE ?0");
		query.setParameter(0, "%" + nIdentificacion.trim() + "%");

		Revisor r = null;
		try {
			r = (Revisor) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (r != null) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Revisor> findRevisosrByParams(String nIDentificacion,
			String[] nombres, String lugarTrabajo) {
		StringBuilder queryString = new StringBuilder(
				"SELECT rev FROM Revisor rev");

		Query query = null;
		String primerNombre = null;
		String segundoNombre = null;

		if (nIDentificacion == null & nombres == null & lugarTrabajo == null) {
			// System.out.println("RETORNA NULL");
			System.out.println("Entra al servicio");
			query = getEntityManager().createQuery(queryString.toString());

			return query.getResultList();
		}else if (nIDentificacion != null) {
			queryString.append(" WHERE rev.nidentificacion LIKE ?0 ");
			
			query = getEntityManager().createQuery(queryString.toString());
			query.setParameter(0, "%" + nIDentificacion.toLowerCase() + "%");
			
			// flag = true;
		}

		
		else if (nombres != null) {
			
			primerNombre = nombres[0];
			// if(flag){
			// queryString.append(" OR");
			// }
			queryString
					.append(" WHERE LOWER(to_ascii(rev.nombres)) LIKE to_ascii(?1) ");
			if (nombres.length > 1) {
				segundoNombre = nombres[1];
				queryString
						.append(" WHERE LOWER(to_ascii(rev.nombres)) LIKE to_ascii(?2) ");
			}
		}

		query = getEntityManager().createQuery(queryString.toString());

		if (nIDentificacion != null) {
			query.setParameter(0, "%" + nIDentificacion.toLowerCase() + "%");
		}

		if (primerNombre != null) {
			query.setParameter(1, "%" + primerNombre.toLowerCase() + "%");
		}
		if (segundoNombre != null) {
			query.setParameter(2, "%" + segundoNombre.toLowerCase() + "%");
		}
		return null;
	}

	@Override
	public void eliminarRevisor(Revisor revisor) {
		// TODO Auto-generated method stub
		getEntityManager().remove(getEntityManager().merge(revisor));
	}


	
	
	
}
