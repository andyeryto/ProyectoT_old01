package ec.edu.epn.articulos.backingbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ec.edu.epn.articulos.bean.ArticuloService;
import ec.edu.epn.articulos.bean.RevisorService;
import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.Revisor;
import ec.edu.epn.articulos.entities.RevisorObservacione;
import ec.edu.epn.articulos.mail.correoVO;

@ManagedBean(name = "articulos")
@SessionScoped
public class ArticuloBackingBean implements Serializable {

	private static final long serialVersionUID = 7743629655022473192L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ArticuloServiceBean!ec.edu.epn.articulos.bean.ArticuloService")
	private ArticuloService articulosb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/RevisorServiceBean!ec.edu.epn.articulos.bean.RevisorService")
	private RevisorService revisorsb;

	private String idArticulo;
	private String palabrasclave;
	private String titulo;
	private Date fechadesde;
	private Date fechaHasta;
	private Integer idEstado;
	private List<Articulo> listaArticulosParametros;
	private Articulo aticuloinfo;
	private List<RevisorObservacione> listarevisores;
	private List<Revisor> listaRevisor;
	private String cedulaRevisor;
	private String apellidosRevisor;
	private String nombresRevisor;
	private Revisor revisorInfo;
	private RevisorObservacione nuevorevObs;
	private List<RevisorObservacione> ListarevisorExiste;
	private Date fechaMAxRev;
	private correoVO correo;

	// Metodos de 
	@PostConstruct
	private void init() {
		initArticulo();

		// sesionUsuario = findBean("sesionUsuario");
	}

	public void buscarArticulosParametros() {
		System.out.println("Entro al metodo");
		listaArticulosParametros = articulosb.findarticulobyParams(idArticulo.trim(),
				palabrasclave.trim(), titulo.trim(), fechadesde, fechaHasta,
				idEstado);

		if (listaArticulosParametros.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}
	}

	public void buscarRevisorArticulos() {

		System.out.println("Entro al metodonde buscar revisores por articulo");

		setListarevisores(revisorsb.getRevisorObservaciones(aticuloinfo
				.getIdArticulo()));

	}

	public void buscarRevisoresParametros() {

		System.out.println("Entro a bbuscarRevisores ");
		setListaRevisor(revisorsb.findRevisorbyParams(
				cedulaRevisor.equals("") ? null : cedulaRevisor.trim(),
				nombresRevisor.equals("") ? null : nombresRevisor.trim(),
				apellidosRevisor.equals("") ? null : apellidosRevisor.trim()));

	}

	public void initArticulo() {
		nuevorevObs = new RevisorObservacione();
		correo = new correoVO();
		

	}

	public void guardarRevisor(ActionEvent event) {
		System.out.println("Entro a guardar revisoress");

		Date fecha = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String fechaAsig = sdf.format(fecha);
		System.out.println("Fecha max rev" + getFechaMAxRev());
		
		
		String fecmaMaxMail =sdf.format(getFechaMAxRev());
		
		String mail = revisorInfo.getEmail();
		System.out.println(mail);
		
		List<String> para = new ArrayList<String>();
				para.add(mail);
		
		
		
		String de= "<roberto.garcia@epn.edu.ec>";
		String subject = "Asignación de Revisión";
		
		String text = "Se le ha asignado para que revise el artículo " + aticuloinfo.getTituloArticulo() +
				" con fecha máxima de revisión hasta" + fecmaMaxMail;
		
//		System.out.println(de+ subject+para);
		
		
		correo.correoObtener(de, para, subject, text);
		

		nuevorevObs.setFechamaxrev(getFechaMAxRev());

		nuevorevObs.setFechaAsignacion(fechaAsig);

		nuevorevObs.setArticulo(aticuloinfo);
		nuevorevObs.setRevisor(revisorInfo);

		ListarevisorExiste = revisorsb.getRevisorArticuloExiste(
				aticuloinfo.getIdArticulo(), revisorInfo.getIdRev());

		System.out.println("Tamaño de la listaaa" + ListarevisorExiste.size());

		if (ListarevisorExiste.size() == 0) {
			try {

				revisorsb.guardarRevisorObservaciones(nuevorevObs);
				initArticulo();
				buscarRevisorArticulos();

				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"Revisor Asignado al artículo exitosamente"));
			}

			catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"A ocurrido un error"));
			}
		}

		else {

			FacesContext
					.getCurrentInstance()
					.addMessage(
							event.getComponent().getClientId(),
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Este revisor ya se halla asignado para el artículo"));

		}

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

	public List<Articulo> getListaArticulosParametros() {
		return listaArticulosParametros;
	}

	public void setListaArticulosParametros(
			List<Articulo> listaArticulosParametros) {
		this.listaArticulosParametros = listaArticulosParametros;
	}

	public Articulo getAticuloinfo() {
		return aticuloinfo;
	}

	public void setAticuloinfo(Articulo aticuloinfo) {
		this.aticuloinfo = aticuloinfo;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public List<RevisorObservacione> getListarevisores() {
		return listarevisores;
	}

	public void setListarevisores(List<RevisorObservacione> listarevisores) {
		this.listarevisores = listarevisores;
	}

	public List<Revisor> getListaRevisor() {
		return listaRevisor;
	}

	public void setListaRevisor(List<Revisor> listaRevisores) {
		this.listaRevisor = listaRevisores;
	}

	public String getCedulaRevisor() {
		return cedulaRevisor;
	}

	public void setCedulaRevisor(String cedulaRevisor) {
		this.cedulaRevisor = cedulaRevisor;
	}

	public String getApellidosRevisor() {
		return apellidosRevisor;
	}

	public void setApellidosRevisor(String apellidosRevisor) {
		this.apellidosRevisor = apellidosRevisor;
	}

	public String getNombresRevisor() {
		return nombresRevisor;
	}

	public void setNombresRevisor(String nombresRevisor) {
		this.nombresRevisor = nombresRevisor;
	}

	public Revisor getRevisorInfo() {
		return revisorInfo;
	}

	public void setRevisorInfo(Revisor revisorInfo) {
		this.revisorInfo = revisorInfo;
	}

	public RevisorObservacione getNuevorevObs() {
		return nuevorevObs;
	}

	public void setNuevorevObs(RevisorObservacione nuevorevObs) {
		this.nuevorevObs = nuevorevObs;
	}

	public List<RevisorObservacione> getListarevisorExiste() {
		return ListarevisorExiste;
	}

	public void setListarevisorExiste(
			List<RevisorObservacione> listarevisorExiste) {
		ListarevisorExiste = listarevisorExiste;
	}

	public Date getFechaMAxRev() {
		return fechaMAxRev;
	}

	public void setFechaMAxRev(Date fechaMAxRev) {
		this.fechaMAxRev = fechaMAxRev;
	}

	public correoVO getCorreo() {
		return correo;
	}

	public void setCorreo(correoVO correo) {
		this.correo = correo;
	}

}
