import javax.swing.*;

public class Home {
    private JButton manageEmployeeButton;
    private JButton manageDoctorsButton;
    private JButton manageMedecineButton;
    private JButton manageBillsButton;
    private JPanel home;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Home().home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
    }
}
