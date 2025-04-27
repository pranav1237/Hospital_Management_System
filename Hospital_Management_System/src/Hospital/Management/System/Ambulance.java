package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Ambulance extends JFrame {

    Ambulance() {
        setTitle("Ambulance Management");

        // Panel Setup
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 169, 157));
        panel.setLayout(null);
        add(panel);

        // Title
        JLabel title = new JLabel("Ambulance Availability");
        title.setBounds(320, 15, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Table Setup
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(0, 134, 122));
        table.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 850, 400);
        panel.add(scrollPane);

        // Load Data into Table
        try {
            conn c = new conn();
            String q = "SELECT * FROM Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Table Header Labels (Optional, if you want static headers on panel)
        String[] headers = {"Name", "Gender", "Car Name", "Available", "Location"};
        int[] xPositions = {25, 200, 376, 560, 730};

        for (int i = 0; i < headers.length; i++) {
            JLabel headerLabel = new JLabel(headers[i]);
            headerLabel.setBounds(xPositions[i], 50, 100, 14);
            headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
            headerLabel.setForeground(Color.WHITE);
            panel.add(headerLabel);
        }

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(380, 500, 140, 35);
        styleButton(backBtn);
        panel.add(backBtn);

        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        // Frame Settings
        setUndecorated(true);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(30, 50, 60));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 80, 90));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 50, 60));
            }
        });
    }

    public static void main(String[] args) {
        new Ambulance();
    }
}
