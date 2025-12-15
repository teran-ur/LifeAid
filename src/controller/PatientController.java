package controller;

import dao.PatientDAO;
import model.Patient;

public class PatientController {

    PatientDAO dao = new PatientDAO();

    public void addPatient(String name, int age, String gender, String phone, String history) {
        Patient p = new Patient(name, age, gender, phone, history);
        dao.addPatient(p);
    }

    public void updatePatient(int id, String name, int age, String gender, String phone, String history) {
        Patient p = new Patient(id, name, age, gender, phone, history);
        dao.updatePatient(p);
    }

    public void deletePatient(int id) {
        dao.deletePatient(id);
    }
}
