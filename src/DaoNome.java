import java.sql.ResultSet;  
import java.sql.SQLException;  
  
public class DaoNome {  
    MySQLCon con;     
      
    public DaoNome(){  
        con = new MySQLCon();     
    }  
    public ResultSet buscaDadosPessoais(String nome){  
        ResultSet rs = null;;  
        try {  
            rs = con.retornaComando().executeQuery("SELECT * FROM visitante WHERE nome LIKE '"+nome + "%'");  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }     
        return rs;    
    }  
      
}  