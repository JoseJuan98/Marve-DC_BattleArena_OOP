package marvelydc.cargador;

import java.util.List;

import marvelydc.mapeado.*;
import marvelydc.personajes.Personaje;
import marvelydc.personajes.SHExtraSensorial;
import marvelydc.personajes.SHFlight;
import marvelydc.personajes.SHPhysical;
import marvelydc.personajes.Villano;
import marvelydc.ui.InterfazGrafica;

/**
 * Clase creada para ser usada en la utilidad cargador contiene el main del
 * cargador. Se crea una instancia de la clase Estacion, una instancia de la
 * clase Cargador y se procesa el fichero de inicio, es decir, se leen todas las
 * lineas y se van creando todas las instancias de la simulacion
 * 
 * @version 5.0 - 27/10/2016
 * @author Profesores DP
 */ 
public class Cargador {
	/**
	 * numero de elementos distintos que tendra la simulacion
	 */
	static final int NUMELTOSCONF = 5;
	/**
	 * atributo para almacenar el mapeo de los distintos elementos
	 */
	static private DatoMapeo[] mapeo;

	/**
	 * constructor parametrizado
	 * 
	 * @param e
	 *            referencia a la instancia del patron Singleton
	 */
	Mapa m = Mapa.getInstancia();

	public Cargador() {
		InterfazGrafica.getInstancia().boardPanel.clearCharacters();
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0] = new DatoMapeo("MAP", 5);
		mapeo[1] = new DatoMapeo("SHPHYSICAL", 4);
		mapeo[2] = new DatoMapeo("SHEXTRASENSORIAL", 4);
		mapeo[3] = new DatoMapeo("SHFLIGHT", 4);
		mapeo[4] = new DatoMapeo("VILLAIN", 4);

	}

	/**
	 * busca en mapeo el elemento leido del fichero inicio.txt y devuelve la
	 * posicion en la que esta
	 * 
	 * @param elto
	 *            elemento a buscar en el array
	 * @return res posici�n en mapeo de dicho elemento
	 */
	private int queElemento(String elto) {
		int res = -1;
		boolean enc = false;

		for (int i = 0; (i < NUMELTOSCONF && !enc); i++) {
			if (mapeo[i].getNombre().equals(elto)) {
				res = i;
				enc = true;
			}
		}
		return res;
	}

	/**
	 * Metodo que crea las distintas instancias de la simulacion
	 * 
	 * @param elto
	 *            nombre de la instancia que se pretende crear
	 * @param numCampos
	 *            numero de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo de la instancia
	 * @throws Exception
	 * 				Datos invalidos
	 */
	public void crear(String elto, int numCampos, List<String> vCampos) throws Exception {
		// Si existe elemento y el número de campos es correcto, procesarlo... si no,
		// error
		int numElto = queElemento(elto);

		// Comprobaci�n de datos básicos correctos
		if ((numElto != -1) && (mapeo[numElto].getCampos() == numCampos)) {
			// procesar
			switch (numElto) {
			case 0:
				crearMap(numCampos, vCampos);
				break;
			case 1:
				crearSHPhysical(numCampos, vCampos);
				break;
			case 2:
				crearSHExtraSensorial(numCampos, vCampos);
				break;
			case 3:
				crearSHFlight(numCampos, vCampos);
				break;
			case 4:
				crearVillain(numCampos, vCampos);
				break;
			}
		} else {
			System.out.println(
					"ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos + "\n");
			throw new Exception("Linea invalida");
		}
	}

	/**
	 *  metodo que crea una instancia de la clase Planta
	 *  @param numCampos numero de atributos que tendra la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 * @throws Exception 
	 */
	private void crearMap(int numCampos, List<String> vCampos) throws Exception{
	    
		Mapa m = Mapa.getInstancia();
	    if((Integer.parseInt(vCampos.get(1))>new Integer(10)&&(Integer.parseInt(vCampos.get(1))<new Integer(4)))) {
	    	System.out.println(vCampos.get(1));
		   throw new Exception("Dimensiones no validas");
		   
	    }
	    if((Integer.parseInt(vCampos.get(2))>new Integer(10))&&(Integer.parseInt(vCampos.get(2))<new Integer(4))) {
	    	System.out.println(vCampos.get(2));
			   throw new Exception("Dimensiones no validas");
		}
	    if(!(Integer.parseInt(vCampos.get(3))<(Integer.parseInt(vCampos.get(2))*Integer.parseInt(vCampos.get(1)))&&Integer.parseInt(vCampos.get(3))>=0)) {
			   throw new Exception("Ubicacion daily erronea");
		}
		m.inicializar(Integer.parseInt(vCampos.get(1)), 
	    			Integer.parseInt(vCampos.get(2)), 
	    			Integer.parseInt(vCampos.get(3)), 
	    			Integer.parseInt(vCampos.get(4)));
	    System.out.println("Creado Map: " + vCampos.get(1) + "\n");
	}

	/**
	 * metodo que crea una instancia de la clase SHPhysical
	 * 
	 * @param numCampos
	 *            numero de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 * @throws Exception 
	 */
	private void crearSHPhysical(int numCampos, List<String> vCampos) throws Exception {
		System.out.println("Creado SHPhysical: " + vCampos.get(1) + "\n");
		int idInicial = 0;
		if(Integer.parseInt(vCampos.get(3))<=0)
			throw new Exception("Turno inicial invalido");
		Personaje p = new SHPhysical(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				idInicial

		);
		InterfazGrafica.getInstancia().boardPanel.addCharacter("SHPhysical", p.getId(), p.getNombre(),
				Integer.parseInt(vCampos.get(3)));
		m.insertarPersonaje(p, idInicial);

	}

	/**
	 * metodo que crea una instancia de la clase SHExtraSensorial
	 * 
	 * @param numCampos
	 *            numero de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 * @throws Exception 
	 */
	private void crearSHExtraSensorial(int numCampos, List<String> vCampos) throws Exception {
		System.out.println("Creado SHExtraSensorial: " + vCampos.get(1) + "\n");
		int idInicial = 0;
		if(Integer.parseInt(vCampos.get(3))<=0)
			throw new Exception("Turno inicial invalido");
		Personaje p = new SHExtraSensorial(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				idInicial);

		m.insertarPersonaje(p, idInicial);
		InterfazGrafica.getInstancia().boardPanel.addCharacter("SHPsych", p.getId(), p.getNombre(),
				Integer.parseInt(vCampos.get(3)));

	}

	/**
	 * metodo que crea una instancia de la clase SHFlight
	 * 
	 * @param numCampos
	 *            numero de atributos que tendra la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 * @throws Exception 
	 * 			En caso de datos invalidos
	 */
	private void crearSHFlight(int numCampos, List<String> vCampos) throws Exception {
		System.out.println("Creado SHFlight: " + vCampos.get(1) + "\n");
		int idInicial = (m.getFilas() - 1) * m.getColumnas();
		if(Integer.parseInt(vCampos.get(3))<=0)
			throw new Exception("Turno inicial invalido");
		Personaje p = new SHFlight(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				idInicial);

		InterfazGrafica.getInstancia().boardPanel.addCharacter("SHFlight", p.getId(), p.getNombre(),
				Integer.parseInt(vCampos.get(3)));

		m.insertarPersonaje(p, idInicial);
	}

	/**
	 * metodo que crea una instancia de la clase Villain
	 * 
	 * @param numCampos
	 *            numero de atributos que tendra la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 * @throws Exception 
	 * 			En caso de datos invalidos
	 */
	private void crearVillain(int numCampos, List<String> vCampos) throws Exception {
		System.out.println("Creado Villain: " + vCampos.get(1) + "\n");
		int idInicial = (m.getColumnas() - 1);
		if(Integer.parseInt(vCampos.get(3))<=0)
			throw new Exception("Turno inicial invalido");
		Personaje v = new Villano(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				idInicial);

		InterfazGrafica.getInstancia().boardPanel.addCharacter("Villain", v.getId(), v.getNombre(),
				Integer.parseInt(vCampos.get(3)));

		m.insertarPersonaje(v, idInicial);
	}
	

}
