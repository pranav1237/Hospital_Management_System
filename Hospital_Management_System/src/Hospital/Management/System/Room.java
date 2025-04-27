package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Room extends JFrame {
    JTable table;

    Room() {
        setTitle("Room Information");

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(245, 248, 255));
        panel.setLayout(null);
        add(panel);

        // Room Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ROOM.png"));
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(680, 50, 180, 180);
        panel.add(label);

        // Header Label
        JLabel header = new JLabel("ROOM DETAILS");
        header.setBounds(300, 10, 400, 40);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(new Color(40, 60, 90));
        panel.add(header);

        // Table Setup
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(220, 220, 220));
        table.setSelectionBackground(new Color(180, 215, 255));

        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headerTable.setBackground(new Color(100, 150, 255));
        headerTable.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 580, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        panel.add(scrollPane);

        // Load data into table
        try {
            conn c = new conn();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back Button
        JButton back = new JButton("Back");
        back.setBounds(250, 460, 150, 35);
        back.setBackground(new Color(52, 152, 219));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(back);

        back.addActionListener((ActionEvent e) -> setVisible(false));

        // Frame settings
        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}
