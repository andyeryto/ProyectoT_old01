package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the revisor_observaciones database table.
 * 
 */
@Entity
@Table(name="revisor_observaciones",catalog = "bddcorpepn", schema = "`Articulos`" )
public class RevisorObservacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_revisorart")
	private Integer idRevisorart;

   
	//@Temporal( TemporalType.DATE)
	@Column(name="fecha_asignacion")
	private String fechaAsignacion;
	
	@Column(name="fechamaxrev")
	private Date fechamaxrev;

	//bi-directional many-to-one association to ObservacionArticulo
	@OneToMany(mappedBy="revisorObservacione")
	private List<ObservacionArticulo> observacionArticulos;

	//bi-directional many-to-one association to Articulo
    @ManyToOne
	@JoinColumn(name="id_articulo")
	private Articulo articulo;

	//bi-directional many-to-one association to Revisor
    @ManyToOne
	@JoinColumn(name="id_rev")
	private Revisor revisor;

    public RevisorObservacione() {
    }

	public Integer getIdRevisorart() {
		return this.idRevisorart;
	}

	public void setIdRevisorart(Integer idRevisorart) {
		this.idRevisorart = idRevisorart;
	}

	public String getFechaAsignacion() {
		return this.fechaAsignacion;
	}

	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public List<ObservacionArticulo> getObservacionArticulos() {
		return this.observacionArticulos;
	}

	public void setObservacionArticulos(List<ObservacionArticulo> observacionArticulos) {
		this.observacionArticulos = observacionArticulos;
	}
	
	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public Revisor getRevisor() {
		return this.revisor;
	}

	public void setRevisor(Revisor revisor) {
		this.revisor = revisor;
	}

	public Date getFechamaxrev() {
		return fechamaxrev;
	}

	public void setFechamaxrev(Date fechamaxrev) {
		this.fechamaxrev = fechamaxrev;
	}
	
}