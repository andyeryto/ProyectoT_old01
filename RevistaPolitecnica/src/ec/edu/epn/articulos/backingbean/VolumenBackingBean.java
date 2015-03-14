package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ec.edu.ec.gestionDocente.entities.Publicacione;
import ec.edu.epn.articulos.bean.ArticuloService;
import ec.edu.epn.articulos.bean.ConvocatoriaService;
import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.Convocatoria;
import ec.edu.epn.articulos.entities.Volumen;

@ManagedBean(name = "volumen")
@SessionScoped
public class VolumenBackingBean implements Serializable {

	private static final long serialVersionUID = -2373515373549642576L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ArticuloServiceBean!ec.edu.epn.articulos.bean.ArticuloService")
	private ArticuloService articulosb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ConvocatoriaServiceBean!ec.edu.epn.articulos.bean.ConvocatoriaService")
	private ConvocatoriaService cs;

	// Atributos de Búsqueda
	private String idArticulo;
	private String palabrasclave;
	private String titulo;
	private Date fechadesde;
	private Date fechaHasta;
	private String idconvocatoria;
	private List<Convocatoria> listaConv;
	private List<Articulo> listaArticulosParametros;
	private Integer idEstado;
	private Articulo articuloInfo;
	private List<Volumen> listavolumen;
	private Volumen nuevovolumen; 
	private Integer idvolumen;
	private List<Articulo> listaArticulosVolumen;
	private Volumen volumenSelect;
	private List<Volumen> ListaVol;

	

	
	

	



	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		getListaConvocatorias();
		getListaVolumenes();
		initvolumen();

	}

	
	
	public void initvolumen()
	{
		nuevovolumen = new Volumen();
		volumenSelect = new Volumen();
		
		
	}
	
	
	
	public void buscarArticulosParametros() {
		System.out.println("Entro al metodo");
		
		setIdEstado(2);
		listaArticulosParametros = articulosb.findarticulobyParams(
				idArticulo.trim(), palabrasclave.trim(), titulo.trim(),
				fechadesde, fechaHasta, getIdEstado());

		if (listaArticulosParametros.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}
	}
	
	
	public void buscarArticulosporVolumen() {
		System.out.println("Entro al metodo");
		
		
		listaArticulosVolumen = articulosb.findarticuloVolumen(idvolumen);
		
		volumenSelect= cs.findVolumenselect(idvolumen);
		 
		
		

		if (listaArticulosVolumen.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void getListaConvocatorias() {
		System.out.println("METODO CONV");
		setListaConv(cs.findAllConvocatorias());

	}
	
	
	public void getListaVolumenes() {
		System.out.println("METODO vol");
		setListavolumen(cs.findAllVolumenes());

	}
	
	
	//Artualizar Articulo
		public void actualizarArticulo(ActionEvent event) {
			try {
				
				
				System.out.println("Id volumen  "+nuevovolumen.getIdVol());

				System.out.println("Nombre Vol  "+nuevovolumen.getNombreVol());
				
				articuloInfo.setVolumen(nuevovolumen);
				articulosb.updateArticuloDocente(articuloInfo);
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"Este artículo se publicará en el "+ articuloInfo.getVolumen().getNombreVol()+ " de la Revista Politécnica" ));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"A ocurrido un error"));
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<Articulo> getListaArticulosParametros() {
		return listaArticulosParametros;
	}

	public void setListaArticulosParametros(
			List<Articulo> listaArticulosParametros) {
		this.listaArticulosParametros = listaArticulosParametros;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getPalabrasclave() {
		return palabrasclave;
	}

	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getIdconvocatoria() {
		return idconvocatoria;
	}

	public void setIdconvocatoria(String idconvocatoria) {
		this.idconvocatoria = idconvocatoria;
	}

	public List<Convocatoria> getListaConv() {
		return listaConv;
	}

	public void setListaConv(List<Convocatoria> listaConv) {
		this.listaConv = listaConv;
	}
	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}





	public Articulo getArticuloInfo() {
		return articuloInfo;
	}





	public void setArticuloInfo(Articulo articuloInfo) {
		this.articuloInfo = articuloInfo;
	}





	public List<Volumen> getListavolumen() {
		return listavolumen;
	}





	public void setListavolumen(List<Volumen> listavolumen) {
		this.listavolumen = listavolumen;
	}





	public Volumen getNuevovolumen() {
		return nuevovolumen;
	}





	public void setNuevovolumen(Volumen nuevovolumen) {
		this.nuevovolumen = nuevovolumen;
	}



	public Integer getIdvolumen() {
		return idvolumen;
	}



	public void setIdvolumen(Integer idvolumen) {
		this.idvolumen = idvolumen;
	}



	public List<Articulo> getListaArticulosVolumen() {
		return listaArticulosVolumen;
	}



	public void setListaArticulosVolumen(List<Articulo> listaArticulosVolumen) {
		this.listaArticulosVolumen = listaArticulosVolumen;
	}



	public Volumen getVolumenSelect() {
		return volumenSelect;
	}



	public void setVolumenSelect(Volumen volumenSelect) {
		this.volumenSelect = volumenSelect;
	}
	
	
	public List<Volumen> getListaVol() {
		return ListaVol;
	}



	public void setListaVol(List<Volumen> listaVol) {
		ListaVol = listaVol;
	}
}
