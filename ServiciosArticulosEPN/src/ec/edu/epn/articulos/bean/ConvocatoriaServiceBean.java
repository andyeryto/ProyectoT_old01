package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.epn.articulos.entities.Convocatoria;
import ec.edu.epn.articulos.entities.Volumen;

/**
 * Session Bean implementation class ConvocatoriaServiceBean
 */
@Stateless
public class ConvocatoriaServiceBean implements ConvocatoriaService {
	
	@PersistenceContext(unitName = "ServiciosArticulosEPN")
	protected EntityManager em;	
	
	public EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convocatoria> findAllConvocatorias(){
		return getEntityManager().createQuery("SELECT conv FROM Convocatoria conv ORDER BY LOWER(conv.nombreConvo)").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Volumen> findAllVolumenes(){
		return getEntityManager().createQuery("SELECT vol FROM Volumen vol ORDER BY LOWER(vol.nombreVol)").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Volumen findVolumenselect( Integer idvol){
		Query query = getEntityManager().createQuery("SELECT vol FROM Volumen vol WHERE vol.idVol =?0");
		query.setParameter(0, idvol);
		return (Volumen) query.getSingleResult();
		
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Convocatoria> loadFechaLlamada(Convocatoria conv) {
		Query query = getEntityManager().createQuery("SELECT c FROM Convocatoria c WHERE c.idConv =?0");
		query.setParameter(0, conv);
		return query.getResultList();
	}
	
	
//	public List<Convocatoria> findAllConvocatorias6() {
//		StringBuilder queryString = new StringBuilder("SELECT conv FROM Convocatoria conv");
//		Query query = getEntityManager().createQuery(queryString.toString());
//		return query.getResultList();
//	}
   
	
	
}
