package marvelydc.personajes.movimiento;

import java.util.Iterator;
import java.util.LinkedList;
/**
 * Clase que gestiona las direcciones de los personajes
* @version 10/01/2018
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public abstract class GestionDirecciones implements ComportamientoRuta {
		/**
		 * Direcciones que debe de seguir el personaje
		 */
		LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
		@Override
		/**
		 * Convierte en un string la secuencia de direcciones que el personaje debe de seguir
		 */
		public String toString() {
			String str="";
			for (Iterator<Direccion> iterator = direcciones.iterator(); iterator.hasNext();) {
				Direccion direccion =iterator.next();
				
				str+=" " +direccion;
			}
			
			return str;
			
		}
		
		@Override
		/**
		 *metodo que anade una direecion a la lista de direcciones
		 */
		public void addDireccion(Direccion dir) {
			direcciones.add(dir);
		}
				
		@Override
		/**
		 *metodo que devuelve la direccion actual
		 */
		public Direccion direccionActual() {
			return direcciones.getFirst();
		}
		
		@Override
		/**
		 *metodo que que devuelve un booleano con valor true si quedan direcciones por recorrer
		 */
		public boolean quedanDirecciones() {
			return !direcciones.isEmpty();
		}
		
		@Override
		/**
		 * Saca la primera direccion de la lista de direcciones 
		 */
		public void sacarDireccion() {
			direcciones.removeFirst();
		}
		
		@Override
		/**
		 * Se encarga de meter en direcciones la ruta que tiene que seguir para llegar al
		 * daily planet desde la sala introducida en id inicial este personaje en concreto
		 * @param salaInicial Sala desde la que comienza a moverse
		 */
		public abstract void encontrarRuta(int salaInicial);
}
