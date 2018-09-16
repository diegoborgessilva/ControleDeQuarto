package TelasTipoDeQuarto;

import gerenciarBanco.GerenciarQuarto;

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

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;
import telasReserva.AutoCompletarCampo;
import TelasMenus.Menu;

public class AlterarTipoDeQurto extends JFrame {

	private JPanel contentPane;
	private JTextField textTipoAtual;
	private JMenuBar menuBar;
	private JButton btnMenu;
	private JTextField textResultado;
	private JComboBox comboBox;
	private JComboBox listSugestao;
	private JLabel lblNovoTipo;
	private JTextField textNovoTipo;
	private JButton btnAlterar;
	OperacaoCRUD opc = new OperacaoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarTipoDeQurto frame = new AlterarTipoDeQurto();
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
	public AlterarTipoDeQurto() {
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

		textResultado = new JTextField();
		menuBar.add(textResultado);
		textResultado.setColumns(10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblTipoASer = new JLabel("Tipo a ser Alterado");
		GridBagConstraints gbc_lblTipoASer = new GridBagConstraints();
		gbc_lblTipoASer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoASer.anchor = GridBagConstraints.EAST;
		gbc_lblTipoASer.gridx = 1;
		gbc_lblTipoASer.gridy = 1;
		contentPane.add(lblTipoASer, gbc_lblTipoASer);

		textTipoAtual = new JTextField();
		textTipoAtual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTipoAtual = new JTextField();
				textTipoAtual.setBackground(Color.WHITE);
				textTipoAtual.setBackground(Color.WHITE);
				textResultado.setText("");

				// textResultado.setBackground(Color.WHITE);
				repaint();

			}
		});
		GridBagConstraints gbc_textTipoAtual = new GridBagConstraints();
		gbc_textTipoAtual.insets = new Insets(0, 0, 5, 0);
		gbc_textTipoAtual.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTipoAtual.gridx = 2;
		gbc_textTipoAtual.gridy = 1;
		contentPane.add(textTipoAtual, gbc_textTipoAtual);
		textTipoAtual.setColumns(10);

		listSugestao = new JComboBox();
		listSugestao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_listSugestao = new GridBagConstraints();
		gbc_listSugestao.insets = new Insets(0, 0, 5, 0);
		gbc_listSugestao.fill = GridBagConstraints.HORIZONTAL;
		gbc_listSugestao.gridx = 2;
		gbc_listSugestao.gridy = 2;
		contentPane.add(listSugestao, gbc_listSugestao);

		lblNovoTipo = new JLabel("Novo Tipo");
		GridBagConstraints gbc_lblNovoTipo = new GridBagConstraints();
		gbc_lblNovoTipo.anchor = GridBagConstraints.EAST;
		gbc_lblNovoTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNovoTipo.gridx = 1;
		gbc_lblNovoTipo.gridy = 4;
		contentPane.add(lblNovoTipo, gbc_lblNovoTipo);

		textNovoTipo = new JTextField();
		textNovoTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResultado.setText("");
				textNovoTipo.setBackground(Color.WHITE);
				// textResultado.setBackground(Color.WHITE);
				repaint();

			}
		});
		GridBagConstraints gbc_textNovoTipo = new GridBagConstraints();
		gbc_textNovoTipo.insets = new Insets(0, 0, 5, 0);
		gbc_textNovoTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNovoTipo.gridx = 2;
		gbc_textNovoTipo.gridy = 4;
		contentPane.add(textNovoTipo, gbc_textNovoTipo);
		textNovoTipo.setColumns(10);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterarTipo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.gridx = 2;
		gbc_btnAlterar.gridy = 6;
		contentPane.add(btnAlterar, gbc_btnAlterar);

		textTipoAtual.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (textTipoAtual.getText().equals("")) {
					textTipoAtual.setVisible(true);
					return;
				}
				String dados[] = null;
				try {
					dados = AutoCompletarCampo.buscaSugestaotipo(textTipoAtual
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
				textTipoAtual.setText((String) listSugestao.getSelectedItem());
			}
		});
	}

	public void alterarTipo() throws SQLException {
		if (verificaCampos() == true) {
			opc.AlterarTipoDeQuarto(textTipoAtual.getText(),
					textNovoTipo.getText());
			textResultado.setText("Alterado com sucesso!");
		} else {
			textResultado.setText("Verifique os campos!");
		}

	}

	private boolean verificaCampos() throws SQLException {

		if (Relacoes.estaVazio(textTipoAtual.getText())
				&& !textTipoAtual.getText().equals("Não encontrado!")) {
			if (Relacoes.existeTipoDeQuarto(textTipoAtual.getText())) {

				if (Relacoes.estaVazio(textNovoTipo.getText())) {

					return true;

				} else {
					textNovoTipo.setBackground(Color.YELLOW);
				}

			} else {
				textTipoAtual.setBackground(Color.YELLOW);
			}

		} else {

			textTipoAtual.setBackground(Color.YELLOW);
		}

		return false;
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}

}
