package gerenciarBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionFactory;
import conversoes.Converter;
import entidades.Quarto;

public class GerenciarQuarto {

	public static final String SQLSELECTALL = "select * from quarto";

	public static final String SQLSELECTQUARTO = "select quarto from quarto"
			+ "  where numQuarto = ?";

	public static final String SQLUPDATE = "update quarto "
			+ "set numQuarto = ?," + "FKtipo= ?," + "capacidade =? "
			+ "descricao = ?" + "where PKidQuarto=?";

	public static final String SQLINSERT = "insert into quarto "
			+ "(numQuarto,FKtipo, capacidade, descricao) " + "VALUES "
			+ "(?, ?, ?, ?)";

	public static final String SQLDELETE = "delete from quartos "
			+ "where PKidQuarto=?";

	public List<Quarto> listarQuartos(String pesqQuarto) {
		Connection conexao = null;
		List<Quarto> Lquartos = new ArrayList<Quarto>();

		try {
			PreparedStatement ps = conexao.prepareStatement(SQLSELECTALL);
			pesqQuarto = "%" + pesqQuarto.toUpperCase() + "%";
			System.out.println(pesqQuarto);
			ps.setString(1, pesqQuarto);
			ResultSet rs = ps.executeQuery();

			// tratar o resultado
			while (rs.next()) {
				Quarto objQuarto = new Quarto();
				objQuarto.setPKIdQuarto(Converter.StringPARAint(rs
						.getString("PKidQuarto")));
				objQuarto.setNumeroDoQuarto(rs.getInt("numQuarto"));
				objQuarto.setFKTipo(Converter.StringPARAint(rs.getString("FKtipo")));
				objQuarto.setCapacidade(Converter.StringPARAint(rs
						.getString("capacidade")));
				objQuarto.setDescricao(rs.getString("descricao"));
				Lquartos.add(objQuarto);
			}
			rs.close();
			ps.close();
			conexao.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if ((conexao != null) && (!conexao.isClosed())) {
					conexao.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return Lquartos;
	}

	public void alterarQuartos(Quarto novoQuarto) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement("update quarto "
					+ "set numQuarto = ?, FKtipo= ?, descricao = ?, capacidade =?  where PKidQuarto = "+novoQuarto.getPKIdQuarto());			
			ps.setInt(1, novoQuarto.getNumeroDoQuarto());
			ps.setInt(2, novoQuarto.getFKTipo());			
			ps.setString(3, novoQuarto.getDescricao());
			ps.setInt(4, novoQuarto.getCapacidade());

			ps.execute();
			ps.close();
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if ((conexao != null) && (!conexao.isClosed())) {
					conexao.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void inserirQuarto(Quarto novoQuarto) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLINSERT);
			
			ps.setInt(1, novoQuarto.getNumeroDoQuarto());
			ps.setInt(2, novoQuarto.getFKTipo());
			ps.setInt(3, novoQuarto.getCapacidade());
			ps.setString(4, novoQuarto.getDescricao());
			ps.execute();
			ps.close();
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if ((conexao != null) && (!conexao.isClosed())) {
					conexao.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public Quarto BuscaQuartos(int numQuarto) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		ResultSet rs = null;
		Quarto objQuarto = new Quarto();
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM quarto WHERE numQuarto =" + numQuarto);
			while (rs.next()) {
				objQuarto = new Quarto();
				objQuarto.setPKIdQuarto(Converter.StringPARAint(rs
						.getString("PKidQuarto")));
				objQuarto.setNumeroDoQuarto(rs.getInt("numQuarto"));
				objQuarto
						.setFKTipo(Converter.StringPARAint(rs.getString("FKtipo")));
				objQuarto.setCapacidade(Converter.StringPARAint(rs
						.getString("capacidade")));
				objQuarto.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("QUARTO: "+objQuarto.getDescricao());
			
		return objQuarto;
	}

	public void excluirQuarto(int id) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao
					.prepareStatement("delete from quarto " + "where PKidQuarto="
							+ id);
			ps.execute();
			ps.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Quarto BuscaQuartosId(int id) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		ResultSet rs = null;
		Quarto objQuarto = new Quarto();
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM quarto WHERE PKidQuarto =" + id);
			while (rs.next()) {
				objQuarto = new Quarto();
				objQuarto.setPKIdQuarto(Converter.StringPARAint(rs
						.getString("PKidQuarto")));
				objQuarto.setNumeroDoQuarto(rs.getInt("numQuarto"));
				objQuarto
						.setFKTipo(Converter.StringPARAint(rs.getString("FKtipo")));
				objQuarto.setCapacidade(Converter.StringPARAint(rs
						.getString("capacidade")));
				objQuarto.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("QUARTO: "+objQuarto.getDescricao());
			
		return objQuarto;
	}
}
