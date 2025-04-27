package Hospital.Management.System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton loginButton, cancelButton;

    Login() {
        setTitle("Hospital Management System - Login");
        setSize(800, 400);
        setLocationRelativeTo(null); // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel with image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(350, 400));
        imagePanel.setLayout(new BorderLayout());

        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/Hospital_logo.png"));
            Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Image not found.");
        }

        // Right panel with form
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(245, 252, 255));
        formPanel.setLayout(null);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 30, 100, 25);
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(usernameLabel);

        textField = new JTextField();
        textField.setBounds(50, 60, 220, 35);
        textField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textField.setBorder(new LineBorder(new Color(173, 216, 230), 2, true));
        formPanel.add(textField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 110, 100, 25);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formPanel.add(passwordLabel);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(50, 140, 220, 35);
        jPasswordField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jPasswordField.setBorder(new LineBorder(new Color(173, 216, 230), 2, true));
        formPanel.add(jPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 200, 100, 35);
        loginButton.setBackground(new Color(66, 135, 245));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new LineBorder(new Color(66, 135, 245), 2, true));
        loginButton.addActionListener(this);
        formPanel.add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(170, 200, 100, 35);
        cancelButton.setBackground(new Color(245, 66, 66));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new LineBorder(new Color(245, 66, 66), 2, true));
        cancelButton.addActionListener(this);
        formPanel.add(cancelButton);

        add(imagePanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                conn c = new conn();
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());

                String q = "SELECT * FROM login WHERE ID = '" + user + "' AND PW = '" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
