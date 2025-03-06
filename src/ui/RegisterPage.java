package ui;

import db.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;

public class RegisterPage {
    public static void showRegisterPage() {
        JFrame frame = new JFrame("Register");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Register"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailText = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneText = new JTextField(15);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressText = new JTextField(30);
        JLabel educationLabel = new JLabel("Education:");
        JTextField educationText = new JTextField(30);
        JLabel skillsLabel = new JLabel("Skills:");
        JTextField skillsText = new JTextField(30);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(20);
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(userLabel, gbc);
        gbc.gridx = 1; panel.add(userText, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(emailLabel, gbc);
        gbc.gridx = 1; panel.add(emailText, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(phoneLabel, gbc);
        gbc.gridx = 1; panel.add(phoneText, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(addressLabel, gbc);
        gbc.gridx = 1; panel.add(addressText, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(educationLabel, gbc);
        gbc.gridx = 1; panel.add(educationText, gbc);
        gbc.gridx = 0; gbc.gridy = 5; panel.add(skillsLabel, gbc);
        gbc.gridx = 1; panel.add(skillsText, gbc);
        gbc.gridx = 0; gbc.gridy = 6; panel.add(passLabel, gbc);
        gbc.gridx = 1; panel.add(passText, gbc);
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2; panel.add(registerButton, gbc);
        gbc.gridy = 8; panel.add(backButton, gbc);

        registerButton.addActionListener(e -> {
            String username = userText.getText().trim();
            String password = new String(passText.getPassword());
            String email = emailText.getText().trim();
            String phone = phoneText.getText().trim();
            String address = addressText.getText().trim();
            String education = educationText.getText().trim();
            String skills = skillsText.getText().trim();

            // Validation patterns
            if (!username.matches("^[a-zA-Z]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid username! Only letters allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!email.matches("^[a-z0-9@.]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid email! Only lowercase letters, numbers, '@', and '.' allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!phone.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid phone! Only numbers allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!address.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid address! Only letters allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!education.matches("^[a-zA-Z ]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid education! Only letters allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!skills.matches("^[a-zA-Z, ]+$")) {
                JOptionPane.showMessageDialog(frame, "Invalid skills! Only letters and commas allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check empty fields
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                address.isEmpty() || education.isEmpty() || skills.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(username, password, email, phone, address, education, skills);
            if (UserDAO.registerUser(newUser)) {
                JOptionPane.showMessageDialog(frame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Registration Failed! User may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            LoginPage.showLoginPage();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
