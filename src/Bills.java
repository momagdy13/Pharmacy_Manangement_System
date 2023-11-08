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

public class Bills {
    private JScrollPane tab;
    private JTable table;
    private JPanel bill;
    private JButton deleteButton;
    private JComboBox mdcn;
    private JComboBox emp_name;
    private JTextField cost;
    private JTextField PHNAME;
    private JButton add;
    private JButton update;
    private JComboBox PNAME;
    private JTextField id;
    private JButton clear;
    private JButton homeButton;
    private JButton search;
    private JButton sh;
    private JTextField searchh;
    Connection connection = database_connection.connection();
    Statement statement = null;

    public Bills() {
        JFrame frame = new JFrame("Bills");
        frame.setContentPane(bill);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        ShowRecord();
        addItem();
        AddItem();
        AdItem();

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String EName = (String) emp_name.getSelectedItem();
                    String MName = (String) mdcn.getSelectedItem();
                    String Cost = cost.getText();
                    String PN = (String) PNAME.getSelectedItem();
                    String sql = "INSERT INTO bills (b_id,b_cost,mdcn_name,p_name,emp_name ) VALUES ('" + id.getText() + "','" + Cost + "','" + MName + "','" + PN + "','" + EName + "') ";
                    if (Cost.isEmpty() || MName.isEmpty() || EName.isEmpty() || PN.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items..!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Insert Data Successfully..!");
                        frame.dispose();
                        new Bills();
                    }
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, E, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String EName = (String) emp_name.getSelectedItem();
                    String MName = (String) mdcn.getSelectedItem();
                    String Cost = cost.getText();
                    String PN = PHNAME.getText();
                    String sql = "UPDATE bills SET b_id = '" + id.getText() + "', b_cost = '" + Cost + "',mdcn_name ='" + MName + "',p_name = '" + PN + "',emp_name = '" + EName + "'";
                    if (Cost.isEmpty() || MName.isEmpty() || EName.isEmpty() || PN.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Fill Out All Items..!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Update Data Successfully..!");
                        frame.dispose();
                        new Bills();
                    }
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Fill Out All Items OR U Can't Insert Same Name!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int MyIndex = table.getSelectedRow();
                    id.setText(model.getValueAt(MyIndex, 0).toString());
                    mdcn.setSelectedItem(model.getValueAt(MyIndex, 2).toString());
                    cost.setText(model.getValueAt(MyIndex, 1).toString());
                    PNAME.setSelectedItem(model.getValueAt(MyIndex,3));
                    emp_name.setSelectedItem(model.getValueAt(MyIndex,4));


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }

            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                mdcn.setSelectedItem(null);
                cost.setText("");
                PNAME.setSelectedItem(null);
                emp_name.setSelectedItem(null);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql  = "DELETE FROM bills WHERE b_id = '"+id.getText()+"' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Delete Data Successfully..! ");
                    frame.dispose();
                    new Bills();

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
        sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowRecord();
                searchh.setText("");

            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    String sql = "SELECT * FROM bills WHERE b_id = '"+ searchh.getText()+"'";
                    ResultSet rs = statement.executeQuery(sql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }catch (Exception E){
                    JOptionPane.showMessageDialog(null,E);
                }
            }

        });
    }

    public void ShowRecord() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM bills ";
            ResultSet rs = statement.executeQuery(sql);

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void addItem() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM medecine";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                mdcn.addItem(rs.getString(1));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void AdItem() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM pation";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                PNAME.addItem(rs.getString(1));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

    public void AddItem() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM employee";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                emp_name.addItem(rs.getString(1));
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }

}
