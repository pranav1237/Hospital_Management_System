package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Department extends JFrame {

    Department() {
        setTitle("Department Information");

        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(232, 245, 255));
        add(panel);

        // Header label
        JLabel header = new JLabel("DEPARTMENT DETAILS");
        header.setBounds(220, 10, 300, 30);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setForeground(new Color(30, 60, 90));
        panel.add(header);

        // Table setup
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(184, 234, 255));
        table.setBackground(Color.WHITE);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(new Color(0, 153, 204));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 620, 320);
        panel.add(scrollPane);

        // Load table data
        try {
            conn c = new conn();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back button
        JButton back = new JButton("BACK");
        back.setBounds(280, 400, 120, 35);
        back.setBackground(new Color(52, 152, 219));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener((ActionEvent e) -> setVisible(false));
        panel.add(back);

        // Frame settings
        setUndecorated(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
