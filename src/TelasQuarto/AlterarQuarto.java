package TelasQuarto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import TelasMenus.Menu;
import conversoes.Converter;
import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JMenuBar;

import entidades.Quarto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlterarQuarto extends JFrame {

	private JPanel contentPane;
	private JTextField textNumero;
	private JTextField textCapacidade;
	private JTextField textDescricao;
	private JTextField textResultado;
	OperacaoCRUD opc = new OperacaoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarQuarto frame = new AlterarQuarto();
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
	public AlterarQuarto() {
		setTitle("Alterar Quarto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoToMenu();
			}
		});
		menuBar.add(btnMenu);

		textResultado = new JTextField();
		menuBar.add(textResultado);
		textResultado.setColumns(10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNQuarto = new JLabel("N\u00BA Quarto");
		GridBagConstraints gbc_lblNQuarto = new GridBagConstraints();
		gbc_lblNQuarto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNQuarto.anchor = GridBagConstraints.EAST;
		gbc_lblNQuarto.gridx = 2;
		gbc_lblNQuarto.gridy = 1;
		contentPane.add(lblNQuarto, gbc_lblNQuarto);

		textNumero = new JTextField();
		textNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				textCapacidade.setText("");
				textDescricao.setText("");
				textResultado.setText("");
			}
		});
		textNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					buscar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_textNumero = new GridBagConstraints();
		gbc_textNumero.insets = new Insets(0, 0, 5, 5);
		gbc_textNumero.anchor = GridBagConstraints.WEST;
		gbc_textNumero.gridx = 3;
		gbc_textNumero.gridy = 1;
		contentPane.add(textNumero, gbc_textNumero);
		textNumero.setColumns(8);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					buscar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnBuscar.setIcon(new ImageIcon("C:\\Users\\Diego\\Desktop\\ps.png"));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 2;
		contentPane.add(btnBuscar, gbc_btnBuscar);

		JLabel lblCapacidade = new JLabel("Capacidade");
		GridBagConstraints gbc_lblCapacidade = new GridBagConstraints();
		gbc_lblCapacidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidade.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidade.gridx = 2;
		gbc_lblCapacidade.gridy = 6;
		contentPane.add(lblCapacidade, gbc_lblCapacidade);

		textCapacidade = new JTextField();
		GridBagConstraints gbc_textCapacidade = new GridBagConstraints();
		gbc_textCapacidade.insets = new Insets(0, 0, 5, 5);
		gbc_textCapacidade.anchor = GridBagConstraints.WEST;
		gbc_textCapacidade.gridx = 3;
		gbc_textCapacidade.gridy = 6;
		contentPane.add(textCapacidade, gbc_textCapacidade);
		textCapacidade.setColumns(10);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlterar.gridx = 3;
		gbc_btnAlterar.gridy = 7;
		contentPane.add(btnAlterar, gbc_btnAlterar);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescricao = new GridBagConstraints();
		gbc_lblDescricao.anchor = GridBagConstraints.EAST;
		gbc_lblDescricao.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescricao.gridx = 2;
		gbc_lblDescricao.gridy = 8;
		contentPane.add(lblDescricao, gbc_lblDescricao);

		textDescricao = new JTextField();
		GridBagConstraints gbc_textDescricao = new GridBagConstraints();
		gbc_textDescricao.insets = new Insets(0, 0, 0, 5);
		gbc_textDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDescricao.gridx = 3;
		gbc_textDescricao.gridy = 8;
		contentPane.add(textDescricao, gbc_textDescricao);
		textDescricao.setColumns(10);
	}

	public void buscar() throws SQLException {
		if (eNumero(textNumero.getText())) {
			try {
				if (Relacoes.existeQuarto(Converter.StringPARAint(textNumero
						.getText()))) {
					Quarto q = opc.buscarQuarto(Converter
							.StringPARAint(textNumero.getText()));
					carregaDados(q);
				} else {
					textResultado.setText("Quarto não existe!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			textResultado.setText("Insira Numero!");
		}

	}

	public void carregaDados(Quarto q) {
		textNumero.setText("" + q.getNumeroDoQuarto());
		textCapacidade.setText("" + q.getCapacidade());
		textDescricao.setText("" + q.getDescricao());
	}

	public void alterar() throws SQLException {

		if (verificarCampos() == true) {
			opc.AlterarQuarto(Converter.StringPARAint(textNumero.getText()),
					textDescricao.getText(),
					Converter.StringPARAint(textCapacidade.getText()));
			textResultado.setText("Alterado com sucesso!!!");
		}

	}

	public boolean verificarCampos() {
		if (Relacoes.estaVazio(textDescricao.getText())
				&& Relacoes.estaVazio(textCapacidade.getText())) {
			if (eNumero(textCapacidade.getText())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}

	public boolean eNumero(String num) {
		return num.matches("[0-9]+");
	}
}
