import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JButton manageEmployeeButton;
    private JButton manageDoctorsButton;
    private JButton manageMedicineButton;
    private JButton manageBillsButton;
    private JPanel home;
    private JButton companyButton1;
    private JButton patientButton;
    private JButton logOutButton;

    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1600,820);
        frame.setLocationRelativeTo(null);
        manageMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Medicine();
            }
        });
        companyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Company();
            }
        });
        manageDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Doctor();
            }
        });
        manageEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Employee();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
    }
}
