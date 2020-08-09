package marvelydc.eds;
/**
* @version 10/01/2018
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class Pared {
	/**
	 * Pared inicial
	 */
	int origen;
	/**
	 * Pared final
	 */
	int destino;
	/**
	 * Crea una pared entre las dos salas
	 * @param o sala1
	 * @param d sala2
	 */
	public Pared(int o, int d) {
		origen = o;
		destino = d;
	}
	/**
	 * 
	 * @return sala1
	 */
	public int getOrigen() {
		return origen;
	}
/**
 * 
 * @param origen sala1
 */
	public void setOrigen(int origen) {
		this.origen = origen;
	}
/**
 * 
 * @return sala2
 */
	public int getDestino() {
		return destino;
	}
/**
 * 
 * @param destino sala2
 */
	public void setDestino(int destino) {
		this.destino = destino;
	}


	/***
	 *Metodo que devuelve una cadena con los puntos de origen y destino de la pared
	 * @return String
	 */
	@Override
	public String toString() {
		return "Pared [" + origen + "-" + destino + "]";
	}

}
