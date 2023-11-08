import net.proteanit.sql.DbUtils;

import javax.lang.model.type.NullType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Medicine {
    private JPanel med;
    private JTable table;
    private JTextField name;
    private JTextField exp_date;
    private JTextField prod_date;
    private JTextField qnt;
    private JTextField price;
    private JComboBox combo;
    private JComboBox phName;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JScrollPane tab;
    private JButton homeButton;
    private JButton clear;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Medicine() {
        JFrame frame = new JFrame("Medicine");
        frame.setContentPane(med);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        ShowRecord();
        addItem();
        AddItem();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Qnt = qnt.getText();
                    String Price = price.getText();
                    String Ph_name = (String) phName.getSelectedItem();
                    String Comp_name = (String) combo.getSelectedItem();
                    String ExD = exp_date.getText();
                    String PRO = prod_date.getText();
                    String sql = "INSERT INTO medecine (mdcn_name,mdcn_price,mdcn_quanity,ph_name,comp_name,Exp_date,Prod_date) VALUES ('" + Name + "','" + Price + "','" + Qnt + "','" + Ph_name + "','" + Comp_name + "','" + ExD + "','" + PRO + "')";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Insert Data Successfully!");
                    frame.dispose();
                    new Medicine();


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items OR You can't insert same name of  medicine " ,"ERROR",JOptionPane.ERROR_MESSAGE);
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
                    price.setText(model.getValueAt(MyIndex, 1).toString());
                    qnt.setText(model.getValueAt(MyIndex, 2).toString());
                    exp_date.setText(model.getValueAt(MyIndex, 5).toString());
                    prod_date.setText(model.getValueAt(MyIndex, 6).toString());


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }

        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String Name = name.getText();
                    String Qnt = qnt.getText();
                    String Price = price.getText();
                    String Ph_name = (String) phName.getSelectedItem();
                    String Comp_name = (String) combo.getSelectedItem();
                    String ExD = exp_date.getText();
                    String PRO = prod_date.getText();
                    String sql = "UPDATE medecine SET mdcn_price ='" + Price + "' ,mdcn_quanity = '" + Qnt + "' ,ph_name ='" + Ph_name + "',comp_name = '" + Comp_name + "',Exp_date = '" + ExD + "',Prod_date = '" + PRO + "' WHERE mdcn_name ='" + Name + "'    ";
                    if (Name.isEmpty() || Qnt.isEmpty() || Ph_name.isEmpty() || Price.isEmpty() || PRO.isEmpty() || Comp_name.isEmpty() || ExD.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items...!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Update Data Successfully!");
                        frame.dispose();
                        new Medicine();
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
                    String Name = name.getText();
                    String Qnt = qnt.getText();
                    String Price = price.getText();
                    String Ph_name = (String) phName.getSelectedItem();
                    String Comp_name = (String) combo.getSelectedItem();
                    String ExD = exp_date.getText();
                    String PRO = prod_date.getText();
                    statement = connection.createStatement();
                    String sql = "DELETE FROM medecine WHERE mdcn_name = '" + name.getText() + "' ";
                    if (Name.isEmpty() || Qnt.isEmpty() || Ph_name.isEmpty() || Price.isEmpty() || PRO.isEmpty() || Comp_name.isEmpty() || ExD.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items...!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                        frame.dispose();
                        new Medicine();
                    }

                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, E);
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
                price.setText("");
                qnt.setText("");
                exp_date.setText("");
                prod_date.setText("");
                phName.setSelectedItem("Null");
                combo.setSelectedItem("Null");
            }
        });
    }

    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM medecine ";
            ResultSet rs = statement.executeQuery(sql);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void addItem() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM pharmacy";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                phName.addItem(rs.getString(1));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void AddItem() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM company";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                combo.addItem(rs.getString(1));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
