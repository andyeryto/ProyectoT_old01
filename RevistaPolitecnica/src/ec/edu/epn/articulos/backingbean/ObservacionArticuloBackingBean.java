package ec.edu.epn.articulos.backingbean;

import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import client.ftp.controler.FTPConnectionManagement;

import ec.edu.epn.articulos.bean.ObservacionArticuloService;
import ec.edu.epn.articulos.bean.UsuarioService;
import ec.edu.epn.articulos.entities.ObservacionArticulo;
import ec.edu.epn.articulos.entities.RevisorObservacione;
import ec.edu.epn.articulos.mail.correoVO;
import ec.edu.epn.seguridad.VO.SesionUsuario;

@ManagedBean(name = "observArticulo")
@SessionScoped
public class ObservacionArticuloBackingBean implements Serializable {
	private static final long serialVersionUID = 7119857077935116080L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ObservacionArticuloServiceBean!ec.edu.epn.articulos.bean.ObservacionArticuloService")
	private ObservacionArticuloService observArticsb;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/UsuarioServiceBean!ec.edu.epn.articulos.bean.UsuarioService")
	private UsuarioService us;

	private String idArticulo;
	private String palabrasclave;
	private String titulo;
	private Date fechadesde;
	private Date fechaHasta;
	private Integer idEstado;
	private RevisorObservacione revisorInfo;
	List<RevisorObservacione> listObservArticulos;
	private ObservacionArticulo nuevaObservacion;
	private correoVO correo;
	private List<ObservacionArticulo> ObservacionExistente;

	
	

	// Variables y métodos necesarios para el uso del cliente FTP//
	private FTPConnectionManagement ftpClient = new FTPConnectionManagement();

	private String SERVER;
	private Integer PORT;
	private String USERNAME;
	private String PASSWORD;

	// Para descargar el archivo
	private String savedFile = null;
	private StreamedContent remoteFile = null;
	private File tempFile = null;

