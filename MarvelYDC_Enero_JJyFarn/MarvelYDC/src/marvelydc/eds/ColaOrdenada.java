package marvelydc.eds;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**
 * Cola de prioridad usada en las salas para almacenar las armas ya que solo tienen que devolver la mayor siempre
 * Siempre va a estar ordenada de mayor a menor por lo que siempre se sacara la de mayor valor,
 * Util especialmente para las armas de la sala
 *  
 * @version 10/01/2018 
 * @author Jose Juan Pena Gomez & Francisco Nunez Sierra
 *         Desarrollo de Programas<br/>
 *         <b>Grupo: JJyFarn </b><br>
 *         <b>Entrega: EC_Final </b><br>
 *         Curso: 2017/2018
 *
 * @param <T>
 */
public class ColaOrdenada<T extends Comparator<T>> {
	/**
	 * Comparador que usara esta cola
	 */
	private Comparator<T> Comparador;
	/**
	 * Almacen de datos de la cola
	 */
	private ArrayList<T> almacen;
	/**
	 * Crea una cola ordenada usando un comparador concreto
	 * @param Comparator<T>
	 * 	Comparator de objeto
	 */
	public ColaOrdenada(Comparator<T> comparador) {
		Comparador=comparador;
		almacen= new ArrayList<T>();
	}
	/**
	 * Inserta un nuevo comparador
	 * @param Comparator<T>
	 * 	Comparator de objeto
	 */
	public void insertarComparador(Comparator<T> comparador) {Comparador=comparador;}
	/**
	 * Añade un dato de manera ordenada
	 * @param T
	 * dato annadido
	 */
	public void encolar(T dato) {almacen.add(dato);
	Collections.sort(almacen, Comparador);}
	/**
	 * Devuelve el valor maximo, el primero
	 * @return T
	 * El maximo
	 */
	public T primero() {return almacen.get(0);}
	/**
	 * Elimina y devuelve el maximo
	 * @return T
	 * 	El maximo
	 */
	public T desencolar() {return almacen.remove(0);}
	/**
	 * Devuelve si esta vacia
	 * @return boolean
	 * 		Vacia
	 */
	public boolean vacia() {return almacen.isEmpty();}
	/**
	 * Devuelve el tamaño de la cola
	 * @return int
	 * Tamanno de cola
	 */
	public int tamanio() {return almacen.size();}
	/**
	 * Develve un iterador sobre los datos
	 * @return Iterator<T>
	 * iterador del dato
	 */
	public Iterator<T> getIterator(){return almacen.iterator();}

	/***
	 *Metodo que muestra los datos de la cola ordenada
	 *@return String
	 */
	@Override
	public String toString(){
		Iterator<T> it=almacen.iterator();
		String cola="";
		while(it.hasNext()) {
			cola+=it.next();
		}
		return cola;
	}
}
