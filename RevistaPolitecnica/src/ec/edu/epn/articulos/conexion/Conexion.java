package ec.edu.epn.articulos.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
	private Connection con = null;
	private DataSource ds = null;

	// para cargar desde el archivo properties.xml
	private Properties p = new Properties();

	public Connection getCon() {
		Context ctx;
		try {
			ctx = new InitialContext();
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup(getP()
					.getProperty("dataSourceName"));

			con = ds.getConnection();
			return con;
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void cerrarConexion(Connection con1, PreparedStatement ps1) {
		//System.out.println("Clase conexion metodo cerrar");
		//System.out.println("Valor de con Antes: "+con1);
		//System.out.println("Valor de ps Antes: "+ps1);
		try {
			if (ps1 != null)
				ps1.close();
			//System.out.println("Valor de ps Despues: "+ps1);

			if (con1 != null)
				con1.close();
			//System.out.println("Valor de con Despues: "+con1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Metodo para traer la fecha actual
	public String traerFechaActual() {
		String auxFecha = " ";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			//System.out.println("Estamos en el metodo TRAER FECHA ACTUAL");
			con = getCon();
			if (con != null) {
				ps = con.prepareStatement("select to_char"
						+ "(current_date,'dd/MM/yyyy')");
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					auxFecha = rs.getString(1);
				}

				return auxFecha;

			} else {
				System.out.println("Error en la conexion");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			cerrarConexion(con, ps);
		}
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public Properties getP() {
		try {
			p.loadFromXML(new FileInputStream("c:\\properties.xml"));
			return p;
		} catch (InvalidPropertiesFormatException e1) {
			e1.printStackTrace();
			return null;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}

	}

	public void setP(Properties p) {
		this.p = p;
	}

}
