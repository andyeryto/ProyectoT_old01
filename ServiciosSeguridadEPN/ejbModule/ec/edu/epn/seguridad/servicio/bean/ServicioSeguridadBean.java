package ec.edu.epn.seguridad.servicio.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import ec.edu.epn.jdbc.JDBCUtil;
import ec.edu.epn.seguridad.servicio.ServicioSeguridad;
import ec.edu.epn.seguridad.vo.AccesoUrl;
import ec.edu.epn.seguridad.vo.Aplicacion;
import ec.edu.epn.seguridad.vo.Autorizacion;
import ec.edu.epn.seguridad.vo.Menu;
import ec.edu.epn.seguridad.vo.Perfil;
import ec.edu.epn.seguridad.vo.Personal;
import ec.edu.epn.seguridad.vo.Unidad;
import ec.edu.epn.seguridad.vo.Usuario;

@Stateless
public class ServicioSeguridadBean implements ServicioSeguridad{
    @Resource(mappedName="java:jboss/datasources/SeguridadEPNDS")
    DataSource ds;
    @PersistenceContext(unitName="ServiciosSeguridadEPN")
    EntityManager em;
    
    public void guardaAccesoUrl(AccesoUrl accesoUrl) {
    	em.persist(accesoUrl);
    }
    
    public void guardarAplicacion(Aplicacion aplicacion) {
    	em.persist(aplicacion);
    }
    
    public void actualizarAplicacion(Aplicacion aplicacion) {
    	em.merge(aplicacion);
    }
    
    ///////////Saulo///////////////
    public void actualizarMenu(Menu menu){
    	System.out.println("Actualizando");
    	em.merge(menu);
    }
    
    public List<Aplicacion> consultarAplicaciones() {
    	Query q = em.createQuery("Select a from Aplicacion as a");
    	return q.getResultList();
    }
    
    public Usuario obtenerUsuario(String nombreUsuario){
    	Query q = em.createQuery("Select u from Usuario as u where u.nombreUsuario = ?1");
    	q.setParameter(1, nombreUsuario);
    	return (Usuario)q.getSingleResult();
    }
    
    public List<Menu> obtenerMenusPorRol(String rol) {
    	Query q = em.createQuery("select a from Autorizacion as a join fetch a.perfil p join fetch a.menu m join fetch m.aplicacion where p.nombre=?1 and p.activo='SI' and m.activo='SI' order by COALESCE(m.menuPadre.id,0), m.orden");
		q.setParameter(1, rol);
		List<Autorizacion> resultado = q.getResultList();
//		for (int i = 0; i<resultado.size();i++) {
//			Autorizacion a = resultado.get(i);
//			for (int j = 0; j< resultado.size();j++) {
//				Autorizacion b = resultado.get(j);
//				//System.out.println(i!=j);
//				//System.out.println(a.getId()==b.getId() & resultado.indexOf(a)!=resultado.indexOf(b));
//				if(a.getId()==b.getId() ){
//					System.out.println(a.getId()==b.getId());
//					System.out.println("Indice i: "+i+" Indice j: "+j);
//					//System.out.println("ELIMINANDO REPETIDOS: "+resultado.remove(j));
//				}
//			}
//		}
		
		List<Menu> menus = new ArrayList<Menu>();
		for (Autorizacion a : resultado) {
			menus.add(a.getMenu());
			Menu m = a.getMenu();
			m.setPermisos(a.getPermisos());
			if (m.getUrl()!=null){
				if(m.getAplicacion()==null){
					m.setUrlCompleto(m.getUrl());	
				}else {
			        m.setUrlCompleto(m.getAplicacion().getUrl() + m.getUrl());
				}
			}
		}
		return menus;
    }
    
    ///////////Saulo/////////////
    public int getMaxMenuOrdenByMenuPadreAndAplicacion(long idMenuPadre, long idAplicacion){
    	Query q = em.createQuery("select MAX(m.orden) from Menu as m where m.menuPadre.id=?1 and m.aplicacion.id=?2");
		q.setParameter(1, idMenuPadre);
		q.setParameter(2, idAplicacion);
		try{
			return (Integer)q.getSingleResult();
		}catch(Exception e){
			System.out.println("Menu sin elementos");
		}
		return 1;
    }
    
