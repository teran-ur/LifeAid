package controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import model.Doctor;

import java.util.ArrayList;

public class DoctorAssignmentController {

    public Doctor assignDoctor(String specialty, String time) {
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();

        ArrayList<Doctor> doctors = doctorDAO.getAllDoctors();

        // 1️⃣ MATCH specialty + availability + NOT BOOKED
        for (Doctor d : doctors) {
            boolean specMatch = d.getSpecialty().equalsIgnoreCase(specialty);
            boolean availMatch = d.getAvailability().toLowerCase().contains(time.toLowerCase());
            boolean free = appointmentDAO.isDoctorAvailable(d.getId(), null, null); // Check later

            if (specMatch && availMatch)
                return d; // tentatively return, conflicts will be checked in AppointmentForm
        }

        // 2️⃣ MATCH specialty only, fallback to anyone free
        for (Doctor d : doctors) {
            if (d.getSpecialty().equalsIgnoreCase(specialty)) {
                return d; // conflict checked in AppointmentForm
            }
        }

        return null; // No doctor in this specialty at all
    }
}
