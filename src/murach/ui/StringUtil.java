/*
 * Author: Kevin Alvarado
 * Date: 10/27/2022
 * 
 * Description: this program displays products from a database and it lets you enter a product 
 * of your own to the database.
 */


package murach.ui;

public class StringUtil {

    public static String padWithSpaces(String s, int length) {
        if (s.length() < length) {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}
