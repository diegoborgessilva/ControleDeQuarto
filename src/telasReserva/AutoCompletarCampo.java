package telasReserva;

import gerenciarBanco.GerenciarReserva;
import gerenciarBanco.GerenciarTipoQuarto;
import gerenciarBanco.GerenciarVisitante;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoCompletarCampo {
	static String dados[];
	private static int id;
	public static String[] buscaSugestao(String texto) throws SQLException {

		ResultSet rs = GerenciarReserva.buscaDadosPessoais(texto);	 
		dados = new String[5];
		if (dados != null&& rs!=null) {

			try {
				for (int i = 0; rs.next() && i < dados.length; i++) {
					if(i==0){
						id = rs.getInt("idvisitante");
					}
					dados[i] = rs.getString("nome");
					System.out.println(i + " - " + dados[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	
		return dados;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		AutoCompletarCampo.id = id;
	}
	
	
	
	
	
	public static String[] buscaSugestaotipo(String text) throws SQLException  {
		ResultSet rs = GerenciarTipoQuarto.resultTipoQuartos(text);
		dados = new String[5];
		if (dados != null&& rs!=null) {

			try {
				for (int i = 0; rs.next() && i < dados.length; i++) {
					if(i==0){
						id = rs.getInt("idTipo");
					}
					dados[i] = rs.getString("descricao");
					System.out.println(i + " - " + dados[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	
		return dados;
	}

}
