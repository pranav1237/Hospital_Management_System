package Hospital.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    public update_patient_details() {
        setTitle("Update Patient Details");

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 168, 150));
        panel.setLayout(null);
        add(panel);

        // Heading
        JLabel title = new JLabel("Update Patient Details");
        title.setBounds(80, 20, 400, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Patient Choice Dropdown
        JLabel labelName = new JLabel("Patient Name:");
        labelName.setBounds(40, 80, 150, 25);
        labelName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        Choice patientChoice = new Choice();
        patientChoice.setBounds(200, 80, 180, 25);
        panel.add(patientChoice);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info");
            while (rs.next()) {
                patientChoice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Form Fields
        String[] labels = {"Room Number:", "In Time:", "Amount Paid (Rs):", "Pending Amount (Rs):"};
        int yPos = 130;
        JTextField[] fields = new JTextField[4];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(40, yPos, 180, 25);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setForeground(Color.WHITE);
            panel.add(label);

            JTextField field = new JTextField();
            field.setBounds(200, yPos, 180, 25);
            fields[i] = field;
            panel.add(field);

            yPos += 40;
        }

        JTextField roomField = fields[0];
        JTextField inTimeField = fields[1];
        JTextField amountField = fields[2];
        JTextField pendingField = fields[3];
        pendingField.setEditable(false);

        // Image Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(450, 90, 350, 300);
        panel.add(imageLabel);

        // Buttons
        String[] btnTexts = {"UPDATE", "BACK", "CHECK"};
        JButton[] buttons = new JButton[btnTexts.length];
        int[] xPos = {40, 160, 280};

        for (int i = 0; i < btnTexts.length; i++) {
            JButton button = new JButton(btnTexts[i]);
            button.setBounds(xPos[i], 390, 100, 35);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setBackground(new Color(30, 50, 60));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            int index = i;

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(50, 80, 90));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(30, 50, 60));
                }
            });

            buttons[i] = button;
            panel.add(button);
        }

        // Action: CHECK
        buttons[2].addActionListener((ActionEvent e) -> {
            String name = patientChoice.getSelectedItem();
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info WHERE Name = '" + name + "'");
                while (rs.next()) {
                    roomField.setText(rs.getString("Room_Number"));
                    inTimeField.setText(rs.getString("Time"));
                    amountField.setText(rs.getString("Deposite"));
                }

                String paidText = amountField.getText().trim();
                if (paidText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount paid before checking.");
                    return;
                }

                ResultSet rs2 = c.statement.executeQuery("SELECT * FROM room WHERE room_no = '" + roomField.getText() + "'");
                while (rs2.next()) {
                    String price = rs2.getString("Price");
                    int remaining = Integer.parseInt(price) - Integer.parseInt(paidText);
                    pendingField.setText("" + remaining);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Action: UPDATE
        buttons[0].addActionListener((ActionEvent e) -> {
            String name = patientChoice.getSelectedItem();
            String room = roomField.getText();
            String time = inTimeField.getText();
            String paid = amountField.getText().trim();

            if (paid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Amount paid field cannot be empty.");
                return;
            }

            try {
                conn c = new conn();

                ResultSet rs = c.statement.executeQuery("SELECT Price FROM room WHERE room_no = '" + room + "'");
                int totalPrice = 0;
                if (rs.next()) {
                    totalPrice = Integer.parseInt(rs.getString("Price"));
                }

                int paidAmt = Integer.parseInt(paid);
                String status = (paidAmt >= totalPrice) ? "Paid" : "Unpaid";

                c.statement.executeUpdate("UPDATE patient_info SET Room_Number = '" + room + "', Time = '" + time + "', Deposite = '" + paid + "', Payment_Status = '" + status + "' WHERE Name = '" + name + "'");

                JOptionPane.showMessageDialog(null, "Updated Successfully!");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Action: BACK
        buttons[1].addActionListener((ActionEvent e) -> setVisible(false));

        // Frame Settings
        setUndecorated(true);
        setSize(950, 500);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
