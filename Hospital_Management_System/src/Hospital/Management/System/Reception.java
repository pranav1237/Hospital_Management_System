package Hospital.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Reception extends JFrame {

    public Reception() {
        setTitle("Reception Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 123, 167));
        headerPanel.setPreferredSize(new Dimension(1200, 120));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel title = new JLabel("Hospital Reception Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title, BorderLayout.WEST);

        ImageIcon docIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Doctor.png"));
        Image docImg = docIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        headerPanel.add(new JLabel(new ImageIcon(docImg)), BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 20, 20));
        buttonPanel.setBackground(new Color(242, 250, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        String[] buttonLabels = {
                "Add New Patient", "Room", "Department",
                "All Employee Info", "Patient Info", "Patient Discharge",
                "Update Patient Details", "Hospital Ambulance", "Search Room",
                "Logout"
        };

        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            button.addActionListener(e -> handleButtonClick(label));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 150, 136));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "Add New Patient":
                new New_Patient();
                break;
            case "Room":
                new Room();
                break;
            case "Department":
                new Department();
                break;
            case "All Employee Info":
                new Employee_info();
                break;
            case "Patient Info":
                new All_Patient_info();
                break;
            case "Patient Discharge":
                new patient_discharge();
                break;
            case "Update Patient Details":
                new update_patient_details();
                break;
            case "Hospital Ambulance":
                new Ambulance();
                break;
            case "Search Room":
                new SearchRoom();
                break;
            case "Logout":
                setVisible(false);
                new Login();
                break;
        }
    }

    public static void main(String[] args) {
        new Reception();
    }
}
