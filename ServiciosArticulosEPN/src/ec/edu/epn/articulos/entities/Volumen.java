package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the volumen database table.
 * 
 */
@Entity
@Table(name="volumen",catalog = "bddcorpepn", schema = "`Articulos`" )
public class Volumen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_vol")
	private Integer idVol;

	@Column(name="descr_vol")
	private String descrVol;

   
	@Column(name="fecha_pub")
	private String fechaPub;

	@Column(name="nombre_vol")
	private String nombreVol;
	
	
	@OneToMany(mappedBy = "volumen")
	private List<Articulo> articulo;

    public Volumen() {
    }

	public Integer getIdVol() {
		return this.idVol;
	}

	public void setIdVol(Integer idVol) {
		this.idVol = idVol;
	}

	public String getDescrVol() {
		return this.descrVol;
	}

	public void setDescrVol(String descrVol) {
		this.descrVol = descrVol;
	}

	public String getFechaPub() {
		return this.fechaPub;
	}

	public void setFechaPub(String fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getNombreVol() {
		return this.nombreVol;
	}

	public void setNombreVol(String nombreVol) {
		this.nombreVol = nombreVol;
	}

	public List<Articulo> getArticulo() {
		return articulo;
	}

	public void setArticulo(List<Articulo> articulo) {
		this.articulo = articulo;
	}

}