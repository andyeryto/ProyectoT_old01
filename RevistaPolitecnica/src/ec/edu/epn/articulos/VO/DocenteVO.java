package ec.edu.epn.articulos.VO;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DocenteVO {

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
			.getRequest();
	HttpSession session = request.getSession(true);

	private String nCed;
	private String apel;
	private String nombre;
	private String cargo;
	private Date fechaIngresoEPN;
	private String relacionLab;

	private ArrayList<DocenteVO> docentesArray;

	private String sucesos = " ";

	private int idUsuario;

	// otros
	private String idFacultad;
	private String nomFacultad;
	private String idDepartamento;
	private String nomDepartamento;
	private String dedicacion;
	private String categoria;
	private String auxFechaIngresoEPN;

	private String style;
	private String styleSuccess = "COLOR: #008000;";
	private String styleError = "COLOR: #fc5868;";

	public DocenteVO() {
		super();
	}

	public DocenteVO(String nCed, String apel, String nombre, String cargo,
			int idUsuario, String idFacultad, String nomFacultad,
			String idDepartamento, String nomDepartamento, String dedicacion,
			String categoria, Date fechaIngresoEPN, String auxFechaIngresoEPN,
			String relacionLab) {
		super();
		this.nCed = nCed;
		this.apel = apel;
		this.nombre = nombre;
		this.cargo = cargo;
		this.idUsuario = idUsuario;
		this.idFacultad = idFacultad;
		this.nomFacultad = nomFacultad;
		this.idDepartamento = idDepartamento;
		this.nomDepartamento = nomDepartamento;
		this.dedicacion = dedicacion;
		this.categoria = categoria;
		this.fechaIngresoEPN = fechaIngresoEPN;
		this.auxFechaIngresoEPN = auxFechaIngresoEPN;
		this.relacionLab = relacionLab;
	}

	// **********************
	// Ver Datos Docente VER
	// **********************

	public void tomarDatosDocenteVer(ActionEvent event) throws Exception {
		System.out.println("***tomarDatosDocenteVer***");

		UIParameter componentCI = (UIParameter) event.getComponent()
				.findComponent("verCI");
		String auxCI = componentCI.getValue().toString();
		System.out.println("auxCI: " + auxCI);
		session.setAttribute("CI", auxCI);

		setnCed(auxCI);

	}

	// ****************************
	// Ver Datos Docente ACTUALIZAR
	// ****************************

	public void tomarDatosDocenteActualizar(ActionEvent event) throws Exception {
		System.out.println("***tomarDatosDocenteActualizar***");

		/*
		 * UIParameter component0 = (UIParameter)
		 * event.getComponent().findComponent("verCI"); String auxCI =
		 * component0.getValue().toString(); System.out.println("auxCI: " +
		 * auxCI); session.setAttribute("CIFormAcad", auxCI);
		 */

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("verNombreA");
		String auxNombre = component.getValue().toString();
		System.out.println("auxNombre: " + auxNombre);

		UIParameter component1 = (UIParameter) event.getComponent()
				.findComponent("verApellidoA");
		String auxApellido = component1.getValue().toString();
		System.out.println("auxApellido: " + auxApellido);

		UIParameter componentF = (UIParameter) event.getComponent()
				.findComponent("verFacA");
		String auxFac = componentF.getValue().toString();
		System.out.println("auxFac: " + auxFac);

		UIParameter componentD = (UIParameter) event.getComponent()
				.findComponent("verDepA");
		String auxDep = componentD.getValue().toString();
		System.out.println("auxDep: " + auxDep);

		UIParameter component7 = (UIParameter) event.getComponent()
				.findComponent("verDedicacionA");
		String auxDedicacion = component7.getValue().toString();
		System.out.println("auxDedicacion: " + auxDedicacion);

		UIParameter component8 = (UIParameter) event.getComponent()
				.findComponent("verCategoriaA");
		String auxCategoria = component8.getValue().toString();
		System.out.println("auxCategoria: " + auxCategoria);

		// setnCed(auxCI);
		setNombre(auxNombre);
		setApel(auxApellido);
		setNomFacultad(auxFac);
		setNomDepartamento(auxDep);
		// setLugarNac(auxLugNac);
		// setFecNac(fechaTransf1);
		// setDireccion(auxDireccion);
		// setTelef(auxTelefono);
		setDedicacion(auxDedicacion);
		setCategoria(auxCategoria);
		// setFecIngresoEPN(fechaTransf2);

	}

	public String getnCed() {
		return nCed;
	}

	public void setnCed(String nCed) {
		this.nCed = nCed;
	}

	public String getApel() {
		return apel;
	}

	public void setApel(String apel) {
		this.apel = apel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(String idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNomFacultad() {
		return nomFacultad;
	}

	public void setNomFacultad(String nomFacultad) {
		this.nomFacultad = nomFacultad;
	}

	public String getNomDepartamento() {
		return nomDepartamento;
	}

	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}

	public String getDedicacion() {
		return dedicacion;
	}

	public void setDedicacion(String dedicacion) {
		this.dedicacion = dedicacion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFechaIngresoEPN() {
		return fechaIngresoEPN;
	}

	public void setFechaIngresoEPN(Date fechaIngresoEPN) {
		this.fechaIngresoEPN = fechaIngresoEPN;
	}

	public String getAuxFechaIngresoEPN() {
		return auxFechaIngresoEPN;
	}

	public void setAuxFechaIngresoEPN(String auxFechaIngresoEPN) {
		this.auxFechaIngresoEPN = auxFechaIngresoEPN;
	}

	public String getRelacionLab() {
		return relacionLab;
	}

	public void setRelacionLab(String relacionLab) {
		this.relacionLab = relacionLab;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleSuccess() {
		return styleSuccess;
	}

	public void setStyleSuccess(String styleSuccess) {
		this.styleSuccess = styleSuccess;
	}

	public String getStyleError() {
		return styleError;
	}

	public void setStyleError(String styleError) {
		this.styleError = styleError;
	}

	public String getSucesos() {
		return sucesos;
	}

	public void setSucesos(String sucesos) {
		this.sucesos = sucesos;
	}

}
