import java.sql.*;
public class SendQuery {
	
	//column names
	final static private String databaseName = "xtapp";
	final static private String table_id = "id";
	final static private String column_user_name = "user_name";
	final static private String column_score = "score";
	final static private String column_performance_points =  "performance_points"; 
	//connection 
	final static private String connection = "jdbc:mysql://localhost:3306/xtapp?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	final static private String user = "root";
	final static private String password = "";
	
	static void readDatabase() {
		
		try (
				// maakt een connectie met de database
		         Connection conn = DriverManager.getConnection(connection,user,password);   
		               
		         Statement stmt = conn.createStatement();
				
		      ) {
		        
				// De Query die nodig is om data van de tabel op te halen
		         String strSelect = "SELECT id, user_name, score, performance_points FROM highscores";

		         
		         ResultSet rset = stmt.executeQuery(strSelect);
		         
		         
		         while(rset.next()) {   
		        	int id = rset.getInt("id");
		            String user_name = rset.getString("user_name");  
		            int score = rset.getInt("score");  
		            int    performance_points   = rset.getInt("performance_points");       
		            System.out.printf("%d %s %d %d \n", id ,user_name, score, performance_points);
		         }
		         
		      } catch(SQLException e) {
		         e.printStackTrace();
		      } 
	
	}
	
	
	
	static void insertQuery(int id,String playername,int score,int performance_points) {
		// id, playername,score,performance_points
		try (
		       
				Connection conn = DriverManager.getConnection(connection,user,password);    
		         
		         Statement stmt = conn.createStatement();
		      ) {
		         
			
		         String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
		         
		         stmt.executeUpdate(sqlInsert);
		        
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		      } 
	}
}
