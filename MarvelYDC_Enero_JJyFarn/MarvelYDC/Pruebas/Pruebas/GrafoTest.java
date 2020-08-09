package Pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import marvelydc.eds.Grafo;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class GrafoTest {
	Grafo g;
	
	@Before
	public void test() {
		g = new Grafo();
	}
	
	/**
	 *test que prueba la creacion de nodos y arcos 
	 */
	@Test
	public void crearNodos_ArcosTest() {
	    for (int j = 0; j < 9; j++) {
	    	g.nuevoNodo(j);			
		}
		g.nuevoArco(0, 1, 1);
		g.nuevoArco(0, 3, 1);
		g.nuevoArco(1, 2, 1);
		

	    assertEquals(new String("(0)(1)(2)(3)(4)(5)(6)(7)(8)\n"), g.toStringNodos());

	    
	    String s = new String("  0  1  #  1  #  #  #  #  #\r\n" + 
	    					"  #  0  1  #  #  #  #  #  #\r\n" + 
	    					"  #  #  0  #  #  #  #  #  #\r\n" + 
	    					"  #  #  #  0  #  #  #  #  #\r\n" + 
				    		"  #  #  #  #  0  #  #  #  #\r\n" + 
				    		"  #  #  #  #  #  0  #  #  #\r\n" + 
				    		"  #  #  #  #  #  #  0  #  #\r\n" + 
				    		"  #  #  #  #  #  #  #  0  #\r\n" + 
				    		"  #  #  #  #  #  #  #  #  0\r\n" +
				    		"\n");
	    //assertEquals(s, g.toStringArcos());
	}
}
