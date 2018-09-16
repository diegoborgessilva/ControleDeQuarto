package TelasMenus;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;


public class Apresentacao extends JWindow {
	JProgressBar barraDeprogresso; 
	public Apresentacao() throws InterruptedException{
		getContentPane().setForeground(Color.LIGHT_GRAY);
		int w = this.getToolkit().getDefaultToolkit().getScreenSize().width;
		int h = this.getToolkit().getDefaultToolkit().getScreenSize().height;
		int z = 2;
		int x = (w-521)/z;
		int y =(h-335)/z;
	JLabel img = new JLabel(new ImageIcon("src/apresentacaoControleQuarto.jpg"));
	img.setLocation(0,0);
	img.setSize(521, 315);
	
	
	getContentPane().setLayout(null);
	getContentPane().add(img);
	this.setLocation(new Point(x,y));
	this.setSize(521, 315);
	this.setVisible(true);
	
	
	barraDeprogresso = new JProgressBar();
	barraDeprogresso.setBackground(new Color(0,0,0));
	barraDeprogresso.setBounds(0, 0, 500,15);
	barraDeprogresso.setStringPainted(true);
	barraDeprogresso.setLocation(0,170);
	getContentPane().add(barraDeprogresso);

	
	
	new Thread(){
		public void run(){
			for (int i = 0; i <101; i++) {
				try {
					barraDeprogresso.setValue(i);
							
					sleep(80);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}.start();
	Thread.sleep(9500);
	this.setVisible(false);
	Menu frame = new Menu();
	frame.setVisible(true);
	
		
	}
	
	public static void main(String [] args) throws InterruptedException{
		
		new Apresentacao();
		
		
		
		
	}
	
	
	
}
