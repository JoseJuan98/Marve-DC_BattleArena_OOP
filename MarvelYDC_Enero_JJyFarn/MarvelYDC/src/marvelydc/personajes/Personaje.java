package marvelydc.personajes;

import java.util.Iterator;

import javax.swing.ImageIcon;

import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.Sala;
import marvelydc.personajes.movimiento.ComportamientoRuta;
import marvelydc.personajes.movimiento.Direccion;
import marvelydc.ui.Resources;

/**
* @version q0/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public abstract class Personaje {
	/**
	 * Cadena con el nombre del personaje
	 */
	String nombre;
	/**
	 *Carecter identificador 
	 */
	char id;
	/**
	 * Turno en el que empieza el personaje a funcionar
	 */
	int turno;
	/**
	 * Sala en la que se encuentra actualmente el personaje
	 */
	Sala salaActual = null;
	/**
	 * Tipo de ruta que sigue este personaje
	 */
	public ComportamientoRuta ruta;
	/**
	 * Imagen para la interfaz grafica
	 */
	public ImageIcon icono = Resources.personaje;
	/**
	 * Booleano que te dice si ha actuado o no en este turno
	 */
	public boolean haActuado = false;

	/**
	 * parametrized constructor public void encontrarRuta();
	 * 
	 * @param nombre
	 * @param id
	 */
	public Personaje(String nombre, char id, int turno) {

		this.nombre = nombre;
		this.id = id;
		this.turno = turno;
	}

	/**
	 * devuelve el nombre del personaje
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * devuelve el caracter del id de personaje
	 * 
	 * @return
	 */
	public char getId() {
		return id;
	}

	@Override
	public abstract String toString();

	/**
	 * cambia el id de un pnj
	 * 
	 * @param id
	 */
	public void setId(char id) {
		this.id = id;
	}

	public void asignarSala(Sala sala) {
		this.salaActual = sala;
	}

	public void salirDeSalaActual() {
		salaActual.eliminarPj(this);
	}
	public boolean seHaMovido;

	public boolean acciones() {
		
		if (this.turno <= Mapa.getInstancia().getTurnoActual()&&this.haActuado==false) {
			System.out.println("Actuando "+this.nombre);
			// Si falla al abrir el portal
			haActuado=true;
			if (!intentarAbrirPortal()) {
				seHaMovido=mover();
				recogerArmaDeSalaActual();
				if(seHaMovido)
					interactuarConSala();
				turno = Mapa.getInstancia().getTurnoActual();

			} else {// Si lo consigue
				salirDeSalaActual();
				turno = Mapa.getInstancia().getTurnoActual();
				Mapa.getInstancia().teseractoGanadores.addLast(this);
				return true;
			}

		}
		haActuado=true;
		return false;
	}

	/**
	 * Metodo del personaje para intentar abrir el portal del hombre puerta si esta
	 * en la sala del mismo
	 * 
	 * @param hp
	 */
	public abstract boolean intentarAbrirPortal();

	/**
	 * Metodo abstracto de cada personaje con su forma de interactuar con otros
	 * personajes
	 * 
	 * @param pj
	 */
	public abstract boolean interactuarCon(Personaje pj);

	public boolean interactuarConSala() {
		Iterator<Personaje> it = salaActual.getIteradorPersonajes();
		boolean haInteractuado = false;
		while (it.hasNext() && !haInteractuado) {
			Personaje pj = it.next();
			if(pj.haActuado)
				haInteractuado = this.interactuarCon(pj);
		}
		return haInteractuado;
	}

	/**
	 * Metodo abstracto para recoger el arma de la sala actual segun los criterios
	 * de cada personaje
	 */
	public abstract boolean recogerArmaDeSalaActual();

	public boolean mover() {
		System.out.println(this.nombre + this.turno + Mapa.getInstancia().getTurnoActual());
		int idActual = this.salaActual.getIdSala();
			

				if (ruta.quedanDirecciones()) {
					this.salirDeSalaActual();
					Direccion d_aux = ruta.direccionActual();
					Mapa m = Mapa.getInstancia();

					int idDestino = 0;
					Sala salaDestino;
					if (d_aux.equals(Direccion.E)) {
						idDestino = (idActual + 1);

					}
					if (d_aux.equals(Direccion.N)) {
						idDestino = (idActual - m.getColumnas());

					}
					if (d_aux.equals(Direccion.W)) {
						idDestino = (idActual - 1);

					}
					if (d_aux.equals(Direccion.S)) {
						idDestino = (idActual + m.getColumnas());

					}
					// Si ha encontrado el destino

					if (!m.hayPared(idActual, idDestino)) {

						salaDestino = m.getSala(idDestino);
						salaDestino.insertarPj(this);

					}
					ruta.sacarDireccion();
					
					return true;
				}
		
		return false;
	}
}
