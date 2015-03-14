package ec.edu.epn.articulos.bean;

import javax.ejb.Local;

@Local
public interface UsuarioService {

	public int buscarCedulaUsuario(Integer idUser); 
	
}
