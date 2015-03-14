package ec.edu.epn.seguridad.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Aplicacion {
	@Id
	@Column (name="id_aplicacion")
	@SequenceGenerator (name="secAplicacion", sequenceName="sec_aplicacion", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secAplicacion")
	private long id;
	private String nombre;
	private String descripcion;
	private String activo;	
	private String url;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
