import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Company {
    private JPanel panel1;
    private JPanel med;
    private JScrollPane tab;
    private JTable table;
    private JTextField name;
    private JButton homeButton;
    private JButton clear;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton;


    private JTextField address;
    private JTextField email;
    private JTextField phone;
    Connection connection = database_connection.connection();
    Statement statement = null;


    public Company() {
        JFrame frame = new JFrame("Company");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ShowRecord();


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int MyIndex = table.getSelectedRow();
                    name.setText(model.getValueAt(MyIndex, 0).toString());
                    email.setText(model.getValueAt(MyIndex, 1).toString());
                    address.setText(model.getValueAt(MyIndex, 2).toString());
                    phone.setText(model.getValueAt(MyIndex, 3).toString());


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Phone = phone.getText();
                    String Address = address.getText();
                    String sql = "INSERT INTO company (comp_name ,comp_email,comp_address,comp_phone) VALUES ('" + Name + "','" + Email + "','" + Address + "','" + Phone + "')";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Insert Data Successfully!");
                    frame.dispose();
                    new Company();


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items OR You can't insert same name of  medicine ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Phone = phone.getText();
                    String Address = address.getText();
                    String sql = "UPDATE company SET comp_name ='" + Name + "' , comp_email = '" + Email + "' ,comp_address ='" + Address + "',comp_phone = '" + Phone + "' WHERE comp_name ='" + Name + "'    ";
                    if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items...!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Update Data Successfully!");
                        frame.dispose();
                        new Company();
                    }


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Phone = phone.getText();
                    String Address = address.getText();
                    String sql = "DELETE FROM company WHERE comp_name ='" + Name + "'    ";
                    if (Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items...!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Delete Data Successfully!");
                        frame.dispose();
                        new Company();
                    }


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                email.setText("");
                address.setText("");
                phone.setText("");
            }
        });
    }

    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM company";
            ResultSet rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }
}
