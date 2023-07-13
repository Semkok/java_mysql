package Querys;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SendQuery {
	
	
	/*ALL STRINGS ARE SPECIFIC AND NEED TO BE CHANGED IN ORDER TO WORK FOR YOUR DATABASE CONNECTION*/
	
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
	
	
	
	
	
	SendQuery(int id,String name, int score, int performance_points) throws SQLException{
		this.insertQuery(id,name,score,performance_points);
		
	}
	
	

	 public ArrayList<String> readDatabase() {
		
		 ArrayList<String> info = new ArrayList<String>();
		
		try (

		         Connection conn = DriverManager.getConnection(connection,user,password);   
		         
				
		         Statement stmt = conn.createStatement();
				
		      ) {
		        
		         String strSelect = String.format("SELECT * FROM %s", table_name);
		         
		        
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
		
		
		
		return null;
		
	
	}
	
	
	
	private void insertQuery(int id,String playername,int score,int performance_points) throws SQLException {
		
		
		ArrayList<String> a = readDatabase();
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		
		Connection conn = DriverManager.getConnection(connection,user,password);    
		         
		Statement stmt = conn.createStatement();
	
	    ArrayList<String> info = a;
			       
			    for(String i : info) {

			    	 boolean playerIsIncluded = i.contains(playername);
			    	 boolean idIsIncluded = i.contains("id "+ id);
			    	 b.add(playerIsIncluded);
			    	 b.add(idIsIncluded);
			    	 }    
			    
			    if(b.contains(true) ) {
			    	
			    	String sqlDelete = String.format("DELETE FROM %s WHERE %s = '%s' OR %s = '%s'",table_name,column_user_name,playername,table_id,id);
			         
			         stmt.executeUpdate(sqlDelete);
			    }
			    if(b.contains(false) ){
			    	String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
			         stmt.executeUpdate(sqlInsert);
			        
			    }
			    if(b.isEmpty()) {
			    	String sqlInsert = String.format("INSERT INTO highscores values (%d,'%s',%d,%d)",id,playername,score,performance_points);
			         stmt.executeUpdate(sqlInsert);
			    }
			    
			    stmt.close();
			    }
		
	
		    	
	}


