package Hospital.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class All_Patient_info extends JFrame {

    JTable table;
    JButton backButton;

    public All_Patient_info() {
        setTitle("All Patient Information");

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(250, 250, 255));

        JLabel headerLabel = new JLabel("All Patient Records", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(new Color(40, 72, 104));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setBackground(Color.WHITE);
        table.setGridColor(new Color(220, 220, 220));
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(0, 123, 255));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        buttonPanel.setBackground(new Color(250, 250, 255));

        backButton = new JButton("← Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setForeground(Color.WHITE);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setPreferredSize(new Dimension(120, 40));

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(52, 152, 219));
            }
        });

        backButton.addActionListener((ActionEvent e) -> setVisible(false));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load patient data from database
        loadPatientData();
        setVisible(true);
    }

    private void loadPatientData() {
        try {
            conn c = new conn();
            String q = "SELECT ID AS 'ID Type', Number AS 'Patient ID', Name AS 'Name', Gender AS 'Gender', " +
                    "Patient_Disease AS 'Disease', Room_Number AS 'Room No', Time AS 'Admitted Time', " +
                    "Deposite AS 'Deposit', Payment_Status AS 'Payment Status' " +
                    "FROM Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new All_Patient_info();
    }
}
