package ec.edu.epn.articulos.bean;

import javax.ejb.Stateless;
//import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

@Stateless
public class UsuarioServiceBean implements UsuarioService {
	
//	FacesContext fc = FacesContext.getCurrentInstance();
//	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
//			.getRequest();
//	HttpSession session = request.getSession();

	@PersistenceContext(unitName="ServiciosArticulosEPN")
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public int buscarCedulaUsuario(Integer idUser) {
		Query qUsuario = getEntityManager().createQuery("Select user.cedula FROM Usuario user WHERE user.idUsuario = ?1");
		qUsuario.setParameter(1, idUser);
		int cedula = Integer.parseInt((String) qUsuario.getSingleResult());
		System.out.println("Cedula Docente******" + cedula);
//		session.setAttribute("CedulaDocente", cedula);
		return cedula;
	}

}
