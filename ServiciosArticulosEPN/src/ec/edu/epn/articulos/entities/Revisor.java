package ec.edu.epn.articulos.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the revisor database table.
 * 
 */
@Entity

@Table(name="revisor",catalog = "bddcorpepn", schema = "`Articulos`" )
public class Revisor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_rev")
	private Integer idRev;

	private String apellidos;

	@Column(name="attribute_7")
	private String attribute7;

	private String email;

	@Column(name="lugar_trabajo")
	private String lugarTrabajo;

	private String nidentificacion;

	private String nombres;

	//bi-directional many-to-one association to TipoRev
    @ManyToOne
	@JoinColumn(name="id_tiporev")
	private TipoRev tipoRev;

	//bi-directional many-to-many association to AreaInvestigacion
    @ManyToMany
	@JoinTable(name="revisor_area"
		, joinColumns={
			@JoinColumn(name="id_rev")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_areainv")
			}
		)
	private List<AreaInvestigacion> areaInvestigacions;

	//bi-directional many-to-one association to RevisorObservacione
	@OneToMany(mappedBy="revisor")
	private List<RevisorObservacione> revisorObservaciones;

    public Revisor() {
    }

	public Integer getIdRev() {
		return this.idRev;
	}

	public void setIdRev(Integer idRev) {
		this.idRev = idRev;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getAttribute7() {
		return this.attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLugarTrabajo() {
		return this.lugarTrabajo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	public String getNidentificacion() {
		return this.nidentificacion;
	}

	public void setNidentificacion(String nidentificacion) {
		this.nidentificacion = nidentificacion;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public TipoRev getTipoRev() {
		return this.tipoRev;
	}

	public void setTipoRev(TipoRev tipoRev) {
		this.tipoRev = tipoRev;
	}
	
	public List<AreaInvestigacion> getAreaInvestigacions() {
		return this.areaInvestigacions;
	}

	public void setAreaInvestigacions(List<AreaInvestigacion> areaInvestigacions) {
		this.areaInvestigacions = areaInvestigacions;
	}
	
	public List<RevisorObservacione> getRevisorObservaciones() {
		return this.revisorObservaciones;
	}

	public void setRevisorObservaciones(List<RevisorObservacione> revisorObservaciones) {
		this.revisorObservaciones = revisorObservaciones;
	}
	
}