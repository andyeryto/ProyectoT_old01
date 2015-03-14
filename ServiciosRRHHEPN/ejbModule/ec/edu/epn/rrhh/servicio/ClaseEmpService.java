package ec.edu.epn.rrhh.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.rrhh.entities.Clasemp;

@Local
public interface ClaseEmpService {

	public Clasemp findClaseEmp(int id);
	
	public List<Clasemp> findAllClaseEmp();
}
