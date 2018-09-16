package TelasMenus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JMenuBar;
import javax.swing.JButton;

import telasReserva.AlterarReserva;
import telasReserva.EfetuarReserva;
import telasReserva.ExcluirReserva;
import telasVisitantes.AlterarVisitante;
import telasVisitantes.CadastroVisitante;
import telasVisitantes.ExcluirVistante;
import TelasQuarto.AlterarQuarto;
import TelasQuarto.CadastrarQuarto;
import TelasQuarto.ExcluirQuarto;
import TelasTipoDeQuarto.AlterarTipoDeQurto;
import TelasTipoDeQuarto.CadastrarTipo;
import TelasTipoDeQuarto.ExcluirTipoDeQuarto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Escolha extends JFrame {

	private JPanel contentPane;

	private String opc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Escolha frame = new Escolha();
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
	public Escolha() {
		setTitle("Escolha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 219);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoToMenu();
			}
		});
		menuBar.add(btnMenu);

		JButton btnAjuda = new JButton("Ajuda");
		menuBar.add(btnAjuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCadastrar.gridwidth = 3;
		gbc_btnCadastrar.gridheight = 3;
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCadastrar.gridx = 1;
		gbc_btnCadastrar.gridy = 1;
		contentPane.add(btnCadastrar, gbc_btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.gridwidth = 3;
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 5);
		gbc_btnExcluir.gridx = 2;
		gbc_btnExcluir.gridy = 4;
		contentPane.add(btnExcluir, gbc_btnExcluir);
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.gridheight = 2;
		gbc_btnAlterar.gridx = 6;
		gbc_btnAlterar.gridy = 4;
		contentPane.add(btnAlterar, gbc_btnAlterar);
	}

	public void inserir() {

		if (opc == null) {
			System.out.println("O que deseja fazer?");
		} else {
			if (opc.equals("quarto")) {
				CadastrarQuarto frame = new CadastrarQuarto();
				this.setVisible(false);
				frame.setVisible(true);
			} else {
				if (opc.equals("tipoQuarto")) {
					CadastrarTipo frame = new CadastrarTipo();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("visitante")) {
					CadastroVisitante frame = new CadastroVisitante();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("reserva")) {
					this.setVisible(false);
					EfetuarReserva frame = new EfetuarReserva();
					frame.setVisible(true);
				} else {
					System.out.println("O que deseja fazer?");
				}
			}

		}

	}

	public void excluir() {

		if (opc == null) {
	
			System.out.println("O que deseja fazer?");
		}else{
			if (opc.equals("quarto")) {
				ExcluirQuarto frame = new ExcluirQuarto();
				this.setVisible(false);
				frame.setVisible(true);
			} else {
				if (opc.equals("tipoQuarto")) {
					ExcluirTipoDeQuarto frame = new ExcluirTipoDeQuarto();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("visitante")) {
					ExcluirVistante frame = new ExcluirVistante();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("reserva")) {
					this.setVisible(false);
					ExcluirReserva frame = new ExcluirReserva();
					frame.setVisible(true);
				} else {
					System.out.println("O que deseja fazer?");
				}

			}
		}

	}

	public void alterar() {

		if (opc == null) {
			System.out.println("O que deseja fazer?");
		}else{
			if (opc.equals("quarto")) {
				AlterarQuarto frame = new AlterarQuarto();
				this.setVisible(false);
				frame.setVisible(true);
			} else {
				if (opc.equals("tipoQuarto")) {
					AlterarTipoDeQurto frame = new AlterarTipoDeQurto();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("visitante")) {
					AlterarVisitante frame = new AlterarVisitante();
					this.setVisible(false);
					frame.setVisible(true);
				} else if (opc.equals("reserva")) {
					this.setVisible(false);
					AlterarReserva frame = new AlterarReserva();
					frame.setVisible(true);
				} else {
					System.out.println("O que deseja fazer?");
				}
			}
		}

	}

	public void GoToMenu() {
		Menu frame = new Menu();
		this.setVisible(false);
		frame.setVisible(true);
	}

	public void opcao(String s) {
		this.opc = s;

	}

}
