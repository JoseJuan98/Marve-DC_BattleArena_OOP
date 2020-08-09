package marvelydc.mapeado;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import marvelydc.eds.Grafo;
import marvelydc.eds.Pared;
import marvelydc.objetos.Arma;
import marvelydc.personajes.HombrePuerta;
import marvelydc.personajes.Personaje;
import marvelydc.personajes.movimiento.Direccion;
import marvelydc.ui.InterfazGrafica;

/**
* @version 10/1/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class Mapa {
	/**
	 * Numero de idSala en el que se encuentra Sala DailyPlanet 
	 */
	int dailyPlanet;
	/**
	 * Numero de columnas que tiene el mapa 
	 */
	int columnas;
	/**
	 * Numero de filas que tiene el mapa 
	 */
	int filas;
	/**
	 * La profundidad que tiene que tener el arbol binario del hombre puerta para que el portal 
	 * se abra
	 */
	int altura;
	/**
	 * Turno actual por el que sigue el curso del juego 
	 */
	int turnoActual;
	/**
	 * Lista de las salas ordenadas por frecuencia
	 */
	TreeSet<Sala> listaSalasFrecuencia = new TreeSet<Sala>(new ComparadorSalas());
	/**
	 * Lista de las paredes
	 */
	List<Pared> paredes = new ArrayList<Pared>();
	/**
	 * Lista de personajes que consiguen llegar al Teseracto
	 */
	public LinkedList<Personaje> teseractoGanadores=new LinkedList<Personaje>();
	/**
	 * Matriz de las salas del mapa
	 */
	public Sala[][] salas;

	/**
	 * Lista de los caminos
	 */
	ArrayList<List<Integer>> caminos = new ArrayList<List<Integer>>();

	/**
	 * Grafo que contiene las conexiones entre las salas
	 */
	Grafo grafo = new Grafo();
	/**
	 * Instancia estatica del mapa para el Singleton
	 */
	private static Mapa mapa = new Mapa();

	/**
	 * <p>
	 * Singleton del mapa que devuelve la unica instancia del mapa
	 * </p>
	 * 
	 * @return La unica instancia del mapa
	 */
	public static Mapa getInstancia() {
		if (mapa == null)
			mapa = new Mapa();
		return mapa;
	}
	
	/**
	 * Metodo que reinicia el mapa
	 */
	public static void reiniciar() {
		mapa = null;
	}
	/**
	 * <p>
	 * Metodo que inicializa el mapa a partir de unos valores determinados
	 * </p>
	 * 
	 * @param dimY
	 *            Numero de filas
	 * @param dimX
	 *            Numero de columnas
	 * @param salaDailyPlanet
	 *            Id de la sala de DP (Menor que el tamaño total)
	 * @param alturaApertura
	 *            Altura de apertura del hombre puerta
	 */
	public void inicializar( int dimX,int dimY, int salaDailyPlanet, int alturaApertura) {

		this.dailyPlanet = salaDailyPlanet;
		this.columnas = dimX;
		this.filas = dimY;
		this.altura = alturaApertura;
		this.salas = new Sala[filas][columnas];
		this.turnoActual = 0;
		

		for (int i = 0; i < filas * columnas; i++) {

			grafo.nuevoNodo(i);
			if (i != salaDailyPlanet)
				salas[i / columnas][i % columnas] = new Sala(i, i);
			else
				salas[i / columnas][i % columnas] = new SalaDailyPlanet(i, i, altura);
		}

		this.kruskal();
		
		this.crearAtajos();
		
		// Creacion de las armas para el hombre puerta
		Arma[] armasPuerta = { new Arma("CampoEnergia", 5), new Arma("Armadura", 13), new Arma("Anillo", 11),
				new Arma("Acido", 1), new Arma("Antorcha", 5), new Arma("Bola", 3), new Arma("Baston", 22),
				new Arma("CadenaFuego", 11), new Arma("Espada", 11), new Arma("Cetro", 20), new Arma("Capa", 10),
				new Arma("CampoMagnetico", 5), new Arma("Escudo", 3), new Arma("Garra", 22), new Arma("Flecha", 12),
				new Arma("Gema", 4) };

		// Creacion del hombre puerta y configuracion
		HombrePuerta doorMan = new HombrePuerta();

		// Configurar el hombre puerta introduciendo la combinacion de armas
		doorMan.configurarArmas(armasPuerta);

		// Añadir el hombre puerta al mapa
		mapa.insertarHombrePuerta(doorMan);
		
		this.distribuirArmasPorFrecuencia();
	}
	/**
	 * Metodo que distribuye las armas por las salas segun su frecuencia 
	 */
	private void distribuirArmasPorFrecuencia() {
		calcularTodosLosCaminos();
	 	
		for (Iterator<List<Integer>> it = caminos.iterator(); it.hasNext();) {
			List<Integer> camino = it.next();
			for (Integer idSala : camino) {
				getSala(idSala).frecuencia++;
			}
		}

		// Pasar salas a una ED y ordenarla por frecuencia
		for (int i = 0; i < filas * columnas; i++)
			listaSalasFrecuencia.add(getSala(i));
		
		// Recorrer las salas ORDENADAS e ir repartiendo las armas de 5 en 5
		int numArmasSalas = 60;
		Arma[] armasSalas = { new Arma("Mjolnir", 29), new Arma("Anillo", 1), new Arma("Garra", 27),
				new Arma("Armadura", 3), new Arma("Red", 25), new Arma("Escudo", 5), new Arma("Lucille", 23),
				new Arma("Lawgiver", 7), new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9),
				new Arma("CadenaFuego", 19), new Arma("Capa", 11), new Arma("Flecha", 17), new Arma("Tridente", 13),
				new Arma("Antorcha", 15), new Arma("Baston", 28), new Arma("Latigo", 2), new Arma("MazaOro", 26),
				new Arma("CampoMagnetico", 4), new Arma("Tentaculo", 24), new Arma("CampoEnergia", 6),
				new Arma("Cetro", 22), new Arma("RayoEnergia", 8), new Arma("Laser", 20), new Arma("Bola", 10),
				new Arma("Espada", 18), new Arma("Sable", 12), new Arma("Acido", 16), new Arma("Gema", 14),
				new Arma("Nullifier", 23), new Arma("Mjolnir", 1), new Arma("Anillo", 29), new Arma("Garra", 3),
				new Arma("Armadura", 27), new Arma("Red", 5), new Arma("Escudo", 25), new Arma("Lucille", 7),
				new Arma("Lawgiver", 23), new Arma("GuanteInfinito", 9), new Arma("LazoVerdad", 21),
				new Arma("CadenaFuego", 11), new Arma("Capa", 19), new Arma("Flecha", 13), new Arma("Tridente", 17),
				new Arma("Antorcha", 28), new Arma("Baston", 15), new Arma("Latigo", 26), new Arma("MazaOro", 2),
				new Arma("CampoMagnetico", 24), new Arma("Tentaculo", 4), new Arma("CampoEnergia", 22),
				new Arma("Cetro", 6), new Arma("RayoEnergia", 20), new Arma("Laser", 8), new Arma("Bola", 18),
				new Arma("Espada", 10), new Arma("Sable", 16), new Arma("Acido", 12), new Arma("Gema", 1),
				new Arma("Nullifier", 3) };
		int armaActual = 0;
		Sala salaActual;
		Iterator<Sala> sala;
		sala = this.listaSalasFrecuencia.descendingIterator();
		while (armaActual < numArmasSalas && sala.hasNext()) {
			salaActual = sala.next();
			for (int i = 0; i < 5; i++) {
				salaActual.insertaArma(armasSalas[armaActual]);
				armaActual++;
			}
		}
	}
	
	public int getAltura() {
		return this.altura;
	}
	/**
	 * Metodo que calcula todos los caminos del mapa que se utilizaran para
	 *  obtener la frecuencia de cada sala 
	 */
	private void calcularTodosLosCaminos() {
		
		List<Integer> nuevoCamino = new ArrayList<Integer>();
		Integer inicial = 0;
		calcularCamino(inicial, nuevoCamino);
	}

	/**
	 * Metodo que calcula los caminos posibles desde un punto inicial hasta la Sala DailyPlanet
	 * @param v
	 * @param viejoVisitados
	 */
	void calcularCamino(Integer v, List<Integer> viejoVisitados) {
		List<Integer> Visitados = new ArrayList<Integer>(viejoVisitados);
		if (v == this.dailyPlanet) {
			Visitados.add(v);
			caminos.add(Visitados);
		} else {
			LinkedList<Integer> Ady;
			Grafo G = this.grafo;
			Integer w;
			Ady = new LinkedList<Integer>();
			Visitados.add(v);
			G.adyacentes(v, Ady);
			Ady.remove(v);
			while (!Ady.isEmpty()) {
				w = Ady.getFirst();
				Ady.removeFirst();
				if (!Visitados.contains(w))
					calcularCamino(w, Visitados);
			}
		}

	}

	
	/**
	 * Devuelve la ubicacion del dailyPlanet
	 * @return ubicacionDaily
	 */
	public int getDailyPlanet() {
		return dailyPlanet;
	}
	/**
	 * 
	 * @param dailyPlanet ubicacion daily
	 */
	public void setDailyPlanet(int dailyPlanet) {
		this.dailyPlanet = dailyPlanet;
	}
