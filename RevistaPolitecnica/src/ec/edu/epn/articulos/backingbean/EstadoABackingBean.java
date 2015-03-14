package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.epn.articulos.bean.EstadoService;
import ec.edu.epn.articulos.entities.EstadoA;




@ManagedBean(name="estados")
@SessionScoped
public class EstadoABackingBean implements Serializable{

	private static final long serialVersionUID = 6562260089989953264L;
	
	@EJB(lookup="java:global/ServiciosArticulosEPN/EstadoServiceBean!ec.edu.epn.articulos.bean.EstadoService") 
	private EstadoService estadosb;
	
	
	List<EstadoA> listaEstado;
	
	
	
	
	@PostConstruct
	private void init(){
		buscarEstados();
	}
	
	
	
	

	public void buscarEstados()
	{
		
		setListaEstado(estadosb.findAllEstados());
		
		
	}
	
	
	
	
	public List<EstadoA> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<EstadoA> listaEstado) {
		this.listaEstado = listaEstado;
	}
	
	
}
