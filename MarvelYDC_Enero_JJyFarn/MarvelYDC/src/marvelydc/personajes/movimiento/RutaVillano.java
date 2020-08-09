package marvelydc.personajes.movimiento;

import java.util.LinkedList;

import marvelydc.mapeado.Mapa;

/**
* Se encarga de gestionar la forma en la que el villano encuentra su ruta 
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class RutaVillano extends GestionDirecciones{

	@Override
	/**
	 * @see GestionDirecciones
	 */
	public void encontrarRuta(int salaInicial) {
		LinkedList<Integer> ruta=Mapa.getInstancia().rutaWallFollowerZurdo(salaInicial);
	    direcciones=Mapa.getInstancia().direccionesDeUnaRuta(ruta);
	}


}
