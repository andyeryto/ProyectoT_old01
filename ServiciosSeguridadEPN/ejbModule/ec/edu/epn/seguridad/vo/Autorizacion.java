package ec.edu.epn.seguridad.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;

@Entity
public class Autorizacion {
	@Id
	@Column (name="id_autorizacion")
//	@SequenceGenerator (name="secAutorizacion", sequenceName="sec_autorizacion")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secAutorizacion")
	private long id;
	@ManyToOne
	@JoinColumn (name="id_perfil")
 	private Perfil perfil;
	@ManyToOne
	@JoinColumn (name="id_menu")
 	private Menu menu;
	@Column
	private String permisos;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getPermisos() {
		return permisos;
	}
	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}

}
