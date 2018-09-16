package operacoesBanco;

import entidades.Quarto;
import entidades.Reserva;
import entidades.Tipo_Quarto;
import entidades.Visitante;
import gerenciarBanco.GerenciarQuarto;
import gerenciarBanco.GerenciarTipoQuarto;
import gerenciarBanco.GerenciarVisitante;
import gerenciarBanco.GerenciarReserva;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;




import conexao.ConnectionFactory;
import conversoes.Converter;

public class OperacaoCRUD {
	GerenciarTipoQuarto gtq = new GerenciarTipoQuarto();
	GerenciarQuarto gq = new GerenciarQuarto();
	GerenciarReserva gr = new GerenciarReserva();
	GerenciarVisitante gv = new GerenciarVisitante();
	public void inserirReserva(int visitante, int idQuarto, String data_entrada,
			String data_saida, String pagamento, String status) throws Exception {
		Reserva novaReserva = new Reserva();
		novaReserva.setVisitante(visitante);
		novaReserva.setIdQuarto(idQuarto);
		novaReserva.setData_entrada(Converter.FORMATADORDATA.parse(data_entrada)); 		
		novaReserva.setData_saida((Converter.FORMATADORDATA.parse(data_saida)));
		novaReserva.setPagamento(pagamento);
		novaReserva.setStatus(status);
		gr.inserirReserva(novaReserva);
	}

	public void inserirTipoDeQuarto(String tipo) {

		Tipo_Quarto novoTipo = new Tipo_Quarto();
		novoTipo.setDescricao(tipo);

		gtq.inserirTipoQuarto(novoTipo);
	}

	public void inserirVisitante(String nome, String email, String telefone) {
		Visitante novoVisitante = new Visitante();
		novoVisitante.setNome(nome);
		novoVisitante.setEmail(email);
		novoVisitante.setTelefone(telefone);

		
		gv.inserirVisitante(novoVisitante);

	}

	public void inserirQuarto(int num, int tipo, int capacidade,
			String descricao) {
		System.out.println("... INSERINDO QUARTO ...");
		Quarto novoQuarto = new Quarto();
		novoQuarto.setNumeroDoQuarto(num);
		novoQuarto.setFKTipo(tipo);
		novoQuarto.setCapacidade(capacidade);
		novoQuarto.setDescricao(descricao);
		gq.inserirQuarto(novoQuarto);
	}

	public void AlterarQuarto(int numero, String descricao, int capacidade ) throws SQLException {
		Quarto q = buscarQuarto(numero);
		q.setCapacidade(capacidade);
		q.setDescricao(descricao);
		gq.alterarQuartos(q);
	}
	
	public void AlterarReserva(int codRe,int idvisitante, int idQuarto , String dataEntrada,String dataSaida ) throws SQLException, ParseException {
		Reserva novaReserva = new Reserva();
		novaReserva.setCodReserva(codRe);
		novaReserva.setIdQuarto(idQuarto);
		novaReserva.setVisitante(idvisitante);
		novaReserva.setData_entrada(Converter.FORMATADORDATA.parse(dataEntrada));
		novaReserva.setData_saida(Converter.FORMATADORDATA.parse(dataSaida));
		gr.alterarReserva(novaReserva);
	}
	
	
	public void AlterarTipoDeQuarto(String descAtual, String descNovo ) throws SQLException {
		System.out.println("... ALTERANDO QUARTO ...");
		Tipo_Quarto novoQuarto = gtq.BuscaTipoQuartos(descAtual);
		novoQuarto.setDescricao(descNovo);
		gtq.alterarTipoDeQuartos(novoQuarto);
		
	}
	
	public Quarto buscarQuarto(int num) throws SQLException{
		return gq.BuscaQuartos(num);
	}
	
	
	public void excluirTipoDeQurto(String desc) throws SQLException {
		Tipo_Quarto qt = GerenciarTipoQuarto.BuscaTipoQuartos(desc);
		gtq.excluirTipoQuartos(qt.getPKIdTipo());
	}

	public void excluirQurto(String text) {
		gq.excluirQuarto(Converter.StringPARAint(text)); 	
		
	}

	public void excluirVisitante(int id) {
		gv.excluirVisitante(id);
		// TODO Auto-generated method stub
		
	}

	public void alterarVisitante(Visitante novoVisitante) {  
		gv.alterarVisitante(novoVisitante);
		
	}
	
}
