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

public class Patient {
    private JScrollPane tab;
    private JTable table;
    private JTextField name;
    private JTextField email;
    private JTextField phone;
    private JTextField Name;
    private JButton search;
    private JButton sh;
    private JPanel pa;
    private JButton add;
    private JButton updateButton;
    private JButton delete;
    private JButton clear;
    private JButton homeButton;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Patient() {
        JFrame frame = new JFrame("Patient");
        frame.setContentPane(pa);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        ShowRecord();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int MyIndex = table.getSelectedRow();
                    name.setText(model.getValueAt(MyIndex, 0).toString());
                    email.setText(model.getValueAt(MyIndex, 2).toString());
                    phone.setText(model.getValueAt(MyIndex, 1).toString());


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
        sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowRecord();
                Name.setText("");
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM pation WHERE p_name = '" + Name.getText() + "'";
                    ResultSet RS = statement.executeQuery(sql);

                    table.setModel(DbUtils.resultSetToTableModel(RS));

                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, E);
                }
            }

        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                phone.setText("");
                email.setText("");
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Phone = phone.getText();

                    String sql = "INSERT INTO pation (p_name,p_phone,p_email) VALUES ('" + Name + "','" + Phone + "','" + Email + "') ";
                    if (Name.isEmpty() || Email.isEmpty() || Phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items..!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Insert Data Successfully..!");
                        frame.dispose();
                        new Patient();
                    }
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items OR U Can't Insert Same Name!", "ERROR", JOptionPane.ERROR_MESSAGE);
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

                    String sql = "UPDATE pation SET p_name='"+Name+"' ,p_phone ='"+Phone+"' ,p_email = '"+Email+"' WHERE p_name= '"+name.getText()+"'  ";
                    if (Name.isEmpty()||Email.isEmpty()||Phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Fill Out All Items..!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update Data Successfully..!");
                        frame.dispose();
                        new Patient();
                    }


                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E,"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql  = "DELETE FROM pation WHERE p_name = '"+name.getText()+"' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Delete Data Successfully..! ");
                    frame.dispose();
                    new Patient();

                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E);
                }
            }

        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home();
            }
        });
    }

    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM pation ";
            ResultSet rs = statement.executeQuery(sql);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

}
