package marvelydc.personajes;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
import java.util.LinkedList;

import marvelydc.eds.Arbol;
import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.SalaDailyPlanet;
import marvelydc.objetos.Arma;
import marvelydc.objetos.ComparadorArmasPoder;

/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public abstract class SuperHeroe extends Personaje {
	
	/**
	 * Arbol de armas
	 */
	public Arbol<Arma> armasHeroe = new Arbol<Arma>();;
	/**
	 * Lista con los villanos capturados
	 */
	LinkedList<Villano> villanosCapturados = new LinkedList<Villano>();

	/**
	 * parametrized constructor
	 * 
	 * @param nombre
	 * @param id
	 */
	public SuperHeroe(String nombre, char id, int turno) {
		super(nombre, id, turno);
	}

	@Override
	public abstract String toString();

	/***
	 * Metodo de interaccion entre el superheroe y el hombre puerta
	 */
	@Override
	public boolean intentarAbrirPortal() {		
		Mapa m = Mapa.getInstancia();
		
		if(salaActual.getIdSala() == m.getDailyPlanet()) {
			if(m.checkPortal()) {
				this.eliminarArma(this.armaFuerte());
				return true;
			}
			System.out.println(this.nombre+" va a intentar abrir el portal");
			System.out.println(this.armasHeroe+" "+this.armasHeroe.cantidadDeElementos());
			SalaDailyPlanet salaPortal = (SalaDailyPlanet) salaActual;
			HombrePuerta doorMan = salaPortal.getHombrePuerta();
			ComparadorArmasPoder comparador = new ComparadorArmasPoder();
			
			if (!armasHeroe.vacio()) {
				Arma armaFrte=this.armaFuerte();
				Arma armaHp = doorMan.buscarArma(armaFrte);
				if(armaHp!=null)
				if (comparador.compare(armaHp, armaFrte) < 0) {
					doorMan.eliminarArma(armaFrte);
				}
				if(!this.eliminarArma(armaFrte)) {
					System.err.println("Fallo al eliminar arma: "+armaFrte);
				}
			}
			System.out.println("La profundidad del hombre puerta es ahora de: "+doorMan.profundidad());
			if (doorMan.profundidad() < salaPortal.getAlturaApertura())
				salaPortal.abrirPortal();
			
			return salaPortal.checkPortal();
		}
		return false;
	}
	
	/***
	 * Metodo de interaccion entre superheroe y villano
	 */
	@Override
	public boolean interactuarCon(Personaje pj) {
		boolean capturado=false;
		if(pj.haActuado)
		if (!this.armasHeroe.vacio()) {
			if (pj instanceof Villano) {
				ComparadorArmasPoder comparator = new ComparadorArmasPoder();
				Villano vil = (Villano) pj;
				
				if(vil.armaPortada!=null) {
					Arma armaHeroe=this.buscarArma(vil.getArmaPortada());
					if(armaHeroe!=null)
						if (comparator.compare(vil.getArmaPortada(),armaHeroe ) < 0) {
							capturado=true;
						}
				}
					
				if (capturado) {
					vil.salirDeSalaActual();
					villanosCapturados.add(vil);
				}
			}
		}
		return capturado;
	}

	/**
	 * @return la arma mas fuerte
	 */
	public Arma armaFuerte() {
		return armasHeroe.getMaximo(new ComparadorArmasPoder());
	}

	/**
	 * Metodo que busca un determinado arma
	 * @param wpn
	 * @return
	 */
	public Arma buscarArma(Arma wpn) {
		return armasHeroe.buscar(wpn);
	}

	/**
	 * Metodo que elimina un determinada arma
	 * @param arma
	 * @return
	 */
	public boolean eliminarArma(Arma arma) {
		if(armasHeroe.pertenece(arma)) {
			armasHeroe.borrar(arma);
			return true;
		}
		return false;
	}

	/**
	 * Metodo de recoleccion de armas
	 */
	@Override
	public boolean recogerArmaDeSalaActual() {
		if (this.salaActual.noQuedanArmas()) {
			return false;
		}else {
			Arma arma = this.salaActual.armaPoderosa();
			Arma copiaEnHeroe = armasHeroe.buscar(arma);
			if (copiaEnHeroe == null) {
				armasHeroe.insertar(arma);
			} else {
				copiaEnHeroe.setPoder(copiaEnHeroe.getPoder() + arma.getPoder());
				armasHeroe.borrar(arma);
				if (armasHeroe.insertar(copiaEnHeroe)) {
					System.out.println(arma);
				} else {
					System.out.println(arma);
				}
			}
			this.salaActual.destruirArmaPoderosa();
			return true;
			}
	}
	
}
