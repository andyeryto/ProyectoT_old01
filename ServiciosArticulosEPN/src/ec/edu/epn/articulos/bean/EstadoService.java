package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.EstadoA;



@Local
public interface EstadoService {
	List<EstadoA> findAllEstados();

}
