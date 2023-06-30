package Querys;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) throws SQLException {
		
//		SendQuery a = new SendQuery(6,"Jan",200,40);
		
		JFrame frame = new JFrame("Scoreboard");
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		
		ArrayList<String> get = new ArrayList<String>();
		
		
		
		for(String i : ShowData.read()) {
			get.add(i);
		}
		
		JLabel[] a = new JLabel[get.size()];
		
		for(int i =0; i < get.size(); i++) {
			a[i] = new JLabel(get.get(i));
			a[i].setText(get.get(i));
			a[i].setForeground(Color.white);
			a[i].setLayout(null);
			panel1.add(a[i]);
			
		}
		
		panel.setSize(450,725);
		panel.setBackground(Color.black);
		
		panel.setVisible(true);
		
		frame.setSize(500,800);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		panel.setLocation(25, 20 );
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		panel1.setSize(450,725);
		panel1.setBackground(Color.blue);
		panel1.setLocation(25, 20 );
		panel1.setVisible(true);
		frame.setLayout(null);
		
		
		
		panel.add(panel1);
		
		
		
		
		
		
		
		
		
	}

}
