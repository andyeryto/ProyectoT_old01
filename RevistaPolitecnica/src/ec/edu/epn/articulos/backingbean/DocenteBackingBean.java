package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.epn.articulos.bean.UsuarioService;
import ec.edu.epn.rrhh.entities.Emp;
import ec.edu.epn.rrhh.servicio.EmpleadoService;
import ec.edu.epn.seguridad.VO.SesionUsuario;

import javax.ejb.EJB;

@ManagedBean (name= "docenteBackingBean")
@SessionScoped
public class DocenteBackingBean implements Serializable{
	
	private static final long serialVersionUID = -5116891953251509058L;
	
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");


	
	@EJB(lookup="java:global/ServiciosRRHHEPN/EmpleadoServiceBean!ec.edu.epn.rrhh.servicio.EmpleadoService")
	private EmpleadoService esb;
	@EJB(lookup="java:global/ServiciosArticulosEPN/UsuarioServiceBean!ec.edu.epn.articulos.bean.UsuarioService")
	private UsuarioService usb;
	
	public DocenteBackingBean() {
		super();
	}

	public List<Emp> getDocente(){
		int cedula = usb.buscarCedulaUsuario((int)su.id_usuario_log);
		System.out.println(cedula);
		List<Emp> list = esb.findEmpleadoByParameters(Integer.toString(cedula), null);
		System.out.println("Tamaño de la lista "+ list.size());
		return list;
	}

	

}
