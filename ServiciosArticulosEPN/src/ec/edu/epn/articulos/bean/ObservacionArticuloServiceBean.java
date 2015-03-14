package ec.edu.epn.articulos.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.ObservacionArticulo;
import ec.edu.epn.articulos.entities.RevisorObservacione;

/**
 * Session Bean implementation class ObservacionArticuloServiceBean
 */
@Stateless
public class ObservacionArticuloServiceBean implements ObservacionArticuloService {

	
	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RevisorObservacione> findRevArtbyParams(String idArticulo,
			String palabrasclave, String titulo, Date fechadesde,
			Date fechaHasta, Integer idEstado, String cedulaRev) {
	
		
		StringBuilder queryString = new StringBuilder("SELECT obsr FROM RevisorObservacione obsr WHERE obsr.revisor.nidentificacion like ?0 ");
		
		
		if(idArticulo == null & palabrasclave == null & titulo == null & fechadesde == null
				& fechaHasta == null & idEstado == null){
			//System.out.println("RETORNA NULL");
			return null;
		}
		
		if(palabrasclave!=null){
			queryString.append(" AND obsr.articulo.palabrasClave LIKE ?1");
		}
		
		
		if(titulo!=null){
			queryString.append(" AND obsr.articulo.tituloArticulo LIKE ?2");
		}
		
		if(fechadesde!=null & fechaHasta != null ){
			queryString.append(" AND obsr.articulo.fechaEnvio BETWEEN ?3 AND ?4 ");
		}
	
		if(idEstado!=null ){
			queryString.append(" AND obsr.articulo.estadoA.idEstadoa = ?5 ");
		}
		
		if(idArticulo!=null ){
			queryString.append(" AND obsr.articulo.auxAutogenerado like ?6 ");
		}
		
		queryString.append("  ORDER BY obsr.idRevisorart ");
		
		Query query = getEntityManager().createQuery(queryString.toString());

		
		
		if(cedulaRev != null) {
			query.setParameter(0, "%"+cedulaRev+"%");
		}
		if(palabrasclave!=null){
			query.setParameter(1, "%"+palabrasclave+"%");
		}
		if(titulo!=null){
			query.setParameter(2, "%"+titulo+"%");
		}
		if(fechadesde!=null & fechaHasta != null ){
			query.setParameter(3, fechadesde);
			query.setParameter(4, fechaHasta);
			
			
		}
		
		if(idEstado!= 0){
			query.setParameter(5, +idEstado);
		}
		
		if(idArticulo != null) {
			query.setParameter(6, "%"+idArticulo+"%");
		}
		
		
		return query.getResultList();
		
		
		
	
	}



