package ec.edu.epn.seguridad.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="acceso_parametro")
public class AccesoParametro {
	@Id
	@Column (name="id_acceso_parametro")
	@SequenceGenerator (name="secAccesoParametro", sequenceName="sec_acceso_parametro",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secAccesoParametro")
	private Long id;
	private String nombre;
	private String valor;
	@ManyToOne
	@JoinColumn (name="id_acceso_url")
	private AccesoUrl cabecera;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public AccesoUrl getCabecera() {
		return cabecera;
	}
	public void setCabecera(AccesoUrl cabecera) {
		this.cabecera = cabecera;
	}
}
