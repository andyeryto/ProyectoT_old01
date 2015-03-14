package ec.edu.epn.articulos.backingbean;

import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import client.ftp.controler.FTPConnectionManagement;

import ec.edu.epn.articulos.bean.AreaInvestigacionService;
import ec.edu.epn.articulos.bean.ArticuloServiceBean;
import ec.edu.epn.articulos.bean.ArticulosDocenteService;
import ec.edu.epn.articulos.bean.ConvocatoriaService;
import ec.edu.epn.articulos.bean.EstadoArticuloService;
import ec.edu.epn.articulos.bean.ObservacionArticuloService;
import ec.edu.epn.articulos.bean.UsuarioService;
import ec.edu.epn.articulos.entities.AreaInvestigacion;
import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.Convocatoria;
import ec.edu.epn.articulos.entities.EstadoA;
import ec.edu.epn.articulos.entities.ObservacionArticulo;
import ec.edu.epn.articulos.mail.correoVO;
import ec.edu.epn.seguridad.VO.SesionUsuario;
import ec.edu.epn.articulos.bean.ArticuloService;

@ManagedBean(name = "articulosBackingBean")
@SessionScoped
public class ArticulosBackingBean implements Serializable {

	private static final long serialVersionUID = -3349870009920188056L;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ArticulosDocenteServiceBean!ec.edu.epn.articulos.bean.ArticulosDocenteService")
	private ArticulosDocenteService ads;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/UsuarioServiceBean!ec.edu.epn.articulos.bean.UsuarioService")
	private UsuarioService us;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ArticuloServiceBean!ec.edu.epn.articulos.bean.ArticuloService")
	private ArticuloService as;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/AreaInvestigacionServiceBean!ec.edu.epn.articulos.bean.AreaInvestigacionService")
	private AreaInvestigacionService ais;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/ConvocatoriaServiceBean!ec.edu.epn.articulos.bean.ConvocatoriaService")
	private ConvocatoriaService cs;

	@EJB(lookup = "java:global/ServiciosArticulosEPN/EstadoArticuloServiceBean!ec.edu.epn.articulos.bean.EstadoArticuloService")
	private EstadoArticuloService eas;
	
	@EJB (lookup = "java:global/ServiciosArticulosEPN/ObservacionArticuloServiceBean!ec.edu.epn.articulos.bean.ObservacionArticuloService")
	private ObservacionArticuloService obsb;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	private Articulo articuloIngreso;
	private Articulo articuloEdit;
	private Articulo articuloInfo;
	private List<Articulo> listaArticulos;

	private ArticuloServiceBean asb;
	// Areas de investigacion
	private List<AreaInvestigacion> listaAreasInv;
	private AreaInvestigacion nuevaAreaInv;
	// Convocatoria
	private List<Convocatoria> listaConv;
	private List<Convocatoria> listFechaLlamada;
	private Convocatoria nuevaConvocatoria;
	// Estado
	private List<EstadoA> listaEstadoI;
	private EstadoA nuevoEstadoA;
	
	
	private correoVO correo;

	private Integer idArt;

	// /Variables locales para las consultas a la base
	private Articulo articulo;
	private Boolean isUpdate = false;

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

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		ResourceBundle bundle = ResourceBundle
				.getBundle("client.ftp.controler.FTPServerProperties");
		SERVER = bundle.getString("SERVER");
		PORT = Integer.parseInt(bundle.getString("PORT"));
		USERNAME = bundle.getString("USERNAME");
		PASSWORD = bundle.getString("PASSWORD");
		initArticulo();
		getListaAreasInvest();
		getListaConv();
		getListaConvocatorias();
		getEstadoIngresado();

	}

	private void initArticulo() {
		articuloIngreso = new Articulo();
		nuevaAreaInv = new AreaInvestigacion();
		nuevaConvocatoria = new Convocatoria();
		nuevoEstadoA = new EstadoA();
		articuloEdit = new Articulo();
		articuloInfo = new Articulo();
		correo = new correoVO();

	}

	private void initFtpClient() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.createConection(SERVER, PORT);
		ftpClient.login(USERNAME, PASSWORD);
	}

	private void closeFtpClient() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.logout();
	}

	private void createDirectory(String directoryName)
			throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.createDirectory(directoryName);
	}

	private void setCurrentDirectory(String directoryName)
			throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		ftpClient.setCurrentDirectory(directoryName);
	}

	// //////////////////////////////////////////////////////