    public List<Perfil> obtenerPerfilesUsuario (String nombreUsuario) {
    	Query q = em.createQuery("select p from Perfil as p, IN (p.usuarios) u where p.activo='SI' and u.nombreUsuario=?1");
		q.setParameter(1, nombreUsuario);
		return q.getResultList(); 
    }
    
    public boolean usuarioTienePerfil(String nombreUsuario, String nombrePerfil){
    	Query q = em.createQuery("select p from Perfil as p, IN (p.usuarios) u where p.activo='SI' and p.nombre=?1 and u.nombreUsuario=?2");
		q.setParameter(1, nombrePerfil);
		q.setParameter(2, nombreUsuario);
    	return q.getResultList().size()>0;
    }
    
    public boolean validarUsuarioPassword(String cedula, String password) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		con = ds.getConnection();
    		ps = con.prepareStatement("select clave = crypt(?,clave) from usuario where nombre_usuario=? ;");
    		ps.setString(1,password);
    		ps.setString(2,cedula);
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			if (rs.getBoolean(1)==true){
    				return true;
    			}
    		}
    		return false;
    	}catch (Exception e){
    		e.printStackTrace();
    		return false;
    	}finally {
    		JDBCUtil.close(rs);
    		JDBCUtil.close(ps);
    		JDBCUtil.close(con);
    	}	
    }
    
    public void actualizarPassword(String cedula, String password) {
    	Connection con=null;
    	PreparedStatement ps=null;
    	try {
    		con = ds.getConnection();
    		ps = con.prepareStatement("update usuario set clave = crypt(?,gen_salt('md5')) where nombre_usuario=?;");
    		ps.setString(1,password);
    		ps.setString(2,cedula);
    		ps.executeUpdate();
       	}catch (Exception e){
    		e.printStackTrace();
    	}finally {
    		JDBCUtil.close(ps);
    		JDBCUtil.close(con);
    	}
    }
    
    
    //********----
    public List<Menu> consultarMenusPorAplicacion(String nomAplicacion){
    	System.out.println("*****-----nom de la aplicacion en el bean:*******------ " 
    			+nomAplicacion);
    	Query q = em.createQuery("select m from Menu as m, IN (m.aplicacion) a  " +
    			"where m.activo='SI' and a.nombre=?1 and m.tipo='menu' ");
		q.setParameter(1, nomAplicacion);
		return q.getResultList();
    }
    
    public Aplicacion obtenerAplicacion(String nomAplicacion){
    	Query q= em.createQuery("select a from Aplicacion as a where a.nombre=?1");
    	q.setParameter(1, nomAplicacion);
    	return (Aplicacion)q.getSingleResult();
    }
    
    public Menu obtenerMenu(String nombreMenu){
    	Query q =em.createQuery("select DISTINCT m from Menu as m where m.nombre=?1");
    	q.setParameter(1,nombreMenu);
    	q.setMaxResults(1);
    	System.out.println("EN EL MENU: "+q.getSingleResult());
    	return  (Menu) q.getSingleResult();
    }
    
    public void guardarMenu(Menu m){
    	System.out.println("*****-----GUARDAR MENU BK------******");
    	//System.out.println("id del Menu: " +m.getId());
    	em.persist(m);
    }

    ///////////Saulo//////////////////
	public long selectMaxIDMenu() {
		Query q =em.createQuery("select MAX(m.id) from Menu as m");
		return (Long)q.getSingleResult();
	}

	///////////Saulo/////////////////
	public List<Menu> consultarOpcionesPorMenu(String aplicacion, String menuPadre) {
		System.out.println("Aplicacion: " + aplicacion + "  Menu padre: "+menuPadre);
    	Query q = em.createQuery("select m from Menu as m where m.aplicacion.nombre=?1 and m.menuPadre.nombre=?2 and m.tipo='opcion'");
		q.setParameter(1, aplicacion);
		q.setParameter(2, menuPadre);
		return q.getResultList();
	}

	public List<Menu> consultarMenusConIgualPadre(String aplicacion,
			String menuPadre) {
		System.out.println("Aplicacion: " + aplicacion + "  Menu padre: "+menuPadre);
    	Query q = em.createQuery("select m from Menu as m where m.aplicacion.nombre=?1 and m.menuPadre.nombre=?2 and m.tipo='menu'");
		q.setParameter(1, aplicacion);
		q.setParameter(2, menuPadre);
		return q.getResultList();
	}

	//////SAULO////////////////
	public void guardarPerfil(Perfil p) {
		em.persist(p);
	}
	
	////////////SAULO/////////////
	public void actualizarPerfil(Perfil p) {
		em.merge(p);
	}

	//////SAULO/////////////////
	public Perfil buscarPerfilPorNombre(String nombre) {
		Query q = em.createQuery("Select p from Perfil as p where p.nombre=?1");
		q.setParameter(1, nombre);
		return (Perfil)q.getSingleResult();
	}

	//////SAULO/////////////////
	public List<Perfil> buscarPerfiles() {
		Query q = em.createQuery("Select p from Perfil as p");
		return q.getResultList();
	}

	//////SAULO/////////////////
	public List<Unidad> getListaUnidades() {
		Query q = em.createQuery("Select u from Unidad as u");
		return q.getResultList();
	}
	
	//////SAULO/////////////////
	public Unidad buscarUnidadPorNombre(String nombreU){
		Query q = em.createQuery("Select u from Unidad as u where u.nombreU=?1");
		q.setParameter(1, nombreU);
		return (Unidad)q.getSingleResult();
	}

	//////SAULO/////////////////
	public List<Personal> getListaPersonal() {
		Query q = em.createQuery("Select per from Personal as per");
		return q.getResultList();
	}

	//////SAULO/////////////////
	public Personal buscarPersonalPorNombre(String nombresPe) {
		Query q = em.createQuery("Select per from Personal as per where per.nombresPe=?1");
		q.setParameter(1, nombresPe);
		return (Personal)q.getSingleResult();
	}

	//////SAULO/////////////////
	public void guardarUsuario(Usuario user) {
		em.persist(user);
	}

	//////SAULO/////////////////
	public void actualizarUsuario(Usuario user) {
		em.merge(user);
	}

	//////SAULO/////////////////
	public Usuario buscarUsuarioPorNombre(String nombre) {
		Query q = em.createQuery("Select user from Usuario as user where user.nombreUsuario=?1");
		q.setParameter(1, nombre);
		return (Usuario)q.getSingleResult();
	}

	//////SAULO/////////////////
	public List<Usuario> getListaUsuarios() {
		Query q = em.createQuery("Select user from Usuario as user");
		return q.getResultList();
	}
	
	//////SAULO/////////////////
	public List<Menu> getListaMenus() {
		Query q = em.createQuery("Select m from Menu as m");
		return q.getResultList();
	}
	
	//////SAULO/////////////////
	public Menu obtenerMenuPorID(Long id) {
		Query q = em.createQuery("Select m from Menu as m where m.id=?1");
		q.setParameter(1, id);
		return (Menu)q.getSingleResult();
	}

	//////SAULO/////////////////
	public Perfil buscarPerfilPorID(Long id) {
		Query q = em.createQuery("Select p from Perfil as p where p.id=?1");
		q.setParameter(1, id);
		return (Perfil)q.getSingleResult();
	}

	//////SAULO/////////////////
	public void guardarAutorizacion(Autorizacion autorizacion) {
		em.persist(autorizacion);
	}

	//////SAULO/////////////////
	public long selectMaxIDAutorizacion() {
		Query q =em.createQuery("select MAX(a.id) from Autorizacion as a");
		return (Long)q.getSingleResult();
	}

	//////SAULO/////////////////
	public List<Autorizacion> getListaAutorizaciones() {
		Query q = em.createQuery("Select a from Autorizacion as a");
		return q.getResultList();
	}

	//////SAULO/////////////////
	public Autorizacion buscarAutorizacionPorID(Long id) {
		Query q = em.createQuery("Select a from Autorizacion as a where a.id=?1");
		q.setParameter(1, id);
		return (Autorizacion)q.getSingleResult();
	}

	//////SAULO/////////////////
	public void actualizarAutorizacion(Autorizacion a) {
		em.merge(a);
	}

	//////SAULO/////////////////
	public Personal buscarPersonalPorID(String id) {
		Query q = em.createQuery("Select per from Personal as per where per.id=?1");
		q.setParameter(1, id);
		return (Personal)q.getSingleResult();
	}

	//////SAULO/////////////////
	public Unidad buscarUnidadPorID(int id) {
		Query q = em.createQuery("Select uni from Unidad as uni where uni.id=?1");
		q.setParameter(1, id);
		return (Unidad)q.getSingleResult();
	}

}
