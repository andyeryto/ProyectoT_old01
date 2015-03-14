package ec.edu.epn.articulos.backingbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ec.edu.epn.articulos.bean.EstadoArticuloService;
import ec.edu.epn.articulos.entities.EstadoA;

@ManagedBean(name = "estadoA")
@SessionScoped
public class EstadoArticuloBackingBean {

	@EJB(lookup = "java:global/ServiciosArticulosEPN/EstadoArticuloServiceBean!ec.edu.epn.articulos.bean.EstadoArticuloService")
	private EstadoArticuloService eas;

	private List<EstadoA> listEstadoArt;
	private EstadoA nuevoEstadoArt;
	private int codEstadoA;
	
	@PostConstruct
	public void init(){
		initEstadoA();
	}
	
	
	public void initEstadoA() {
		nuevoEstadoArt = new EstadoA();

	}

	// /////////////////////////////////////////////////////
	// / Métodos de Estado Articulo
	// /////////////////////////////////////////////////////

	public void buscarEstadoA() {
			setListEstadoArt(eas.findAllEstadoA());			
		}

	public void guardarEstA(ActionEvent event) {
		nuevoEstadoArt.setIdEstadoa(codEstadoA);
		try {

			eas.insertEstadoA(nuevoEstadoArt);
			buscarEstadoA();

			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Estado ingresado"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}

	}

	public List<EstadoA> getListEstadoArt() {
		return listEstadoArt;
	}

	public void setListEstadoArt(List<EstadoA> listEstadoArt) {
		this.listEstadoArt = listEstadoArt;
	}

	public EstadoA getNuevoEstadoArt() {
		return nuevoEstadoArt;
	}

	public void setNuevoEstadoArt(EstadoA nuevoEstadoArt) {
		this.nuevoEstadoArt = nuevoEstadoArt;
	}

	public EstadoArticuloService getEas() {
		return eas;
	}

	public void setEas(EstadoArticuloService eas) {
		this.eas = eas;
	}

	public int getCodEstadoA() {
		return codEstadoA;
	}

	public void setCodEstadoA(int codEstadoA) {
		this.codEstadoA = codEstadoA;
	}

}
