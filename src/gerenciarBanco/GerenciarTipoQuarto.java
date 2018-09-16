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
import entidades.Tipo_Quarto;

public class GerenciarTipoQuarto {

	public static final String SQLSELECTALL = "select * from tipo_quarto";

	public static final String SQLSELECTTIPOQUARTO = "select * from tipo_quarto"
			+ "  where idTipo = ?";

	public static final String SQLUPDATE = "update tipo_quarto "
			+ "set descricao= ? where idTipo=?";

	public static final String SQLINSERT = "insert into tipo_quarto "
			+ "(descricao) " + "VALUES " + "(?)";

	public static final String SQLDELETE = "delete from tipo_quarto "
			+ "where id=?";

	public List<Tipo_Quarto> listarTiposQuartos(String pesqtipo_Quarto) {
		Connection conexao = null;
		List<Tipo_Quarto> Lquartos = new ArrayList<Tipo_Quarto>();

		try {
			PreparedStatement ps = conexao
					.prepareStatement(SQLSELECTTIPOQUARTO);
			pesqtipo_Quarto = "%" + pesqtipo_Quarto.toUpperCase() + "%";
			System.out.println(pesqtipo_Quarto);
			ps.setString(1, pesqtipo_Quarto);
			ResultSet rs = ps.executeQuery();

			// tratar o resultado
			while (rs.next()) {
				Tipo_Quarto objQuarto = new Tipo_Quarto();
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

	public void excluirTipoQuartos(int idTipo) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao
					.prepareStatement("delete from tipo_quarto " + "where idTipo="
							+ idTipo);
			ps.execute();
			ps.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarTipoDeQuartos(Tipo_Quarto novoQuarto) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao
					.prepareStatement("update tipo_quarto set descricao= ? where idTipo = "
							+ novoQuarto.getPKIdTipo());

			ps.setString(1, novoQuarto.getDescricao().toUpperCase());
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

	public void inserirTipoQuarto(Tipo_Quarto novoQuarto) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLINSERT
					+ novoQuarto);
			ps.setString(1, novoQuarto.getDescricao().toUpperCase());
			ps.execute();
			ps.close();
			conexao.close();
		} catch (SQLException e) {
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

	public static Tipo_Quarto BuscaTipoQuartos(String desc) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		ResultSet rs = null;
		Tipo_Quarto objTipoQuarto = new Tipo_Quarto();
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM tipo_quarto WHERE descricao LIKE '"
							+ desc.toUpperCase() + "%'");
			while (rs.next()) {
				objTipoQuarto = new Tipo_Quarto();
				objTipoQuarto.setPKIdTipo(Converter.StringPARAint(rs
						.getString("idTipo")));
				objTipoQuarto.setDescricao(rs.getString("descricao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objTipoQuarto;
	}

	public static ResultSet resultTipoQuartos(String desc) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		ResultSet rs = null;
		Tipo_Quarto objTipoQuarto = new Tipo_Quarto();
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM tipo_quarto WHERE descricao LIKE '"
							+ desc.toUpperCase() + "%'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

}
