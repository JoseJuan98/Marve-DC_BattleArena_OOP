package marvelydc.eds;

import java.util.Comparator;

/**
 * Implementacion de arbol binario de busqueda.
 * 
 * @version 10/01/2018
 * @author Jose Juan Pena Gomez y Francisco Nunez Sierra Asignatura
 *         Desarrollo de Programas<br/>
 *         <b>Grupo: JJyFarn </b><br>
 *         <b>Entrega: EC_Final </b><br>
 *         Curso: 2017/2018
 */
public class Arbol<T extends Comparable<T>> {
	/** Dato almacenado en cada nodo del Arbol. */
	private T datoRaiz;

	/** Atributo que indica si el Arbol esta vacio. */
	boolean esVacio;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;
	
	/**
	 * Constructor por defecto de la clase. Inicializa un Arbol vacio.
	 */
	public Arbol() {
		datoRaiz = null;
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	

	/**
	 * Devuelve el hijo izquierdo del Arbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol<T> getHijoIzq() {
		return hIzq;
	}

	/**
	 * Devuelve el hijo derecho del Arbol
	 *
	 * @return Hijo derecho del Arbol
	 */
	public Arbol<T> getHijoDer() {
		return hDer;
	}

	/**
	 * Devuelve la raiz del Arbol
	 *
	 * @return La raiz del Arbol
	 */
	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el Arbol esta vacio.
	 * 
	 * @return verdadero si el Arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el Arbol.
	 *
	 * @param dato
	 *            El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso
	 *         contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if(dato!=null) {
		if (datoRaiz==null) {
			datoRaiz = dato;
			esVacio = false;
		} else {
			if (!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
				if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
					if (getHijoIzq() == null) {
						hIzq = new Arbol<T>();
					}
					aux=hIzq;
				} else { // dato > datoRaiz
					if (getHijoDer() == null) {
						hDer = new Arbol<T>();
					}
					aux=hDer;
					
				}
				resultado = aux.insertar(dato);
				
			} else
				resultado = false;
		}
		}else {
			resultado=false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el Arbol
	 *
	 * @param dato
	 *            El dato a buscar
	 * @return verdadero si el dato se encuentra en el Arbol, falso en caso
	 *         contrario
	 */
	public boolean pertenece(T dato) {
		Arbol<T> aux = null;
		boolean encontrado = false;
		if (!vacio()) {
			if (this.datoRaiz.equals(dato))
				encontrado = true;
			else {
				if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
					aux = getHijoIzq();
				else // dato > datoRaiz
					aux = getHijoDer();
				if (aux != null)
					encontrado = aux.pertenece(dato);
			}
		}
		return encontrado;
	}

	/**
	 * Busca si un dato ya existe en la lista
	 * 
	 * @param datoABuscar
	 * @return el dato a buscar
	 */ 
	public T buscar(T datoABuscar) { 
		Arbol<T> aux = null;
		T dato = null;
		if (this.datoRaiz != null) {
			if (this.datoRaiz.compareTo(datoABuscar) == 0) {

				return this.datoRaiz;
			} else {
				if (datoABuscar.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
					aux = getHijoIzq();
				else // dato > datoRaiz
					aux = getHijoDer();
				if (aux != null)
					dato = aux.buscar(datoABuscar);
			}
		}
		return dato;
	}
	/**
	 * Borrar un dato del Arbol.
	 *
	 * @param dato El dato que se quiere borrar
	 */
	public void borrar(T dato){
	    if (!vacio()) {
	    	
	        if (dato.compareTo(this.datoRaiz)<0&&hIzq!=null){			//dato<datoRaiz
					hIzq = hIzq.borrarOrden(dato);
			}	
	        else
	            if (dato.compareTo(this.datoRaiz)>0&&hDer!=null) {		//dato>datoRaiz 
	            		hDer = hDer.borrarOrden(dato);
				}
	            else //En este caso el dato es datoRaiz
	            {
	                if (hIzq==null && hDer==null)
	                {
	                	this.datoRaiz=null;
	                	esVacio=true;
	                }
	                else
	                	borrarOrden(dato);
	            }
	    }
	}
	

	/**
	 * Borrar un dato. Este metodo es utilizado por el metodo borrar anterior.
	 *
	 * @param dato El dato a borrar
	 * @return Devuelve el Arbol resultante despues de haber realizado el borrado
	 */
	private Arbol<T> borrarOrden(T dato)
	{
	    T datoaux;
	    Arbol<T> retorno=this;
	    Arbol<T> aborrar, candidato, antecesor;

	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0&&hIzq!=null){		// dato<datoRaiz
		    	        hIzq = hIzq.borrarOrden(dato);
	        }
			else
	            if (dato.compareTo(this.datoRaiz)>0&&hDer!=null) {	// dato>datoRaiz
	    	           hDer = hDer.borrarOrden(dato);
	            }
				else {
	                aborrar=this;
	                if ((hDer==null)&&(hIzq==null)) { /*si es hoja*/
	                    retorno=null;
	                }
	                else {
	                    if (hDer==null) { /*Solo hijo izquierdo*/
	                        aborrar=hIzq;
	                        datoaux=this.datoRaiz;
	                        datoRaiz=hIzq.getRaiz();
	                        hIzq.datoRaiz = datoaux;
	                        hIzq=hIzq.getHijoIzq();
	                        hDer=aborrar.getHijoDer();

	                        retorno=this;
	                    }
	                    else
	                        if (hIzq==null) { /*Solo hijo derecho*/
	                            aborrar=hDer;
	                            datoaux=datoRaiz;
	                            datoRaiz=hDer.getRaiz();
	                            hDer.datoRaiz = datoaux;
	                            hDer=hDer.getHijoDer();
	                            hIzq=aborrar.getHijoIzq();

	                            retorno=this;
	                        }
	                        else { /* Tiene dos hijos */
	                            candidato = this.getHijoIzq();
	                            antecesor = this;
	                            while (candidato.getHijoDer()!=null) {
	                                antecesor = candidato;
	                                candidato = candidato.getHijoDer();
	                            }

	                            /*Intercambio de datos de candidato*/
	                            datoaux = datoRaiz;
	                            datoRaiz = candidato.getRaiz();
	                            candidato.datoRaiz=datoaux;
	                            aborrar = candidato;
	                            if (antecesor==this)
	                                hIzq=candidato.getHijoIzq();
	                            else
	                                antecesor.hDer=candidato.getHijoIzq();
	                        } //Eliminar solo ese nodo, no todo el subarbol
	                    aborrar.hIzq=null;
	                    aborrar.hDer=null;
	                }
	            }
	    }
	    return retorno;
	}

