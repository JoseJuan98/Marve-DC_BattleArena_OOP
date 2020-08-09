package Pruebas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 *Clase TestSuite desde la que se ejecutan todas los tests
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
@RunWith(Suite.class)
@SuiteClasses({ SalaTest.class, MapaTest.class, PersonajeTest.class, 
	ArbolTest.class, GenAleatoriosTest.class, GrafoTest.class})
public class AllTests {

}
