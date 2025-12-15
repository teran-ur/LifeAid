package controller;

import dao.DoctorDAO;
import model.Doctor;

public class DoctorController {

    DoctorDAO dao = new DoctorDAO();

    public void addDoctor(String name, String specialty, String availability, String schedule) {
        Doctor d = new Doctor(name, specialty, availability, schedule);
        dao.addDoctor(d);
    }

    public void updateDoctor(int id, String name, String specialty, String availability, String schedule) {
        Doctor d = new Doctor(id, name, specialty, availability, schedule);
        dao.updateDoctor(d);
    }

    public void deleteDoctor(int id) {
        dao.deleteDoctor(id);
    }
}
