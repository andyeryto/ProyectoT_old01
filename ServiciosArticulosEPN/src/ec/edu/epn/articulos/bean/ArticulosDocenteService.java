package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Local;
import ec.edu.epn.articulos.entities.Articulo;

@Local
public interface ArticulosDocenteService {

	public List<Articulo> getArticulosPorDocente(Integer IdUser);

}
