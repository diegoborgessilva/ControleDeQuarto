package telasReserva;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JComboBox;

import TelasMenus.Menu;

import javax.swing.JButton;

import conversoes.Converter;
import operacoesBanco.OperacaoCRUD;
import entidades.Quarto;
import entidades.Reserva;
import entidades.Visitante;
import gerenciarBanco.GerenciarQuarto;
import gerenciarBanco.GerenciarReserva;
import gerenciarBanco.GerenciarVisitante;

import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class ExcluirReserva extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JComboBox listSugestao;
	private JButton btnExcluir;
	GerenciarQuarto gq = new GerenciarQuarto();
	OperacaoCRUD opc = new OperacaoCRUD();
	GerenciarVisitante gv = new GerenciarVisitante();
	GerenciarReserva gr = new GerenciarReserva();
	private JLabel lblQuarto;
	private JTextField textQuarto;
	private JLabel lblEntrada;
	private JTextField textDataEntrada;
	private JLabel lblSaida;
	private JTextField textSaida;
	private JLabel lblPagamento;
	private JTextField textPagamento;
	private JLabel lblStatus;
	private JTextField textStatus;
	private Reserva reserva;
	private JMenuBar menuBar;
	private JButton btnMenu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirReserva frame = new ExcluirReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExcluirReserva() {
		setTitle("Excluir Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToMenu();
				
			}
		});
		menuBar.add(btnMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblVisitante = new JLabel("Visitante");
		GridBagConstraints gbc_lblVisitante = new GridBagConstraints();
		gbc_lblVisitante.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisitante.anchor = GridBagConstraints.EAST;
		gbc_lblVisitante.gridx = 2;
		gbc_lblVisitante.gridy = 0;
		contentPane.add(lblVisitante, gbc_lblVisitante);

		textNome = new JTextField();
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.insets = new Insets(0, 0, 5, 5);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 3;
		gbc_textNome.gridy = 0;
		contentPane.add(textNome, gbc_textNome);
		textNome.setColumns(10);
		textNome.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (textNome.getText().equals("")) {
					textNome.setVisible(true);
					return;
				}
				String dados[] = null;
				try {
					dados = AutoCompletarCampo.buscaSugestao(textNome.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				listSugestao.removeAllItems();
				listSugestao.setVisible(true);
				if (dados[0] == null) {
					dados[0] = "Não encontrado!";
				}

				for (int i = 0; i < dados.length; i++) {
					listSugestao.addItem(dados[i]);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		listSugestao = new JComboBox();
		GridBagConstraints gbc_listSugestao = new GridBagConstraints();
		gbc_listSugestao.insets = new Insets(0, 0, 5, 5);
		gbc_listSugestao.fill = GridBagConstraints.HORIZONTAL;
		gbc_listSugestao.gridx = 3;
		gbc_listSugestao.gridy = 1;
		contentPane.add(listSugestao, gbc_listSugestao);
		
		lblQuarto = new JLabel("Quarto");
		GridBagConstraints gbc_lblQuarto = new GridBagConstraints();
		gbc_lblQuarto.anchor = GridBagConstraints.EAST;
		gbc_lblQuarto.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuarto.gridx = 2;
		gbc_lblQuarto.gridy = 3;
		contentPane.add(lblQuarto, gbc_lblQuarto);
		
		textQuarto = new JTextField();
		textQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					carregar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		GridBagConstraints gbc_textQuarto = new GridBagConstraints();
		gbc_textQuarto.insets = new Insets(0, 0, 5, 5);
		gbc_textQuarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textQuarto.gridx = 3;
		gbc_textQuarto.gridy = 3;
		contentPane.add(textQuarto, gbc_textQuarto);
		textQuarto.setColumns(10);
		
		lblEntrada = new JLabel("Entrada");
		GridBagConstraints gbc_lblEntrada = new GridBagConstraints();
		gbc_lblEntrada.anchor = GridBagConstraints.EAST;
		gbc_lblEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrada.gridx = 2;
		gbc_lblEntrada.gridy = 4;
		contentPane.add(lblEntrada, gbc_lblEntrada);
		
		textDataEntrada = new JTextField();
		GridBagConstraints gbc_textDataEntrada = new GridBagConstraints();
		gbc_textDataEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_textDataEntrada.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDataEntrada.gridx = 3;
		gbc_textDataEntrada.gridy = 4;
		contentPane.add(textDataEntrada, gbc_textDataEntrada);
		textDataEntrada.setColumns(10);
		
		lblSaida = new JLabel("Saida");
		GridBagConstraints gbc_lblSaida = new GridBagConstraints();
		gbc_lblSaida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaida.gridx = 2;
		gbc_lblSaida.gridy = 5;
		contentPane.add(lblSaida, gbc_lblSaida);
		
		textSaida = new JTextField();
		GridBagConstraints gbc_textSaida = new GridBagConstraints();
		gbc_textSaida.insets = new Insets(0, 0, 5, 5);
		gbc_textSaida.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSaida.gridx = 3;
		gbc_textSaida.gridy = 5;
		contentPane.add(textSaida, gbc_textSaida);
		textSaida.setColumns(10);
		
		lblPagamento = new JLabel("Pagamento");
		GridBagConstraints gbc_lblPagamento = new GridBagConstraints();
		gbc_lblPagamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblPagamento.gridx = 2;
		gbc_lblPagamento.gridy = 6;
		contentPane.add(lblPagamento, gbc_lblPagamento);
		
		textPagamento = new JTextField();
		GridBagConstraints gbc_textPagamento = new GridBagConstraints();
		gbc_textPagamento.insets = new Insets(0, 0, 5, 5);
		gbc_textPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPagamento.gridx = 3;
		gbc_textPagamento.gridy = 6;
		contentPane.add(textPagamento, gbc_textPagamento);
		textPagamento.setColumns(10);
		
		lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 2;
		gbc_lblStatus.gridy = 7;
		contentPane.add(lblStatus, gbc_lblStatus);
		
		textStatus = new JTextField();
		GridBagConstraints gbc_textStatus = new GridBagConstraints();
		gbc_textStatus.insets = new Insets(0, 0, 5, 5);
		gbc_textStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textStatus.gridx = 3;
		gbc_textStatus.gridy = 7;
		contentPane.add(textStatus, gbc_textStatus);
		textStatus.setColumns(10);

		btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 9;
		contentPane.add(btnExcluir, gbc_btnExcluir);

		listSugestao.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textNome.setText((String) listSugestao.getSelectedItem());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
				
			}
		});
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}



	private void carregar() throws SQLException {
		reserva = new Reserva();
		reserva = gr.BuscaReservas(textQuarto.getText());
		System.out.println(reserva.getStatus());
		
		textDataEntrada.setText(""+reserva.getData_entrada().toString());
		textSaida.setText(""+reserva.getData_saida().toString());
		textPagamento.setText(reserva.getPagamento());
		textStatus.setText(reserva.getStatus());
		
	}
}
