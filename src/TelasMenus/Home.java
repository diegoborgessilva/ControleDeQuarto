package TelasMenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home extends JPanel {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		
		System.out.println("Data/Hora atual: "+c.getTime());
		System.out.println("Ano: "+c.get(Calendar.YEAR));
		System.out.println("M�s: "+c.get(Calendar.MONTH));
		System.out.println("Dia do M�s: "+c.get(Calendar.DAY_OF_MONTH));
	}

 
}