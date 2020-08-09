package marvelydc.ui;

import javax.swing.ImageIcon;
/**
 * Guarda todos los datos de las rutas de los ficheros de imagen, cambiandola aqui, basta
 * para cambiar la imagen porque luego se cojen de esta clase. Sus atributos son estáticos
 * y no se pueden cambiar durate el programa.
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public final class Resources {
	/**
	 * Almacena los iconos de los edificios
	 */
	public static  ImageIcon[] edificios={new ImageIcon("images/edificios/cityscape.png"),
			new ImageIcon("images/edificios/hospital.png"),
					new ImageIcon("images/edificios/factory.png"),
							new ImageIcon("images/edificios/bank.png")
			};
	/**
	 * Almacena el icono del dp abierto 
	 */
	public static ImageIcon dpOpen=new ImageIcon("images/dailyPlanetAbierto.png");
	/**
	 * Almacena el icono del dp cerrado
	 */
	public static ImageIcon dpClosed=new ImageIcon("images/dailyPlanetCerrado.png");
	/**
	 * Almacena el icono de cuando hay varios pj
	 */
	public static ImageIcon multitud=new ImageIcon("images/personajes/multitud.png");
	/**
	 * Almacena el icono de el pj por defecto
	 */
	public static ImageIcon personaje=new ImageIcon("images/personajes/defaultPj.png");
	/**
	 * Almacena el icono de el pj fisico
	 */
	public static ImageIcon pjPhys=new ImageIcon("images/personajes/SHPhysical.png");
	/**
	 * Almacena el icono del villano
	 */
	public static ImageIcon pjVil=new ImageIcon("images/personajes/Villain.png");
	/**
	 * Almacena el icono del pj psiquico
	 */
	public static ImageIcon pjPsy=new ImageIcon("images/personajes/SHPsych.png");
	/**
	 * Almacena el icono del pj de vuelo
	 */
	public static ImageIcon pjFly=new ImageIcon("images/personajes/SHFlight.png");
	/**
	 * Almacena el icono de cuando hay armas en una sala
	 */
	public static ImageIcon salaArma=new ImageIcon("images/armas.png");
}
