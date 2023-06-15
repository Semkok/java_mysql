import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SendQuery {
	
	//SQL table information 
	final private String databaseName = "xtapp";
	final private String table_id = "id";
	final private String column_user_name = "user_name";
	final private String column_score = "score";
	final private String column_performance_points =  "performance_points"; 
	final private String table_name = "highscores";
	//connection information
	final private String connection = "jdbc:mysql://localhost:3306/" + databaseName +"?allowPublicKeyRetrieval=false&useSSL=false&serverTimezone=UTC";
	final private String user = "root";
	final private String password = "";
	
	
	
	SendQuery(int id,String name, int score, int performance_points){
		this.insertQuery(id,name,score,performance_points);
	}
	
	
	
	
	 private String readDatabase() {
		
		
		try (
				// maakt een connectie met de database
		         Connection conn = DriverManager.getConnection(connection,user,password);   
		               
		         Statement stmt = conn.createStatement();
				
		      ) {
		        
				// De Query die nodig is om data van de tabel op te halen
		         String strSelect = "SELECT * FROM highscores";

		         
		         ResultSet rset = stmt.executeQuery(strSelect);
		         
		         
		         while(rset.next()) {   
		        	int id = rset.getInt(table_id);
		            String user_name = rset.getString(column_user_name);  
		            int score = rset.getInt(column_score);  
		            int    performance_points   = rset.getInt(column_performance_points);       
		            
		            
		            // moet nog meer returnen!!!
		            return table_id + " " + id + "\n" + column_user_name + " "  + user_name + "\n" + column_score + " " +  score + "\n" + column_performance_points + " " + performance_points + "\n\n";
		         }
		         
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
		return null;
	
	}
	
	
	
	private void insertQuery(int id,String playername,int score,int performance_points) {
		// id, playername,score,performance_points
		try (
				Connection conn = DriverManager.getConnection(connection,user,password);    
		         
		         Statement stmt = conn.createStatement();
				
				
				
		      ) {
			
				// alle gegevens van de database in een string
			    String info = readDatabase();
			    
			    System.out.println(info);
			    
			    // of de spelernaam er al in zit
			    boolean playerIsIncluded = info.contains(playername);
			    
			    
			    //  met id 
//			    boolean idIsIncluded = info.contains(Integer.toString(id));
			    
			    // als de speler er al in zit in de highscoretable
			    if(playerIsIncluded == true) {
			    	// haalt de speler weg die er al in zit
			    	String sqlDelete = String.format("DELETE FROM %s WHERE %s = '%s'",table_name,column_user_name,playername);
			         
			         stmt.executeUpdate(sqlDelete);
			       
			    	
			         String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
    		         
			         stmt.executeUpdate(sqlInsert);
			    	
			    	
			    }
			    // laat de query door gaan en zorgt dat de nieuwe speler in de highscore table komt 
			    else {
			    	String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
			         stmt.executeUpdate(sqlInsert);
			    }
			     
			     
			
		         
		        
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		      } 
	}
}
