package marvelydc.personajes.movimiento;

import java.util.LinkedList;

import marvelydc.mapeado.Mapa;

/**
 * Clase que genera y contiene las rutas del SH Extrasensorial
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class RutaSHExtrasensorial extends GestionDirecciones{

	@Override
	/**
	 * @see GestionDirecciones
	 */
	public void encontrarRuta(int salaInicial) {
		LinkedList<Integer> ruta=Mapa.getInstancia().rutaWallFollowerDiestro(salaInicial);
		this.direcciones=Mapa.getInstancia().direccionesDeUnaRuta(ruta);
	    
		
	}

}
