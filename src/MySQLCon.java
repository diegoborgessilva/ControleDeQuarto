    import java.sql.Connection;  
    import java.sql.SQLException;  
    import java.sql.Statement;  
      
    public class MySQLCon {  
          
        Connection conexao;  
        Statement comando;  
          
        private void conectar(){  
            try {  
                conexao = Conexao.getConnection();  
                comando = conexao.createStatement();  
           } catch (SQLException e) {  
                e.printStackTrace();  
            }         
        }     
          
        public Statement retornaComando(){  
            conectar();  
            return comando;  
        }  
          
        public void desconectar(){  
            try {  
                comando.close();  
                conexao.close();  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  

