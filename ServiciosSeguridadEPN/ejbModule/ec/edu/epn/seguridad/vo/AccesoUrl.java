package ec.edu.epn.seguridad.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="acceso_url")
public class AccesoUrl {
	@Id
	@Column (name="id_acceso_url")
	@SequenceGenerator (name="secAccesoURL", sequenceName="sec_acceso_url",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secAccesoURL")
	private Long id;
	private String url;
	@Column (name="fecha_proceso")
    private Date fechaProceso;
	@OneToMany (mappedBy="cabecera",cascade=CascadeType.PERSIST)
	private List<AccesoParametro> parametros;
	@ManyToOne
	@JoinColumn (name="id_usuario")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public List<AccesoParametro> getParametros() {
		return parametros;
	}
	public void setParametros(List<AccesoParametro> parametros) {
		this.parametros = parametros;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
