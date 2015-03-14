package ec.edu.epn.seguridad.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column (name="id_usuario")
	@SequenceGenerator (name="secUsuario", sequenceName="sec_usuario", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secUsuario")
    private long id;
    @Column (name="nombre_usuario")
    private String nombreUsuario;
    private String clave;
	private String cedula;
	@Temporal( TemporalType.DATE)
    @Column (name="fecha_creacion")
    private Date fechaCreacion;
    @Column(name="id_personal")
	private String idPersonal;
	private int id_unidad;
	//private String id_laboratorio;
	//private String id_bodega;
    @ManyToMany (mappedBy="usuarios")
    private List<Perfil>perfiles;
    
	//bi-directional many-to-one association to AccesoUrl
	@OneToMany(mappedBy="usuario")
	private List<AccesoUrl> accesoUrls;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	public int getId_unidad() {
		return id_unidad;
	}
	public void setId_unidad(int idUnidad) {
		id_unidad = idUnidad;
	}
	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}
	public String getIdPersonal() {
		return idPersonal;
	}
	public void setAccesoUrls(List<AccesoUrl> accesoUrls) {
		this.accesoUrls = accesoUrls;
	}
	public List<AccesoUrl> getAccesoUrls() {
		return accesoUrls;
	}
	
	/*public String getId_laboratorio() {
		return id_laboratorio;
	}
	public void setId_laboratorio(String idLaboratorio) {
		id_laboratorio = idLaboratorio;
	}
	public String getId_bodega() {
		return id_bodega;
	}
	public void setId_bodega(String idBodega) {
		id_bodega = idBodega;
	}*/
	
}
