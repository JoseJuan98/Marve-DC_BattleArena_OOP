package marvelydc.ui;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/

import marvelydc.mapeado.GenAleatorios;
/**
 * Interfaz grafica que permite al usuario gestionar las opciones del proyecto
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
@SuppressWarnings("serial")
public class InterfazGrafica extends JFrame {
	// Componente principal, panel con pestañas
	private JTabbedPane tabs = new JTabbedPane();
	// Componentes para cada pestaña del JTabbedPane
	private FilePanel initFilePanel = new FilePanel();
	public FilePanel logFilePanel = new FilePanel();
	public BoardPanel boardPanel = new BoardPanel(initFilePanel);
	// Componentes para la barra de Menús
	private JMenuBar menus = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem openFileOption = new JMenuItem("Open Init File");
	private JMenuItem saveFileOption = new JMenuItem("Save Log File");
	private JMenuItem exitOption = new JMenuItem("Exit");
	private JMenu aleMenu = new JMenu("Aleatorios");
	private JMenuItem resetOption = new JMenuItem("Reset Randoms");
	private static InterfazGrafica gui=null;
	public static InterfazGrafica getInstancia() {
		if (gui == null)
			gui = new InterfazGrafica("Default");
		return gui;
	}
	public InterfazGrafica(String titulo) {
		
		super(titulo);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		if(gui==null)
			gui=this;
		this.setBackground(Color.GRAY);
		tabs.add("Init file", initFilePanel);
		tabs.add("Map", boardPanel);

		tabs.add("Log file", logFilePanel);

		initMenus();

		this.setContentPane(tabs);
		this.setBounds(300, 100, 500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void initMenus() {
		// La opción abrir de archivo debe invocar el método abrirInicio()
		// La opción guardar de archivo debe invocar el método guardarLog()
		// La opción salir de archivo debe invocar el método salir()
		openFileOption.setMnemonic(KeyEvent.VK_O);
		openFileOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		saveFileOption.setMnemonic(KeyEvent.VK_S);
		saveFileOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		exitOption.setMnemonic(KeyEvent.VK_E);
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		openFileOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openInitFile();

			}
		});
		saveFileOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveLogFile();

			}
		});
		exitOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});
		// Salir

		fileMenu.add(openFileOption);
		fileMenu.add(saveFileOption);
		fileMenu.addSeparator();
		fileMenu.add(exitOption);
		resetOption.setMnemonic(KeyEvent.VK_R);
		resetOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		resetOption.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				GenAleatorios.reset();

			}
		});
		aleMenu.add(resetOption);
		menus.add(fileMenu);
		menus.add(aleMenu);
		this.setJMenuBar(menus);
	}

	public void openInitFile() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setDialogTitle("Elige tu archivo de init: ");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int answer = fc.showOpenDialog(this);
		if (answer == JFileChooser.APPROVE_OPTION) {
			initFilePanel.setText("");
			File f = fc.getSelectedFile();

			
			try {
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					initFilePanel.addText(line + "\n");
				}
				fr.close();
			} catch (Exception e) {
			}
		}
		this.boardPanel.bGenerate.setEnabled(true);
	}

	

	public void saveLogFile() {
		try {
			PrintWriter log=new PrintWriter("Registro.log");
			log.println(this.logFilePanel.getTexto());
			log.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void exit() {
		boardPanel.simulando=false;
		System.exit(0);
	}

	
}
