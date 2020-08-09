package marvelydc.ui;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.Sala;
/**
 * Panel que contiene el propio tablero y permite ser actualizado segun el estado actual del mapa 
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/

@SuppressWarnings("serial")
public class Board extends JPanel{
	private int dimX;
	private int dimY;
	private int numSquares;
	private GridLayout layout;
	private List<Square> squares = new ArrayList<Square>();
	public boolean fastEnd;
	/**
	 * Inicializa el tablero con unas dimensiones concretas
	 * @param dimY
	 * @param dimX
	 */
	public Board(int dimY, int dimX){
		fastEnd=false;
		this.setBackground(Color.BLACK);
		this.dimX = dimX;
		this.dimY = dimY;
		this.numSquares = this.dimX * this.dimY;
		layout =new GridLayout(this.dimY,this.dimX);
		this.setLayout(layout);
		for (int i = 0; i < numSquares; i++) {
			Square s=new Square(i);
			squares.add(s);
			this.add(s);
		}
		
	}
	
	
	public void drawWallsTo(int idSala,boolean walls[]) {
		int pareds[]=new int[4];
		for(int i=0;i<4;i++) {
			if(walls[i]) {
				pareds[i]=1;
		}else
				pareds[i]=0;
		}
		squares.get(idSala).setWalls(pareds);
		
	}
	
	
	/**
	 * Se encarga de pintar las paredes de todos los cuadrados del mapa
	 */
	public void paintWalls(){
		for (Square square : squares) {
			square.paintWalls();
		}
	}
	/**
	 * Se encarga de actualizar cada turno los personajes de cada sala, las paredes ya estan
	 * asi que no las modifica
	 */
	public void update() {
		Sala salaAct;
		for(int i=0;i<numSquares;i++) {
			salaAct=Mapa.getInstancia().getSala(i);
			if(salaAct.pjsEnSala.size()>1) {
				squares.get(i).setTextAndIcon(Integer.toString(salaAct.pjsEnSala.size()), Resources.multitud);
			}else
				if(salaAct.pjsEnSala.size()==1) {
					squares.get(i).setTextAndIcon(Character.toString(salaAct.pjsEnSala.getLast().getId()), salaAct.pjsEnSala.getLast().icono);
				}else {
					squares.get(i).clearTextAndIcon();
				}
		}
	}

	


}
