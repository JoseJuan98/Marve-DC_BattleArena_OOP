package Pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import marvelydc.mapeado.GenAleatorios;
/**
* @version 20/12/2016 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Enrega: EC3 </b><br>
*         Curso: 2017/2018
*/
public class GenAleatoriosTest {

	/**
	 *test que comprueba que los numeros aleatorios no se salgan de su limite
	 */
	@Test
	public void generarAleatTest() {
		int i=1;
		while(i <= 5) {
			assertTrue(0 <= GenAleatorios.generarNumero(5));
			assertTrue(5 >= GenAleatorios.generarNumero(5));
			i++;
		}	
	}
}
