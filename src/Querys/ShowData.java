package Querys;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import Querys.SendQuery;


public class ShowData{
	// Database name
	static final private String databaseName = "rbout1897_xtp";
		
		//SQL table information 
		static final private String table_name = "highscores";
		
		//SQL column information 
		static final private String table_id = "id";
		static final private String column_user_name = "user_name";
		static final private String column_score = "score";
		static final private String column_performance_points =  "performance_points"; 
		
		//connection information
		static final private String server_type = "mysql";
		static final private String user = "rbout1897_xtp";
		static final private String password = "Bp136Ft3uLd8opuH";
		static final private String connection = "jdbc:" + server_type + "://10.0.0.69:3306/" + databaseName +"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	
	
	
	public static ArrayList<String> read() {
		ArrayList<String> info = new ArrayList<String>();
		
		try (
				
				// maakt een connectie met de database
		         Connection conn = DriverManager.getConnection(connection,user,password);   
		         
				// zorgt er voor dat er SQL statements kunnen worden uitgevoerd
		         Statement stmt = conn.createStatement();
				
		      ) {
		        
				// De Query die nodig is om data van de tabel op te halen
		         String strSelect = String.format("SELECT * FROM %s ORDER BY score DESC,performance_points", table_name);
		         
		         // voert de query uit
		         ResultSet rset = stmt.executeQuery(strSelect);
		         
		         // kijkt wat er in de table zit
		         while(rset.next()) {   
		        	 
		        	 // voegt de waarde van de rows toe aan datatypes
		        	int id = rset.getInt(table_id);
		            String user_name = rset.getString(column_user_name);  
		            int score = rset.getInt(column_score);  
		            int    performance_points   = rset.getInt(column_performance_points); 
		            
		            // maakt er een string van met een format string van en voegt de row toe aan de info arraylist
		            String row = String.format("%d \n  %s \n %s %d \n %s %d \n \n", id, user_name, column_score, score, column_performance_points, performance_points);
		            info.add(row);
		            
		         }
		         
		         // returnt De arraylist 
		         return info;
		         
		         
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
		
		
		// als de connectie niet werkt 
		return null;
	}

	
}
