package Querys;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SendQuery {
	
	
	/*ALLE STRINGS ZIJN SPECIFIEK EN MOETEN AANGEPAST WORDEN 
	 * VOOR EEN SPECIFIEKE DATABASE CONNECTIE, ZIE HIERVOOR DE STRINGS ZIJN NAMEN*/
	
	// Database name
	final private String databaseName = "xtapp";
	
	//SQL table information 
	final private String table_name = "highscores";
	
	//SQL column information 
	final private String table_id = "id";
	final private String column_user_name = "user_name";
	final private String column_score = "score";
	final private String column_performance_points =  "performance_points"; 
	
	//connection information
	final private String server_type = "mysql";
	final private String user = "root";
	final private String password = "";
	final private String connection = "jdbc:" + server_type + "://127.0.0.1:3306/" + databaseName +"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	
	
	
	// Constructor voor het uitvoeren van de insertQuery functie
	SendQuery(int id,String name, int score, int performance_points) throws SQLException{
		this.insertQuery(id,name,score,performance_points);
		
	}
	
	
	// <String> is een reference datatype 
	 public ArrayList<String> readDatabase() {
		 /*returnt een arraylist met alle rows en informatie van de table*/
		
		 
		 // de arraylist die wordt returnt met alle informatie
		 ArrayList<String> info = new ArrayList<String>();
		
		try (
				
				// maakt een connectie met de database
		         Connection conn = DriverManager.getConnection(connection,user,password);   
		         
				// zorgt er voor dat er SQL statements kunnen worden uitgevoerd
		         Statement stmt = conn.createStatement();
				
		      ) {
		        
				// De Query die nodig is om data van de tabel op te halen
		         String strSelect = String.format("SELECT * FROM %s", table_name);
		         
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
		            String row = String.format("%s %d \n %s %s \n %s %d \n %s %d \n \n",table_id, id, column_user_name, user_name, column_score, score, column_performance_points, performance_points);
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
	
	
	
	private void insertQuery(int id,String playername,int score,int performance_points) throws SQLException {
		
		// a = de hele arraylist met alle rows 
		ArrayList<String> a = readDatabase();
		
		// array om te checken of de table al dezelfde username heeft
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		
				Connection conn = DriverManager.getConnection(connection,user,password);    
		         
		         Statement stmt = conn.createStatement();
	
			
				
			    ArrayList<String> info = a;
			    
			    // gaat over alle strings heen van de arraylist info en  
			    for(String i : info) {
			    	
			    	// als de playername  er in zit true/false
			    	 boolean playerIsIncluded = i.contains(playername);
			    	 
			    	 
                    // boolean idIsIncluded = i.contains(Integer.toString(id));		    	
			    	 
			    	 // voegt de boolean toe aan de arraylist b
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
			    
			    
			    // als er nog geen rows zijn met data
			    if(b.isEmpty()) {
			    	String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
			         stmt.executeUpdate(sqlInsert);
			    }
			    
			    // closed de querys 
			    stmt.close();
			    }
		
	
		    	
	}


