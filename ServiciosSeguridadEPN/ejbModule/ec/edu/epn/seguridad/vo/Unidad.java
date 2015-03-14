package ec.edu.epn.seguridad.vo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unidad database table.
 * 
 */
@Entity
@Table(name = "unidad", catalog = "seguridad", schema = "`Laboratorios`") 
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_unidad")
	private Integer idUnidad;

	@Column(name="codigo_u")
	private String codigoU;

	@Column(name="contacto1_u")
	private String contacto1U;

	@Column(name="contacto2_u")
	private String contacto2U;

	@Column(name="descr_u")
	private String descrU;

	@Column(name="direccion_u")
	private String direccionU;

	@Column(name="email_cont1_u")
	private String emailCont1U;

	@Column(name="email_cont2_u")
	private String emailCont2U;

	@Column(name="jefe_u")
	private String jefeU;

	@Column(name="nombre_u")
	private String nombreU;

	@Column(name="sigla_u")
	private String siglaU;

	@Column(name="tel_cont1_u")
	private String telCont1U;

	@Column(name="tel_cont2_u")
	private String telCont2U;

	@Column(name="telefono_u")
	private String telefonoU;

    public Unidad() {
    }

	public Integer getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getCodigoU() {
		return this.codigoU;
	}

	public void setCodigoU(String codigoU) {
		this.codigoU = codigoU;
	}

	public String getContacto1U() {
		return this.contacto1U;
	}

	public void setContacto1U(String contacto1U) {
		this.contacto1U = contacto1U;
	}

	public String getContacto2U() {
		return this.contacto2U;
	}

	public void setContacto2U(String contacto2U) {
		this.contacto2U = contacto2U;
	}

	public String getDescrU() {
		return this.descrU;
	}

	public void setDescrU(String descrU) {
		this.descrU = descrU;
	}

	public String getDireccionU() {
		return this.direccionU;
	}

	public void setDireccionU(String direccionU) {
		this.direccionU = direccionU;
	}

	public String getEmailCont1U() {
		return this.emailCont1U;
	}

	public void setEmailCont1U(String emailCont1U) {
		this.emailCont1U = emailCont1U;
	}

	public String getEmailCont2U() {
		return this.emailCont2U;
	}

	public void setEmailCont2U(String emailCont2U) {
		this.emailCont2U = emailCont2U;
	}

	public String getJefeU() {
		return this.jefeU;
	}

	public void setJefeU(String jefeU) {
		this.jefeU = jefeU;
	}

	public String getNombreU() {
		return this.nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}

	public String getSiglaU() {
		return this.siglaU;
	}

	public void setSiglaU(String siglaU) {
		this.siglaU = siglaU;
	}

	public String getTelCont1U() {
		return this.telCont1U;
	}

	public void setTelCont1U(String telCont1U) {
		this.telCont1U = telCont1U;
	}

	public String getTelCont2U() {
		return this.telCont2U;
	}

	public void setTelCont2U(String telCont2U) {
		this.telCont2U = telCont2U;
	}

	public String getTelefonoU() {
		return this.telefonoU;
	}

	public void setTelefonoU(String telefonoU) {
		this.telefonoU = telefonoU;
	}

}