package marvelydc.personajes;

import marvelydc.personajes.movimiento.RutaSHExtrasensorial;
import marvelydc.ui.Resources;
/**
* @version 10/1/2018
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class SHExtraSensorial extends SuperHeroe {

	/**
	 * Constructor parametrizado
	 * @param nombre
	 * @param id
	 * @param turno
	 * @param idSalaInicial
	 */
	public SHExtraSensorial(String nombre, char id, int turno, int idSalaInicial) {
		super(nombre, id, turno);
		icono=Resources.pjPsy;
		ruta=new RutaSHExtrasensorial();
		ruta.encontrarRuta(idSalaInicial);
	}

	@Override
	public String toString() {
		if(salaActual!=null)
			return "(shextrasensorial:"+this.id+":"+this.salaActual.getIdSala()+":"+turno+":"+ this.armasHeroe+")";
		else
			return "(shextrasensorial:"+this.id+":"+1111+":"+turno+":"+ this.armasHeroe+")";
	}

}
