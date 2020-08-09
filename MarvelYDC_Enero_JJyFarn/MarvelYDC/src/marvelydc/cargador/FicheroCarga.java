package marvelydc.cargador;
import java.util.ArrayList;
import java.util.List;



public class FicheroCarga {
	/**   
	atributo de la clase que indica el numero maximo de campos que se pueden leer
	*/
	public static final int MAXCAMPOS  = 10;

	/**  
	buffer para almacenar el flujo de entrada
	 * @throws Exception 
	*/
	public void loadInitField(String init) throws Exception {
		
		List<String> vCampos = new ArrayList<String>();
		
		int numCampos = 0;

		System.out.println("Procensando el fichero...");

		Cargador cargador=new Cargador();
		
		String[] lineas = init.split("\n");
		for (String linea : lineas) {
			System.out.println("S: " + linea);
			if (!linea.startsWith("--")) {
				vCampos.clear();
				numCampos = trocearLinea(linea, vCampos);
				System.out.println(vCampos);
				if (!(vCampos.size() == 0))
					try {
						cargador.crear(vCampos.get(0), numCampos, vCampos);
					} catch (Exception e) {
						throw e;
					}
			}
		}
		
	}

	/**
	 * Metodo para trocear cada línea y separarla por campos
	 * 
	 * @param S
	 *            cadena con la línea completa leída
	 * @param vCampos.
	 *            Array de String que contiene en cada posición un campo distinto
	 * @return numCampos. Número campos encontrados
	 */
	private static int trocearLinea(String S, List<String> vCampos) {
		boolean eol = false;
		int pos = 0, posini = 0, numCampos = 0;

		while (!eol) {
			pos = S.indexOf("#");
			if (pos != -1) {
				vCampos.add(new String(S.substring(posini, pos)));
				S = S.substring(pos + 1, S.length());
				numCampos++;
			} else
				eol = true;
		}
		return numCampos;
	}

}
