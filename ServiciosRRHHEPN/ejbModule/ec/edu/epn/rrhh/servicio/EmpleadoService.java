package ec.edu.epn.rrhh.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.rrhh.entities.Emp;

@Local
public interface EmpleadoService {

	public Emp findEmpleado(int id);
	
	public List<Emp> findAllEmpleado();
	
	public List<Emp> findEmpleadoByParameters(String nced, String apellido);
}
