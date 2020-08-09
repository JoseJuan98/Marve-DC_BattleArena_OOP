package marvelydc.objetos;

import java.util.Comparator;

/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
* El comparator por defecto compara los strings, para comparar por poder y
* para usarlo en las colecciones se usa o el otro o el de esta
*/ 
public class Arma implements Comparable<Arma>, Comparator<Arma> {
	/**
	 * Cadena con el tipo de arma
	 */
	String nombre;
	/**
	 * Numero con el poder del arma
	 */
	int poder;

	/**
	 * default constructor
	 */
	public Arma() {
		this.nombre = "";
		this.poder = 0;
	}

	/**
	 * parametrized constructor
	 * 
	 * @param nombre
	 * @param poder
	 */
	public Arma(String nombre, int poder) {
		this.nombre = nombre;
		this.poder = poder;
	}

	/**
	 * devuelve el nombre del arma
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * cambia el nombre del arma
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * devuelve el poder de un arma
	 * 
	 * @return
	 */
	public int getPoder() {
		return poder;
	}

	/**
	 * cambia el poder de un arma
	 * 
	 * @param poder
	 */
	public void setPoder(int poder) {
		this.poder = poder;
	}
	/**
	 *	Dos armas se consideran iguales si tienen el mismo nombre
	 */
	@Override
	public boolean equals(Object wpn) {
		if(wpn==null)
			return false;
		if(this==wpn)
			return true;
		if(!(wpn instanceof Arma))
			return false;
		//Casting de arma
		Arma arma=(Arma)wpn;
		return this.nombre.equals(arma.nombre);
		
	}
	@Override
	public int compareTo(Arma wpn) {
		return (this.nombre.compareTo(wpn.getNombre()));
	}

	@Override
	public String toString() {
		return "("+this.getNombre() + "," + this.getPoder()+")";
	}

	@Override
	public int compare(Arma o1, Arma o2) {		
		return o1.getNombre().compareTo(o2.getNombre());
	}
}
