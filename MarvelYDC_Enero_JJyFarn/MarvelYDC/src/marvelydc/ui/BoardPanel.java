package marvelydc.ui;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import marvelydc.cargador.FicheroCarga;
import marvelydc.mapeado.Mapa;

/**
 * Pestaña de el panel que contiene el propio tablero y sus acciones 
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	//Dimensiones del tablero
	final static short dimX = 6;
	final static short dimY = 6;
	//Componentes de parte superior del panel: tabla de personajes
	private JTable table;
	private JScrollPane scroll;
	//Componentes centrales: tablero
	private Board board;
	//Componentes parte inferior: panel con dos botones
	private JPanel lowerPanel = new JPanel();
	public JButton bGenerate = new JButton (new ImageIcon("images/laberinto.png"));
	private JButton bSimulate = new JButton (new ImageIcon("images/pause-play-button.png"));
	private JButton bFast = new JButton (new ImageIcon("images/fast.png"));
	//Layout del panel principal
	private GridBagLayout layout = new GridBagLayout();
	Mapa map;
	//Referencia al panel de texto de donde carga los datos
	FilePanel panelInit;


	private Object[][] charactersData= new Object[10][4];
	int numPersonajes=0;
	/**
	 * Añade un personaje con sus atributos a la tabla
	 * @param tipo
	 * @param id
	 * @param nombre
	 * @param turno
	 */
	public void addCharacter(String tipo,char id, String nombre, int turno) {
		Object[] pers= {tipo,id,nombre,turno};
		charactersData[numPersonajes]=pers;
		numPersonajes++;
	}
	/**
	 * Limpia la lista de personajes
	 */
	public void clearCharacters() {
		charactersData=new Object[10][4];
		numPersonajes=0;
		initCharactersList();
	}
	private String [] tableRows = {"Type","Mark","Name","Turn"};
	/**
	 * Constructor del panel que añade una referencia al init para poder carga usando
	 * el texto del init en vez del fichero y asi cambiar en tiempo real
	 * @param init
	 */
	public BoardPanel(FilePanel init){
		
		panelInit=init;
		initCharactersList();
		
		initLowerPanel();
		addComponents();
	}
	/**
	 * Inicializa la lista de personajes
	 */
	public void initCharactersList(){
		table = new JTable(charactersData,tableRows);
		scroll = new JScrollPane (table);
	}

	/**
	 * Inicializa los botones de abajo y llama a la funcion que pone sus acciones
	 */
	public void initLowerPanel(){
		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(bGenerate);
		lowerPanel.add(bSimulate);
		lowerPanel.add(bFast);
		bGenerate.setToolTipText("Create map");
		bGenerate.setEnabled(false);
		
		bSimulate.setToolTipText("Init simulation");
		bSimulate.setEnabled(false);
		bFast.setToolTipText("Fast simulation");
		bFast.setEnabled(false);
		//Añadir acciones sobre los botones
		addButtonActions();
	}
	/**
	 * Prepara la layout y sus componentes 
	 */
	public void addComponents(){
		this.removeAll();
		this.setLayout(layout);
		GridBagConstraints cons = new GridBagConstraints();
		
		// Añadiendo una etiqueta antes de lista de personajes
		JLabel lCharacters = new JLabel("List of characters: ");
		cons.gridx=0;
		cons.gridy=0;
		this.add(lCharacters,cons);
		
		// Añadiendo la tabla de personajes
		cons.weighty=1;
		cons.weightx=1;
		
		cons.gridx=0;
		cons.gridy=1;
		cons.ipady=60;
//		cons.gridwidth=2;
		cons.fill = GridBagConstraints.BOTH;
		this.add(scroll,cons);
		
		// Añadiendo el tablero de salas
		
		cons.gridx=0;
		cons.gridy=2;
		cons.ipady=150;
		if(board!=null)
		this.add(board, cons);
		// Añadiendo el panel inferior con los botones
		cons.gridx=0;
		cons.gridy=3;
		cons.ipady=10;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.PAGE_END; 
		this.add(lowerPanel,cons);
		
	
	}
	/**
	 * Añade todos los botones de accion que permiten las opciones del mapa
	 */
	public void addButtonActions(){
		bGenerate.addActionListener(new ActionListener() {
			//CLASE ANONIMA, MOLA
			@Override
			public void actionPerformed(ActionEvent e) {
				
				InterfazGrafica.getInstancia().logFilePanel.setText("");
				if(simulador!=null) {
					
				if(simulador.isRunning) {
				simulador.interrupt();
				
				}
				simulador=null;
				}
				bFast.setEnabled(false);
				simulando=true;
				
				
				try {
					generateBoard();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		bSimulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bGenerate.setEnabled(false);
				if(simulador==null) {
					simulador=new Simulador(board);
					simulador.start();
					bFast.setEnabled(true);
					
				}else {
					simulando=!simulando;
					if(simulando)
						simulador.resume();
					else {
						simulador.suspend();
					}
					
				}
			}
		});
		bFast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				simulador.resume();
				bGenerate.setEnabled(true);
				bSimulate.setEnabled(false);
				board.fastEnd=true;
				bFast.setEnabled(false);
			}
		});
	}
	boolean simulando=false;
	public Simulador simulador=null;
	/**
	 * Genera el tablero para que simule a la instancia inicial del mapa
	 * y pinte correctamente sus paredes y pjs
	 * @throws Exception
	 */
	public void generateBoard() throws Exception {
		Mapa.reiniciar();
		map=Mapa.getInstancia();
		FicheroCarga carga=new FicheroCarga();
		try {
			carga.loadInitField(panelInit.getTexto());
		} catch (Exception e) {
			throw e;
		}
		map.pintarDireccionesPjs();
		int dimY=map.getFilas();
		int dimX=map.getColumnas();
		int numSalas=dimY*dimX;
	
		board=new Board(dimY,dimX);
		//Cargar gif portal

		
		for (int i = 0; i < numSalas; i++) {
			
			board.drawWallsTo(i, map.paredesDeUnaSala(i));
		}
		addComponents();
		board.paintWalls();
		bSimulate.setEnabled(true);
		
	}
	
	/**
	 * Actualiza el tablero para que muestre la info que ahora tenga el mapa
	 */
	public void updateBoard(){
		this.board.update();
	}
}
