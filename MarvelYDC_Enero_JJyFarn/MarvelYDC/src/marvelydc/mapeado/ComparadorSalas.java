package marvelydc.mapeado;

import java.util.Comparator;

/**
* Comparador de salas segun el criterio de la frecuencia, y en caso de tener la misma frecuencia la que 
* tenga el idSala menor
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class ComparadorSalas implements Comparator<Sala> {
 
	@Override
	public int compare(Sala o1, Sala o2) {
		if(o1.frecuencia>o2.frecuencia)
			return 1;
		else
			if(o1.frecuencia<o2.frecuencia)
				return -1;
			else
				if(o1.getIdSala()<o2.getIdSala())
					return 1;
				else
					if(o1.getIdSala()>o2.getIdSala())
						return -1;
					else
						return 0;
		
	}

}