	@Override
	public void insertObservaArticulo(ObservacionArticulo observacion) {
		
		Query q = getEntityManager().createQuery("SELECT MAX(obs.idObservacion) FROM ObservacionArticulo obs");
		Integer idObservacion = null;

				
		try {
			idObservacion = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			idObservacion = null;
		}
		if (idObservacion == null) {
			idObservacion = 1;
		} else {
			++idObservacion;
		}
		
		observacion.setIdObservacion(idObservacion);
		
		
		getEntityManager().persist(observacion);
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findRev_ObservacionesByParams(String idArticulo,
			String palabrasclave, String titulo, Date fechadesde,
			Date fechaHasta, Integer idEstado, String idconvoca) 
		
		{
	
		
		StringBuilder queryString =  new StringBuilder("SELECT art FROM Articulo art WHERE art.auxAutogenerado like ?0 ");
		
		
		if(idArticulo == null & palabrasclave == null & titulo == null & fechadesde == null
				& fechaHasta == null & idEstado == null & idconvoca == null){
			//System.out.println("RETORNA NULL");
			return null;
		}
		
		if(palabrasclave!=null){
			queryString.append(" AND art.palabrasClave LIKE ?1");
		}
		
		
		if(titulo!=null){
			queryString.append(" AND art.tituloArticulo LIKE ?2");
		}
		
		if(fechadesde!=null & fechaHasta != null ){
			queryString.append(" AND art.fechaEnvio BETWEEN ?3 AND ?4 ");
		}
	
		if(idEstado!=null ){
			queryString.append(" AND art.estadoA.idEstadoa = ?5 ");
		}
		
		if(idArticulo!=null ){
			queryString.append(" AND art.convocatoria.idConv like ?6 ");
		}
		
		queryString.append("ORDER BY art.auxAutogenerado");
		
		Query query = getEntityManager().createQuery(queryString.toString());

		
		
		if(idArticulo != null) {
			query.setParameter(0, "%"+idArticulo+"%");
		}
		if(palabrasclave!=null){
			query.setParameter(1, "%"+palabrasclave+"%");
		}
		if(titulo!=null){
			query.setParameter(2, "%"+titulo+"%");
		}
		if(fechadesde!=null & fechaHasta != null ){
			query.setParameter(3, fechadesde);
			query.setParameter(4, fechaHasta);
			
			
		}
		
		if(idEstado!= 0){
			query.setParameter(5, +idEstado);
		}
		
		if(idconvoca != null) {
			query.setParameter(6, "%"+idconvoca+"%");
		}
		
		
		return query.getResultList();
		
		
		
	
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<ObservacionArticulo> findObservacionArticulo(
			String auxAutogenerado) {
		
		StringBuilder queryString =  new StringBuilder("SELECT obsrArt FROM ObservacionArticulo obsrArt WHERE obsrArt.revisorObservacione.articulo.auxAutogenerado like ?0 ");
		
		Query query = getEntityManager().createQuery(queryString.toString());
		
		query.setParameter(0, "%"+auxAutogenerado+"%");
		
		
		return query.getResultList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ObservacionArticulo> findObservacionArticuloExiste(
			String auxAutogenerado, String nidentificacion) {
		
		StringBuilder queryString =  new StringBuilder("SELECT obsrArt FROM ObservacionArticulo obsrArt WHERE obsrArt.revisorObservacione.articulo.auxAutogenerado like ?0 ");
		
		queryString.append(" AND  obsrArt.revisorObservacione.revisor.nidentificacion like ?1");
		Query query = getEntityManager().createQuery(queryString.toString());
		
		query.setParameter(0, "%"+auxAutogenerado+"%");
		query.setParameter(1, "%"+nidentificacion+"%");
		
		
		return query.getResultList();
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<String> findemailrevisorObservaciones(String auxautogenerado) {
		StringBuilder queryString = new StringBuilder("SELECT obsr.revisor.email FROM RevisorObservacione obsr WHERE obsr.articulo.auxAutogenerado like ?0 ");
		Query query = getEntityManager().createQuery(queryString.toString());
		query.setParameter(0, "%"+auxautogenerado+"%");
		return query.getResultList();
		
	}
	
	
	
	@Override
	public void deleteObserv(Integer id) {

		try {
			Query q = getEntityManager()
					.createQuery(
							"DELETE FROM ObservacionArticulo obsr WHERE  obsr.idObservacion = ?0 ");
			q.setParameter(0,id );
		
			q.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);

		}

		// getEntityManager().remove(evento);

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findIdObservacion(
			String auxAutogenerado, String estado) {
		
		StringBuilder queryString =  new StringBuilder("SELECT obsrArt.idObservacion FROM ObservacionArticulo obsrArt WHERE obsrArt.revisorObservacione.articulo.auxAutogenerado like ?0 ");
		
		queryString.append(" AND  obsrArt.estado like ?1");
		Query query = getEntityManager().createQuery(queryString.toString());
		
		query.setParameter(0, "%"+auxAutogenerado+"%");
		query.setParameter(1, "%"+estado+"%");
		
		
		return query.getResultList();
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

   
}
