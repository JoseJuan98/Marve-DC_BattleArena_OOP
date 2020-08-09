package marvelydc.objetos;

import java.util.Comparator;
/**
* Comparador que sigue el criterio de comparar armas por el poder
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
public class ComparadorArmasPoder implements Comparator<Arma> {

	@Override
	public int compare(Arma o1, Arma o2) {
		if(o1.poder<o2.poder)
			return -1;
		if(o1.poder>o2.poder)
			return 1;
		
		return 0;
	}

}
