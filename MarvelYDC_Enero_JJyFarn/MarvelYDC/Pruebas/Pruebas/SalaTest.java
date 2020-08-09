package Pruebas;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import marvelydc.mapeado.Sala;
import marvelydc.objetos.Arma;
import marvelydc.personajes.SHPhysical;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class SalaTest {
	Sala s1;
	Sala s2;
	Sala s3;
	
	@Before
	public void setUp() {
		s1  = new Sala(15, 8);
		s2 = new Sala(15, 8);
		s3 = new Sala(1, 7);
	}
	/**
	 * 
	 */
	/**
	 *test del equals de salas 
	 */
	@Test
	public void equalsTest() {
		
		assertFalse("Error en comparacion distintos",s1.equals(s2));
		s2 = s3;
		assertTrue("Error comparacion iguales",s2.equals(s3));
	}
	/**
	 *test el toString de salas 
	 */
	@Test
	public void  toStringTest() {
		assertEquals("Error toString iguales",s1.toString(),s2.toString());
		assertNotEquals("Error toString diferentes",s2.toString(),s3.toString());
	}
	@Test
	public void insertaArmaTest() {
		Arma m1 = new Arma("Escudo", 10);
		s1.insertaArma(m1);
		s2.insertaArma(m1);
		assertEquals(m1, s1.armas.desencolar());
	}
	/**
	 *test del metodo que devuelve el arma mas poderosa de la sala 
	 */
	@Test
	public void armaPoderosaTest() {
		Arma m1 = new Arma("Escudo", 10);
		Arma m2 = new Arma("Escudo", 15);
		s1.insertaArma(m1);
		s1.insertaArma(m2);
		
		assertEquals(m2, s1.armaPoderosa());
		s1.armas.desencolar();
		s1.armas.desencolar();
		
		assertNull(s1.armaPoderosa());
	}
	/**
	 *Test del metodo destruirArmaPoderosa de la sala
	 */
	@Test
	public void destruirArmaPoderosaTest() {
		Arma m1 = new Arma("Escudo", 10);
		Arma m2 = new Arma("Escudo", 15);
		s1.insertaArma(m1);
		s1.insertaArma(m2);
		
		s1.destruirArmaPoderosa();
		assertEquals(m1, s1.armaPoderosa());
	}
	/**
	 *test del metodo noQuedanArmas de la sala, que devuelve el valor booleano true si no quedan 
	 */
	@Test
	public void noQuedanArmasTest(){
		Arma m1 = new Arma("Escudo", 10);
		Arma m2 = new Arma("Escudo", 15);
		s1.insertaArma(m1);
		s1.insertaArma(m2);
		
		s1.destruirArmaPoderosa();
		s1.destruirArmaPoderosa();
		
		assertTrue(s1.noQuedanArmas());
	}
}
