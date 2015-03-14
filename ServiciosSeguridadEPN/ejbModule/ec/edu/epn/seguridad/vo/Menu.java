package ec.edu.epn.seguridad.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Menu {
	@Id
	@Column (name="id_menu")
//	@SequenceGenerator (name="secMenu", sequenceName="sec_menu")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secMenu")
	private long id;
	@JoinColumn (name="id_menu_padre")
	@ManyToOne 
	private Menu menuPadre;
	@OneToMany (mappedBy="menuPadre")
	private List<Menu> subMenus;
	private String tipo;
	private String nombre;
	private String descripcion;
	private String url;
	private int orden;
	private String activo;
	@JoinColumn (name="id_aplicacion")
    @ManyToOne 
	private Aplicacion aplicacion;
	@OneToMany (mappedBy="menu")
	private List<Autorizacion> autorizaciones;   
	
    @Transient
    private String urlCompleto;

    @Transient
    private String permisos;
    
    @Transient
    public boolean getExpandido(){
    	if (this.getMenuPadre()== null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Menu getMenuPadre() {
		return menuPadre;
	}
	public void setMenuPadre(Menu menuPadre) {
		this.menuPadre = menuPadre;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public String toString(){
	    return String.valueOf(id);
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUrlCompleto() {
		return urlCompleto;
	}
	public void setUrlCompleto(String urlCompleto) {
		this.urlCompleto = urlCompleto;
	}
	public Aplicacion getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	public List<Autorizacion> getAutorizaciones() {
		return autorizaciones;
	}
	public void setAutorizaciones(List<Autorizacion> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	public String getPermisos() {
		return permisos;
	}

	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}
}
