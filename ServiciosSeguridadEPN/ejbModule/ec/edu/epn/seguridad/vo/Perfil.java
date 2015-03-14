package ec.edu.epn.seguridad.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Perfil {
	@Id
	@Column (name="id_perfil")
	@SequenceGenerator (name="secPerfil", sequenceName="sec_perfil", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secPerfil")
	private long id;
    private String nombre;
    private String descripcion;
    private String activo;
    @ManyToMany 
    @JoinTable (name="perfil_usuario",
    		    joinColumns=@JoinColumn(name="id_perfil"),
                inverseJoinColumns=@JoinColumn(name="id_usuario"))
    private List<Usuario>usuarios;
    
	@OneToMany (mappedBy="perfil")
	private List<Autorizacion> autorizaciones;   
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Autorizacion> getAutorizaciones() {
		return autorizaciones;
	}
	public void setAutorizaciones(List<Autorizacion> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

}
