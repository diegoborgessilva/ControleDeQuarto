package TelasMenus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import TelasQuarto.CadastrarQuarto;
import TelasTipoDeQuarto.CadastrarTipo;
import telasReserva.EfetuarReserva;
import telasVisitantes.CadastroVisitante;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnGerenciarQuartos = new JButton("Gerenciar Quartos");
		btnGerenciarQuartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoToEscolha("quarto");
			}
		});
		
		JButton btnCadastro = new JButton("Gerenciar Visitantes");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToEscolha("visitante");
			}
		});
		GridBagConstraints gbc_btnCadastro = new GridBagConstraints();
		gbc_btnCadastro.insets = new Insets(0, 0, 5, 5);
		gbc_btnCadastro.gridx = 5;
		gbc_btnCadastro.gridy = 2;
		contentPane.add(btnCadastro, gbc_btnCadastro);
		
		JButton btnGerenciarReservas = new JButton("Gerenciar Reservas");
		btnGerenciarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToEscolha("reserva");
			}
		});
		GridBagConstraints gbc_btnGerenciarReservas = new GridBagConstraints();
		gbc_btnGerenciarReservas.insets = new Insets(0, 0, 5, 5);
		gbc_btnGerenciarReservas.gridx = 9;
		gbc_btnGerenciarReservas.gridy = 2;
		contentPane.add(btnGerenciarReservas, gbc_btnGerenciarReservas);
		GridBagConstraints gbc_btnGerenciarQuartos = new GridBagConstraints();
		gbc_btnGerenciarQuartos.insets = new Insets(0, 0, 5, 5);
		gbc_btnGerenciarQuartos.gridx = 5;
		gbc_btnGerenciarQuartos.gridy = 7;
		contentPane.add(btnGerenciarQuartos, gbc_btnGerenciarQuartos);
		
		JButton btnGeenciarTipoDe = new JButton("Gerenciar Tipo de Quarto");
		btnGeenciarTipoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToEscolha("tipoQuarto");
			}
		});
		GridBagConstraints gbc_btnGeenciarTipoDe = new GridBagConstraints();
		gbc_btnGeenciarTipoDe.insets = new Insets(0, 0, 5, 5);
		gbc_btnGeenciarTipoDe.gridx = 9;
		gbc_btnGeenciarTipoDe.gridy = 7;
		contentPane.add(btnGeenciarTipoDe, gbc_btnGeenciarTipoDe);
	}

	public void GoToEscolha(String opc){
		this.setVisible(false);
		Escolha frame = new Escolha();
		frame.setVisible(true);	
		frame.opcao(opc);
		
	}
	
	
}
