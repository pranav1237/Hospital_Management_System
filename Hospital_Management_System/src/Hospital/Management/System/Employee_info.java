package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Employee_info extends JFrame {

    JTable table;

    Employee_info() {
        setTitle("Employee Information");

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(240, 248, 255));
        panel.setLayout(null);
        add(panel);

        // Header Label
        JLabel header = new JLabel("EMPLOYEE INFORMATION");
        header.setBounds(330, 10, 400, 30);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setForeground(new Color(40, 60, 90));
        panel.add(header);

        // Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setBackground(Color.WHITE);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(new Color(0, 153, 204));
        tableHeader.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 920, 400);
        panel.add(scrollPane);

        try {
            conn c = new conn();
            String q = "select * from EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back Button
        JButton back = new JButton("BACK");
        back.setBounds(430, 490, 120, 35);
        back.setBackground(new Color(52, 152, 219));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener((ActionEvent e) -> setVisible(false));
        panel.add(back);

        // Frame Settings
        setUndecorated(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
