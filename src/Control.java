import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;
public class Control {  
    private DaoNome daoNome;      
    String dados[];  
  
    public Control() {  
        daoNome = new DaoNome();  
    }  
	  
	    public String[] buscaSug(String texto) {  
	        ResultSet rs = daoNome.buscaDadosPessoais(texto);  
	        int i = 0;  
	        // Vamos limitar a 10 sugestões <img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">  
	        dados = new String[10];  
	        try {  
	            for (i = 0; rs.next() && i < dados.length; i++) {  
	                dados[i] = rs.getString("nome");  
	                System.out.println(i + " - " + dados[i]);  
	            }  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	              
	        return dados;  
	    }  
}  