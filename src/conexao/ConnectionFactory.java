package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static final String URL = "jdbc:mysql://localhost/labinfo";
	public static final String USUARIO = "root";
	public static final String SENHA = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);

	}

}
