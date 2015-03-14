package ec.edu.epn.cas.bdd;

import javax.naming.InitialContext;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import ec.edu.epn.seguridad.servicio.ServicioSeguridad;

public class AutenticacionBDD extends  AbstractUsernamePasswordAuthenticationHandler {

	@Override
	protected boolean authenticateUsernamePasswordInternal(
			UsernamePasswordCredentials credentials) throws AuthenticationException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
		System.out.println("usuario: " + username );//+ " credentials " + credentials.getClass().getName());
		//System.out.println("pwd: " + password);
		//System.out.println("pwd: " + getPasswordEncoder().encode(password));
		try {
			InitialContext ctx = new InitialContext();
			ServicioSeguridad ss = (ServicioSeguridad) ctx.lookup("java:global/ServiciosSeguridadEPN/ServicioSeguridadBean!ec.edu.epn.seguridad.servicio.ServicioSeguridad");
			return ss.validarUsuarioPassword(username, password);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
