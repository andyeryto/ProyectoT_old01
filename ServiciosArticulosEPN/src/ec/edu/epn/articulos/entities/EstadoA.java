package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the estado_a database table.
 * 
 */
@Entity
@Table(name="estado_a",catalog = "bddcorpepn", schema = "`Articulos`" )
public class EstadoA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estadoa")
	private Integer idEstadoa;

	@Column(name="descripcion_e")
	private String descripcionE;

	@Column(name="nombre_e")
	private String nombreE;

	//bi-directional many-to-one association to Articulo
	@OneToMany(mappedBy="estadoA")
	private List<Articulo> articulos;

    public EstadoA() {
    }

	public Integer getIdEstadoa() {
		return this.idEstadoa;
	}

	public void setIdEstadoa(Integer idEstadoa) {
		this.idEstadoa = idEstadoa;
	}

	public String getDescripcionE() {
		return this.descripcionE;
	}

	public void setDescripcionE(String descripcionE) {
		this.descripcionE = descripcionE;
	}

	public String getNombreE() {
		return this.nombreE;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
}