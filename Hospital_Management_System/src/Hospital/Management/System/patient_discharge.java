package Hospital.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class patient_discharge extends JFrame {

    Choice choice;
    JLabel lblRoom, lblInTime, lblOutTime;

    public patient_discharge() {
        setTitle("Patient Discharge");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 150, 83));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Patient Check-Out");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(Color.WHITE);
        heading.setBounds(260, 20, 300, 30);
        panel.add(heading);

        JLabel labelId = new JLabel("Patient ID:");
        JLabel labelRoom = new JLabel("Room Number:");
        JLabel labelInTime = new JLabel("In Time:");
        JLabel labelOutTime = new JLabel("Out Time:");

        JLabel[] labels = {labelId, labelRoom, labelInTime, labelOutTime};
        int y = 80;
        for (JLabel label : labels) {
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label.setForeground(Color.WHITE);
            label.setBounds(100, y, 120, 30);
            panel.add(label);
            y += 50;
        }

        choice = new Choice();
        choice.setBounds(250, 85, 200, 25);
        panel.add(choice);

        lblRoom = new JLabel();
        lblInTime = new JLabel();
        lblOutTime = new JLabel("" + new Date());

        JLabel[] values = {lblRoom, lblInTime, lblOutTime};
        y = 130;
        for (JLabel val : values) {
            val.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            val.setForeground(Color.WHITE);
            val.setBounds(250, y, 350, 30);
            panel.add(val);
            y += 50;
        }

        JButton btnCheck = new JButton("Check");
        JButton btnDischarge = new JButton("Discharge");
        JButton btnBack = new JButton("Back");

        JButton[] buttons = {btnCheck, btnDischarge, btnBack};
        int x = 130;
        for (JButton button : buttons) {
            button.setBounds(x, 300, 140, 35);
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setBackground(new Color(0, 100, 0));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(0, 128, 0));
                }

                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(0, 100, 0));
                }
            });

            panel.add(button);
            x += 170;
        }

        loadPatientIDs();

        // --- Check Button ---
        btnCheck.addActionListener(e -> {
            try {
                conn c = new conn();
                String selectedNumber = choice.getSelectedItem();
                ResultSet rs = c.statement.executeQuery("SELECT * FROM patient_info WHERE number = '" + selectedNumber + "'");
                if (rs.next()) {
                    lblRoom.setText(rs.getString("Room_Number"));
                    lblInTime.setText(rs.getString("Time"));
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- Discharge Button ---
        btnDischarge.addActionListener(e -> {
            try {
                conn c = new conn();

                // Check payment status
                ResultSet rs = c.statement.executeQuery("SELECT Payment_Status, Room_Number FROM patient_info WHERE number = '" + choice.getSelectedItem() + "'");
                if (rs.next()) {
                    String status = rs.getString("Payment_Status");
                    String roomNumber = rs.getString("Room_Number");

                    if (status == null || status.trim().equalsIgnoreCase("Unpaid")) {
                        JOptionPane.showMessageDialog(null, "Payment is not completed!\nPlease complete the payment before discharge.", "Payment Pending", JOptionPane.WARNING_MESSAGE);
                    } else if (status.trim().equalsIgnoreCase("Paid")) {
                        // Proceed with discharge
                        c.statement.executeUpdate("DELETE FROM patient_info WHERE number = '" + choice.getSelectedItem() + "'");
                        c.statement.executeUpdate("UPDATE room SET Availability = 'Available' WHERE room_no ='" + roomNumber + "'");
                        JOptionPane.showMessageDialog(null, "Patient discharged successfully.");
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown payment status: '" + status + "'. Please check manually.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not found in database.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });


        btnBack.addActionListener(e -> setVisible(false));

        // Final frame setup
        setUndecorated(true);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
    }

    private void loadPatientIDs() {
        try {
            choice.removeAll();
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT number FROM patient_info");
            while (rs.next()) {
                choice.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        loadPatientIDs();
        lblRoom.setText("");
        lblInTime.setText("");
        lblOutTime.setText("" + new Date());
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
