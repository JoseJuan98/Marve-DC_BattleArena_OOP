package Pruebas;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import marvelydc.eds.Arbol;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class ArbolTest {
	Arbol<Integer> abb;
	@Before
	public void setUp() {
		abb = new Arbol<Integer>();
		abb.insertar(5);
		abb.insertar(3);
		abb.insertar(4);
		abb.insertar(1);
		abb.insertar(6);
		abb.insertar(7);
		abb.insertar(8);
		
	}
	/**
	 * test del metodo buscar del arbol que devuelve el objeto si lo encuentra, y si no null
	 */

	@Test
	public void buscarTest() {
		assertEquals("Error en buscar dato existente",new Integer(4), abb.buscar(4));
		assertEquals("Error en buscar dato existente",new Integer(8), abb.buscar(8));
		assertNull("Error en buscar dato no existente", abb.buscar(0));
	}
	
	/**
	 *test que comprueba que el metodo pertenece del arbol funciona correctamente 
	 */
	@Test
	public void perteneceTest() {
		assertTrue("Error en pertenece un num existente",abb.pertenece(8));
		assertFalse("Error en pertenece un num NO existente",abb.pertenece(0));
	}
	/**
	 *test que comprueba que el metodo borrar del arbol borra en orden y le reestructura correctamente 
	 */
	@Test
	public void borrarTest() {
		abb.borrar(new Integer(3));
		assertFalse("Error en borrar un nodo existente",abb.pertenece(3));
		//comprbamos que ha restaurado la rama afectada
		assertTrue(abb.pertenece(1));
		assertTrue(abb.pertenece(4));
		//un dato que no pertenece
		//assertFalse(abb.borrar(10));
	}
	/**
	 *test del toString de Arbol
	 */
	@Test
	public void toStringTest() {
		assertEquals("1"+"3"+"4"+"5"+"6"+"7"+"8", abb.toString());
	}
	/**
	 *test del metodo que devuelve la cantidad de elementos que hay 
	 */
	@Test
	public void cantidadDeElemtsTest() {
		assertEquals(7, abb.cantidadDeElementos());
	}
	/**
	 *test del metodo que devuelve la profundidad del arbol
	 */
	@Test
	public void profundidadTest() {
		assertEquals(4, abb.profundidad());
	}
}
