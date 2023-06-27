import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	
	
	SendQuery(int id,String name, int score, int performance_points) throws SQLException{
		this.insertQuery(id,name,score,performance_points);
	}
	
	 private ArrayList<String> readDatabase() {
		
		 ArrayList<String> info = new ArrayList<String>();
		
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
		            String row = String.format("%s %d \n %s %s \n %s %d \n %s %d \n \n",table_id, id, column_user_name, user_name, column_score, score, column_performance_points, performance_points);
		            info.add(row);
		            
		         }
		         
		         return info;
		         
		      } catch(SQLException e) {
		         e.printStackTrace();
		      }
		
		
		// als de connectie niet werkt 
		return null;
		
	
	}
	
	
	
	private void insertQuery(int id,String playername,int score,int performance_points) throws SQLException {
		ArrayList<String> a = readDatabase();
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		
		
				Connection conn = DriverManager.getConnection(connection,user,password);    
		         
		         Statement stmt = conn.createStatement();
	
			
				// alle gegevens van de database in een string
			    ArrayList<String> info = a;
			    
			    
			    
			    for(String i : info) {
			    	System.out.println(i);
			    	 boolean playerIsIncluded = i.contains(playername);
			    	 boolean idIsIncluded = i.contains(Integer.toString(id));
			    	 System.out.println(playerIsIncluded);
			    	 b.add(playerIsIncluded);
			    	
					
			    	 }    
			    
			    
			    if(b.contains(true)) {
			    	// haalt de speler weg die er al in zit
			    	String sqlDelete = String.format("DELETE FROM %s WHERE %s = '%s' OR %s = '%s'",table_name,column_user_name,playername,table_id,id);
			         
			         stmt.executeUpdate(sqlDelete);

			    	
			    }
			    // laat de query door gaan en zorgt dat de nieuwe speler in de highscore table komt 
			    if(b.contains(false) ){
			    	String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
			         stmt.executeUpdate(sqlInsert);
			    }
		
			    }
			    
			
			    
	}

