package marvelydc.personajes.movimiento;

import java.util.LinkedList;
/**
 * Interfaz que engloba el comportamiento que un gestor de rutas debe seguir
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public interface ComportamientoRuta {
	/**
	 * Lista de direcciones que debe haber
	 */
	LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
	/**
	 * Encontrar la ruta que debe seguir el personaje
	 * @param salaInicial
	 */
	public void encontrarRuta(int salaInicial);
	/**
	 * Añade una nueva direccion a la lista de direcciones del movible
	 * @param dir
	 */
	public void addDireccion(Direccion dir);
	/**
	 * Devuelve la direccion actual
	 * @return Direccion siguiente
	 */
	public Direccion direccionActual();
	/**
	 * Saca la direccion actual de la lista
	 */
	public void sacarDireccion();
	/**
	 * Dice si aun quedan direcciones en la lista
	 * @return Si quedan direcciones true
	 */
	public boolean quedanDirecciones();
}
