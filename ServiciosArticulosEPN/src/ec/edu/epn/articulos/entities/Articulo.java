package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.edu.epn.rrhh.entities.Emp;

/**
 * The persistent class for the articulo database table.
 * 
 */
@Entity
@Table(name = "articulo", catalog = "bddcorpepn", schema = "`Articulos`")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_articulo")
	private Integer idArticulo;

	@Column(name = "breve_resumen")
	private String breveResumen;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_envio")
	private Date fechaEnvio;

	@Column(name = "nced_articulista")
	private String ncedArticulista;

	@Column(name = "palabras_clave")
	private String palabrasClave;

	@Column(name = "participacion_articulo")
	private String participacionArticulo;

	@Column(name = "path_documento")
	private String pathDocumento;

	@Column(name = "titulo_articulo")
	private String tituloArticulo;

	@Column(name = "aux_autogenerado")
	private String auxAutogenerado;

	@Column(name = "tipo_documento")
	private String tipoDocumento;

	// bi-directional many-to-one association to Convocatoria
	@ManyToOne
	@JoinColumn(name = "id_conv")
	private Convocatoria convocatoria;

	// bi-directional many-to-one association to EstadoA
	@ManyToOne
	@JoinColumn(name = "id_estadoa")
	private EstadoA estadoA;

	// bi-directional many-to-one association to RevisorObservacione
	@OneToMany(mappedBy = "articulo")
	private List<RevisorObservacione> revisorObservaciones;

	@JoinColumn(name = "id_areainv")
	@ManyToOne
	private AreaInvestigacion areaInvestigacion;

	
	@JoinColumn(name = "id_vol")
	@ManyToOne
	private Volumen volumen;
	
	
	
	@ManyToOne
	@JoinColumn(name = "nced_emp")
	private Emp emp_1;

	public Articulo() {
	}

	public Integer getIdArticulo() {
		return this.idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getBreveResumen() {
		return this.breveResumen;
	}

	public void setBreveResumen(String breveResumen) {
		this.breveResumen = breveResumen;
	}

	public Date getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getNcedArticulista() {
		return this.ncedArticulista;
	}

	public void setNcedArticulista(String ncedArticulista) {
		this.ncedArticulista = ncedArticulista;
	}

	public String getPalabrasClave() {
		return this.palabrasClave;
	}

	public void setPalabrasClave(String palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

	public String getParticipacionArticulo() {
		return this.participacionArticulo;
	}

	public void setParticipacionArticulo(String participacionArticulo) {
		this.participacionArticulo = participacionArticulo;
	}

	public String getPathDocumento() {
		return this.pathDocumento;
	}

	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
	}

	public String getTituloArticulo() {
		return this.tituloArticulo;
	}

	public void setTituloArticulo(String tituloArticulo) {
		this.tituloArticulo = tituloArticulo;
	}

	public Convocatoria getConvocatoria() {
		return this.convocatoria;
	}

	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}

	public EstadoA getEstadoA() {
		return this.estadoA;
	}

	public void setEstadoA(EstadoA estadoA) {
		this.estadoA = estadoA;
	}

	public List<RevisorObservacione> getRevisorObservaciones() {
		return this.revisorObservaciones;
	}

	public void setRevisorObservaciones(
			List<RevisorObservacione> revisorObservaciones) {
		this.revisorObservaciones = revisorObservaciones;
	}

	public AreaInvestigacion getAreaInvestigacion() {
		return areaInvestigacion;
	}

	public void setAreaInvestigacion(AreaInvestigacion param) {
		this.areaInvestigacion = param;
	}

	public Emp getEmp_1() {
		return emp_1;
	}

	public void setEmp_1(Emp param) {
		this.emp_1 = param;
	}

	public String getAuxAutogenerado() {
		return auxAutogenerado;
	}

	public void setAuxAutogenerado(String auxAutogenerado) {
		this.auxAutogenerado = auxAutogenerado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Volumen getVolumen() {
		return volumen;
	}

	public void setVolumen(Volumen volumen) {
		this.volumen = volumen;
	}

}