package ec.edu.epn.articulos.VO;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.epn.seguridad.VO.SesionUsuario;

public class ArticulosVO {
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext()
			.getRequest();
	HttpSession session = request.getSession();
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");

	private Integer idArticulo;
	private String idConv;
	private Integer idEstadoa;
	private String ncedArticulista;
	private Date fechaEnvio;
	private String tituloArticulo;
	private String participacionArticulo;
	private String breveResumen;
	private String palabrasclave;
	private String pathDocumento;

	// style del mensaje success
	private String style;
	private String styleSuccess = "COLOR: #008000;";
	private String styleError = "COLOR: #fc5868;";
	private String oncomplete;
	private String success = " ";

	public ArticulosVO() {
		super();
	}

	public ArticulosVO(Integer idArticulo, String idConv, Integer idEstadoa,
			String ncedArticulista, Date fechaEnvio, String tituloArticulo,
			String participacionArticulo, String breveResumen,
			String palabrasclave, String pathDocumento) {
		super();
		this.idArticulo = idArticulo;
		this.idConv = idConv;
		this.idEstadoa = idEstadoa;
		this.ncedArticulista = ncedArticulista;
		this.fechaEnvio = fechaEnvio;
		this.tituloArticulo = tituloArticulo;
		this.participacionArticulo = participacionArticulo;
		this.breveResumen = breveResumen;
		this.palabrasclave = palabrasclave;
		this.pathDocumento = pathDocumento;
	}

	public void setArticulo(ArticulosVO art) {
		this.setIdArticulo(art.getIdArticulo());
		this.setIdConv(art.getIdConv());
		this.setIdEstadoa(art.getIdEstadoa());
		this.setNcedArticulista(art.getNcedArticulista());
		this.setFechaEnvio(art.getFechaEnvio());
		this.setTituloArticulo(art.getTituloArticulo());
		this.setParticipacionArticulo(art.getParticipacionArticulo());
		this.setBreveResumen(art.getBreveResumen());
		this.setPalabrasclave(art.getPalabrasclave());
		this.setPathDocumento(art.getPathDocumento());
	}

	public ArticulosVO getArticulo() {
		return new ArticulosVO(this.getIdArticulo(), this.getIdConv(),
				this.getIdEstadoa(), this.getNcedArticulista(),
				this.getFechaEnvio(), this.getTituloArticulo(),
				this.getParticipacionArticulo(), this.getBreveResumen(),
				this.getPalabrasclave(), this.getPathDocumento());
	}
	
	/** METODO PARA LIMPIAR EL MENSAJE */
	public void limpiar_success(ActionEvent event) {
		setSuccess("");
	}

	public Integer getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Integer idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getIdConv() {
		return idConv;
	}

	public void setIdConv(String idConv) {
		this.idConv = idConv;
	}

	public Integer getIdEstadoa() {
		return idEstadoa;
	}

	public void setIdEstadoa(Integer idEstadoa) {
		this.idEstadoa = idEstadoa;
	}

	public String getNcedArticulista() {
		return ncedArticulista;
	}

	public void setNcedArticulista(String ncedArticulista) {
		this.ncedArticulista = ncedArticulista;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getTituloArticulo() {
		return tituloArticulo;
	}

	public void setTituloArticulo(String tituloArticulo) {
		this.tituloArticulo = tituloArticulo;
	}

	public String getParticipacionArticulo() {
		return participacionArticulo;
	}

	public void setParticipacionArticulo(String participacionArticulo) {
		this.participacionArticulo = participacionArticulo;
	}

	public String getBreveResumen() {
		return breveResumen;
	}

	public void setBreveResumen(String breveResumen) {
		this.breveResumen = breveResumen;
	}

	public String getPalabrasclave() {
		return palabrasclave;
	}

	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}

	public String getPathDocumento() {
		return pathDocumento;
	}

	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
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

	public String getOncomplete() {
		return oncomplete;
	}

	public void setOncomplete(String oncomplete) {
		this.oncomplete = oncomplete;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
