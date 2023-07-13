package Querys;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import Querys.SendQuery;


public class ShowData{
	
	/*ALL STRINGS ARE SPECIFIC AND NEED TO BE CHANGED IN ORDER TO WORK FOR YOUR DATABASE CONNECTION*/
	
	// Database name
	static final private String databaseName = "xtapp";
		
	//SQL table information 
	static final private String table_name = "highscores";
		
	//SQL column information 
	static final private String table_id = "id";
	static final private String column_user_name = "user_name";
	static final private String column_score = "score";
	static final private String column_performance_points =  "performance_points"; 
		
	//connection information
	static final private String server_type = "mysql";
	static final private String user = "root";
	static final private String password = "";
	static final private String connection = "jdbc:" + server_type + "://127.0.0.1:3306/" + databaseName +"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	
	
	
	public static ArrayList<String> read() {
		ArrayList<String> info = new ArrayList<String>();
		
		try (

		         Connection conn = DriverManager.getConnection(connection,user,password);   

		         Statement stmt = conn.createStatement();
				
		      ) {

		         String strSelect = String.format("SELECT * FROM %s ORDER BY score DESC,performance_points", table_name);
		   
		         ResultSet rset = stmt.executeQuery(strSelect);
		         
		         while(rset.next()) {   
		        	 
		        	
		        	int id = rset.getInt(table_id);
		            String user_name = rset.getString(column_user_name);  
		            int score = rset.getInt(column_score);  
		            int    performance_points   = rset.getInt(column_performance_points); 
		            
		            
		            String row = String.format("%d \n  %s \n %s %d \n %s %d \n \n", id, user_name, column_score, score, column_performance_points, performance_points);
		            info.add(row);
		            
		         }
		         
		         return info;
		         
		         
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
		
		
		return null;
	}

	
}
