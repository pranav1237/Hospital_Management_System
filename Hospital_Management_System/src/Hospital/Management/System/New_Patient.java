package Hospital.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class New_Patient extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2;
    ButtonGroup genderGroup;
    Choice c1;
    JLabel date;
    JButton b1, b2;

    New_Patient() {
        setTitle("New Patient Form");

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 900, 540);
        panel.setBackground(new Color(240, 248, 255));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(600, 40, 200, 200);
        panel.add(label);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(200, 10, 400, 40);
        labelName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelName.setForeground(new Color(30, 60, 90));
        panel.add(labelName);

        // --- ID Type ---
        JLabel labelID = new JLabel("ID:");
        labelID.setBounds(50, 70, 150, 20);
        styleLabel(labelID, panel);

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(250, 70, 200, 25);
        styleInput(comboBox, panel);

        // --- Number ---
        JLabel labelNumber = new JLabel("Number:");
        labelNumber.setBounds(50, 110, 150, 20);
        styleLabel(labelNumber, panel);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(250, 110, 200, 25);
        styleInput(textFieldNumber, panel);

        // --- Name ---
        JLabel labelName1 = new JLabel("Name:");
        labelName1.setBounds(50, 150, 150, 20);
        styleLabel(labelName1, panel);

        textName = new JTextField();
        textName.setBounds(250, 150, 200, 25);
        styleInput(textName, panel);

        // --- Gender ---
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(50, 190, 150, 20);
        styleLabel(labelGender, panel);

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");

        r1.setBounds(250, 190, 80, 25);
        r2.setBounds(340, 190, 80, 25);
        r1.setBackground(panel.getBackground());
        r2.setBackground(panel.getBackground());

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        panel.add(r1);
        panel.add(r2);

        // --- Disease ---
        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(50, 230, 150, 20);
        styleLabel(labelDisease, panel);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(250, 230, 200, 25);
        styleInput(textFieldDisease, panel);

        // --- Room ---
        JLabel labelRoom = new JLabel("Room:");
        labelRoom.setBounds(50, 270, 150, 20);
        styleLabel(labelRoom, panel);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from Room");
            while (rs.next()) {
                c1.add(rs.getString("room_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        c1.setBounds(250, 270, 200, 25);
        panel.add(c1);

        // --- Time ---
        JLabel labelDate = new JLabel("Time:");
        labelDate.setBounds(50, 310, 150, 20);
        styleLabel(labelDate, panel);

        Date currentDate = new Date();
        date = new JLabel(currentDate.toString());
        date.setBounds(250, 310, 300, 20);
        date.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        date.setForeground(Color.DARK_GRAY);
        panel.add(date);

        // --- Deposit ---
        JLabel labelDeposit = new JLabel("Deposit:");
        labelDeposit.setBounds(50, 350, 150, 20);
        styleLabel(labelDeposit, panel);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(250, 350, 200, 25);
        styleInput(textFieldDeposite, panel);

        // --- Buttons ---
        b1 = new JButton("Add Patient");
        b1.setBounds(120, 420, 150, 35);
        styleButton(b1);
        panel.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("Back");
        b2.setBounds(300, 420, 150, 35);
        styleButton(b2);
        panel.add(b2);
        b2.addActionListener(this);

        setSize(850, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void styleLabel(JLabel label, JPanel panel) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(44, 62, 80));
        panel.add(label);
    }

    private void styleInput(JComponent field, JPanel panel) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(new Color(44, 62, 80));
        panel.add(field);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            conn c = new conn();
            String gender = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : null);

            String s1 = (String) comboBox.getSelectedItem();   // ID
            String s2 = textFieldNumber.getText();             // Number
            String s3 = textName.getText();                    // Name
            String s4 = gender;                                // Gender
            String s5 = textFieldDisease.getText();            // Patient_Disease
            String s6 = c1.getSelectedItem();                  // Room_Number
            String s7 = date.getText();                        // Time
            String s8 = textFieldDeposite.getText();           // Deposite
            String s9 = "Unpaid";                              // Payment_Status

            try {
                String q = "INSERT INTO Patient_info (ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Deposite, Payment_Status) " +
                        "VALUES ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "','" + s9 + "')";
                String q1 = "UPDATE Room SET Availability = 'Occupied' WHERE room_no = '" + s6 + "'";

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null, "Patient Added Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new New_Patient();
    }
}
