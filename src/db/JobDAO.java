package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Job;

public class JobDAO {
    public static List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM jobs")) {
            while (rs.next()) {
                Job job = new Job(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("company"),
                    rs.getString("skills_required"),
                    rs.getString("description")
                );
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
