package marvelydc.personajes;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.SalaDailyPlanet;
import marvelydc.objetos.Arma;
import marvelydc.objetos.ComparadorArmasPoder;

import marvelydc.personajes.movimiento.RutaVillano;
import marvelydc.ui.Resources;

/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class Villano extends Personaje {
	@Override 
	public String toString() {
		String vil = "(villain:" + this.id + ":";
		if (salaActual != null)
			vil += this.salaActual.getIdSala() + ":" + turno + ":";
		else
			vil += 1111 + ":" + turno + ":";
		if (armaPortada != null)
			vil += armaPortada + ")";
		else
			vil += ")";
		return vil;
	}

	/**
	 * Arma del villano
	 */
	Arma armaPortada;

	public Arma getArmaPortada() {
		return armaPortada;
	}

	/**
	 * Crea el villano, que solo necesita de los parametros de personaje para ser
	 * creado
	 * 
	 * @param nombre
	 * @param id
	 * @param armaPortada
	 */
	public Villano(String nombre, char id, int turno, int idSalaInicial) {
		super(nombre, id, turno);
		icono = Resources.pjVil;
		this.armaPortada = null;
		ruta = new RutaVillano();
		ruta.encontrarRuta(idSalaInicial);
	}

	public void setArma(Arma arma) {
		this.armaPortada = arma;
	}

	/**
	 * Metodo de interaccion del villano con el hombre puerta
	 */
	@Override
	public boolean intentarAbrirPortal() {
		Mapa m = Mapa.getInstancia();
		if (salaActual.getIdSala() == m.getDailyPlanet()) {
			System.out.println(this.nombre+" trata de abrir el portal");
			if(Mapa.getInstancia().checkPortal())
				return true;
			SalaDailyPlanet salaPortal = (SalaDailyPlanet) salaActual;
			HombrePuerta doorMan = salaPortal.getHombrePuerta();
			ComparadorArmasPoder comparador = new ComparadorArmasPoder();
			if (armaPortada != null) {
				if (comparador.compare(armaPortada, doorMan.armaFuerte()) >= 0) {
					System.out.println("Ha roto: "+doorMan.armaFuerte());
					System.out.println(doorMan.armasHP.cantidadDeElementos());
					if(!doorMan.eliminarArma(doorMan.armaFuerte())) {
						System.out.println("No se ha podido romper");
					}
				}
			}
			System.out.println("La profundidad es de: "+doorMan.profundidad());
			if (doorMan.profundidad() < salaPortal.getAlturaApertura())
				salaPortal.abrirPortal();
			return salaPortal.checkPortal();
		}
		return false;
	}

	/**
	 * Metodo de interaccion entre el villano y un superheroe
	 */
	@Override
	public boolean interactuarCon(Personaje pj) {
		if(pj.haActuado)
		if (this.armaPortada != null) {
			if (pj instanceof SuperHeroe) {
				ComparadorArmasPoder comparator = new ComparadorArmasPoder();
				SuperHeroe superh = (SuperHeroe) pj;
				if (!superh.armasHeroe.vacio()) {
					Arma armaHeroe = superh.buscarArma(this.armaPortada);
					if (armaHeroe != null) {
						if (comparator.compare(armaHeroe, armaPortada) < 0) {
							superh.eliminarArma(armaHeroe);

						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo de recoleccion de armas
	 */
	@Override
	public boolean recogerArmaDeSalaActual() {
		ComparadorArmasPoder comparator = new ComparadorArmasPoder();
		Arma arma = salaActual.armaPoderosa();
		if (arma != null) {
			if (armaPortada == null) {
				armaPortada = arma;
				salaActual.destruirArmaPoderosa();
			} else {
				if (comparator.compare(armaPortada, arma) == -1) {
					salaActual.destruirArmaPoderosa();
					salaActual.insertaArma(armaPortada);
					armaPortada = arma;
					return true;
				}
			}
		}
		return false;
	}

}
