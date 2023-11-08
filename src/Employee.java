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

public class Employee {
    private JPanel emp;
    private JScrollPane tab;
    private JTable table;
    private JTextField name;
    private JButton updateButton;
    private JButton homeButton;
    private JButton search;
    private JTextField Name;
    private JButton sh;
    private JButton add;
    private JTextField phone;
    private JTextField salary;
    private JTextField email;
    private JButton delete;
    private JButton clear;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Employee() {
        JFrame frame = new JFrame("Employee");
        frame.setContentPane(emp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        ShowRecord();

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM employee WHERE emp_name = '"+ Name.getText()+"'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next()){
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }else {
                        JOptionPane.showMessageDialog(null,"Employee  Not Found..! ");
                        Name.setText("");
                    }

                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E);
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
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Email = email.getText();
                    String Phone = phone.getText();
                    String Salary = salary.getText();
                    String sql = "INSERT INTO employee (emp_name,emp_phone,emp_email,emp_salary) VALUES ('"+Name+"','"+Phone+"','"+Email+"','"+Salary+"') ";
                    if (Name.isEmpty()||Email.isEmpty()||Phone.isEmpty()||Salary.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Fill Out All Items..!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Insert Data Successfully..!");
                        frame.dispose();
                        new Employee();
                    }
                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,"Fill Out All Items OR U Can't Insert Same Name!","ERROR",JOptionPane.ERROR_MESSAGE);
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
                    String Salary = salary.getText();
                    String sql = "UPDATE employee SET emp_name='"+Name+"' ,emp_phone ='"+Phone+"' ,emp_email = '"+Email+"',emp_salary = '"+Salary+"' WHERE emp_name= '"+name.getText()+"'  ";
                    if (Name.isEmpty()||Email.isEmpty()||Phone.isEmpty()||Salary.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Fill Out All Items..!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update Data Successfully..!");
                        frame.dispose();
                        new Employee();
                    }


                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E,"ERROR",JOptionPane.ERROR_MESSAGE);
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
                    salary.setText(model.getValueAt(MyIndex, 3).toString());
                    phone.setText(model.getValueAt(MyIndex, 1).toString());



                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql  = "DELETE FROM employee WHERE emp_name = '"+name.getText()+"' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Delete Data Successfully..! ");
                    frame.dispose();
                    new Employee();

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
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                phone.setText("");
                salary.setText("");
                email.setText("");
            }
        });
    }
    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM employee ";
            ResultSet rs = statement.executeQuery(sql);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
