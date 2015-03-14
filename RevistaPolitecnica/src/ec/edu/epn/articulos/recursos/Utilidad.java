package ec.edu.epn.articulos.recursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ec.edu.epn.articulos.conexion.Conexion;
import ec.edu.epn.seguridad.VO.SesionUsuario;

public class Utilidad extends Conexion {
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpSession session = request.getSession(true);
	SesionUsuario su = (SesionUsuario) session.getAttribute("sesionUsuario");	
	
	private String nombUsuario;
	
	
	/** Inicial Unidad */
	public String consultarInicialUnidad()  {
		Connection con = null;
		PreparedStatement ps = null;
		String codUnidad = "";
		
		try {
			con = super.getCon();
			if (con != null) {
				ps = con.prepareStatement("select codigo_u FROM " +
										" \"Laboratorios\".unidad u, " +
										" \"Laboratorios\".personal p, " +
										" usuario us where " +
										" u.id_unidad = p.id_unidad and " +
										" p.id_personal = us.id_personal and " +
										" us.id_usuario = ?");
				
				ps.setLong(1, su.id_usuario_log);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					codUnidad = rs.getString(1);
				}
				System.out.print("COOOODIGUITO: " + codUnidad);
				return codUnidad;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			super.cerrarConexion(con, ps);
		}
	}
	
	
	/** Consultar Perfil de Usuario */
	public ArrayList<String> consultarPerfilesUsuario()  {
		Connection con = null;
		PreparedStatement ps = null;
		
		su.getPerfiles();
		su.getPerfilSeleccionado();
		
		System.out.println("Lista de perfiles desde session de usuario: " + su.getPerfiles());
		System.out.println("perfile desde session de usuario: " + su.getPerfilSeleccionado());
		
		try {
			con = super.getCon();
			if (con != null) {
				ps = con.prepareStatement("select id_perfil from perfil_usuario where id_usuario=?");
				
				ps.setLong(1, su.id_usuario_log);
				ResultSet rs = ps.executeQuery();
				
				ArrayList<String> listaPerfilesUsuario = new ArrayList<String>();
				
				while (rs.next()) {
					listaPerfilesUsuario.add(rs.getString(1));
				}
					
				System.out.print("Perfiles de Usuario: " + listaPerfilesUsuario);
				return listaPerfilesUsuario;
				
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			super.cerrarConexion(con, ps);
		}
	}
	
	
	/** Consultar Perfil de Usuario Seleccionado*/
	public String consultarPerfilUsuarioSelec(){
		su.getPerfilSeleccionado();
		System.out.println("perfile desde session de usuario: " + su.getPerfilSeleccionado());
		return su.getPerfilSeleccionado();
	}
	
	public long idUsuarioLog() {
		long idUsuarioLog = 0;
		idUsuarioLog = su.id_usuario_log;
		return idUsuarioLog;
	}
	
	public int idUnidad() {
		int idUnidadUsuarioLog = 0;
		idUnidadUsuarioLog = su.UNIDAD_USUARIO_LOGEADO;
		return idUnidadUsuarioLog;
	}
	
	public String idSessionU() {
		String idSessionUsuario = "";
		idSessionUsuario = session.getId();
		return idSessionUsuario;
	}
	
	public String nombreUsuario() {
		String nombreUsuario = "";
		nombreUsuario = su.nombre_usuario_logeado;
		return nombreUsuario;
	}


	public String getNombUsuario() {
		nombUsuario = su.nombre_usuario_logeado;
		return nombUsuario;
	}


	public void setNombUsuario(String nombUsuario) {
		this.nombUsuario = nombUsuario;
	}
}
