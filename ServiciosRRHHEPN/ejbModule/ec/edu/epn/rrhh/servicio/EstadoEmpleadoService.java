package ec.edu.epn.rrhh.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.rrhh.entities.Estemp;

@Local
public interface EstadoEmpleadoService {

	public Estemp findEstadoEmpleado(int id);
	
	public List<Estemp> findAllEstadoEmpleado();
}
