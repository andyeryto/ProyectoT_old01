package ec.edu.epn.seguridad.servicio;

import java.util.List;

import javax.ejb.Local;

//import ec.edu.epn.seguridad.vo.AccesoParametro;
import ec.edu.epn.seguridad.vo.AccesoUrl;
import ec.edu.epn.seguridad.vo.Aplicacion;
import ec.edu.epn.seguridad.vo.Autorizacion;
import ec.edu.epn.seguridad.vo.Menu;
import ec.edu.epn.seguridad.vo.Perfil;
import ec.edu.epn.seguridad.vo.Personal;
import ec.edu.epn.seguridad.vo.Unidad;
import ec.edu.epn.seguridad.vo.Usuario;

@Local
public interface ServicioSeguridad {
    public List<Perfil> obtenerPerfilesUsuario (String nombreUsuario);
    public boolean usuarioTienePerfil(String nombreUsuario, String nombrePerfil);
	public boolean validarUsuarioPassword(String cedula, String password);
    public void actualizarPassword(String cedula, String password);
    public List<Menu> obtenerMenusPorRol(String rol);
    public Usuario obtenerUsuario(String nombreUsuario);
    public void guardaAccesoUrl(AccesoUrl accesoUrl);
    
    public void guardarAplicacion(Aplicacion aplicacion);
    public void actualizarAplicacion(Aplicacion aplicacion);
    public List<Aplicacion> consultarAplicaciones();
    
    public List<Menu> consultarMenusPorAplicacion(String nomAplicacion);
    public Aplicacion obtenerAplicacion(String nomAplicacion);
    public void actualizarMenu(Menu menu);
    public Menu obtenerMenu(String nombreMenu);
    public Menu obtenerMenuPorID(Long id); 
    public void guardarMenu(Menu m);
    public List<Menu> getListaMenus();
    
    public void guardarPerfil(Perfil p);
    public void actualizarPerfil(Perfil p);
    public Perfil buscarPerfilPorNombre(String nombre);
    public Perfil buscarPerfilPorID(Long id);
    public List<Perfil> buscarPerfiles();
    
    public int getMaxMenuOrdenByMenuPadreAndAplicacion(long idMenuPadre, long idAplicacion);
    public List<Menu> consultarOpcionesPorMenu(String aplicacion, String menuPadre);
    public List<Menu> consultarMenusConIgualPadre(String aplicacion, String menuPadre);
    public long selectMaxIDMenu();
    
    
    public void guardarUsuario(Usuario user);
    public void actualizarUsuario(Usuario user);
    public Usuario buscarUsuarioPorNombre(String nombre);
    public List<Usuario> getListaUsuarios();
    
    public List<Unidad> getListaUnidades();
    public Unidad buscarUnidadPorNombre(String nombreU);
    public Unidad buscarUnidadPorID(int id);
    
    public List<Personal> getListaPersonal();
    public Personal buscarPersonalPorNombre(String nombresPe);
    public Personal buscarPersonalPorID(String id);
    
    public void guardarAutorizacion(Autorizacion autorizacion);
    public List<Autorizacion> getListaAutorizaciones();
    public Autorizacion buscarAutorizacionPorID(Long id);
    public void actualizarAutorizacion(Autorizacion a);
    public long selectMaxIDAutorizacion();
}	
	

