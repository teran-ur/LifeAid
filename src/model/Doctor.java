package model;

public class Doctor {
    private int id;
    private String name;
    private String specialty;
    private String availability;
    private String schedule;

    public Doctor(int id, String name, String specialty, String availability, String schedule) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.availability = availability;
        this.schedule = schedule;
    }

    public Doctor(String name, String specialty, String availability, String schedule) {
        this.name = name;
        this.specialty = specialty;
        this.availability = availability;
        this.schedule = schedule;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getAvailability() { return availability; }
    public String getSchedule() { return schedule; }
}
