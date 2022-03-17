package framework;
	
import  java.sql.Connection;		
import  java.sql.Statement;

import org.openqa.selenium.By;

import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;		
public class  SQLConnector {		
	
	private static String jdbc_driver;
	Connection con= null;
	private static String databaseUrl;
	
	/* Name: DB_Setup
	 * Desc: This Method sets up the JDBC connection to DB.
	 * Pram: Type -DB you are trying to connect,Database Url, Ursername and Password.
	 * Return: This Method will return Resultset.
	 * */
    public boolean DB_setUp(String DB_type,String dbUrl,String username,String password) {
		switch(DB_type.toLowerCase()){

		case "oracle":
			jdbc_driver="oracle.jdbc.driver.OracleDriver";
			databaseUrl = "jdbc:oracle:thin:@"+dbUrl+"";
			break;

		case "mysql":
			jdbc_driver="com.mysql.jdbc.Driver";	
			databaseUrl = "jdbc:mysql:@"+dbUrl+"";
			break;

		}		
			
        try {
            Class.forName(jdbc_driver);
            System.out.println("Connecting to Database...");
            System.out.println(databaseUrl);
       		//Create Connection to DB		
        	con = DriverManager.getConnection(databaseUrl,username,password);
            
		            if (con != null) {
		                System.out.println("Connected to the Database...");
		            }
		            return true;
		        } catch (SQLException ex) {
		           ex.printStackTrace();
		           return false;
		        }
        catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
		return false;
}
    
	/**
	 * @Method: SendDB_Query
	 * @Description: This method is used to execute Query .
	 * @param String - Query
	 * @param Return - This Method will return Resultset.
	 */ 
    public ResultSet SendDB_Query(String DB_Query) throws  ClassNotFoundException, SQLException {													
 		try{
				//Query to Execute		
				String query = DB_Query;     	    		
				System.out.println(query);
				
          		//Create Statement Object		
        	   Statement stmt = con.createStatement();					
       
       			// Execute the SQL Query. Store results in ResultSet		
         		ResultSet rs= stmt.executeQuery(query);							
         		return rs;
 		}catch(Exception exception) {
 			exception.printStackTrace();
 				}
		return null;
 		
		}
  
    	/**
    	 * @Method: DB_close_Down
    	 * @Description: To Closes the DB connection.
    	 * @param  - 
    	 * @param Return - Closes the DB connection.
    	 */
    	 // closing DB Connection    	
        public void DB_close_Down() {
            if (con != null) {
                      try {
                          System.out.println("Closing Database Connection...");
                          con.close();
                      } catch (SQLException ex) {
                          ex.printStackTrace();
                      }
                  }
            }
}