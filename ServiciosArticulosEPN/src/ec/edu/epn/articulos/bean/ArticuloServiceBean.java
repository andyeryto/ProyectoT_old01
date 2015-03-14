  package ec.edu.epn.articulos.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.ObservacionArticulo;

/**
 * Session Bean implementation class ArticuloServiceBean
 */
@Stateless
public class ArticuloServiceBean implements ArticuloService {

	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findarticulobyParams(String idArticulo,
			String palabrasclave, String titulo, Date fechadesde,
			Date fechaHasta, Integer idEstado) 
			{

		StringBuilder queryString = new StringBuilder("SELECT art FROM Articulo art WHERE art.auxAutogenerado like ?0");
		
		
		if(idArticulo == null & palabrasclave == null & titulo == null & fechadesde == null
				& fechaHasta == null & idEstado == null){
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
		
		queryString.append("  ORDER BY art.tituloArticulo ");
		
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
		
		
		return query.getResultList();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findarticulobyParams(Integer idArticulo,
			String palabrasclave, String titulo, Date fechadesde,
			Date fechaHasta, Integer idEstado) {
		StringBuilder queryString = new StringBuilder(
				"SELECT art FROM Articulo art WHERE art.idArticulo=?0");

		if (idArticulo == null & palabrasclave == null & titulo == null
				& fechadesde == null & fechaHasta == null & idEstado == null) {
			// System.out.println("RETORNA NULL");
			return null;
		}

		if (palabrasclave != null) {
			queryString.append(" AND art.palabrasClave LIKE ?1");
		}

		if (titulo != null) {
			queryString.append(" AND art.tituloArticulo LIKE ?2");
		}

		if (fechadesde != null & fechaHasta != null) {
			queryString.append(" AND art.fechaEnvio BETWEEN ?3 AND ?4 ");
		}

		if (idEstado != null) {
			queryString.append(" AND art.estadoA.idEstadoa = ?5 ");
		}

		queryString.append("  ORDER BY art.tituloArticulo ");

		Query query = getEntityManager().createQuery(queryString.toString());

		if (idArticulo != null) {
			query.setParameter(0, idArticulo);
		}
		if (palabrasclave != null) {
			query.setParameter(1, "%" + palabrasclave + "%");
		}
		if (titulo != null) {
			query.setParameter(2, "%" + titulo + "%");
		}
		if (fechadesde != null & fechaHasta != null) {
			query.setParameter(3, fechadesde);
			query.setParameter(4, fechaHasta);

		}

		if (idEstado != 0) {
			query.setParameter(5, +idEstado);
		}

		return query.getResultList();
	}


	@Override
	public void insertArticuloDocente(Articulo articulo) {
//		getEntityManager().persist(articulo);
		Query q = getEntityManager().createQuery("SELECT MAX(a.idArticulo) FROM Articulo a");
		Integer idArticulo = null;

				
		try {
			idArticulo = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			idArticulo = null;
		}
		if (idArticulo == null) {
			idArticulo = 1;
		} else {
			++idArticulo;
		}
		
		articulo.setIdArticulo(idArticulo);
		getEntityManager().persist(articulo);

		
	}


	@Override
	public void updateArticuloDocente(Articulo articulo) {
		getEntityManager().merge(articulo);
		
	}


	@Override
	public void deleteArticuloDocente(Articulo articulo) {
		try{
			Query q = getEntityManager().createQuery("DELETE FROM Articulo a WHERE a.idArticulo =?0 ");
			q.setParameter(0,articulo.getIdArticulo());
			q.executeUpdate();
			
			}
			catch (Exception e) {
				System.out.println(e);
				
			}			
					
	}


	@Override
	public int maxCodArticulo() {
		Query q = getEntityManager().createQuery("SELECT MAX(a.idArticulo) FROM Articulo a");
		Integer idArticulo = null;

				
		try {
			idArticulo = (Integer) q.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			idArticulo = null;
		}
		if (idArticulo == null) {
			idArticulo = 1;
		} else {
			++idArticulo;
		}
		return idArticulo;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ObservacionArticulo> observacionesSI(Integer idarticulo )
	{
		String si="SI";
		StringBuilder queryString =  new StringBuilder("SELECT obsrArt FROM ObservacionArticulo obsrArt WHERE obsrArt.revisorObservacione.articulo.idArticulo = ?0 ");
		
		queryString.append(" AND obsrArt.estado like ?1");
		
		Query query = getEntityManager().createQuery(queryString.toString());
		
		query.setParameter(0,idarticulo);
		query.setParameter(1,si);
		return query.getResultList();
		
	}
	
	
	
	
	@Override
	public void updateArticulo(Articulo art) {
		getEntityManager().merge(art);

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findarticuloVolumen(Integer idvolumen) {
		StringBuilder queryString = new StringBuilder(
				"SELECT art FROM Articulo art WHERE art.volumen.idVol=?0 ");

	

		queryString.append(" ORDER BY art.tituloArticulo ");

		Query query = getEntityManager().createQuery(queryString.toString());

		if (idvolumen != null) {
			query.setParameter(0, idvolumen);
		}
		
		return query.getResultList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