//	public String toRegistroDocumento() {
//		verifyIfDocumentoWasCreated();
//		if (!isUpdate) {
//			nuevoArt(); // nuevoContrato();
//			// contrato.setPlazoContrato("1");
//		} else {
//			setArticuloIngreso(getArticulo());
//			// setContrato(programaBeca.getContrato());
//		}
//
//		return "registroDocumentoArticulo";
//	}
	
	

//	private void nuevoArt() {
//		articulo = new Articulo();
//		// contrato = new Contrato();
//	}
//
//	private void verifyIfDocumentoWasCreated() {
//		if (articulo.getPathDocumento() == null) { // programaBeca.getContrato()
//													// == null
//			setIsUpdate(false);
//		} else {
//			setIsUpdate(true);
//		}
//	}

//	public void guardarOActualizarDocumento(ActionEvent event) {
//		System.out.println("Guardar el documento de articulo");
//		 if(!isUpdate){
//		 try{
//		 guardarContrato();
//		 setIsUpdate(true);
//		 FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
//		 new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
//		 "Contrato ingresado con éxito"));
//		 }catch (Exception e) {
//		 FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(),
//		 new
//		 FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","A ocurrido un error al guardar el Contrato"));
//		 e.printStackTrace();
//		 }
//		 return;
//		 }
//		if (isUpdate) {
//			try {
//				actualizarArticuloDoc();
//				FacesContext.getCurrentInstance().addMessage(
//						event.getComponent().getClientId(),
//						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
//								"Documento actualizado con éxito"));
//			} catch (Exception e) {
//				FacesContext
//						.getCurrentInstance()
//						.addMessage(
//								event.getComponent().getClientId(),
//								new FacesMessage(FacesMessage.SEVERITY_ERROR,
//										"Error",
//										"A ocurrido un error al actualizar el Contrato"));
//				e.printStackTrace();
//			}
//			return;
//		}
//	}

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile upFile = event.getFile();
		FacesMessage msgSuccess = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Transacción exitosa", "El documento " + upFile.getFileName()
						+ " ha sido correctamente almacenado");
		FacesMessage msgError = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Error en la transacción", "A ocurrido un error "
						+ upFile.getFileName() + " no ha sido almacenado");
		FacesMessage msgErrorBorrarArchivo = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, "Error en la transacción",
				"A ocurrido un error al eliminar el archivo anterior");
		InputStream incomingFileStream = null;
		try {
			incomingFileStream = upFile.getInputstream();

			tempFile = new File(System.getProperty("user.home")
					+ System.getProperty("file.separator")
					+ event.getFile().getFileName());

			// Abriendo un stream para guardar temporalmente el archivo enviado
			// desde el browser
			OutputStream serverOutFile = null;
			serverOutFile = new FileOutputStream(tempFile);

			// Escribiendo el archivo
			int read = 0;
			byte[] bytes = new byte[1024 * 10];

			while ((read = incomingFileStream.read(bytes)) != -1) {
				serverOutFile.write(bytes, 0, read);
			}
			// Cerrando los streams
			incomingFileStream.close();
			serverOutFile.flush();
			serverOutFile.close();

			// Iniciando la transaccion con el servidor FTP
			initFtpClient();
			
			Integer idArt = articuloInfo.getIdArticulo();
			String idArtString = idArt.toString();
			String s = "_";

			// Enviando el archivo temporal al servidor FTP
			String directorioActual = articuloInfo.getNcedArticulista().concat(s).concat(idArtString); // programaBeca.getPostulante().getNced();
			try {
				directorioActual = ftpClient.getCurrentDirectory()
						+ directorioActual;
				createDirectory(directorioActual);

				setCurrentDirectory(directorioActual);
			} catch (FTPException e) {
				System.out.println("El directorio ya existe");
				savedFile = articuloInfo.getPathDocumento(); // programaBeca.getContrato().getPathContrato();
				String[] fileNameArray = savedFile.split(Pattern.quote("/"));
				String fileName = fileNameArray[fileNameArray.length - 1];
				try {
					setCurrentDirectory(directorioActual);
					if (fileName.toLowerCase().endsWith(".doc")
							| fileName.toLowerCase().endsWith(".docx")
							| fileName.toLowerCase().endsWith(".pdf")) {
						ftpClient.deleteFile(fileName);
					}
				} catch (Exception e2) {
					FacesContext.getCurrentInstance().addMessage(
							event.getComponent().getClientId(),
							msgErrorBorrarArchivo);
					e2.printStackTrace();
					System.out.println("Error al eliminar el archivo anterior");
					return;
				}

			}

			System.out.println(directorioActual);
			System.out.println(tempFile.getPath());

			setSavedFile(directorioActual + "/" + event.getFile().getFileName());

			ftpClient.doUpload(tempFile.getPath());

			articuloInfo.setPathDocumento(getSavedFile()); // programaBeca.getContrato().setPathContrato(getSavedFile());

//			if (isUpdate) {
//				actualizarArticuloDoc();
//			}

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
				// Eliminado el archivo temporal
				tempFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handleDownload(ActionEvent event) {

		savedFile = articuloInfo.getPathDocumento(); // programaBeca.getContrato().getPathContrato();
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

	public void deleteTempFile() {
		Thread deleteFile = new Thread() {
			boolean wasDeleted = false;

			@Override
			public void run() {
				while (!wasDeleted) {
					wasDeleted = tempFile.delete();
					System.out.println("El archivo temporal fue borrado ? "
							+ wasDeleted);
					if (!wasDeleted) {
						try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
			}

		};
		deleteFile.run();
	}

	
	public List<Articulo> getArticulos() {
		return ads.getArticulosPorDocente((int) su.id_usuario_log);

	}

	public void guardarArticulos(ActionEvent event) {
		System.out.println("METODO GUARDAR ARTICULO*************");
		articuloIngreso.setIdArticulo(idArt);
		articuloIngreso.setTipoDocumento("INICIAL");

		Date fechaActual = new Date();
		Calendar c = new GregorianCalendar();
		String anioActual = Integer.toString(c.get(Calendar.YEAR));
		System.out.println("Año actual: "  + anioActual);
		String var = "ART";
		String var1 = "-";
		
		
		Integer cod = as.maxCodArticulo();
		String codAuxd = cod.toString();
		
		if (codAuxd.length() == 1) {
			articuloIngreso.setAuxAutogenerado(var + var1 + "000" + codAuxd + var1 + anioActual);
		}
		if (codAuxd.length() == 2) {
			articuloIngreso.setAuxAutogenerado(var + var1 + "00" + codAuxd + var1 + anioActual);
		}
		
		if (codAuxd.length() == 3) {
			articuloIngreso.setAuxAutogenerado(var + var1 + "0" + codAuxd + var1 + anioActual);
		}
		
		if (codAuxd.length() == 4) {
			articuloIngreso.setAuxAutogenerado(var + var1 + codAuxd + var1 + anioActual);
		}
		

		articuloIngreso.setFechaEnvio(fechaActual);
		// nuevaAreaInv.setIdAreainv("1");
		// nuevaConvocatoria.setIdConv("1");
		nuevoEstadoA.setIdEstadoa(1);

		int cedula = us.buscarCedulaUsuario((int) su.id_usuario_log);
		System.out.println("Cedula INT: " + cedula);

		String cedulaDocente = Integer.toString(cedula);
		System.out.println("Cedula STRING: " + cedulaDocente);

		try {
			articuloIngreso.setNcedArticulista(cedulaDocente);
			articuloIngreso.setAreaInvestigacion(nuevaAreaInv);
			articuloIngreso.setConvocatoria(nuevaConvocatoria);
			articuloIngreso.setEstadoA(nuevoEstadoA);

			as.insertArticuloDocente(articuloIngreso);
			getArticulos();

			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Artículo ingresado"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
			e.printStackTrace();
		}

	}

	public void getListaAreasInvest() {
		listaAreasInv = ais.findAllAreasInv();
	}

	public void getListaConvocatorias() {
		System.out.println("METODO CONV");
		listaConv = cs.findAllConvocatorias();

	}

	public void getEstadoIngresado() {
		System.out.println("LLamar Estado Ingresado");
		listaEstadoI = eas.findEstadoAIngresado();
	}

		
	
	//Artualizar Articulo
	public void actualizarArticulo(ActionEvent event) {
		try {
			as.updateArticuloDocente(articuloEdit);
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Artículo actualizado"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					event.getComponent().getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"A ocurrido un error"));
		}
	}
	
	//Artualizar Articulo Path
		public void actualizarArticuloPath(ActionEvent event) {
			
				System.out.println(articuloInfo.getTipoDocumento());
				
				
				if(articuloInfo.getTipoDocumento().equals("CON CAMBIOS"))
				{
					List<String> mails = new ArrayList<String>();
					
					List<Integer> idobservArti = new ArrayList<Integer>();
					
					String condicion = "NO";
					idobservArti = obsb.findIdObservacion(articuloInfo.getAuxAutogenerado(),condicion);
					
					System.out.println("Tamaña de los que dijero que no :"+idobservArti.size());
					
					
					//String mail ="robxav_g@hotmail.com";
					mails = obsb.findemailrevisorObservaciones(articuloInfo.getAuxAutogenerado());
					
					System.out.println("Voy a eliminar");
					
					
					
					System.out.println("elimine");
					
					
					
					
					
					for(int i =0; i< mails.size(); i++)
					{
						System.out.println(mails.get(i));
					
						
					}
					
					String de= "<roberto.garcia@epn.edu.ec>";
					String subject = "Actualización de Artículo";
					String text = "El articulista  " + articuloInfo.getEmp_1().getNom()+" "+articuloInfo.getEmp_1().getApel()
				+" ha realizado cambios sobre el Artículo :  " + articuloInfo.getTituloArticulo().toUpperCase();

					System.out.println(de+ subject + " Texto"+ text );

					System.out.println("Voy a enviar el mail");
					
					correo.correoObtener(de, mails, subject, text);	
					System.out.println("Cantidad de mails a enviar "+ mails.size());
					
					try{
					as.updateArticuloDocente(articuloInfo);
					FacesContext.getCurrentInstance().addMessage(
							event.getComponent().getClientId(),
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
									"Ha realizado cambios sobre el artículo"));
					
					for(int i=0;i<= idobservArti.size();i++ )
					{
						
						Integer id = idobservArti.get(i);
						System.out.println("Id del que dijo que no "+id);
						
						obsb.deleteObserv(id);
						
					}
					
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(
							event.getComponent().getClientId(),
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
									"A ocurrido un error"));
				}
					
					
				}
				else
					
					try{
						as.updateArticuloDocente(articuloInfo);
						FacesContext.getCurrentInstance().addMessage(
								event.getComponent().getClientId(),
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
										"Artículo ingresado exitosamente"));
					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(
								event.getComponent().getClientId(),
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
										"A ocurrido un error"));
					}
				
				
			
		}

		//Eliminar Articulo

	public void eliminarArticulo(ActionEvent event) {
			
//			FTPConnectionManagement ftpDelete = new FTPConnectionManagement();
			
			try {				
				
				as.deleteArticuloDocente(articuloEdit);
				listaArticulos.remove(articuloEdit);
//				ftpDelete.deleteFile(savedFile);

				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
								"Artículo eliminado"));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						event.getComponent().getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"A ocurrido un error"));
			}
		}
		
	// ///Reglas de navegacion////////////
	public String toRegistroArticulos() {
		nuevoArticulo();
		return "nuevoArticulo";
	}

	// public Articulo getArticulo() {
	// return articulo;
	// }
	//
	// public void setArticulo(Articulo articulo) {
	// this.articulo = articulo;
	// }

	public void nuevoArticulo() {
		articuloIngreso = new Articulo();
	}

	public Articulo getArticuloIngreso() {
		return articuloIngreso;
	}

	public void setArticuloIngreso(Articulo articuloIngreso) {
		this.articuloIngreso = articuloIngreso;
	}

	public ArticuloServiceBean getAsb() {
		return asb;
	}

	public void setAsb(ArticuloServiceBean asb) {
		this.asb = asb;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
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

	public List<AreaInvestigacion> getListaAreasInv() {
		return listaAreasInv;
	}

	public void setListaAreasInv(List<AreaInvestigacion> listaAreasInv) {
		this.listaAreasInv = listaAreasInv;
	}

	public AreaInvestigacion getNuevaAreaInv() {
		return nuevaAreaInv;
	}

	public void setNuevaAreaInv(AreaInvestigacion nuevaAreaInv) {
		this.nuevaAreaInv = nuevaAreaInv;
	}

	public List<Convocatoria> getListaConv() {
		// if(listaConv == null){
		// listaConv = cs.findAllConvocatorias();
		// }
		return listaConv;
	}

	public void setListaConv(List<Convocatoria> listaConv) {
		this.listaConv = listaConv;
	}

	public Convocatoria getNuevaConvocatoria() {
		return nuevaConvocatoria;
	}

	public void setNuevaConvocatoria(Convocatoria nuevaConvocatoria) {
		this.nuevaConvocatoria = nuevaConvocatoria;
	}

	public List<Convocatoria> getListFechaLlamada() {
		return listFechaLlamada;
	}

	public void setListFechaLlamada(List<Convocatoria> listFechaLlamada) {
		this.listFechaLlamada = listFechaLlamada;
	}

	public EstadoArticuloService getEas() {
		return eas;
	}

	public void setEas(EstadoArticuloService eas) {
		this.eas = eas;
	}

	public List<EstadoA> getListaEstadoI() {
		return listaEstadoI;
	}

	public void setListaEstadoI(List<EstadoA> listaEstadoI) {
		this.listaEstadoI = listaEstadoI;
	}

	public EstadoA getNuevoEstadoA() {
		return nuevoEstadoA;
	}

	public void setNuevoEstadoA(EstadoA nuevoEstadoA) {
		this.nuevoEstadoA = nuevoEstadoA;
	}

	public UsuarioService getUs() {
		return us;
	}

	public void setUs(UsuarioService us) {
		this.us = us;
	}

	public Integer getIdArt() {
		return idArt;
	}

	public void setIdArt(Integer idArt) {
		this.idArt = idArt;
	}

	public List<Articulo> getListaArticulos() {
		return listaArticulos;
	}

	public void setListaArticulos(List<Articulo> listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	public Articulo getArticuloEdit() {
		return articuloEdit;
	}

	public void setArticuloEdit(Articulo articuloEdit) {
		this.articuloEdit = articuloEdit;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Articulo getArticuloInfo() {
		return articuloInfo;
	}

	public void setArticuloInfo(Articulo articuloInfo) {
		this.articuloInfo = articuloInfo;
	}
	
	
	
	
	
	

}
