package TelasQuarto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import TelasMenus.Menu;
import conversoes.Converter;
import entidades.Quarto;
import entidades.Tipo_Quarto;
import gerenciarBanco.GerenciarReserva;
import gerenciarBanco.GerenciarTipoQuarto;
import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CadastrarQuarto extends JFrame {

	private JPanel contentPane;
	private JTextField textDescricao;
	private JTextField textCapacidade;
	private JTextField textNumero;
	OperacaoCRUD opc = new OperacaoCRUD();
	GerenciarTipoQuarto gtq = new GerenciarTipoQuarto();
	GerenciarReserva gr = new GerenciarReserva();
	private JTextField textResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarQuarto frame = new CadastrarQuarto();
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
	public CadastrarQuarto() {
		setTitle("Cadastrar Quarto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 271);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoToMenu();
			}
		});
		menuBar.add(btnMenu);

		JPanel panel = new JPanel();
		menuBar.add(panel);

		textResultado = new JTextField();
		textResultado.setBackground(Color.LIGHT_GRAY);
		textResultado.setForeground(Color.BLACK);
		textResultado.setEditable(false);
		menuBar.add(textResultado);
		textResultado.setColumns(8);

		JButton btnAjuda = new JButton("Ajuda");
		menuBar.add(btnAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnCadastrar = new JButton("Gravar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inserirQuarto();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNumero = new JLabel("Numero");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.gridx = 1;
		gbc_lblNumero.gridy = 1;
		contentPane.add(lblNumero, gbc_lblNumero);

		textNumero = new JTextField();
		textNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
				repaint();
			}
		});
		GridBagConstraints gbc_textNumero = new GridBagConstraints();
		gbc_textNumero.insets = new Insets(0, 0, 5, 5);
		gbc_textNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNumero.gridx = 2;
		gbc_textNumero.gridy = 1;
		contentPane.add(textNumero, gbc_textNumero);
		textNumero.setColumns(10);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.anchor = GridBagConstraints.EAST;
		gbc_lblDescrio.gridx = 1;
		gbc_lblDescrio.gridy = 2;
		contentPane.add(lblDescrio, gbc_lblDescrio);

		textDescricao = new JTextField();
		textDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResultado.setText("");
				textResultado.setBackground(Color.WHITE);
				repaint();
			}
		});
		GridBagConstraints gbc_textDescricao = new GridBagConstraints();
		gbc_textDescricao.insets = new Insets(0, 0, 5, 5);
		gbc_textDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDescricao.gridx = 2;
		gbc_textDescricao.gridy = 2;
		contentPane.add(textDescricao, gbc_textDescricao);
		textDescricao.setColumns(10);

		JLabel lblCapacidade = new JLabel("Capacidade");
		GridBagConstraints gbc_lblCapacidade = new GridBagConstraints();
		gbc_lblCapacidade.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidade.gridx = 1;
		gbc_lblCapacidade.gridy = 4;
		contentPane.add(lblCapacidade, gbc_lblCapacidade);

		textCapacidade = new JTextField();
		textCapacidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
				textResultado.setBackground(Color.WHITE);
				repaint();
			}
		});
		GridBagConstraints gbc_textCapacidade = new GridBagConstraints();
		gbc_textCapacidade.insets = new Insets(0, 0, 5, 5);
		gbc_textCapacidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCapacidade.gridx = 2;
		gbc_textCapacidade.gridy = 4;
		contentPane.add(textCapacidade, gbc_textCapacidade);
		textCapacidade.setColumns(10);
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 9;
		contentPane.add(btnCadastrar, gbc_btnCadastrar);
	}

	public void GoToMenu() {
		Menu frame = new Menu();
		this.setVisible(false);
		frame.setVisible(true);
	}

	public void inserirQuarto() throws SQLException { 
		if (verificaCampos() == true) {
			// TIPO , CAPACIDADE , DESCRIÇÃO
			int numero = Converter.StringPARAint(textNumero.getText());
			int capa = Converter.StringPARAint(textCapacidade.getText());
			int tip = gtq.BuscaTipoQuartos(textDescricao.getText())
					.getPKIdTipo();
			String des = textDescricao.getText();
			opc.inserirQuarto(numero, tip, capa, des);
			textResultado.setBackground(Color.WHITE);
			textResultado.setForeground(Color.RED);
			textResultado.setText("Dados Inseridos Com sucesso!!");
		} else {
			textResultado.setBackground(Color.YELLOW);
			textResultado.setForeground(Color.RED);
			textResultado.setText("Preencha Corretamente os Campos");
		}

	}

	public void carregaDados(Quarto q) {
		textNumero.setText("" + q.getNumeroDoQuarto());
		textCapacidade.setText("" + q.getCapacidade());
		textDescricao.setText("" + q.getDescricao());
	}

	public boolean verificaCampos() throws SQLException {

		if (eNumero(textNumero.getText()) == true) {// e numero
			if (!Relacoes.existeQuarto(Converter.StringPARAint(textNumero
					.getText()))) {// quarto não existe

				if (Relacoes.existeTipoDeQuarto(textDescricao.getText()) == true) {
					if (eNumero(textCapacidade.getText()) == true) {
						return true;
					} else {
						textCapacidade.setBackground(Color.YELLOW);
						return false;
					}

				} else {
					textDescricao.setBackground(Color.YELLOW);
					return false;
				}

			} else {
				textNumero.setBackground(Color.YELLOW);
				return false;
			}

		} else {
			textNumero.setBackground(Color.YELLOW);
			return false;
		}

	}

	public boolean eNumero(String num) {
		return num.matches("[0-9]+");
	}

}
