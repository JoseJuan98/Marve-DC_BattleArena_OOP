package marvelydc;

import marvelydc.ui.InterfazGrafica;
/**
 *  Programa principal de de el cual se realiza ejecución 
 * @version 10/1/2018
 * @author Jose Juan Pena Gomez & Francisco Nunez Sierra
 *         Desarrollo de Programas<br/>
 *         <b>Grupo: JJyFarn </b><br>
 *         <b>Entrega: Entrega final </b><br>
 *         Curso: 2017/2018
 */
public class Main {
	/**
	 * Metodo de ejecucion principal
	 * @param args Por parametro se puede pasar el nombre del programa
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		InterfazGrafica ui;
		if(args.length>0)
			ui=new InterfazGrafica(args[0]);
		else
			ui=new InterfazGrafica("MarvelVSDC");
		
	}

}
