package ec.edu.epn.usuarios.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity

@Table(name="usuario",catalog = "bddcorpepn", schema = "public" )
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private Integer idUsuario;

	private String activo;

	private String cedula;

	private String clave;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_bodega")
	private String idBodega;

	@Column(name="id_laboratorio")
	private String idLaboratorio;

	@Column(name="id_personal")
	private String idPersonal;

	@Column(name="id_unidad")
	private Integer idUnidad;

	@Column(name="nombre_usuario")
	private String nombreUsuario;

	private String prueba;

	//bi-directional many-to-many association to Perfil
	@ManyToMany(mappedBy="usuarios")
	private List<Perfil> perfils;

    public Usuario() {
    }

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdBodega() {
		return this.idBodega;
	}

	public void setIdBodega(String idBodega) {
		this.idBodega = idBodega;
	}

	public String getIdLaboratorio() {
		return this.idLaboratorio;
	}

	public void setIdLaboratorio(String idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public String getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Integer getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrueba() {
		return this.prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}
	
}