	private void initFtpClient() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.createConection(SERVER, PORT);
		ftpClient.login(USERNAME, PASSWORD);
	}

	private void closeFtpClient() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.logout();
	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("client.ftp.controler.FTPServerProperties");
		SERVER = bundle.getString("SERVER");
		PORT = Integer.parseInt(bundle.getString("PORT"));
		USERNAME = bundle.getString("USERNAME");
		PASSWORD = bundle.getString("PASSWORD");
		initObserv();
	}

		
	private void initObserv()
	{
		nuevaObservacion = new ObservacionArticulo();
		correo = new correoVO();
		
	}


	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	
	
	
	
	
	public void buscarObservaArticulo() {
		System.out.println("Entro a buscar Observ Rev");

		int cedula = us.buscarCedulaUsuario((int) su.id_usuario_log);
		System.out.println("Cedula INT: " + cedula);

		String cedulaRev = Integer.toString(cedula);

		listObservArticulos = observArticsb.findRevArtbyParams(
				idArticulo.trim(), palabrasclave.trim(), titulo.trim(),
				fechadesde, fechaHasta, idEstado, cedulaRev);
		if (listObservArticulos.size() == 0) {
			System.out.println("No hay nada");
		} else {

			System.out.println("Si hay daots");
		}

	}

	public void handleDownload(ActionEvent event) {

		savedFile = revisorInfo.getArticulo().getPathDocumento();
		System.out.println(savedFile);
		                                 // programaBeca.getContrato().getPathContrato();
		String[] fileNameArray = savedFile.split(Pattern.quote("/"));
		String fileName = fileNameArray[fileNameArray.length - 1];

		String localFilePath = System.getProperty("user.home")
				+ System.getProperty("file.separator") + fileName;

		FacesMessage msgSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Transacción exitosa", "El documento " + fileName
						+ " ha sido descargado");
		FacesMessage msgError = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Error en la transacción", "A ocurrido un error con "
						+ fileName + " durante la descarga");

		System.out.println(localFilePath);
		InputStream streamedFile = null;
		try {
			// Iniciando la transaccion con el servidor FTP
			initFtpClient();

			ftpClient.doDownload(localFilePath, savedFile);
			tempFile = new File(localFilePath);

			streamedFile = new FileInputStream(tempFile);

			remoteFile = new DefaultStreamedContent(streamedFile,
					"application/octet-stream", fileName);

			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(), msgSuccess);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(), msgError);
			e.printStackTrace();
		} finally {
			// Cerrando la conexión con el Servidor FTP
			try {
				closeFtpClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public void guardarObservacion(ActionEvent event)
	{
		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaAsig = sdf.format(fecha);
		nuevaObservacion.setFechaRegistro(fecha);
		nuevaObservacion.setRevisorObservacione(revisorInfo);
		
		
		String auxauto = revisorInfo.getArticulo().getAuxAutogenerado();
		String cedula = revisorInfo.getRevisor().getNidentificacion();
		
		
		System.out.println("Estado : "+nuevaObservacion.getEstado() + " Descripcion: "+nuevaObservacion.getObservacion());
		
		
		ObservacionExistente= observArticsb.findObservacionArticuloExiste(auxauto, cedula);
		
		System.out.println("Tamañe que si existen Observaciones"+ObservacionExistente.size());
		
		
		if(nuevaObservacion.getEstado().equals("") || nuevaObservacion.getObservacion().equals(""))
		{
			
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Recuerde que debe ingresar una observación y seleccionar si esta de acuerdo o no con la publicación del artículo"));
			
		}
		
		else
		{
		
		if(ObservacionExistente.size() == 1)
		{
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Usted ya ha realizado una observación sobre este Artículo"));
			
		}
		else {
		 
		
		String mail = revisorInfo.getArticulo().getEmp_1().getEmail1();
		System.out.println(mail);
		
		List<String> para = new ArrayList<String>();
		para.add(mail);



		String de= "<roberto.garcia@epn.edu.ec>";
			String subject = "Observación Artículo";
			String text = "El revisor  " + revisorInfo.getRevisor().getNombres().toUpperCase()+" "+revisorInfo.getRevisor().getApellidos().toUpperCase()
		+" ha realizado la siguiente observación sobre su artículo en la que menciona   " + nuevaObservacion.getObservacion();

			//System.out.println(de+ subject+para);


			correo.correoObtener(de, para, subject, text);
	
		try {

			
			observArticsb.insertObservaArticulo(nuevaObservacion);
			
			initObserv();
			
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Observación al artículo ingresada exitosamente"));
		}

		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}
		}
		}
		
		
		
	}
	
	

	public String getSavedFile() {
		return savedFile;
	}

	public void setSavedFile(String savedFile) {
		this.savedFile = savedFile;
	}

	public StreamedContent getRemoteFile() {
		return remoteFile;
	}

	public void setRemoteFile(StreamedContent remoteFile) {
		this.remoteFile = remoteFile;
	}

	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	public List<RevisorObservacione> getListObservArticulos() {
		return listObservArticulos;
	}

	public void setListObservArticulos(
			List<RevisorObservacione> listObservArticulos) {
		this.listObservArticulos = listObservArticulos;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getPalabrasclave() {
		return palabrasclave;
	}

	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RevisorObservacione getRevisorInfo() {
		return revisorInfo;
	}

	public void setRevisorInfo(RevisorObservacione revisorInfo) {
		this.revisorInfo = revisorInfo;
	}

	public ObservacionArticulo getNuevaObservacion() {
		return nuevaObservacion;
	}

	public void setNuevaObservacion(ObservacionArticulo nuevaObservacion) {
		this.nuevaObservacion = nuevaObservacion;
	}

	public List<ObservacionArticulo> getObservacionExistente() {
		return ObservacionExistente;
	}

	public void setObservacionExistente(List<ObservacionArticulo> observacionExistente) {
		ObservacionExistente = observacionExistente;
	}

}
