import javax.swing.*;

public class Home {
    private JButton manageEmployeeButton;
    private JButton manageDoctorsButton;
    private JButton manageMedicineButton;
    private JButton manageBillsButton;
    private JPanel home;
    private JButton companyButton1;
    private JButton patientButton;

    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1600,820);
        frame.setLocationRelativeTo(null);
    }
}