/**
 * Devuelve el turno actual
 * @return El turno actual
 */
	public int getTurnoActual() {
		return turnoActual;
	}
/**
 * Inicializa el turno actual
 * @param turnoActual
 */
	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}

	

	/**
	 * Metodo que devuelve la siguiente sala a una dada segun el criterio de la mano derecha
	 * @param salaAnterior
	 * @param salaActual
	 * @return
	 */
	public int siguienteSalaDiestra(Integer salaAnterior, Integer salaActual) {
		int salaSig = 0;
		int posSalaAnterior = 0;
		LinkedList<Integer> ady = adyacentesSENO(salaActual);
		if (salaAnterior != -1) {
			posSalaAnterior = ady.indexOf(salaAnterior);
			if (posSalaAnterior == (ady.size() - 1)) {
				salaSig = ady.getFirst();
			} else
				salaSig = ady.get(posSalaAnterior + 1);

		} else
			salaSig = ady.getFirst();
		return salaSig;

	}

	/**
	 * Devuelve las salas adyacentes en orden de Norte Este Sur y Oeste Como las
	 * adyacentes del grafo las devuelve en orden Norte Oeste Este Sur, elimina el
	 * Oeste si esta y lo mete al final
	 * 
	 * @param sala
	 * @return
	 */
	public LinkedList<Integer> adyacentesSENO(int sala) {
		Integer oeste = sala - 1;
		Integer este = sala + 1;
		Integer norte = sala - columnas;
		LinkedList<Integer> ady = new LinkedList<Integer>();
		grafo.adyacentes(sala, ady);
		if (ady.remove(este)) {
			ady.addLast(este);
		}
		if (ady.remove(norte)) {
			ady.addLast(norte);
		}
		if (ady.remove(oeste)) {
			ady.addLast(oeste);
		}
		return ady;
	}

	/**
	 * Metodo que empieza mirando al sur,despues comienza a adaptarse segun su sala diestra
	 * 
	 * @param salaInicio
	 * @return
	 */
	public LinkedList<Integer> rutaWallFollowerDiestro(int salaInicio) {
		LinkedList<Integer> ruta = new LinkedList<Integer>();
		int anterior = -1;
		int actual = salaInicio;
		int sig = -1;
		ruta.add(salaInicio);
		while (sig != dailyPlanet) {
			sig = siguienteSalaDiestra(anterior, actual);

			ruta.add(sig);
			anterior = actual;
			actual = sig;
		}

		return ruta;
	}
