package ui;

import db.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;

public class LoginPage {
    public static void showLoginPage() {
        JFrame frame = new JFrame("Job Portal - Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Login"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Login to Job Portal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Username Label & Field
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userText, gbc);

        // Password Label & Field
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        JPasswordField passText = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passText, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        // Register Button
        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        // Message Label
        JLabel messageLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);

        // Login Action
        loginButton.addActionListener(e -> {
            String username = userText.getText().trim();
            String password = new String(passText.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Enter both username and password.");
                return;
            }

            User user = UserDAO.authenticateUser(username, password);
            if (user != null) {
                System.out.println("Logging in as: " + user.getUsername()); // Debugging output
                messageLabel.setText("Login successful!");
                frame.dispose();
                JobListingsPage.showJobListings(user.getUsername()); // Pass correct username
            } else {
                messageLabel.setText("Invalid credentials, try again.");
            }
        });

        // Register Action
        registerButton.addActionListener(e -> {
            frame.dispose();
            RegisterPage.showRegisterPage();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
