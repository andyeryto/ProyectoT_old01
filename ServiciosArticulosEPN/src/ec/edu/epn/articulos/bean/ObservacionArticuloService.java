package ec.edu.epn.articulos.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.ObservacionArticulo;
import ec.edu.epn.articulos.entities.RevisorObservacione;

@Local
public interface ObservacionArticuloService {
	
	List<RevisorObservacione> findRevArtbyParams(String idArticulo, String palabrasclave, String titulo, Date fechadesde, Date fechaHasta,
			Integer idEstado, String cedralRev);
	
	
	void insertObservaArticulo(ObservacionArticulo observacion);


	List<Articulo> findRev_ObservacionesByParams(String idArticulo,
			String palabrasclave, String titulo, Date fechadesde,
			Date fechaHasta, Integer idEstado, String idconvoca);


	List<ObservacionArticulo> findObservacionArticulo(String auxAutogenerado);


	List<ObservacionArticulo> findObservacionArticuloExiste(
			String auxAutogenerado, String nidentificacion);
	
	List<String> findemailrevisorObservaciones(String auxautogenerado);


	void deleteObserv(Integer Id);


	List<Integer> findIdObservacion(String auxAutogenerado,
			String estado);

}
