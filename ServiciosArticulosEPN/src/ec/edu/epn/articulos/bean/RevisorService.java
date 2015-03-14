package ec.edu.epn.articulos.bean;


import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.Revisor;
import ec.edu.epn.articulos.entities.RevisorObservacione;
import ec.edu.epn.articulos.entities.TipoRev;


@Local
public interface RevisorService {
	
	List<RevisorObservacione> getRevisorObservaciones(Integer id);
	List<Revisor> findRevisorbyParams(String cedula, String apellidos, String nombres);
	void guardarRevisorObservaciones(RevisorObservacione revObs);
	List<RevisorObservacione> getRevisorArticuloExiste(Integer IdArtc,
			Integer idrev);
	
	void eliminarRevisor(Revisor revisor);

	void guardarRevisor(Revisor revisor);

	void updateRevisor(Revisor revisor);

	Revisor findRevisor(int idRevisor);

	List<Revisor> findAllRevisor();

	List<TipoRev> finfAllTiposRevisor();

	TipoRev findTipoRevisor(int id);

	boolean isRevisorRegistered(String nIdentificacion);

	List<Revisor> findRevisosrByParams(String nIDentificacion,
			String nombres[], String lugarTrabajo);
	
	
	

}
