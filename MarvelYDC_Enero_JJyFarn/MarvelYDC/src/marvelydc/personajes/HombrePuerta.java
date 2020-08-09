package marvelydc.personajes;


import marvelydc.eds.Arbol;
import marvelydc.objetos.Arma;
import marvelydc.objetos.ComparadorArmasPoder;


/**
* @version 10/10/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class HombrePuerta{

	/**
	 * Arbol de armas del Hombre Puerta
	 */
	Arbol<Arma> armasHP=new Arbol<Arma>();
	/**
	 *inserta las armas del hombre puerta 
	 * @param armasPuerta
	 */
	public void configurarArmas(Arma [] armasPuerta) {
		int i;
		for(i=0 ; i < armasPuerta.length; i++) {
			if(!this.insertaArma(armasPuerta[i]))
				System.out.println(armasPuerta[i]+" no insertada");
		}
	}
		
	/**
	 * @return
	 */
	public int profundidad() {
		return armasHP.profundidad();
	}

	/**
	 * Metodo que devuelve el arma mas fuerte del hombre puerta
	 * @return
	 */
	public Arma armaFuerte() {
		
		ComparadorArmasPoder comparador= new ComparadorArmasPoder();
		Arma armaFuerte=armasHP.getMaximo(comparador);		
		return armaFuerte;
	}
	
	/**
	 * @param weapon
	 * @return
	 */
	public boolean insertaArma(Arma weapon) {
		return armasHP.insertar(weapon);
	}

	/**
	 * Metodo que busca un arma del arbol de armas del hombre puerta
	 * @param armabuscada
	 * @return
	 */
	public Arma buscarArma(Arma armabuscada) {
	  return armasHP.buscar(armabuscada);
	}
	/**
	 * Metodo que elimina un determinado arma del arbol del hombre puerta
	 * @param arma
	 * @return
	 */
	public boolean eliminarArma(Arma arma) {
		if(armasHP.pertenece(arma)) {
		armasHP.borrar(arma);
		return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return armasHP+"";
	}	
}
