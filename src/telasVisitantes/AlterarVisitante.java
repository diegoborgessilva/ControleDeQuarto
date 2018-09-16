package telasVisitantes;

import entidades.Visitante;
import gerenciarBanco.GerenciarVisitante;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JComboBox;

import TelasMenus.Menu;
import operacoesBanco.OperacaoCRUD;
import operacoesBanco.Relacoes;
import telasReserva.AutoCompletarCampo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterarVisitante extends JFrame {

	private JPanel contentPane;
	private JTextField textResultado;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textTelefone;
	GerenciarVisitante gv = new GerenciarVisitante();
	OperacaoCRUD opc = new OperacaoCRUD();
	private Visitante v;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarVisitante frame = new AlterarVisitante();
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
	public AlterarVisitante() {
		setTitle("Alterar Visitante");
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
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 2;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);
		
		textNome = new JTextField();
		GridBagConstraints gbc_textNome = new GridBagConstraints();
		gbc_textNome.insets = new Insets(0, 0, 5, 0);
		gbc_textNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNome.gridx = 3;
		gbc_textNome.gridy = 1;
		contentPane.add(textNome, gbc_textNome);
		textNome.setColumns(10);
		
		JComboBox listSugestao = new JComboBox();
		GridBagConstraints gbc_listSugestao = new GridBagConstraints();
		gbc_listSugestao.anchor = GridBagConstraints.WEST;
		gbc_listSugestao.insets = new Insets(0, 0, 5, 0);
		gbc_listSugestao.gridx = 3;
		gbc_listSugestao.gridy = 2;
		contentPane.add(listSugestao, gbc_listSugestao);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 4;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.gridx = 3;
		gbc_textEmail.gridy = 4;
		contentPane.add(textEmail, gbc_textEmail);
		textEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 2;
		gbc_lblTelefone.gridy = 5;
		contentPane.add(lblTelefone, gbc_lblTelefone);
		
		textTelefone = new JTextField();
		GridBagConstraints gbc_textTelefone = new GridBagConstraints();
		gbc_textTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_textTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefone.gridx = 3;
		gbc_textTelefone.gridy = 5;
		contentPane.add(textTelefone, gbc_textTelefone);
		textTelefone.setColumns(10);
		
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
		gbc_btnAlterar.gridx = 3;
		gbc_btnAlterar.gridy = 7;
		contentPane.add(btnAlterar, gbc_btnAlterar);
		
		textNome.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				textResultado.setText("");
				if (textNome.getText().equals("")) {
					textNome.setVisible(true);
					return;
				}
				String dados[] = null;
				try {
					dados = AutoCompletarCampo.buscaSugestao(textNome.getText());
				} catch (SQLException e1) {
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
				textNome.setText((String) listSugestao.getSelectedItem());

				try {
					textResultado.setText("");
					v = gv.buscaVisitante(textNome.getText());
					textEmail.setText(v.getEmail());
					textTelefone.setText(v.getTelefone());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	private void alterar() throws SQLException {		
		if (verificaCampos(textNome.getText()) == true) {
			if (Relacoes.existeVisitante(textNome.getText()) == true) {
				v.setEmail(textEmail.getText());
				v.setTelefone(textTelefone.getText());
				opc.alterarVisitante(v);
				textResultado.setText("Alterado com sucesso!");
			}else{
				textResultado.setText("kvhPreencha corretamente!!!");
			}
		}else{
			textResultado.setText("Preencha corretamente!!!");
		}
		

	}

	private boolean verificaCampos(String campo) {
		if (Relacoes.estaVazio(campo) == true) {
			System.out.println("chieo");
			return true;
		}
		System.out.println("vazio");
		return false;
	}
	public void GoToMenu() {
		Menu frame = new Menu();
		this.setVisible(false);
		frame.setVisible(true);
	}
}
