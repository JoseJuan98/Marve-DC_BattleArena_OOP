package marvelydc.cargador;
/**
 * Clase creada para ser usada en la utilidad cargador
 * se utiliza para realizar el mapeo de los distintos tipos de datos que se utilizaran en la aplicacion
 * 
 * @version 4.0 -  15/10/2014 
 * @author Profesores DP
 */ 
public class DatoMapeo {
  /**
 *cadena con el nombre del dato de mapa
 */
private String nombre;
  /**
 * numero de campos que tiene la cadena
 */
private int numCampos;
 /**
  * constructor por defecto
  */
  DatoMapeo() {
	  nombre = new String();
	  nombre = "..";
	  numCampos = 0;
  }

  /**
   *  constructor parametrizado 
   *  @param _nombre nombre del tipo de datos
   *  @param _numCampos numero de campos-atributos que contendra
   */
  DatoMapeo(String _nombre, int _numCampos) {
	  nombre = new String(_nombre);
	  numCampos = _numCampos;
  }
 
  /**
   * devuelve el nombre del tipo
   * @return nombre
   */
  public String getNombre() {
	  return nombre;
  }
  
  /**
   * devuelve el numero de campos del tipo
   * @return numCampos
   */
  public int getCampos() {
	  return numCampos;
  }
  
  
}
