/*
 * Author: Kevin Alvarado

 * Date: 10/27/2022
 * 
 * Description: this program displays products from a database and it lets you enter a product 
 * of your own to the database.
 */



package murach.ui;

import java.sql.*;

import javax.swing.JOptionPane;

import murach.business.Product;
import murach.db.DBException;
import murach.db.DBUtil;

public class DBTesterApp {

    private static Connection connection = null;

    public static void main(String args[]) {
    	
    	/*
        if (val == selections[0]) {
        	
        }
    	*/
    	
    	
    	 String[] selections = { "List all products", "List a product of your choice","Insert a new product of your choice",
    			 "Update a product of your choice", "Delete a product of your choice", "Exit"};
         String val = (String)JOptionPane.showInputDialog(null, "Choose one",
             "Input", JOptionPane.INFORMATION_MESSAGE, null,
             selections, selections[0]);
         if (val != null)
           System.out.println(val.toString());
    	
         
         
         
         	
        // create 1 or more line items
    	
//if (val == selections[2]) {
	
	JOptionPane.showMessageDialog(null, "Insert a new produt of your choice!");

        	
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
    	
    	String enterA = JOptionPane.showInputDialog(null, "Please enter a code:");
    	String enterB = JOptionPane.showInputDialog(null, "Please enter a description:");
    	String enterC = JOptionPane.showInputDialog(null, "Please enter the price");
    	
    	JOptionPane.showMessageDialog(null, 
    	"Code: " + enterA + "\n" +
    	"Description: " + enterB + "\n " +
    	"Price: " + enterC);
    	

    	choice = JOptionPane.showInputDialog(null, "Continue? (y/n):", "Loop test", JOptionPane.YES_NO_OPTION);
        }
        
        //JOptionPane.showMessageDialog(null, "Exit!");
         
         
         
         
         
         
        
        try {
           connection = DBUtil.getConnection();
        } catch (DBException e) {
            //System.out.println(e);
        	JOptionPane.showMessageDialog(null, e);
        }
        
        // select data from database
        //JOptionPane.showMessageDialog(null, printFirstProduct());
        printProducts();
        printFirstProduct();
        printProductByCode("jsp");

        // modify data in the database
        Product p = new Product("test", "Test Product", 49.50);
        insertProduct(p);
        printProducts();
        deleteProduct(p);
        printProducts();

