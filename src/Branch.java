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

public class Branch {
    private JPanel pa;
    private JScrollPane tab;
    private JTable table;
    private JTextField Name;
    private JButton search;
    private JButton sh;
    private JTextField email;
    private JTextField name;
    private JTextField phone;
    private JButton add;
    private JButton updateButton;
    private JButton delete;
    private JButton clear;
    private JButton homeButton;
    private JTextField address;
    private JPanel B;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Branch() {
        JFrame frame = new JFrame("Branch");
        frame.setContentPane(B);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(930,800);
        frame.setLocationRelativeTo(null);
        ShowRecord();


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
                    String sql = "SELECT * FROM pharmacy WHERE ph_name = '" + Name.getText() + "'";
                    ResultSet RS = statement.executeQuery(sql);

                    table.setModel(DbUtils.resultSetToTableModel(RS));

                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, E);
                }

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

                    String sql = "INSERT INTO pharmacy (ph_name,ph_phone,ph_email,Address) VALUES ('" + Name + "','" + Phone + "','" + Email + "','"+address.getText()+"') ";
                    if (Name.isEmpty() || Email.isEmpty() || Phone.isEmpty() || address.equals("")) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items..!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Insert Data Successfully..!");
                        frame.dispose();
                        new Branch();
                    }
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items OR U Can't Insert Same Name!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                phone.setText("");
                address.setText("");
                email.setText("");
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql  = "DELETE FROM pharmacy WHERE ph_name = '"+name.getText()+"' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Delete Data Successfully..! ");
                    frame.dispose();
                    new Branch();

                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E);
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

                    String sql = "UPDATE pharmacy SET ph_name='"+Name+"' ,ph_phone ='"+Phone+"' ,ph_email = '"+Email+"',Address= '"+Address+"' WHERE ph_name= '"+name.getText()+"'  ";
                    if (Name.isEmpty()||Email.isEmpty()||Phone.isEmpty() || Address.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Fill Out All Items..!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update Data Successfully..!");
                        frame.dispose();
                        new Branch();
                    }


                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E,"ERROR",JOptionPane.ERROR_MESSAGE);
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int MyIndex = table.getSelectedRow();
                    name.setText(model.getValueAt(MyIndex, 0).toString());
                    email.setText(model.getValueAt(MyIndex, 2).toString());
                    phone.setText(model.getValueAt(MyIndex, 1).toString());
                    address.setText(model.getValueAt(MyIndex,3).toString());


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
    }
    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM pharmacy ";
            ResultSet rs = statement.executeQuery(sql);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

}
