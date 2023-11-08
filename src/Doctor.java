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

public class Doctor {
    private JPanel med;
    private JScrollPane tab;
    private JTable table;
    private JTextField name;
    private JButton homeButton;
    private JTextField email;
    private JTextField phone;
    private JButton clear;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton;
    private JPanel doc;
    private JComboBox combo;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Doctor() {
        JFrame frame = new JFrame("Doctor");
        frame.setContentPane(doc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ShowRecord();
        addItem();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Phone = phone.getText();
                    String Email = email.getText();
                    String His = (String) combo.getSelectedItem();
                    String sql = "INSERT INTO doctor (dc_name,dc_phone,dc_email,p_name) VALUES ('"+Name+"','"+Phone+"','"+Email+"','"+His+"')";
                    if (Name.isEmpty()||Phone.isEmpty()||Email.isEmpty()||His.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Fill Out All Items!!!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }else{
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Insert Data Successfully..!");
                        frame.dispose();
                        new Doctor();
                    }

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
                    String His = (String) combo.getSelectedItem();
                    String sql = "UPDATE doctor SET dc_name ='" + Name + "' , dc_phone = '" + Phone + "' ,dc_email ='" + Email + "',p_name = '"+His+"' WHERE dc_name ='" + Name + "'    ";
                    if (Name.isEmpty() || His.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items...!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Update Data Successfully!");
                        frame.dispose();
                        new Doctor();
                    }


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
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
                    combo.setSelectedItem(model.getValueAt(MyIndex, 3).toString());
                    phone.setText(model.getValueAt(MyIndex, 1).toString());



                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                phone.setText("");
                email.setText("");
                combo.setSelectedItem(null);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();

                    String sql = "DELETE FROM doctor WHERE dc_name = '"+name.getText()+"'";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Delete Data Successfully..!");
                    frame.dispose();
                    new Doctor();

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

    private void ShowRecord(){
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM doctor";
            ResultSet rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void addItem(){
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM pation";
            ResultSet r = statement.executeQuery(sql);
            while (r.next()){
                combo.addItem(r.getString(1));
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
