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
		
		panel.setSize(250,600);
		panel.setBackground(Color.blue);
		panel.setVisible(true);
		
		frame.setSize(500,800);
		frame.setVisible(true);
		frame.setBackground(Color.green);
		
		frame.getContentPane().add(panel);
		frame.setLayout(null);
		
		
		
		ShowData.read();
		for(String i : ShowData.read()) {
			System.out.println(i);
		}
		
		
	}

}
