package Pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.Sala;
import marvelydc.objetos.Arma;
import marvelydc.personajes.SHExtraSensorial;
import marvelydc.personajes.SHFlight;
import marvelydc.personajes.SHPhysical;
import marvelydc.personajes.Villano;

/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class PersonajeTest {
	
	static Mapa m;
	static SHPhysical shpysh;
	static SHFlight shf;
	static SHExtraSensorial shext;
	static Villano v;
	
	Arma esc1;   Arma arm1;
	Arma c2;   Arma an1;
	Arma ce1;    Arma c1;
	Arma cap1;

	@Before
	public void armasConfig() {

		shpysh = new SHPhysical("Lobezno",'L', 0, 0);
		shf = new SHFlight("Green Lanthern", 'G', 0, 1);
		shext = new SHExtraSensorial("Storm", 'S', 0, 2);
		v = new Villano("Octopus",'O',0, 3);
	
		esc1 = new Arma("Escudo", 10);
		c2 = new Arma("Cetro", 15); 
		ce1 =  new Arma("CampoEnergia", 5);
		arm1 = new Arma("Armadura", 13); 
		an1 =	new Arma("Anillo", 11);
		c1 =	new Arma("Cetro", 20);
		cap1 =	new Arma("Capa", 10);
		
		shpysh.armasHeroe.insertar(esc1);
		shpysh.armasHeroe.insertar(c2);
		shpysh.armasHeroe.insertar(arm1);
		
		shf.armasHeroe.insertar(ce1);
		shf.armasHeroe.insertar(an1);
		
		shext.armasHeroe.insertar(an1);
		shext.armasHeroe.insertar(cap1);
		
		v.setArma(c1);

		
	}
	/**
	 *test de la interaccion de los personajes 
	 */
	@Test
	public void interacturarTest() {
		
		assertTrue(v.interactuarCon(shpysh));
		//Ahora comprobamos que el personaje ha perdido el arma
		assertNull(shpysh.buscarArma(c2));
		
		shf.interactuarCon(shext);
		//No deberia interactuar ya que son heroes ambos
		assertFalse(shf.interactuarCon(shext));
		
	}
	/**
	 *test del del metodo buscar arma del personaje 
	 */
	@Test
	public void buscarArmaTest() {
		
		assertEquals("Error en buscar arma perteneciente",c2, shpysh.buscarArma(c2));
		assertNull("Error en buscar arma no contenida",shext.buscarArma(c1));
	}
	/**
	 *test del metodo que elimina un determinada arma del personaje 
	 */
	@Test
	public void eliminarArmaTest() {
		
		assertTrue("Error en eliminar arma existente", shpysh.eliminarArma(c2));
		assertFalse("Error en eliminar arma NO existente", shext.eliminarArma(c2));
		
	}
	/**
	 *test del toString de personajes 
	 */
	@Test
	public void toStringsTest() {
		
		assertEquals("","(shphysical:L:1111:0:(Armadura,13)(Cetro,15)(Escudo,10))", shpysh.toString() );
		assertEquals("","(shflight:G:1111:0:(Anillo,11)(CampoEnergia,5))", shf.toString() );
		assertEquals("","(shextrasensorial:S:1111:0:(Anillo,11)(Capa,10))", shext.toString() );
		assertEquals("","(villain:O:1111:0:(Cetro,20))", v.toString() );
	}
}
