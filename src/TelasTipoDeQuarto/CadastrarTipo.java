package TelasTipoDeQuarto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JButton;

import TelasMenus.Menu;
import operacoesBanco.OperacaoCRUD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarTipo extends JFrame {

	private JPanel contentPane;
	private JTextField textTipoDEquarto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTipo frame = new CadastrarTipo();
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
	public CadastrarTipo() {
		setTitle("Cadastro de Tipo de Quarto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoToMenu();
			}
		});
		menuBar.add(btnMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("Tipo de Quarto");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		textTipoDEquarto = new JTextField();
		textTipoDEquarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_textTipoDEquarto = new GridBagConstraints();
		gbc_textTipoDEquarto.insets = new Insets(0, 0, 5, 5);
		gbc_textTipoDEquarto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTipoDEquarto.gridx = 2;
		gbc_textTipoDEquarto.gridy = 3;
		contentPane.add(textTipoDEquarto, gbc_textTipoDEquarto);
		textTipoDEquarto.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirTipo();
			}
		});
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCadastrar.gridx = 2;
		gbc_btnCadastrar.gridy = 5;
		contentPane.add(btnCadastrar, gbc_btnCadastrar);
	}

	public void GoToMenu() {
		Menu frame = new Menu();
		this.setVisible(false);
		frame.setVisible(true);
	}
	
	public void inserirTipo(){
		OperacaoCRUD opc = new OperacaoCRUD();
		opc.inserirTipoDeQuarto(textTipoDEquarto.getText());
		
	}
}
