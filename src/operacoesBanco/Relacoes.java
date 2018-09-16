package operacoesBanco;

import java.sql.SQLException;

import conversoes.Converter;
import entidades.Reserva;
import entidades.Tipo_Quarto;
import entidades.Visitante;
import gerenciarBanco.GerenciarQuarto;
import gerenciarBanco.GerenciarReserva;
import gerenciarBanco.GerenciarTipoQuarto;
import gerenciarBanco.GerenciarVisitante;

public class Relacoes {
	static GerenciarTipoQuarto gtq = new GerenciarTipoQuarto();
	static GerenciarReserva gr = new GerenciarReserva();
	static GerenciarVisitante gv = new GerenciarVisitante();

	public static boolean existeQuarto(int num) throws SQLException {
		GerenciarQuarto gq = new GerenciarQuarto();
		if (gq.BuscaQuartos(num) != null
				&& gq.BuscaQuartos(num).getPKIdQuarto() != 0) {
			return true;
		}
		return false;
	}

	public static boolean existeTipoDeQuarto(String desc) throws SQLException {
		gtq = new GerenciarTipoQuarto();

		Tipo_Quarto qtipo = gtq.BuscaTipoQuartos(desc);

		if (qtipo != null && qtipo.getPKIdTipo() != 0) {

			if (qtipo.getDescricao().equals(desc)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean mesma_data(String data) throws SQLException {
		if (gr.BuscaReservasData(data, 19)) {
			return true;
		}
		return false;

	}

	public static boolean estaVazio(String s) {
		if (!s.equals("")) {
			if (!s.equals(null)) {
				return true;
			}
		}
		return false;
	}

	public static boolean existeVisitante(String nome) throws SQLException {
		Visitante v = gv.buscaVisitante(nome);
		if (v != null && v.getIdVisitante() != 0) {
			return true;
		}
		return false;
	}

	public static boolean existeReserva(int id) throws SQLException {
		Reserva r = gr.BuscaReservas("" + id);
		if (r != null && r.getCodReserva() != 0) {
			return true;
		}
		return false;
	}

}