/**
 * Devuelve los adyacentes de una sala en orden Sur Oeste Norte Este
 * @param sala
 * @return
 */
	public LinkedList<Integer> adyacentesSONE(int sala) {
		Integer oeste = sala - 1;
		Integer este = sala + 1;
		Integer norte = sala - columnas;
		LinkedList<Integer> ady = new LinkedList<Integer>();
		grafo.adyacentes(sala, ady);
		if (ady.remove(oeste)) {
			ady.addLast(oeste);
		}
		if (ady.remove(norte)) {
			ady.addLast(norte);
		}
		if (ady.remove(este)) {
			ady.addLast(este);
		}
		return ady;
	}
/**
 * La siguiente sala segun la ruta del wallFollower zurdo
 * @param salaAnterior
 * @param salaActual
 * @return
 */
	public int siguienteSalaZurda(Integer salaAnterior, Integer salaActual) {
		int salaSig = 0;
		int posSalaAnterior = 0;
		LinkedList<Integer> ady = adyacentesSONE(salaActual);
		if (salaAnterior != -1) {
			posSalaAnterior = ady.indexOf(salaAnterior);
			if (posSalaAnterior == (ady.size() - 1)) {
				salaSig = ady.getFirst();
			} else
				salaSig = ady.get(posSalaAnterior + 1);

		} else
			salaSig = ady.getFirst();

		return salaSig;

	}
