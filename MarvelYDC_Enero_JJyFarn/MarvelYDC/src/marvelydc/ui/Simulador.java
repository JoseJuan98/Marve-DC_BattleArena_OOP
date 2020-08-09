package marvelydc.ui;

import java.util.concurrent.TimeUnit;

import marvelydc.mapeado.Mapa;
/**
 * Hilo unico que se encarga de la simulación actual del mapa y que se puede realizar
 * en paralelo al resto de gestiones de la interfaz
 * 
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class Simulador extends Thread {
		/**
		 * Se encarga crear el hilo y asignar el tablero en el que debe editar los turnos
		 * @param board
		 */
		Simulador(Board board){
			super("hiloUnico");
			this.board=board;
		}
		/**
		 * Muestra si la ejecucion esta activa o no 
		 */
		public boolean isRunning=false;
		/**
		 * Referencia al tablero para poder actualizarlo cada turno
		 */
		Board board;
		@Override
		/**
		 * Metodo que realiza la simulacion una vez que el hilo es lanzado
		 */
		public void run(){
			isRunning=true;
			boolean ganado=false;
			for (int i = 0; i < 50&&!this.isInterrupted()&&!ganado; i++) {
				
				ganado=Mapa.getInstancia().realizarTurno();
				board.update();
				if(!board.fastEnd) {
				try {
					TimeUnit.MILLISECONDS.sleep(600);
				} catch (InterruptedException e) {
					
				}
				}
			}
			isRunning=false;
			
			
		}
}
