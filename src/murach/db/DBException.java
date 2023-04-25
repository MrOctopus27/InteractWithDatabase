/*
 * Author: Kevin Alvarado
 * Date: 10/27/2022
 * 
 * Description: this program displays products from a database and it lets you enter a product 
 * of your own to the database.
 */


package murach.db;

/* 
 * This is just a wrapper class so we can throw a common exception for
 * the UI to catch without tightly coupling the UI to the database layer.
 */
@SuppressWarnings("serial")
public class DBException extends Exception {
    DBException() {}
    
    DBException(Exception e) {
        super(e);
    }
}