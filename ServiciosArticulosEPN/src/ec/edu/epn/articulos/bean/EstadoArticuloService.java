package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.EstadoA;

@Local
public interface EstadoArticuloService {
	List<EstadoA> findAllEstadoA();
	void insertEstadoA (EstadoA estA);
	void updateEstadoA (EstadoA estA);
	void deleteEstadoA (EstadoA estA);
	List<EstadoA> findEstadoAIngresado();
	EstadoA findTipoEstado(int idEstadoa);
	

}