	/**
	 * Recorrido inOrden del Arbol. Muestra todos los elementos del arbol
	 * separados por un guion
	 */
	@Override
	public String toString() {
		String arbol="";
		Arbol<T> aux = null;
		if (this.datoRaiz!=null) {
			if ((aux = getHijoIzq()) != null) {
				arbol+=aux.toString();
			}
			
			arbol+=this.datoRaiz.toString();
			if ((aux = getHijoDer()) != null) {
				arbol+=aux.toString();
			}
		} 
		return arbol;
	}

	/**
	 * metodo que cuenta la cantidad de elementos que tiene el arbol
	 * 
	 * @return
	 */
	public int cantidadDeElementos() {
		Arbol<T> aux = this;
		int cantidad = 0;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				cantidad = aux.cantidadDeElementos() + cantidad;
			}

			cantidad++;

			if ((aux = getHijoDer()) != null) {
				cantidad = aux.cantidadDeElementos() + cantidad;
			}
		}
		return cantidad;
	}

	/**
	 * devuelve el elemento maximo segun un criterio concreto de comparacion
	 * 
	 * @return el elemento mayor
	 */
	public T getMaximo(Comparator<T> comp) {
		T maximo = null;
		T Aux;

		if (!this.esVacio) {
			
			if ((getHijoIzq()) != null) {
				
				Aux = getHijoIzq().getMaximo(comp);
				maximo=Aux;
				
			}
			if(maximo!=null) {
				if(comp.compare(datoRaiz, maximo)>0)
					maximo=datoRaiz;
			}else
				maximo=datoRaiz;
			if ((getHijoDer()) != null) {
				Aux = getHijoDer().getMaximo(comp);
				if (Aux != null)
					if (comp.compare(Aux, maximo) > 0)
						maximo = Aux;
			}
		}
		return maximo;
	}

	/**
	 * metodo que calcula la profundidad de un Arbol
	 * 
	 * @return
	 */
	public int profundidad() {
		int max=0;
		if (this.esVacio)
			return 0;
		else {
			int profDer = 0;
			int profIzq = 0;
			if (this.hDer != null)
				profDer = this.hDer.profundidad();
			if (this.hIzq != null)
				profIzq = this.hIzq.profundidad();
			
			if (profDer >= profIzq)
				max = profDer;
			else
				max = profIzq;
			max++;
			return max;

		}

	}
}

