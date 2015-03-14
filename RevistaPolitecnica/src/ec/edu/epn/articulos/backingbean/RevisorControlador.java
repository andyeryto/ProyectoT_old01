  package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ec.edu.epn.articulos.bean.RevisorService;
import ec.edu.epn.articulos.entities.Revisor;
import ec.edu.epn.articulos.entities.TipoRev;

@ManagedBean
@SessionScoped
public class RevisorControlador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4823466909336462131L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/RevisorServiceBean!ec.edu.epn.articulos.bean.RevisorService")
	private RevisorService revisorSb;

	private Revisor revisorIngreso;
	private Revisor revisorModificacion;
	private List<TipoRev> listaTipoRevisores;
	private String revisorSeleccionado;
	private String identificacionConsulta;
	private String nombresBusqueda;
	private String lugarConsulta;
	private List<Revisor> listaRevisores;

	public RevisorControlador() {
		revisorIngreso = new Revisor();
		revisorModificacion = new Revisor();
		listaTipoRevisores = new ArrayList<TipoRev>();
		listaRevisores = new ArrayList<Revisor>();
	}

	public Revisor getRevisorIngreso() {

		return revisorIngreso;
	}

	public void setRevisorIngreso(Revisor revisorIngreso) {
		this.revisorIngreso = revisorIngreso;
	}

	public Revisor getRevisorModificacion() {
		return revisorModificacion;
	}

	public void setRevisorModificacion(Revisor revisorModificacion) {
		this.revisorModificacion = revisorModificacion;
	}

	public String getRevisorSeleccionado() {
		return revisorSeleccionado;
	}

	public void setRevisorSeleccionado(String revisorSeleccionado) {
		this.revisorSeleccionado = revisorSeleccionado;
	}

	public String getIdentificacionConsulta() {
		return identificacionConsulta;
	}

	public void setIdentificacionConsulta(String identificacionConsulta) {
		this.identificacionConsulta = identificacionConsulta;
	}

	public String getNombresBusqueda() {
		return nombresBusqueda;
	}

	public void setNombresBusqueda(String nombresBusqueda) {
		this.nombresBusqueda = nombresBusqueda;
	}

	public String getLugarConsulta() {
		return lugarConsulta;
	}

	public void setLugarConsulta(String lugarConsulta) {
		this.lugarConsulta = lugarConsulta;
	}

	public List<TipoRev> getListaTipoRevisores() {
		return listaTipoRevisores;
	}

	public void setListaTipoRevisores(List<TipoRev> listaTipoRevisores) {
		this.listaTipoRevisores = listaTipoRevisores;
	}

	public List<Revisor> getListaRevisores() {
		return listaRevisores;
	}

	public void setListaRevisores(List<Revisor> listaRevisores) {
		this.listaRevisores = listaRevisores;
	}

	@PostConstruct
	public void inicializarComponentes() {

		listaTipoRevisores = revisorSb.finfAllTiposRevisor();
		// nuevoRevisor();
	}

	public void grabarRevisor(ActionEvent evt) {
		revisorIngreso.setTipoRev(revisorSb.findTipoRevisor(Integer
				.parseInt(revisorSeleccionado)));

		try {
			if (revisorSb.isRevisorRegistered(revisorIngreso
					.getNidentificacion())) {
				System.out.println("ENtro al if");
				FacesContext
						.getCurrentInstance()
						.addMessage(
								evt.getComponent().getClientId(),
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error",
										"La Identificacion ingresada corresponde a otro revisor ya registrado"));
				throw new IllegalArgumentException();
			}
			if (!validarEmail(revisorIngreso.getEmail())) {
				System.out.println("If para validar email");
				FacesContext.getCurrentInstance().addMessage(
						evt.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"El mail ingresado es incorrecto"));

				throw new IllegalArgumentException();
			}

			revisorSb.guardarRevisor(revisorIngreso);
			nuevoRevisor();
			FacesContext.getCurrentInstance().addMessage(
					evt.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Datos de revisor ingresados"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					evt.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}
	}

	public void nuevoRevisor() {

		revisorIngreso = new Revisor();
	}

	public boolean validarEmail(String email) {

		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean esMailValido = email.matches(EMAIL_REGEX);
		return esMailValido;
	}

	public String toRegistroRevisor() {

		nuevoRevisor();
		revisorSeleccionado = null;
		return "registroRevisor";
	}

	public void eliminarRevisor(ActionEvent event) {

		try {
			revisorSb.eliminarRevisor(revisorModificacion);
			listaRevisores.remove(revisorModificacion);
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Datos de revisor eliminados"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}
	}
     
	
	
	
	public String toModificacionRevisor() {
		revisorSeleccionado = ""
				+ revisorModificacion.getTipoRev().getIdTiporev();
		return "modificarRevisor";
	}

	public void buscarRevisor() {

		System.out.println("identificaion: " + identificacionConsulta
				+ " Nombres " + nombresBusqueda + " lugar: " + lugarConsulta);
		listaRevisores = revisorSb.findRevisosrByParams(identificacionConsulta
				.equals("") ? null : identificacionConsulta.trim(),
				nombresBusqueda.equals("") ? null : nombresBusqueda.trim()
						.split("[ ]"), lugarConsulta.equals("") ? null
						: lugarConsulta.trim());

	}

	public void modificarRevisor(ActionEvent event) {
		System.out.println("Entra al metodo actualiuzar Revisor");
		revisorModificacion.setTipoRev(revisorSb.findTipoRevisor(Integer
				.parseInt(revisorSeleccionado)));

		try {
			revisorSb.updateRevisor(revisorModificacion);
			revisorModificacion = new Revisor();
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),

					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Datos de revisor actualizados"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}

	}

}
