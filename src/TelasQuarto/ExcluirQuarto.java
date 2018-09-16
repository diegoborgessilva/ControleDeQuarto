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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JMenuBar;

import conversoes.Converter;
import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;
import TelasMenus.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExcluirQuarto extends JFrame {

	private JPanel contentPane;
	private JTextField textEntrada;
	private JTextField textResultado;
	OperacaoCRUD opc = new OperacaoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirQuarto frame = new ExcluirQuarto();
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
	public ExcluirQuarto() {
		setTitle("Excluir Quarto");
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
		textResultado.setEditable(false);
		menuBar.add(textResultado);
		textResultado.setColumns(10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNDoQuarto = new JLabel("N\u00BA do Quarto");
		GridBagConstraints gbc_lblNDoQuarto = new GridBagConstraints();
		gbc_lblNDoQuarto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNDoQuarto.anchor = GridBagConstraints.EAST;
		gbc_lblNDoQuarto.gridx = 2;
		gbc_lblNDoQuarto.gridy = 2;
		contentPane.add(lblNDoQuarto, gbc_lblNDoQuarto);

		textEntrada = new JTextField();
		GridBagConstraints gbc_textEntrada = new GridBagConstraints();
		gbc_textEntrada.anchor = GridBagConstraints.WEST;
		gbc_textEntrada.insets = new Insets(0, 0, 5, 0);
		gbc_textEntrada.gridx = 3;
		gbc_textEntrada.gridy = 2;
		contentPane.add(textEntrada, gbc_textEntrada);
		textEntrada.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excluir();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 6;
		contentPane.add(btnExcluir, gbc_btnExcluir);
	}

	public void excluir() throws SQLException {
		textResultado.setText("");
		if (verificaCampos() == true) {
			opc.excluirQurto(textEntrada.getText());
			textResultado.setText("Excluir com sucesso!");
		} else {
			textResultado.setText("Preencher o Campo Corretamente!");
		}

	}

	private boolean verificaCampos() throws SQLException {

		if (Relacoes.estaVazio(textEntrada.getText())
				&& eNumero(textEntrada.getText())) {
			if (Relacoes.existeQuarto(Converter.StringPARAint(textEntrada
					.getText()))) {
				textEntrada.setBackground(Color.WHITE);
				return true;
			} else {
				System.out.println("2");
			}

		} else {
			System.out.println("1");
		}

		textEntrada.setBackground(Color.YELLOW);
		return false;
	}

	public boolean eNumero(String num) {
		return num.matches("[0-9]+");
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}
}
