package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tipo_rev database table.
 * 
 */
@Entity
@Table(name="tipo_rev",catalog = "bddcorpepn", schema = "`Articulos`" )
public class TipoRev implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tiporev")
	private Integer idTiporev;

	@Column(name="descripcion_trev")
	private String descripcionTrev;

	@Column(name="nombre_trev")
	private String nombreTrev;

	//bi-directional many-to-one association to Revisor
	@OneToMany(mappedBy="tipoRev")
	private List<Revisor> revisors;

    public TipoRev() {
    }

	public Integer getIdTiporev() {
		return this.idTiporev;
	}

	public void setIdTiporev(Integer idTiporev) {
		this.idTiporev = idTiporev;
	}

	public String getDescripcionTrev() {
		return this.descripcionTrev;
	}

	public void setDescripcionTrev(String descripcionTrev) {
		this.descripcionTrev = descripcionTrev;
	}

	public String getNombreTrev() {
		return this.nombreTrev;
	}

	public void setNombreTrev(String nombreTrev) {
		this.nombreTrev = nombreTrev;
	}

	public List<Revisor> getRevisors() {
		return this.revisors;
	}

	public void setRevisors(List<Revisor> revisors) {
		this.revisors = revisors;
	}
	
}