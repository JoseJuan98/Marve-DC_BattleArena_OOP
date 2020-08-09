package marvelydc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import marvelydc.mapeado.GenAleatorios;
import marvelydc.mapeado.Mapa;

/**
 * Clase encargada de administrar cada sala en la interfaz gráfica
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
@SuppressWarnings("serial")
public class Square extends JPanel{
	/**
	 * Layout para almacenar las etiquetas
	 */
	private BorderLayout layout = new BorderLayout();
	/**
	 * Etiqueta con la id de la sala
	 */
	private JLabel idSquareLabel = new JLabel(" ",JLabel.LEFT);
	/**
	 * Etiqueta con el dato de los pj
	 */
	private JLabel charactersLabel = new JLabel(" ",JLabel.CENTER);
	/**
	 * Array con las paredes para poder poner los bordes correctamente
	 */
	private int walls[] = {1,1,1,1}; // N, W, S, E
	/**
	 * Numero de la sala
	 */
	private int nsala;
	/**
	 * Puntero a la imagen que debe usar para el fondo
	 */
	ImageIcon imagenFondo=null;
	/**
	 * Puntero a la imagen que debe usar para los pj
	 */
	ImageIcon imagenPj=null;
	/**
	 * Constructor del cuadrado que gestiona su id, le crea 4 paredes y le mete 
	 * un edificio de fondo aleatorio o no con una probabilidad de 1/3
	 * @param id 
	 * 			El id de la sala
	 */
	public Square(int id){
		nsala=id;
		this.setLayout(layout);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		setBackground(Color.LIGHT_GRAY);
		if(id==Mapa.getInstancia().getDailyPlanet()) {
			if(Mapa.getInstancia().checkPortal())
				imagenFondo=Resources.dpOpen;
			else
				imagenFondo=Resources.dpClosed;
		}else {
		if(GenAleatorios.generarNumero(3)==1) {
			int nEdificio=GenAleatorios.generarNumero(Resources.edificios.length);
			imagenFondo=Resources.edificios[nEdificio];
		}
		}
		//Configuramos la etiqueta del identificador de sala
		idSquareLabel.setForeground(Color.BLUE);
		Font font = new Font("Helvetica", Font.ITALIC, 10);
		
		idSquareLabel.setFont(font);
		idSquareLabel.setText(Integer.toString(id));
		
		this.add(idSquareLabel, BorderLayout.NORTH);
		
		//Configuramos la etiqueta del robot que hay en cada sala
		charactersLabel.setForeground(Color.BLACK);
		font = new Font("Helvetica", Font.BOLD, 14);
		charactersLabel.setFont(font);
		
			
		this.add(charactersLabel, BorderLayout.CENTER);
	}
	/**
	 * Se le mete un texto y un icono de pj a el cuadrado
	 * @param text Texto de personajes
	 * @param icon Icono de los personajes
	 */
	public void setTextAndIcon(String text,ImageIcon icon){
		imagenPj=icon;
	
		charactersLabel.setText(text);
	}
	/**
	 * Se limpia el texto y el icono del cuadrado
	 */
	public void clearTextAndIcon() {
		imagenPj=null;
		charactersLabel.setText(" ");
	}
	/**
	 * Pone las paredes a un array determinado
	 * @param walls
	 * 				Array de paredes donde un 1 es que hay y un 0 que no
	 */
	public void setWalls(int walls[]){
		this.walls=walls;
	}
	/**
	 * Pinta las paredes poniendo los bordes a las paredes que han sido establecidas con setwalls
	 */
	public void paintWalls(){
		Border border;
		border = BorderFactory.createMatteBorder(walls[0], walls[1], walls[2], walls[3], Color.black);
		this.setBorder(border);		
	}
	 
	@Override
	/**
	 * Sobreescritura del metodo paint que permite en las propias graficas del componente que
	 * pintemos los iconos de pj y de los edificios del fondo
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(imagenFondo==Resources.dpClosed)
			if(Mapa.getInstancia().checkPortal())
				imagenFondo=Resources.dpOpen;
		
		if(imagenFondo!=null) {
			g.setColor(Color.DARK_GRAY);
			g.fillOval(this.getWidth()/8, this.getHeight()/2, this.getWidth()*3/4, this.getHeight()/2);
			g.drawImage(imagenFondo.getImage(), this.getWidth()/4, this.getHeight()/4, this.getWidth()/2, this.getHeight()*5/8, null);
		}
		if(!Mapa.getInstancia().getSala(nsala).noQuedanArmas()) {
			g.drawImage(Resources.salaArma.getImage(), this.getWidth()*3/4, this.getHeight()/16, this.getWidth()/4, this.getHeight()*1/4, null);
		}
		if(imagenPj!=null)
			g.drawImage(imagenPj.getImage(), this.getWidth()/4,this.getHeight()*1/4, this.getWidth()/2, this.getHeight()*3/4, null);
		
	}
	
	
	
}
