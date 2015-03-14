package ec.edu.epn.articulos.bean;

import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.AreaInvestigacion;

@Local
public interface AreaInvestigacionService {
	
	List<AreaInvestigacion> findAllAreasInv();

}
