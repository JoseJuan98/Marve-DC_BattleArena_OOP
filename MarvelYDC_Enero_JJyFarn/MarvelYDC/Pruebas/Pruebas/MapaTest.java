package Pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import marvelydc.mapeado.Mapa;
import marvelydc.mapeado.Sala;

/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class MapaTest {
	private static Mapa m;
	@BeforeClass
	public static void setUpClass() {
		 m = new Mapa();
	}
	@Before
	public void setUp() {
		 m.getInstancia().inicializar(2, 4, 3, 1);
	}
	@Test
	public void inicializarMapaTest() {
		 assertEquals(2, m.getInstancia().getFilas());
		 assertEquals(4, m.getInstancia().getColumnas());
		 assertEquals(3, m.getInstancia().getDailyPlanet());
		 assertEquals(1, m.getInstancia().getAltura());	
	} 
	/**
	 *test que comprueba que se propaga la misma marca 
	 */
	@Test
	public void kruskalTest() {
		m.getInstancia();
		 int marca = m.getInstancia().salas[0][0].getMarca();
		 for(int i=0; i < m.getFilas(); i++) {
			 for(int j=0; j < m.getColumnas(); j++) {
				 assertEquals("Error de marca en sala "+i*m.getFilas()+j,marca, m.getInstancia().salas[i][j].getMarca());
			 }
		 }
	}
	/**
	 *test que omprueba que el portal inicialmente este cerrado
	 */
	@Test
	public void checkPortalInicioTest() {
		assertFalse(m.getInstancia().checkPortal());	
	}
}