/**
 * Devuelve una secuencia de salas correspondiente al wallfollower zurdo
 * @param salaInicio
 * @return
 */
	public LinkedList<Integer> rutaWallFollowerZurdo(int salaInicio) {
		LinkedList<Integer> ruta = new LinkedList<Integer>();
		int anterior = -1;
		int actual = salaInicio;
		int sig = -1;
		ruta.add(salaInicio);
		while (sig != dailyPlanet) {
			sig = siguienteSalaZurda(anterior, actual);

			ruta.add(sig);
			anterior = actual;
			actual = sig;
		}

		return ruta;
	}
	/**
	 * Devuelve las salas del orden mas corto
	 * @param salaInicio
	 * @return
	 */

	public LinkedList<Integer> rutaCaminoPorOrdenDeID(int salaInicio) {

		LinkedList<Integer> camino = new LinkedList<Integer>();
		LinkedList<Integer> caminoFinal = new LinkedList<Integer>();
		Integer inicial = salaInicio;
		caminoFinal = calcularCaminoID(inicial, camino);
		
		
		return caminoFinal;

	}
/**
 * Calcula el camino que va usando un orden de id
 * @param v
 * @param camino
 * @return
 */
	private LinkedList<Integer> calcularCaminoID(Integer v, LinkedList<Integer> camino) {
		LinkedList<Integer> caminoFinal = new LinkedList<Integer>();
		if (v == this.dailyPlanet && caminoFinal.isEmpty()) {
			camino.add(v);
			caminoFinal = camino;			
			return camino;
		} else {
			LinkedList<Integer> Visitados = new LinkedList<Integer>(camino);
			LinkedList<Integer> Ady;
			Grafo G = this.grafo;
			Integer w;
			Ady = new LinkedList<Integer>();
			Visitados.add(v);
			G.adyacentes(v, Ady);
			Ady.remove(v);
			while (!Ady.isEmpty() && caminoFinal.isEmpty()) {
				w = Ady.getFirst();
				Ady.removeFirst();
				if (!Visitados.contains(w))
					caminoFinal = calcularCaminoID(w, Visitados);
			}
		}
		return caminoFinal;

	}

	/**
	 * Metodo que calcula la ruta mas corta desde un punto inicial hasta la Sala DailyPlanet
	 * @param salaInicio
	 * @return
	 */
	public LinkedList<Integer> rutaMasCortaADailyPlanet(int salaInicio) {
		LinkedList<Integer> ruta = new LinkedList<Integer>();
		int sig = salaInicio;
		ruta.add(sig);
		while (sig != dailyPlanet) {
			sig = grafo.siguiente(sig, dailyPlanet);
			ruta.add(sig);
		}
		return ruta;
	}

	/**
	 * <p>
	 * Se encarga de traducir una secuencia de salas en direcciones que un personaje
	 * pueda interpretar
	 * </p>
	 * 
	 * @param ruta
	 *            Secuencia de enteros con ids de rutas a seguir
	 * @param direcciones
	 *            Parametro en el que aparecen las direcciones a seguir segun dicha
	 *            ruta
	 */
	public LinkedList<Direccion> direccionesDeUnaRuta(LinkedList<Integer> ruta) {
		int lugarPasado;
		int lugarActual;
		int diferencia;
		LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
		Direccion dirActual = Direccion.N;
		if (!ruta.isEmpty()) {
			Iterator<Integer> it = ruta.iterator();

			lugarActual = it.next();
			while (it.hasNext()) {
				
				lugarPasado = lugarActual;
				lugarActual = it.next();
				if (grafo.adyacente(lugarPasado, lugarActual)) {
					diferencia = lugarActual - lugarPasado;
					if (diferencia == 1)
						dirActual = Direccion.E;
					if (diferencia == -1)
						dirActual = Direccion.W;
					if (diferencia == columnas)
						dirActual = Direccion.S;
					if (diferencia == -columnas)
						dirActual = Direccion.N;
					direcciones.add(dirActual);
				} else {
					System.err.println("Ruta no valida");
					break;
				}
			}
		}
		return direcciones;
	}

	/**
	 * <p>
	 * Funcion recursiva que propaga la marca por todas las adyacentes
	 * </p>
	 * 
	 * @param marca
	 *            Marca a propagar
	 * @param id
	 *            Id de la sala por la que seguir propagando
	 */
	public void propagarMarca(int marca, int id) {
		this.getSala(id).setMarca(marca);
		LinkedList<Integer> ady = new LinkedList<Integer>();
		grafo.adyacentes(id, ady);

		Sala salaAdyacente;
		Integer idSalaAdy;
		for (Iterator<Integer> iterator = ady.iterator(); iterator.hasNext();) {
			idSalaAdy = (Integer) iterator.next();
			salaAdyacente = this.getSala(idSalaAdy);
			if (salaAdyacente.getMarca() != marca)
				propagarMarca(marca, idSalaAdy);
		}

	}

	/**
	 * Metodo que crea los atajos 
	 */
	public void crearAtajos() {
		int n = filas * columnas;
		n = n / 20;
		Integer oeste;
		Integer este;
		Integer norte;
		Integer sur;
		int sala;
		int i = 0;
		boolean derribada = false;
		
		while (i < n) {
			derribada = false;
			sala = GenAleatorios.generarNumero(filas * columnas);
			
			oeste = sala - 1;
			este = sala + 1;
			norte = sala - columnas;
			sur = sala + columnas;

			if (sala / columnas != 0 && !derribada) {
				if (hayPared(sala, norte)) {
					if (this.grafo.coste(sala, norte) != 3) {
						this.grafo.nuevoArco(sala, norte, 1);
						this.grafo.nuevoArco(norte, sala, 1);
						derribada = true;
					}
				}
			}
			if (sala / columnas != (filas - 1) && !derribada) {
				if (hayPared(sala, sur)) {
					if (this.grafo.coste(sala, sur) != 3) {
						this.grafo.nuevoArco(sala, sur, 1);
						this.grafo.nuevoArco(sur, sala, 1);
						derribada = true;
					}
				}
			}
			if (sala % columnas != 0 && !derribada) {
				if (hayPared(sala, oeste)) {
					if (this.grafo.coste(sala, oeste) != 3) {
						this.grafo.nuevoArco(sala, oeste, 1);
						this.grafo.nuevoArco(oeste, sala, 1);
						derribada = true;
					}
				}
			}
			if (sala % columnas != (columnas - 1) && !derribada) {
				if (hayPared(sala, este)) {
					if (this.grafo.coste(sala, este) != 3) {
						this.grafo.nuevoArco(sala, este, 1);
						this.grafo.nuevoArco(este, sala, 1);
						derribada = true;
					}
				}
			}

			if (derribada == true) {
				
				
				i++;
			}

		}
		this.grafo.warshall();
		this.grafo.floyd();
	}

	/**
	 * <p>
	 * Comienza a tirar paredes que no compartan marcas hasta que todas las salas
	 * compartan la misma marca
	 * </p>
	 * 
	 * @see #seleccionarPared()
	 * @see #tirarPared(int)
	 */
	public void kruskal() {

		generarParedes();
		Pared pared;
		int paredPos;
		while (!this.paredes.isEmpty()) {
			// Siempre busca una pared valida
			paredPos = GenAleatorios.generarNumero(paredes.size());
			pared = paredes.get(paredPos);
			if (this.getSala(pared.getOrigen()).getMarca() != this.getSala(pared.getDestino()).getMarca()) {
				this.propagarMarca(getSala(pared.getOrigen()).getMarca(), getSala(pared.getDestino()).getMarca());
				grafo.nuevoArco(pared.getOrigen(), pared.getDestino(), 1);
				grafo.nuevoArco(pared.getDestino(), pared.getOrigen(), 1);
			}
			paredes.remove(paredPos);

		}
		this.grafo.warshall();
		this.grafo.floyd();
		InterfazGrafica.getInstancia().logFilePanel.addText(this.dibujoMapa());

	}

	/**
	 * <p>
	 * Genera todas las paredes del mapa
	 * </p>
	 */
	public void generarParedes() {
		int idSala = 0;
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (i != 0)
					paredes.add(new Pared(idSala, idSala - columnas));// Norte
				if (j != (columnas - 1))
					paredes.add(new Pared(idSala, idSala + 1));// Este
				if (i != (filas - 1))
					paredes.add(new Pared(idSala, idSala + columnas));// pared Sur
				if (j != 0)
					paredes.add(new Pared(idSala, idSala - 1));// pared Oeste
				idSala++;
			}
		}

	}

	/**
	 * Metodo que inserta el hombre puerta en la sala DailyPlanet
	 * 
	 * @param doorMan
	 *            El hombre puerta ya configurado
	 */
	public void insertarHombrePuerta(HombrePuerta doorMan) {

		SalaDailyPlanet salaDP = (SalaDailyPlanet) this.getSala(dailyPlanet);
		salaDP.insertarHombrePuerta(doorMan);

	}

	/**
	 * Devuelve la lista de paredes
	 * 
	 * @return La lista de paredes
	 */
	public List<Pared> getParedes() {
		return paredes;
	}

	/**
	 * Constructor por defecto
	 */
	public Mapa() {
		this.dailyPlanet = 0;
		this.columnas = 0;
		this.filas = 0;
		this.altura = 0;
		this.salas = null;
	}

	/**
	 * <p>
	 * Metodo que inserta a un personaje en una sala concreta a traves de su id
	 * </p>
	 * 
	 * @param pj
	 *            Personaje a insertar
	 * @param nSala
	 *            Id de la sala en la que insertar
	 */
	public void insertarPersonaje(Personaje pj, int nSala) {

		this.getSala(nSala).insertarPj(pj);
	}

	/**
	 * <p>
	 * Metodo que comprueba el estado del portal de la sala DailyPlanet y que esta
	 * en la sala del mapa asignado
	 * </p>
	 * 
	 * @return True if the portal is opened
	 */
	public boolean checkPortal() {
		SalaDailyPlanet salaDP = (SalaDailyPlanet) this.getSala(dailyPlanet);
		return salaDP.checkPortal();
	}

	/**
	 * Metodo que escribe en el log el estado del juego durante cada turno 
	 */
	public void pintarEstadoTurno() {
		// mapa+hombrePuerta
		InterfazGrafica.getInstancia().logFilePanel.addText("(turn:" + this.turnoActual + ")");
		InterfazGrafica.getInstancia().logFilePanel.addText("(map:" + this.dailyPlanet + ")");
		String hp = "(doorman:";
		if (!checkPortal()) {
			hp += "closed:";
		} else {
			hp += "open:";
		}
		SalaDailyPlanet s = (SalaDailyPlanet) this.getSala(dailyPlanet);
		hp += this.altura + ":" + s.getHombrePuerta().toString() + ")";
		InterfazGrafica.getInstancia().logFilePanel.addText(hp);
		InterfazGrafica.getInstancia().logFilePanel.addText(this.dibujoMapa());

		// armas de cada sala
		Sala sal;
		for(int i=0;i<filas*columnas;i++) {
			sal=this.getSala(i);
			if(!sal.noQuedanArmas()) {
				InterfazGrafica.getInstancia().logFilePanel.addText("(square:"+sal+")");
			}

		}

		for(int i=0;i<filas*columnas;i++) {
			sal=this.getSala(i);
			if(!sal.pjsEnSala.isEmpty()) {
				for (Iterator<Personaje> iterator = sal.pjsEnSala.iterator(); iterator.hasNext();) {
					InterfazGrafica.getInstancia().logFilePanel.addText(iterator.next().toString());
					
				}
			}

		}
		if (!teseractoGanadores.isEmpty()) {
			
			Iterator<Personaje> it=teseractoGanadores.iterator();
			InterfazGrafica.getInstancia().logFilePanel.addText("(teseractomembers)");
			Personaje pj=it.next();
			InterfazGrafica.getInstancia().logFilePanel.addText("(owneroftheworld:"+pj);
			while(it.hasNext())
				InterfazGrafica.getInstancia().logFilePanel.addText(""+it.next());
			
		}
	}
	public void pintarDireccionesPjs() {
		Sala sal;
		Personaje p;
		for(int i=0;i<filas*columnas;i++) {
			sal=this.getSala(i);
			if(!sal.pjsEnSala.isEmpty()) {
				for (Iterator<Personaje> iterator = sal.pjsEnSala.iterator(); iterator.hasNext();) {
					p=iterator.next();
					InterfazGrafica.getInstancia().logFilePanel.addText("(path:"+p.getId()+":"+p.ruta+")");
				}
			}

		}
	}
	/**
	 * Metodo que realiza la simulacion
	 */

	public boolean realizarTurno() {
		boolean ganado=false;
		System.out.println("Turno "+this.turnoActual);
		for(int i=0;i<filas*columnas;i++)
			ganado=this.getSala(i).update();
		
		for(int i=0;i<filas*columnas;i++)
			this.getSala(i).resetearPjs();
		
		pintarEstadoTurno();
		this.turnoActual++;
		return ganado;
	}

	/**
	 * Devuelve si hay una pared entre dos salas adyacentes con un id y si estan en
	 * el grafo
	 * 
	 * @param idSala1
	 *            Id primera sala
	 * @param idSala2
	 *            Id segunda sala
	 * @return True si hay una pared
	 */

	public boolean hayPared(int idSala1, int idSala2) {
		if (idSala1 >= 0 && idSala2 >= 0 && idSala1 < filas * columnas && idSala2 < filas * columnas)
			return !grafo.adyacente(idSala1, idSala2);
		else
			return false;

	}

	/**
	 * Devuelve si hay una pared entre dos salas por sus coordenadas
	 * 
	 * @param colSala1
	 *            X de la sala 1
	 * @param filaSala1
	 *            Y de la sala 2
	 * @param colSala2
	 *            X de la sala 2
	 * @param filaSala2
	 *            Y de la sala2
	 * @return Devuelve true si hay una pared entre las dos salas
	 */
	public boolean hayPared(int colSala1, int filaSala1, int colSala2, int filaSala2) {
		return !grafo.adyacente(filaSala1 * columnas + colSala1, filaSala2 * columnas + colSala2);
	}

	/**
	 * Metodo que devuelve una sala con solo saber su id
	 * 
	 * @param nSala
	 *            Id de la sala
	 * @return Sala de la posicion
	 */
	public Sala getSala(int nSala) {
		return salas[nSala / columnas][nSala % columnas];
	}

	/**
	 * Devuelve el numero de columnas
	 *
	 * @return Numero de columnas
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Devuelve el numero de filas
	 * 
	 * @return Numero de filas
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * 
	 * <p>
	 * Metodo que dibuja el mapa en un string
	 * </p>
	 * 
	 */
	public String dibujoMapa() {
		String paredes = "";
		for (int i = 0; i < columnas; i++)
			paredes += " _";
		
		for (int i = 0; i < filas; i++) {
			paredes += "\n";
			paredes += "|";
			for (int j = 0; j < columnas; j++) {
				if (salas[i][j].pjsEnSala.isEmpty()) {
					if (hayPared(j, i, j, (i + 1)) || i == (filas - 1)) {
						paredes += "_";
					} else {
						paredes += " ";
					}
				} else {
					if (salas[i][j].pjsEnSala.size() == 1) {
						paredes += salas[i][j].pjsEnSala.getFirst().getId();
					} else {

						paredes = paredes + salas[i][j].pjsEnSala.size();
					}
				}
				if (hayPared(j, i, (j + 1), i) || j == (columnas - 1)) {
					paredes += "|";
				} else
					paredes += " ";
			}
			
		}
		return paredes;

	}

	/**
	 * Devuelve las paredes de una sala en orden de norte, oeste, sur, este
	 * 
	 * @param idSala
	 *            Id de la sala a comprobar
	 * @return Un array de 4 booleanos con informacion de las paredes en orden NOSE
	 */
	public boolean[] paredesDeUnaSala(int idSala) {
		boolean paredes[] = new boolean[4];

		if (idSala / columnas != 0) {
			paredes[0] = this.hayPared(idSala, idSala - columnas);
		} else {
			paredes[0] = true;
		}
		if (idSala % columnas != 0) {
			paredes[1] = this.hayPared(idSala, idSala - 1);
		} else {
			paredes[1] = true;
		}
		if (idSala / columnas != (filas)) {
			paredes[2] = this.hayPared(idSala, idSala + columnas);
		} else {
			paredes[2] = true;
		}
		if (idSala % columnas != (columnas)) {
			paredes[3] = this.hayPared(idSala, idSala + 1);
		} else {
			paredes[3] = true;
		}
		return paredes;
	}
}
