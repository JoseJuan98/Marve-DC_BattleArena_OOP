package marvelydc.mapeado;
import java.util.Iterator;
import java.util.LinkedList;



import marvelydc.eds.ColaOrdenada;


import marvelydc.objetos.Arma;
import marvelydc.objetos.ComparadorArmasPoder;
import marvelydc.personajes.Personaje;

/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class Sala  {
	
	/**
	 * Numero que identifica a la sala
	 */
	int idSala;
	/**
	 * Numero utilizado para controlar las paredes en el metodo kruskal
	 */
	int marca;
	/**
	 * Numero de veces por la que pasa un camino por esa sala en concreto
	 */
	int frecuencia;
	/**
	 * Lista de los personajes que se encuentran en esta sala
	 */
	public LinkedList<Personaje> pjsEnSala=new LinkedList<Personaje>();
	/**
	 * Lista de las armas que hay en esta sala..
	 */
	public ColaOrdenada<Arma> armas = new ColaOrdenada<Arma>(new ComparadorArmasPoder().reversed());

	public int getMarca() {
		return marca;
	}

	public void setMarca(int marca) {
		this.marca = marca;
	}


	/**
	 * Iterado de la lista de personajes
	 * @return
	 */
	public Iterator<Personaje> getIteradorPersonajes(){
		return pjsEnSala.iterator();
	}
	/**
	 * Constructor parametrizado de sala
	 * @param idSala
	 * @param marca
	 */
	public Sala(int idSala,int marca) {		
		this.idSala = idSala;
		this.marca=marca;
		this.frecuencia=0;
		this.pjsEnSala=new LinkedList<Personaje>();
		this.armas = new ColaOrdenada<Arma>(new ComparadorArmasPoder().reversed());
		
	}
	
	/***
	 * Metodo que devuelve el string con la informacion de la sala
	 */
	@Override
	public String toString() {
		return  idSala + ":" + armas;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	/**
	 * Metodo que inserta el arma de manera que acaba ordenada de mayor a menor
	 * @param arma
	 */
	public void insertaArma(Arma arma) {
		armas.encolar(arma);
	}
	/**
	 * Inserta un array de armas en la coleccion
	 * @param wpns
	 */
	public void configurarArmas(Arma wpns[]) {
		for(int i=0;i<wpns.length;i++) {
			insertaArma(wpns[i]);
		}
	}
	/**
	 * Metodo que devuelve el arma mas poderosa, la primera de la cola en nuestro sistema
	 * O(1)
	 * @return arma
	 */
	public Arma armaPoderosa() {
		if(this.noQuedanArmas())
			return null;
		else
			return armas.primero();
	}
	/**
	 * Metodo que elimina el arma mas poderosa de la sala, por lo que habría salido de ella
	 */
	public void destruirArmaPoderosa() {
		armas.desencolar();
	}
	/**
	 * Metodo que devuelve true si no quedan armas
	 * @return
	 */
	public boolean noQuedanArmas() {
		return this.armas.vacia();
	}
	/**
	 * metodo que inserta un personaje
	 * @param pj
	 */
	public void insertarPj(Personaje pj) {		
		pj.asignarSala(this);
		pjsEnSala.addLast(pj);
	}
	/**
	 * Metodo que elimina un personaje de la sala
	 * @param pj
	 */
	public void eliminarPj(Personaje pj) {
		pjsEnSala.remove(pj);
		pj.asignarSala(null);
	}

	/**
	 * Se encarga en la sala de realizar todas las operaciones que debe
	 * realizar en un turno, estas son:
	 * Que cada personaje actúe(Recoger,Interactuar,Mover,Etc)
	 * @return
	 */
	public boolean update() {			
		boolean finPartida = false;
		Personaje pj=null;
		LinkedList<Personaje> porInteractuar=new LinkedList<Personaje>(pjsEnSala);
		while(!porInteractuar.isEmpty()){
			
			pj=porInteractuar.removeFirst();
			
			if(!pj.haActuado) {
				System.out.println("Llamando acciones de: "+pj.getNombre());
				finPartida = pj.acciones();
			}else {
				System.out.println(pj.getNombre()+"Ya ha actuado");
			}
			
		}
		return finPartida;
	}

	/**
	 * Metodo que resetea la variable de haActuado de cada personaje de la sala al inicio del turno
	 */
	public void resetearPjs() {
		Personaje pj;
		for(int i=0;i<pjsEnSala.size();i++){
			pj=pjsEnSala.get(i);
			pj.haActuado=false;
		}
	}
}
