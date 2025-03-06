package models;

public class Job {
    private int id;
    private String title;
    private String company;
    private String skillsRequired;
    private String description;

    public Job(int id, String title, String company, String skillsRequired, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.skillsRequired = skillsRequired;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getSkillsRequired() { return skillsRequired; }
    public String getDescription() { return description; }
}
