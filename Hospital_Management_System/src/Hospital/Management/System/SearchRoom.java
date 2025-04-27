package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    SearchRoom() {
        setTitle("Search Room");

        // Panel Setup
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 168, 150));
        panel.setLayout(null);
        add(panel);

        // Title
        JLabel title = new JLabel("Search Room");
        title.setBounds(240, 20, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Status Label
        JLabel status = new JLabel("Room Status:");
        status.setBounds(60, 70, 120, 25);
        status.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        status.setForeground(Color.WHITE);
        panel.add(status);

        // Dropdown for Room Status
        choice = new Choice();
        choice.setBounds(180, 70, 150, 25);
        choice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        // Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(0, 138, 120));
        table.setGridColor(new Color(220, 220, 220));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 650, 230);
        panel.add(scrollPane);

        // Table Column Headers
        JLabel[] headers = {
                new JLabel("Room No"), new JLabel("Availability"),
                new JLabel("Price"), new JLabel("Bed Type")
        };
        int[] xPositions = {30, 180, 340, 490};

        for (int i = 0; i < headers.length; i++) {
            headers[i].setBounds(xPositions[i], 120, 120, 25);
            headers[i].setFont(new Font("Segoe UI", Font.BOLD, 14));
            headers[i].setForeground(Color.WHITE);
            panel.add(headers[i]);
        }

        // Load all rooms initially
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(170, 400, 120, 35);
        styleButton(searchBtn);
        panel.add(searchBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(330, 400, 120, 35);
        styleButton(backBtn);
        panel.add(backBtn);

        // Action Listeners
        searchBtn.addActionListener((ActionEvent e) -> {
            String q = "SELECT * FROM room WHERE Availability = '" + choice.getSelectedItem() + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> setVisible(false));

        // Frame Settings
        setUndecorated(true);
        setSize(700, 500);
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
        new SearchRoom();
    }
}
