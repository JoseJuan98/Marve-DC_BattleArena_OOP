package marvelydc.mapeado;


import marvelydc.personajes.*;


/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class SalaDailyPlanet extends Sala{
	boolean portal;
	public HombrePuerta hp;
	int alturaApertura;
	/**
	 * parametrized constructor
	 * @param altura
	 */ 
	public SalaDailyPlanet(int idSala,int marca,int altura){
		super(idSala,marca);
		this.alturaApertura=altura;
		this.portal = false;
		this.hp = null;
	}
	/**
	 *metodo que cierra el portal 
	 */
	public void cerrarPortal() {
		this.portal=false;
	}
	/**
	 *metodo que abre el portal 
	 */
	public void abrirPortal() {
		this.portal = true;
	}
	/**
	 * metodo que inserta el hombre puerta en esta sala
	 * @param hp
	 * @param altura
	 */
	public void insertarHombrePuerta(HombrePuerta hp){
		this.hp = hp;	
		if(hp.profundidad()<=alturaApertura) {
			this.portal=true;
		}
	}
	public HombrePuerta getHombrePuerta(){
		return this.hp;
	}
	public int getAlturaApertura() {
		return alturaApertura;
	}
	/**
	 * metodo que comprueba el estado del portal
	 * @return
	 */
	public boolean checkPortal() {
		return portal;
	}	
}
