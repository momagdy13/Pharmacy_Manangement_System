import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    private JPanel log;
    private JTextField name;
    private JButton loginButton;
    private JButton cancelButton;
    private JPasswordField password;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Login() {

        JFrame frame = new JFrame("Login");
        frame.setContentPane(log);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,600);








        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out Email", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else if (password.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out password", "MESSAGE", JOptionPane.ERROR_MESSAGE);

                } else {
                    PreparedStatement ps;
                    Connection connection = database_connection.connection();
                    try {
                        ps = connection.prepareStatement("SELECT * FROM admin WHERE email = ? AND password = ?");
                        ps.setString(1, name.getText());
                        ps.setString(2, password.getText());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            frame.dispose();
                            new Home();

                        } else {
                            JOptionPane.showMessageDialog(null, "Email or Password invalid", "MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }


                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }


                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}
