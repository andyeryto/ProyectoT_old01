package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import ec.edu.epn.articulos.bean.EstadoArticuloService;
import ec.edu.epn.articulos.bean.ObservacionArticuloService;
import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.Convocatoria;
import ec.edu.epn.articulos.entities.EstadoA;
import ec.edu.epn.articulos.entities.ObservacionArticulo;
import ec.edu.epn.gestionDocente.beans.PublicacionesService;

@ManagedBean(name = "aprovac_Articulo")
@SessionScoped
public class AprobacionArticuloBackingBean implements Serializable

{
	private static final long serialVersionUID = 780200781223624080L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ObservacionArticuloServiceBean!ec.edu.epn.articulos.bean.ObservacionArticuloService")
	private ObservacionArticuloService observArticsb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ConvocatoriaServiceBean!ec.edu.epn.articulos.bean.ConvocatoriaService")
	private ConvocatoriaService cs;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ArticuloServiceBean!ec.edu.epn.articulos.bean.ArticuloService")
	private ArticuloService articulosb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/EstadoArticuloServiceBean!ec.edu.epn.articulos.bean.EstadoArticuloService")
	private EstadoArticuloService estadoSb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/PublicacionesServiceBean!ec.edu.epn.gestionDocente.beans.PublicacionesService")
	private PublicacionesService publicacionsb;
	 
	 
	 
	 
	// Atributos para la Busqueda
	private String idArticulo;
	private String palabrasclave;
	private String titulo;
	private Date fechadesde;
	private Date fechaHasta;
	private Integer idEstado;
	private String idconvocatoria;
	private List<Articulo> revisor_observaciones;
	private List<Convocatoria> listaConv;
	private Articulo articuloInfo;
	private List<ObservacionArticulo> obsr_Articulo;
	private List<ObservacionArticulo> obsrSi;
	private EstadoA estado;
	private Publicacione publicacion;

	

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		getListaConvocatorias();
		initpublicacion();

	}

	public void buscarObservacionArticulo() {
		obsr_Articulo = observArticsb.findObservacionArticulo(articuloInfo
				.getAuxAutogenerado());
		

		System.out.println("Id del articulo = " + articuloInfo.getIdArticulo());

		if (obsr_Articulo.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}

	}

	public void initpublicacion()
	{
		publicacion = new Publicacione();
		
	}
	
	public void buscarObservaArticulo() {
		System.out.println("Entro a buscar Observ Rev");

		revisor_observaciones = observArticsb.findRev_ObservacionesByParams(
				idArticulo.trim(), palabrasclave.trim(), titulo.trim(),
				fechadesde, fechaHasta, idEstado, idconvocatoria.trim());

		if (revisor_observaciones.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}

	}

	public void getListaConvocatorias() {
		System.out.println("METODO CONV");
		listaConv = cs.findAllConvocatorias();

	}

	public void calificararticulo(ActionEvent event) {
		System.out.println("Entro a calificar articulo");

		System.out.println("Id del articulo = " + articuloInfo.getIdArticulo());
		Integer idarticulo = articuloInfo.getIdArticulo();

		obsrSi = articulosb.observacionesSI(idarticulo);

		System.out.println("Tamaño de la lista que dicen que si"
				+ obsrSi.size());

		Integer idestado;
		if (obsrSi.size() >= 2) {
			idestado = 2;
			System.out.println(idestado);

			estado = estadoSb.findTipoEstado(idestado);
			System.out.println("Estado a tener" + estado.getNombreE());

			try {
				
				Date fecha = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String fechaAsig = sdf.format(fecha);
				
				
				articuloInfo.setEstadoA(estado);
				articulosb.updateArticulo(articuloInfo);
				buscarObservaArticulo(); 
				
				System.out.println("Voy a insertar publicacion");
				
				
				publicacion.setAutores(articuloInfo.getEmp_1().getNom()+" "+articuloInfo.getEmp_1().getApel());
				
				System.out.println("Autores " + publicacion.getAutores());
				
				publicacion.setTituloLibro(articuloInfo.getTituloArticulo());
				publicacion.setTituloPublic(articuloInfo.getTituloArticulo());
				publicacion.setIdTipoPublic("1");
				publicacion.setNced(articuloInfo.getNcedArticulista());
				publicacion.setFechaPublic(fechaAsig);
				publicacion.setDescripcionPublic(articuloInfo.getBreveResumen());
				publicacion.setTipoActividad("Normal");
				publicacion.setMedio("REVISTA POLITÉCNICA");
				publicacion.setTipo("NACIONAL");
				publicacion.setPais("ECUADOR");
				publicacion.setIdPeriodo("2013-1");
				publicacion.setTipoindex("INDEXADA");
				publicacion.setEstado("A");
				publicacion.setValidacion("A");
				publicacion.setCodAreaCon(articuloInfo.getAreaInvestigacion().getIdAreainv());
				
				
				
				System.out.println("Voy a insertar publicacion");
				
				publicacionsb.insertPublicacionDocente(publicacion);	
				FacesContext
						.getCurrentInstance()
						.addMessage(
								event.getComponent().getClientId(),
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Info",
										"El artículo ha sido aprobado para su publicación y se ha añadido a la hoja de vida del docente "
										+ articuloInfo.getEmp_1().getNom()+" "+articuloInfo.getEmp_1().getApel()));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"A ocurrido un error"));
			}

		} else {
			idestado = 3;
			estado = estadoSb.findTipoEstado(idestado);
			System.out.println("Estado a tener" + estado.getNombreE());
			

			try {
				articuloInfo.setEstadoA(estado);
				articulosb.updateArticulo(articuloInfo);
				buscarObservaArticulo();

				FacesContext
						.getCurrentInstance()
						.addMessage(
								event.getComponent().getClientId(),
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Info",
										"El artículo ha sido rechazado"));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"A ocurrido un error"));
			}
		}

		// String mail = revisorInfo.getEmail();
		// System.out.println(mail);
		//
		// List<String> para = new ArrayList<String>();
		// para.add(mail);
		//
		//
		//
		// String de= "<roberto.garcia@epn.edu.ec>";
		// String subject = "Asignación de Revisión";
		// String text = "Se le ha asignado para que revise el artículo " +
		// aticuloinfo.getTituloArticulo() +
		// " con fecha máxima de revisión hasta" + fecmaMaxMail;
		//
		// // System.out.println(de+ subject+para);
		//
		//
		// correo.correoObtener(de, para, subject, text);
		//
		//
		// nuevorevObs.setFechamaxrev(getFechaMAxRev());
		//
		// nuevorevObs.setFechaAsignacion(fechaAsig);
		//
		// nuevorevObs.setArticulo(aticuloinfo);
		// nuevorevObs.setRevisor(revisorInfo);
		//
		// ListarevisorExiste = revisorsb.getRevisorArticuloExiste(
		// aticuloinfo.getIdArticulo(), revisorInfo.getIdRev());
		//
		// System.out.println("Tamaño de la listaaa" +
		// ListarevisorExiste.size());
		//
		// if (ListarevisorExiste.size() == 0) {
		// try {
		//
		// revisorsb.guardarRevisorObservaciones(nuevorevObs);
		// initArticulo();
		// buscarRevisorArticulos();
		//
		// FacesContext.getCurrentInstance().addMessage(
		// event.getComponent().getClientId(),
		// new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
		// "Revisor Asignado al artículo exitosamente"));
		// }
		//
		// catch (Exception e) {
		// FacesContext.getCurrentInstance().addMessage(
		// event.getComponent().getClientId(),
		// new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
		// "A ocurrido un error"));
		// }
		// }
		//
		// else {
		//
		// FacesContext
		// .getCurrentInstance()
		// .addMessage(
		// event.getComponent().getClientId(),
		// new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// "Error",
		// "Este revisor ya se halla asignado para el artículo"));
		//
		// }

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

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdconvocatoria() {
		return idconvocatoria;
	}

	public void setIdconvocatoria(String idconvocatoria) {
		this.idconvocatoria = idconvocatoria;
	}

	public List<Articulo> getRevisor_observaciones() {
		return revisor_observaciones;
	}

	public void setRevisor_observaciones(List<Articulo> revisor_observaciones) {
		this.revisor_observaciones = revisor_observaciones;
	}

	public List<Convocatoria> getListaConv() {
		return listaConv;
	}

	public void setListaConv(List<Convocatoria> listaConv) {
		this.listaConv = listaConv;
	}

	public Articulo getArticuloInfo() {
		return articuloInfo;
	}

	public void setArticuloInfo(Articulo articuloInfo) {
		this.articuloInfo = articuloInfo;
	}

	public List<ObservacionArticulo> getObsr_Articulo() {
		return obsr_Articulo;
	}

	public void setObsr_Articulo(List<ObservacionArticulo> obsr_Articulo) {
		this.obsr_Articulo = obsr_Articulo;
	}

	public List<ObservacionArticulo> getObsrSi() {
		return obsrSi;
	}

	public void setObsrSi(List<ObservacionArticulo> obsrSi) {
		this.obsrSi = obsrSi;
	}

	public EstadoA getEstado() {
		return estado;
	}

	public void setEstado(EstadoA estado) {
		this.estado = estado;
	}
	public Publicacione getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacione publicacion) {
		this.publicacion = publicacion;
	}

}