        // disconnect from the database
        try {
            DBUtil.closeConnection();
        } catch (DBException e) {
           // System.out.println(e);
        	JOptionPane.showMessageDialog(null, e);
        }
        
    }     
		
    //} 
    	
    
    
	
    public static void printProducts() {
        try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Product")) {
            Product p;

            //System.out.println("Product list:");
            JOptionPane.showMessageDialog(null, "Product list:");
            while (rs.next()) {
                String code = rs.getString("Code");
                String description = rs.getString("Description");
                double price = rs.getDouble("ListPrice");

                p = new Product(code, description, price);

                //printProduct(p);
                JOptionPane.showMessageDialog(null, p);
            } 
            System.out.println();
            //JOptionPane.showMessageDialog();
        } catch (SQLException e) {
            //System.out.println(e);
        	JOptionPane.showMessageDialog(null, e);
        
        }
    
    
    }
    
    

    public static void printFirstProduct() {
//        Product p = new Product();
//
//
//        System.out.println("First product:");
//        printProduct(p);
//        System.out.println();
    	try (Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Product")) {
               if (rs.next()) {
                   // code that uses column names
                   String code = rs.getString("Code");
                   String description = rs.getString("Description");
                   double price = rs.getDouble("ListPrice");
                   Product p = new Product(code, description, price);

                   //System.out.println("First product:");
                   JOptionPane.showMessageDialog(null, "First product:");
                   //printProduct(p);
                   JOptionPane.showMessageDialog(null, p);
                   System.out.println();
               }
           } catch (SQLException e) {
               //System.out.println(e);
        	   JOptionPane.showMessageDialog(null, e);
           }
    }

    public static void printProductByCode(String productCode) {
//        Product p = new Product();
//
//        // add code that prints the product with the specified code
//        System.out.println("Product by code: " + productCode);
//        printProduct(p);
//        System.out.println();
    	try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Product WHERE Code = ?")) {
            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String code = rs.getString(2);
                String description = rs.getString(3);
                double price = rs.getDouble(4);
                Product p = new Product(code, description, price);

                //System.out.println("Product by code: " + productCode);
                JOptionPane.showMessageDialog(null, "Product by code: " + productCode);
                //printProduct(p);
                JOptionPane.showMessageDialog(null, p);
                System.out.println();
            }

            rs.close();
        } catch (SQLException e) {
            //System.out.println(e);
        	JOptionPane.showMessageDialog(null, e);
        }
    	
    }

    public static void insertProduct(Product p) {
//        System.out.println("Insert test: ");
//
//        // add code that inserts the specified product into the database
//        // if a product with the specifed code already exists, display an error message
//        printProduct(p);
//        System.out.println();
    	
        String insertProduct
        = "INSERT INTO Product (Code, Description, ListPrice) "
        + "VALUES (?, ?, ?)";

try (PreparedStatement ps = connection.prepareStatement(insertProduct)) {
   // System.out.println("Insert test: ");
	JOptionPane.showMessageDialog(null, "Insert test:");

    ps.setString(1, p.getCode());
    ps.setString(2, p.getDescription());
    ps.setDouble(3, p.getPrice());
    ps.executeUpdate();

    //printProduct(p);
    JOptionPane.showMessageDialog(null, p);
    System.out.println();

} catch (SQLException e) {
    //System.out.println(e);
	JOptionPane.showMessageDialog(null, e);
}
    	
    }

    private static void deleteProduct(Product p) {
//        System.out.println("Delete test: ");
//
//        // add code that deletes the specified product from the database
//        // if a product with the specified code doesn't exist, display an error message
//        printProduct(p);
//        System.out.println();
    	
    	 String deleteProduct
         = "DELETE FROM Product "
         + "WHERE Code = ?";

 try (PreparedStatement ps = connection.prepareStatement(deleteProduct)) {
     //System.out.println("Delete test: ");
	 JOptionPane.showMessageDialog(null, "Delete test:");

     ps.setString(1, p.getCode());
     ps.executeUpdate();
     //printProduct(p);
     JOptionPane.showMessageDialog(null, p);
     
     System.out.println();
 } catch (SQLException e) {
     //System.out.println(e);
	 JOptionPane.showMessageDialog(null, e);
     JOptionPane.showMessageDialog(null, "Exit!");

 }
 //JOptionPane.showMessageDialog(null, "Exit!");

    	
    }
    
    
    // Thi sis for the insert
    /*
    private static void Insert()
    {
    	JOptionPane.showMessageDialog(null, "Insert your own product");

        // create 1 or more line items
     
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
    	
    	String enterA = JOptionPane.showInputDialog(null, "Please enter code:");
    	String enterB = JOptionPane.showInputDialog(null, "Please enter description:");
    	String enterC = JOptionPane.showInputDialog(null, "Please enter the price");
    	
    	JOptionPane.showMessageDialog(null, enterA + enterB+ enterC);
    	
    	choice = JOptionPane.showInputDialog(null, "Continue? (y/n):", "Loop test", JOptionPane.YES_NO_OPTION);
        }
        JOptionPane.showMessageDialog(null, "Bye!");
    	
    }
    */
/*
    // use this method to print a Product object on a single line
    private static void printProduct(Product p) {
       String productString
                = //JOptionPane.showMessageDialog(null, 
                StringUtil.padWithSpaces(p.getCode(), 12) 
                + StringUtil.padWithSpaces(p.getDescription(), 38) +
                 p.getFormattedPrice();

       // System.out.println(productString);
        JOptionPane.showMessageDialog(null, productString);
    }
    */

}
    
