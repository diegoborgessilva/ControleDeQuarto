package gerenciarBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conexao.ConnectionFactory;
import conversoes.Converter;
import entidades.Quarto;
import entidades.Reserva;
import entidades.Tipo_Quarto;
import entidades.Visitante;

import java.util.*;

public class GerenciarReserva {
	private int id;
	public static final String SQLSELECTALL = "select * from reserva";

	public static final String SQLSELECTRESERVA = "select * from reserva"
			+ "  where idVisitante = ?";

	public static final String SQLUPDATE = "update reserva set IdQuarto= ?, idVisitante =?, data_entrada =?, data_saida =?, pagamento=?, status =? where codReserva=?";

	public static final String SQLINSERT = "insert into reserva "
			+ "(idVisitante,idQuarto,data_entrada,data_saida,pagamento,status) "
			+ "VALUES " + "(?,?,?,?,?,?)";

	public static final String SQLDELETE = "delete from reserva "
			+ "where codReserva=?";

	public List<Reserva> listarReservas() {
		Connection conexao = null;
		List<Reserva> Lreserva = new ArrayList<Reserva>();
		Lreserva = null;

		try {
			PreparedStatement ps = conexao.prepareStatement(SQLSELECTALL);
			System.out.println("buscando lista");

			ResultSet rs = ps.executeQuery();

			if (rs != null) {
				// tratar o resultado
				while (rs.next()) {
					Reserva objQuarto = new Reserva();
					objQuarto.setCodReserva(Converter.StringPARAint(rs
							.getString("codReserva")));
					objQuarto.setIdQuarto(Converter.StringPARAint(rs
							.getString("idQuarto")));
					objQuarto.setData_entrada((rs.getDate("data_entrada")));
					objQuarto.setData_saida(rs.getDate("data_saida"));
					objQuarto.setPagamento(rs.getString("pagamento"));
					objQuarto.setStatus(rs.getString("status"));
					Lreserva.add(objQuarto);
				}
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

	public void alterarReserva(Reserva novaReserva) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();

			PreparedStatement ps = conexao.prepareStatement(SQLUPDATE);

			ps.setInt(1, novaReserva.getIdQuarto());
			ps.setInt(2, novaReserva.getVisitante());
			ps.setString(3, Converter.FORMATADORDATA.format((Date) novaReserva
					.getData_entrada()));
			ps.setString(4, Converter.FORMATADORDATA.format((Date) novaReserva
					.getData_saida()));
			ps.setString(5, novaReserva.getPagamento());
			ps.setString(6, novaReserva.getStatus());
			ps.setInt(7, novaReserva.getCodReserva());

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

	public void inserirReserva(Reserva novaReserva) {
		Connection conexao = null;
		try {
			conexao = ConnectionFactory.getConnection();
			PreparedStatement ps = conexao.prepareStatement(SQLINSERT);
			ps.setInt(1, novaReserva.getVisitante());
			ps.setInt(2, novaReserva.getIdQuarto());

			ps.setString(3, Converter.FORMATADORDATA.format((Date) novaReserva
					.getData_entrada()));
			ps.setString(4, Converter.FORMATADORDATA.format((Date) novaReserva
					.getData_saida()));
			ps.setString(5, novaReserva.getPagamento());
			ps.setString(6, novaReserva.getStatus());

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

	public static ResultSet buscaDadosPessoais(String nome) throws SQLException {
		Connection conexao = null;
		ResultSet rs = null;

		conexao = ConnectionFactory.getConnection();

		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM visitante WHERE nome LIKE '" + nome.toUpperCase() + "%'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public Reserva BuscaReservas(String idVisitante) throws SQLException {

		Connection conexao = null;
		Reserva objReserva = new Reserva();
		try {
			conexao = ConnectionFactory.getConnection();

			PreparedStatement ps = conexao
					.prepareStatement("SELECT * FROM reserva WHERE idVisitante ="
							+ idVisitante);

			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {  
				System.out.println("ktjfyujvhtvukgujbkybkub");
				while (rs.next()) {
					objReserva.setCodReserva(Converter.StringPARAint(rs
							.getString("codReserva")));
					objReserva.setData_entrada(rs.getDate("data_entrada"));
					objReserva.setData_saida(rs.getDate("data_saida"));
					objReserva.setPagamento(rs.getString("pagamento"));
					objReserva.setStatus(rs.getString("status"));

				}
				
			}

			
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objReserva;

	}

	public boolean BuscaReservasData(String data, int idQuarto)
			throws SQLException {
		Connection conexao = null;
		ResultSet rs = null;

		conexao = ConnectionFactory.getConnection();
		Reserva objReserva = null;
		try {
			rs = conexao.createStatement().executeQuery(
					"SELECT * FROM reserva WHERE data_entrada = " + data);
			while (rs.next()) {
				objReserva = new Reserva();
				objReserva.setCodReserva(Converter.StringPARAint(rs
						.getString("codReserva")));
				objReserva.setVisitante(rs.getInt("idVisitante"));
				objReserva.setIdQuarto(Converter.StringPARAint(rs
						.getString("idQuarto")));
				objReserva.setData_entrada((rs.getDate("data_entrada")));
				objReserva.setData_saida(rs.getDate("data_saida"));
				objReserva.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (objReserva == null) {
			return true;
		}
		return false;
	}

}