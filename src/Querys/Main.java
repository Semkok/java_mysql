package Querys;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		JFrame frame = new JFrame("Scoreboard");
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JLabel label = new JLabel(ShowData.read().get(0));
		JLabel label1 = new JLabel(ShowData.read().get(1));
		
		panel.setSize(450,725);
		panel.setBackground(Color.black);
		
		panel.setVisible(true);
		
		frame.setSize(500,800);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		panel.setLocation(25, 20 );
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		frame.setLayout(null);
		
		
		label.setForeground(Color.white);
		label1.setForeground(Color.white);
		panel1.add(label);
		panel1.add(label1);
		panel1.setSize(450,725);
		panel1.setBackground(Color.blue);
		panel1.setLocation(25, 20 );
		panel1.setVisible(true);
		panel.add(panel1);
		
		
		int a = 0;
		JLabel[] labels = new JLabel [25];
		
		for(String i : ShowData.read()) {
			
			labels[a] = new JLabel(ShowData.read().get(a));
			labels[a].setForeground(Color.white);
			labels[a].setLocation(22,23);
			panel1.add(labels[a]);
			System.out.println(a);
			a++;
			
		}
		
		
		
	}

}
