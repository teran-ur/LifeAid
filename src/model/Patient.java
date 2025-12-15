package model;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String medicalHistory;

    public Patient(int id, String name, int age, String gender, String phone, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
    }

    public Patient(String name, int age, String gender, String phone, String medicalHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getMedicalHistory() { return medicalHistory; }
}
