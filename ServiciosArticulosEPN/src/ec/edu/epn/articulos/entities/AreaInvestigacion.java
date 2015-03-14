package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the area_investigacion database table.
 * 
 */
@Entity
@Table(name="area_investigacion",catalog = "bddcorpepn", schema = "`Articulos`" )
public class AreaInvestigacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_areainv")
	private String idAreainv;

	@Column(name="decripcion_area")
	private String decripcionArea;

	@Column(name="nombre_area")
	private String nombreArea;

	//bi-directional many-to-many association to Revisor
	@ManyToMany(mappedBy="areaInvestigacions")
	private List<Revisor> revisors;

	@OneToMany(mappedBy = "areaInvestigacion")
	private List<Articulo> articulo;

	public AreaInvestigacion() {
    }

	public String getIdAreainv() {
		return this.idAreainv;
	}

	public void setIdAreainv(String idAreainv) {
		this.idAreainv = idAreainv;
	}

	public String getDecripcionArea() {
		return this.decripcionArea;
	}

	public void setDecripcionArea(String decripcionArea) {
		this.decripcionArea = decripcionArea;
	}

	public String getNombreArea() {
		return this.nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public List<Revisor> getRevisors() {
		return this.revisors;
	}

	public void setRevisors(List<Revisor> revisors) {
		this.revisors = revisors;
	}

	public List<Articulo> getArticulo() {
	    return articulo;
	}

	public void setArticulo(List<Articulo> param) {
	    this.articulo = param;
	}

	

	

	

	

	
}