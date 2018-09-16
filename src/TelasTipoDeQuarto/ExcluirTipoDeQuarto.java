package TelasTipoDeQuarto;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JComboBox;

import telasReserva.AutoCompletarCampo;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;
import TelasMenus.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExcluirTipoDeQuarto extends JFrame {

	private JPanel contentPane;
	private JTextField textEntrada;
	private JMenuBar menuBar;
	private JButton btnMenu;
	private JTextField textResultado;
	private JButton btnExcluir;
	OperacaoCRUD opc = new OperacaoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirTipoDeQuarto frame = new ExcluirTipoDeQuarto();
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
	public ExcluirTipoDeQuarto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnMenu = new JButton("Menu");
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
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblTipoASer = new JLabel("Tipo a ser Excluido");
		GridBagConstraints gbc_lblTipoASer = new GridBagConstraints();
		gbc_lblTipoASer.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoASer.anchor = GridBagConstraints.EAST;
		gbc_lblTipoASer.gridx = 2;
		gbc_lblTipoASer.gridy = 2;
		contentPane.add(lblTipoASer, gbc_lblTipoASer);

		textEntrada = new JTextField();
		textEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResultado.setText("");
			}
		});
		GridBagConstraints gbc_textEntrada = new GridBagConstraints();
		gbc_textEntrada.insets = new Insets(0, 0, 5, 0);
		gbc_textEntrada.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEntrada.gridx = 3;
		gbc_textEntrada.gridy = 2;
		contentPane.add(textEntrada, gbc_textEntrada);
		textEntrada.setColumns(10);

		JComboBox listSugestao = new JComboBox();
		GridBagConstraints gbc_listSugestao = new GridBagConstraints();
		gbc_listSugestao.insets = new Insets(0, 0, 5, 0);
		gbc_listSugestao.fill = GridBagConstraints.HORIZONTAL;
		gbc_listSugestao.gridx = 3;
		gbc_listSugestao.gridy = 3;
		contentPane.add(listSugestao, gbc_listSugestao);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excluirTipo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.gridx = 3;
		gbc_btnExcluir.gridy = 5;
		contentPane.add(btnExcluir, gbc_btnExcluir);

		textEntrada.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (textEntrada.getText().equals("")) {
					textEntrada.setVisible(true);
					return;
				}
				String dados[] = null;
				try {
					dados = AutoCompletarCampo.buscaSugestaotipo(textEntrada
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
				textEntrada.setText((String) listSugestao.getSelectedItem());
			}
		});

	}

	public void excluirTipo() throws SQLException {
		textResultado.setText("");
		if (verificaCampos() == true) {
			opc.excluirTipoDeQurto(textEntrada.getText());
			textResultado.setText("Excluir com sucesso!");			
		}else{
			textResultado.setText("Preencher o Campo Corretamente!");
		}

	}

	private boolean verificaCampos() throws SQLException {

		if (Relacoes.estaVazio(textEntrada.getText())
				&& !textEntrada.getText().equals("Não encontrado!")) {
			if (Relacoes.existeTipoDeQuarto(textEntrada.getText())) {
				return true;
			}

		}

		return false;
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frame = new Menu();
		frame.setVisible(true);
	}

}
