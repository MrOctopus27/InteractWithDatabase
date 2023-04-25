/*
 * Author: Kevin Alvarado
 * Date: 10/27/2022
 * 
 * Description: this program displays products from a database and it lets you enter a product 
 * of your own to the database.
 */


package murach.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection() throws DBException {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://localhost:3306/mma";
            	//String url = "jdbc:mysql://localhost:8000/phpmyadmin/db_structure.php?server=1&db=mma";
            	//String url = "jdbc:mysql://localhost:8000/mma";
                //String username = "mma_user";
            	String username = "root";
                String password = "sesame";

                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
                return connection;
            } catch (SQLException e) {
                throw new DBException(e);
            }            
        }
    }
    
    public static synchronized void closeConnection() throws DBException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                connection = null;                
            }
        }
    }
}