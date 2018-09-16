package telasVisitantes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;

import TelasMenus.Menu;
import operacoesBanco.OperacaoCRUD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroVisitante extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textResultado;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVisitante frame = new CadastroVisitante();
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
	public CadastroVisitante() {
		setTitle("Cadastro de Visitante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 227);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToMenu();

			}
		});
		menuBar.add(btnMenu);

		JPanel panel_3 = new JPanel();
		menuBar.add(panel_3);

		textResultado = new JTextField();
		panel_3.add(textResultado);
		textResultado.setColumns(20);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);

		textNome = new JTextField();
		textNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textResultado.setText("");
				textResultado.setBackground(Color.WHITE);
				textNome.setBackground(Color.WHITE);

			}
		});
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.insets = new Insets(0, 0, 5, 0);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 2;
		gbc_textNome.gridy = 1;
		panel.add(textNome, gbc_textNome);
		textNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 1;
		gbc_lblTelefone.gridy = 2;
		panel.add(lblTelefone, gbc_lblTelefone);

		textTelefone = new JTextField();
		GridBagConstraints gbc_textTelefone = new GridBagConstraints();
		gbc_textTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_textTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefone.gridx = 2;
		gbc_textTelefone.gridy = 2;
		panel.add(textTelefone, gbc_textTelefone);
		textTelefone.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		panel.add(lblEmail, gbc_lblEmail);

		textEmail = new JTextField();
		textEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textEmail.setBackground(Color.WHITE);
				textResultado.setText("");
				textResultado.setBackground(Color.WHITE);

			}
		});
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 3;
		panel.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirVisitante();
			}
		});
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 5;
		panel.add(btnCadastrar, gbc_btnCadastrar);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
	}

	public void GoToMenu() {
		this.setVisible(false);
		Menu frameM = new Menu();
		frameM.setVisible(true);
	}

	public void inserirVisitante() {

		if (verificarCampos() == true) {
			OperacaoCRUD opc = new OperacaoCRUD();
			opc.inserirVisitante(textNome.getText(), textEmail.getText(),
					textTelefone.getText());
			textResultado.setBackground(Color.WHITE);
			textResultado.setForeground(Color.RED);
			textResultado.setText("Dados Inseridos Com sucesso!!");
		}
	}

	private boolean verificarCampos() {

		if (verificarEmeail() == true) {
			return true;
		} else {
			textEmail.setBackground(Color.YELLOW);
			textResultado.setText("Preencha Corretamente os campos");
			return false;
		}
	}

	private boolean verificarEmeail() {
		if (textEmail.getText() != null && textEmail.getText().length() > 0) {
			System.out.println("Metodo de validacao de email");
			Pattern p = Pattern
					.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
			Matcher m = p.matcher(textEmail.getText());
			if (m.find()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public boolean verificaTelefone(String numeroTelefone) {
		return numeroTelefone.matches("[2-9][0-9]{3}-[0-9]{4}");

	}
}
