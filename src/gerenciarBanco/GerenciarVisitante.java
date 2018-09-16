package gerenciarBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionFactory;
import conversoes.Converter;
import entidades.Reserva;
import entidades.Tipo_Quarto;
import entidades.Visitante;

public class GerenciarVisitante {
	public static final String SQLSELECTALL = "select * from visitante";

	public static final String SQLSELECTVISITANTE = "select * from visitante"
			+ "  where idvisitante=?";

	public static final String SQLUPDATE = "update visitante set nome= ?, telefone =?, email=?  where idvisitante=?";

	public static final String SQLINSERT = "insert into visitante "
			+ "(nome, telefone, email) " + "VALUES " + "(?,?,?)";

	public static final String SQLDELETE = "delete from visitante "
			+ "where idvisitante=";

	public List<Visitante> listarVisitantes(String pesqVisitante) {
		Connection conexao = null;
		List<Visitante> Lreserva = new ArrayList<Visitante>();

		try {
			PreparedStatement ps = conexao.prepareStatement(SQLSELECTVISITANTE);
			pesqVisitante = "%" + pesqVisitante.toUpperCase() + "%";
			System.out.println(pesqVisitante);
			ps.setString(1, pesqVisitante);
			ResultSet rs = ps.executeQuery();

			// tratar o resultado
			while (rs.next()) {
				Visitante objVisitante = new Visitante();
				objVisitante.setIdVisitante(Converter.StringPARAint(rs
						.getString("idvisitante")));

				objVisitante.setTelefone(rs.getString("telefone"));
				objVisitante.setNome(rs.getString("nome"));
				objVisitante.setEmail(rs.getString("email"));
				Lreserva.add(objVisitante);
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

		return Lreserva;
	}

	public void alterarVisitante(Visitante novoVisitante) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLUPDATE);
			ps.setString(1, novoVisitante.getNome());
			ps.setString(2, novoVisitante.getTelefone());
			ps.setString(3, novoVisitante.getEmail());
			ps.setInt(4, novoVisitante.getidVisitante());
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

	public void inserirVisitante(Visitante novoVisitante) {
		Connection conexao = null;

		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLINSERT);
			ps.setString(1, novoVisitante.getNome());
			ps.setString(2, novoVisitante.getTelefone());
			ps.setString(3, novoVisitante.getEmail());
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

	public Visitante buscaVisitante(String nome) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		ResultSet rs = null;
		Visitante objVisitante = new Visitante();
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM visitante WHERE nome LIKE '"
							+ nome.toUpperCase() + "%'");
			while (rs.next()) {
				objVisitante = new Visitante();
				objVisitante.setIdVisitante(Converter.StringPARAint(rs
						.getString("idvisitante")));

				objVisitante.setNome(rs.getString("nome"));
				objVisitante.setEmail(rs.getString("email"));
				objVisitante.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objVisitante;
	}

	public void excluirVisitante(int id) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLDELETE+id);			
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

}
