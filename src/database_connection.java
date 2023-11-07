import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class database_connection {
    public static Connection connection() {
        Connection con = null;
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy_management_system","root","");



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}
