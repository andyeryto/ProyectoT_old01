package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the observacion_articulo database table.
 * 
 */
@Entity
@Table(name="observacion_articulo",catalog = "bddcorpepn", schema = "`Articulos`" )
public class ObservacionArticulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_observacion")
	private Integer idObservacion;

	private String estado;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private String observacion;

	//bi-directional many-to-one association to RevisorObservacione
    @ManyToOne
	@JoinColumn(name="id_revisorart")
	private RevisorObservacione revisorObservacione;

    public ObservacionArticulo() {
    }

	public Integer getIdObservacion() {
		return this.idObservacion;
	}

	public void setIdObservacion(Integer idObservacion) {
		this.idObservacion = idObservacion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public RevisorObservacione getRevisorObservacione() {
		return this.revisorObservacione;
	}

	public void setRevisorObservacione(RevisorObservacione revisorObservacione) {
		this.revisorObservacione = revisorObservacione;
	}
	
}