package ec.edu.epn.articulos.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.edu.epn.articulos.entities.Articulo;
import ec.edu.epn.articulos.entities.ObservacionArticulo;

@Local
public interface ArticuloService {
	
	List<Articulo> findarticulobyParams(String idArticulo, String palabrasclave, String titulo, Date fechadesde, Date fechaHasta,Integer idEstado);
	//Sary
	List<Articulo> findarticulobyParams(Integer idArticulo, String palabrasclave, String titulo, Date fechadesde, Date fechaHasta,Integer idEstado);
	
	void insertArticuloDocente(Articulo articulo);
	
	void updateArticuloDocente(Articulo articulo);
	
	void deleteArticuloDocente(Articulo articulo);
	
	int maxCodArticulo();
	List<ObservacionArticulo> observacionesSI(Integer idarticulo);
	void updateArticulo(Articulo art);
	List<Articulo> findarticuloVolumen(Integer idvolumen);
	

}
