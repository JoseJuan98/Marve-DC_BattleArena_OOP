package marvelydc.mapeado;
import java.util.Random;

/**
* Generador de numeros aleatorios - proyecto12_13
* Implementacion de la clase que permite generar numeros aleatorios
* @version 1.0
* @author
* <b> Profesores DP </b><br>
* Asignatura Desarrollo de Programas<br/>
* Curso 16/17
*/
public class GenAleatorios {
	
	/** Instancia de la clase Random que permite generar los numeros aleatorios. */
	private static Random r;
	
	/** Constante con la semilla para inicializar la generacion de numeros aleatorios. °°No cambiar!! */
	private static final int SEMILLA=1987;		 
	
	/** Contador de numeros aleatorios generados */
	private static int numGenerados;			
	
	/** Instancia de la propia clase (solo habra una en el sistema). Ver PATRON DE DISENNO SINGLETON */
	private static GenAleatorios instancia=null;	
	
	/**
	* Constructor por defecto. Inicializa un objeto de tipo GenAleatorio
	* @return Devuelve un objeto de tipo GenAleatorio inicializado
	*/
	private GenAleatorios(){
    	// Inicializacion de la semilla para generar los n√∫meros aleatorios
    	r = new Random(GenAleatorios.SEMILLA);
    	// Inicializacion del atributo que cuenta cu√°ntos n√∫meros aleatorios se han generado
    	numGenerados = 0;
    	}
	
	/**
	 * Metodo generarNumero. Genera un numero aleatorio entre 0 y (limiteRango-1)
	 * @param limiteRango El limite del rango en el que generar los aleatorios
	 * @return Devuelve el numero aleatorio generado
	 */
	public static int generarNumero(int limiteRango){
		if (instancia == null) 
			instancia = new GenAleatorios();
		numGenerados++;
		return r.nextInt(limiteRango);
	}
	
	/**
	 * Devuelve el numero de aleatorios generados
	 * @return Numero de aleatorios generados
	 */
	public static int getNumGenerados(){
		return numGenerados;
	}
	/**
	 * Resetea para que la serie de aleatorios vuelva a empezar
	 */
	public static void reset() {
		instancia=new GenAleatorios();
	}	
}
