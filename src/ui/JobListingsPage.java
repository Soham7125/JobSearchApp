package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JobListingsPage {
    private static List<String> appliedJobs = new ArrayList<>(); // Stores applied jobs

    public static void showJobListings(String username) {
        JFrame jobFrame = new JFrame("Job Listings");
        jobFrame.setSize(600, 500);
        jobFrame.setLayout(new GridLayout(0, 1));

        // Static job listings
        String[][] jobs = {
            {"Software Engineer", "Google", "Java, Python, SQL", "Develop and maintain software applications."},
            {"Data Analyst", "Amazon", "SQL, Excel, Python", "Analyze data trends and generate reports."},
            {"Project Manager", "Microsoft", "Leadership, Agile, Scrum", "Manage software development projects."},
            {"Cyber Security Analyst", "IBM", "Network Security, Ethical Hacking", "Protect company data from cyber threats."},
            {"UI/UX Designer", "Apple", "Figma, Adobe XD, HTML/CSS", "Design intuitive user interfaces."}
        };

        for (String[] job : jobs) {
            JPanel jobPanel = new JPanel(new GridLayout(5, 1));
            jobPanel.setBorder(BorderFactory.createTitledBorder(job[0]));
            jobPanel.add(new JLabel("Company: " + job[1]));
            jobPanel.add(new JLabel("Required Skills: " + job[2]));
            jobPanel.add(new JLabel("Description: " + job[3]));

            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(e -> {
                String jobTitle = job[0] + " at " + job[1];
                if (!appliedJobs.contains(jobTitle)) {
                    appliedJobs.add(jobTitle); // Store applied job
                    JOptionPane.showMessageDialog(jobFrame, "Applied Successfully for " + jobTitle);
                } else {
                    JOptionPane.showMessageDialog(jobFrame, "You have already applied for this job!");
                }
            });

            jobPanel.add(applyButton);
            jobFrame.add(jobPanel);
        }

        // User Profile Button
        JButton profileButton = new JButton("User Profile");
        profileButton.addActionListener(e -> showUserProfile(username));
        jobFrame.add(profileButton);

        jobFrame.setVisible(true);
    }

    // Displays applied jobs
    private static void showUserProfile(String username) {
    JFrame profileFrame = new JFrame(username + "'s Profile");
    profileFrame.setSize(400, 300);
    profileFrame.setLayout(new GridLayout(0, 1));

    // Show correct username
    JLabel nameLabel = new JLabel("Applicant: " + username);  
    profileFrame.add(nameLabel);

    if (appliedJobs.isEmpty()) {
        profileFrame.add(new JLabel("No jobs applied yet."));
    } else {
        DefaultListModel<String> jobListModel = new DefaultListModel<>();
        for (String job : appliedJobs) {
            jobListModel.addElement(job);
        }
        JList<String> jobList = new JList<>(jobListModel);
        profileFrame.add(new JScrollPane(jobList));
    }

    profileFrame.setVisible(true);
}

}
