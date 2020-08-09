package marvelydc.ui;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Panel que permite introducir y modificar texto
* @version 10/01/2018 
* @author Jose Juan Pena Gomez & Francisco Nunez Sierra
*         Desarrollo de Programas<br/>
*         <b>Grupo: JJyFarn </b><br>
*         <b>Entrega: EC_Final </b><br>
*         Curso: 2017/2018
*/
@SuppressWarnings("serial")
public class FilePanel extends JScrollPane{
	private JTextArea area = new JTextArea();
	
	public FilePanel(){
		super();
		setViewportView(area);
		area.setEditable(true);
		area.setLineWrap(true);
	}
	
	public void addText(String text){
		area.append(text);
		area.append("\n");
	}
	
	public void setText(String text){
		area.setText(text);
	}
	
	public String getTexto(){
		return area.getText();
		
	}
}
