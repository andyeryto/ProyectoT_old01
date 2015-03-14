package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the convocatoria database table.
 * 
 */
@Entity

@Table(name="convocatoria",catalog = "bddcorpepn", schema = "`Articulos`" )
public class Convocatoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_conv")
	private String idConv;

	@Column(name="estado_convo")
	private String estadoConvo;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_cierre")
	private Date fechaCierre;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_llamada")
	private Date fechaLlamada;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_revision")
	private Date fechaRevision;

	@Column(name="nombre_convo")
	private String nombreConvo;

	//bi-directional many-to-one association to Articulo
	@OneToMany(mappedBy="convocatoria")
	private List<Articulo> articulos;

    public Convocatoria() {
    }

	public String getIdConv() {
		return this.idConv;
	}

	public void setIdConv(String idConv) {
		this.idConv = idConv;
	}

	public String getEstadoConvo() {
		return this.estadoConvo;
	}

	public void setEstadoConvo(String estadoConvo) {
		this.estadoConvo = estadoConvo;
	}

	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getFechaLlamada() {
		return this.fechaLlamada;
	}

	public void setFechaLlamada(Date fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	public Date getFechaRevision() {
		return this.fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public String getNombreConvo() {
		return this.nombreConvo;
	}

	public void setNombreConvo(String nombreConvo) {
		this.nombreConvo = nombreConvo;
	}

	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
}