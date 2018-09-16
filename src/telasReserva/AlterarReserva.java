package telasReserva;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JSplitPane;
import javax.swing.JList;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.Color;

import javax.swing.JMenuBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;

import TelasMenus.Menu;
import conversoes.Converter;
import entidades.Quarto;
import entidades.Reserva;
import entidades.Visitante;
import gerenciarBanco.GerenciarQuarto;
import gerenciarBanco.GerenciarReserva;
import gerenciarBanco.GerenciarVisitante;
import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;

import java.awt.SystemColor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class AlterarReserva extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JList listListaClientesCadastrados;
	private JList listListaDeQuartosDisponiveis;
	private JLabel lblNome;
	private JTextField textNomeDoCliente;
	private JLabel lblQuarto;
	private JTextField textQuarto;
	private JLabel lblEntrada;
	private JTextField textDataEntrada;
	private JLabel lblPagamento;
	private JLabel lblCapacidade;
	private JTextField textCapacidade;
	private JLabel lblTipo;
	private JTextField textTipo;
	private JButton btnAlterar;
	private JMenuBar menuBar;
	private JButton btnMenu;
	private JComboBox comboBox;
	private JComboBox listSugestao;

	private Visitante visitanteAtual;
	private JTextField textResultado;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextField textStatus;
	private JLabel lblStatus;
	private JTextField textSaida;
	private JLabel lblSaida;
	GerenciarQuarto gq = new GerenciarQuarto();
	OperacaoCRUD opc = new OperacaoCRUD();
	GerenciarVisitante gv = new GerenciarVisitante();
	GerenciarReserva gr = new GerenciarReserva();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EfetuarReserva frame = new EfetuarReserva();
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
	public AlterarReserva() {
		setTitle("Alterar Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 377);

		menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToMenu();
			}
		});
		menuBar.add(btnMenu);

		panel_1 = new JPanel();
		menuBar.add(panel_1);

		textResultado = new JTextField();
		textResultado.setBackground(Color.LIGHT_GRAY);
		textResultado.setForeground(Color.BLACK);
		textResultado.setEditable(false);
		menuBar.add(textResultado);
		textResultado.setColumns(8);

		panel_2 = new JPanel();
		menuBar.add(panel_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblNome = new JLabel("Nome");
		lblNome.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 2;
		panel.add(lblNome, gbc_lblNome);

		textNomeDoCliente = new JTextField();
		textNomeDoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_textNomeDoCliente = new GridBagConstraints();
		gbc_textNomeDoCliente.anchor = GridBagConstraints.WEST;
		gbc_textNomeDoCliente.insets = new Insets(0, 0, 5, 0);
		gbc_textNomeDoCliente.gridx = 1;
		gbc_textNomeDoCliente.gridy = 2;
		panel.add(textNomeDoCliente, gbc_textNomeDoCliente);
		textNomeDoCliente.setColumns(20);

		listSugestao = new JComboBox();
		listSugestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_listSugestao = new GridBagConstraints();
		gbc_listSugestao.anchor = GridBagConstraints.WEST;
		gbc_listSugestao.insets = new Insets(0, 0, 5, 0);
		gbc_listSugestao.gridx = 1;
		gbc_listSugestao.gridy = 3;
		panel.add(listSugestao, gbc_listSugestao);

		lblQuarto = new JLabel("N\u00BA Quarto");
		lblQuarto.setToolTipText("Quarto");
		GridBagConstraints gbc_lblQuarto = new GridBagConstraints();
		gbc_lblQuarto.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuarto.gridx = 0;
		gbc_lblQuarto.gridy = 5;
		panel.add(lblQuarto, gbc_lblQuarto);

		textQuarto = new JTextField();
		
		textQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
				Quarto q;
				try {
					q = gq.BuscaQuartos(Converter.StringPARAint(textQuarto.getText()));
					textCapacidade.setText(""+q.getCapacidade());
					textTipo.setText(""+q.getDescricao());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_textQuarto = new GridBagConstraints();
		gbc_textQuarto.anchor = GridBagConstraints.WEST;
		gbc_textQuarto.insets = new Insets(0, 0, 5, 0);
		gbc_textQuarto.gridx = 1;
		gbc_textQuarto.gridy = 5;
		panel.add(textQuarto, gbc_textQuarto);
		textQuarto.setColumns(10);

		lblCapacidade = new JLabel("Capacidade");
		GridBagConstraints gbc_lblCapacidade = new GridBagConstraints();
		gbc_lblCapacidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidade.gridx = 0;
		gbc_lblCapacidade.gridy = 6;
		panel.add(lblCapacidade, gbc_lblCapacidade);

		textCapacidade = new JTextField();
		textCapacidade.setEditable(false);
		textCapacidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_textCapacidade = new GridBagConstraints();
		gbc_textCapacidade.anchor = GridBagConstraints.WEST;
		gbc_textCapacidade.insets = new Insets(0, 0, 5, 0);
		gbc_textCapacidade.gridx = 1;
		gbc_textCapacidade.gridy = 6;
		panel.add(textCapacidade, gbc_textCapacidade);
		textCapacidade.setColumns(10);

		lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 7;
		panel.add(lblTipo, gbc_lblTipo);

		textTipo = new JTextField();
		textTipo.setEditable(false);
		GridBagConstraints gbc_textTipo = new GridBagConstraints();
		gbc_textTipo.anchor = GridBagConstraints.WEST;
		gbc_textTipo.insets = new Insets(0, 0, 5, 0);
		gbc_textTipo.gridx = 1;
		gbc_textTipo.gridy = 7;
		panel.add(textTipo, gbc_textTipo);
		textTipo.setColumns(10);

		lblEntrada = new JLabel("Entrada");
		GridBagConstraints gbc_lblEntrada = new GridBagConstraints();
		gbc_lblEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrada.gridx = 0;
		gbc_lblEntrada.gridy = 8;
		panel.add(lblEntrada, gbc_lblEntrada);

		textDataEntrada = new JTextField();
		textDataEntrada.setText("0000-00-00");
		GridBagConstraints gbc_textDataEntrada = new GridBagConstraints();
		gbc_textDataEntrada.anchor = GridBagConstraints.WEST;
		gbc_textDataEntrada.insets = new Insets(0, 0, 5, 0);
		gbc_textDataEntrada.gridx = 1;
		gbc_textDataEntrada.gridy = 8;
		panel.add(textDataEntrada, gbc_textDataEntrada);
		textDataEntrada.setColumns(10);

		String statusList[] = { "STATUS", "CONFIRMADO", "PENDENTE" };

		lblSaida = new JLabel("Saida");
		GridBagConstraints gbc_lblSaida = new GridBagConstraints();
		gbc_lblSaida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaida.gridx = 0;
		gbc_lblSaida.gridy = 9;
		panel.add(lblSaida, gbc_lblSaida);

		textSaida = new JTextField();
		textSaida.setText("0000-00-00");
		textSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_textSaida = new GridBagConstraints();
		gbc_textSaida.anchor = GridBagConstraints.WEST;
		gbc_textSaida.insets = new Insets(0, 0, 5, 0);
		gbc_textSaida.gridx = 1;
		gbc_textSaida.gridy = 9;
		panel.add(textSaida, gbc_textSaida);
		textSaida.setColumns(10);

		lblPagamento = new JLabel("Pagamento");
		GridBagConstraints gbc_lblPagamento = new GridBagConstraints();
		gbc_lblPagamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblPagamento.gridx = 0;
		gbc_lblPagamento.gridy = 13;
		panel.add(lblPagamento, gbc_lblPagamento);

		comboBox = new JComboBox(statusList);
		comboBox.setEditable(true);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(comboBox);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 13;
		panel.add(comboBox, gbc_comboBox);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					alterar();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 14;
		panel.add(lblStatus, gbc_lblStatus);

		textStatus = new JTextField();
		textStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_textStatus = new GridBagConstraints();
		gbc_textStatus.anchor = GridBagConstraints.WEST;
		gbc_textStatus.insets = new Insets(0, 0, 5, 0);
		gbc_textStatus.gridx = 1;
		gbc_textStatus.gridy = 14;
		panel.add(textStatus, gbc_textStatus);
		textStatus.setColumns(10);
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.anchor = GridBagConstraints.WEST;
		gbc_btnAlterar.gridx = 1;
		gbc_btnAlterar.gridy = 17;
		panel.add(btnAlterar, gbc_btnAlterar);

		textNomeDoCliente.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (textNomeDoCliente.getText().equals("")) {
					textNomeDoCliente.setVisible(true);
					return;
				}
				String dados[] = null;
				try {
					dados = AutoCompletarCampo.buscaSugestao(textNomeDoCliente
							.getText());
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

		listSugestao.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textNomeDoCliente.setText((String) listSugestao
						.getSelectedItem());
	  
			}
		});
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}

	public void alterar() throws Exception {
		if (verificar() == true) {
			Visitante vi = gv.buscaVisitante(textNomeDoCliente.getText());
			Reserva r = gr.BuscaReservas("" + vi.getIdVisitante());
			Quarto q = gq.BuscaQuartos(Converter.StringPARAint(textQuarto.getText()));
			 opc.AlterarReserva(r.getCodReserva(), vi.getIdVisitante(),q.getPKIdQuarto() , textDataEntrada.getText(),textSaida.getText());

			textResultado.setBackground(Color.WHITE);
			textResultado.setForeground(Color.RED);
			textResultado.setText("Dados Inseridos Com sucesso!!");
		} else {
			textResultado.setBackground(Color.YELLOW);
			textResultado.setForeground(Color.RED);
			textResultado.setText("Preencha Corretamente os Campos");
		}
	}

	public Boolean verificar() throws SQLException {

		if (Relacoes.estaVazio(textNomeDoCliente.getText())) {
			if (Relacoes.existeVisitante(textNomeDoCliente.getText()) == true) {

				Visitante vi = gv.buscaVisitante(textNomeDoCliente.getText());

				if (Relacoes.existeReserva(vi.getIdVisitante()) == true) {

					if (Relacoes.estaVazio(textQuarto.getText())) {
						if (Relacoes.existeQuarto(Converter	.StringPARAint(textQuarto.getText()))) {
							Quarto q = gq.BuscaQuartos(Converter.StringPARAint(textQuarto.getText()));
							textCapacidade.setText("" + q.getCapacidade());
							textTipo.setText("" + q.getFKTipo());
							String ss = "0000-00-00";
							if (Relacoes.estaVazio(textDataEntrada.getText())
									&& Relacoes.estaVazio(textSaida.getText())
									&& !textDataEntrada.getText().equals(ss)
									&& !textSaida.getText().equals(ss)) {
								int sel = comboBox.getSelectedIndex();
								if (!(sel == 0)) {
									return true;

								}
							} else {
								return false;
							}
						} else {
							return false;
						}
					}

				}

			} else {
				return false;
			}

		} else {
			return false;
		}
		return false;

	}

}
