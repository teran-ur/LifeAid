package controller;

import dao.AppointmentDAO;
import model.Appointment;
import java.util.ArrayList;

public class AppointmentController {

    AppointmentDAO dao = new AppointmentDAO();

    public void addAppointment(int patientId, int doctorId, String date, String time) {
        Appointment a = new Appointment(patientId, doctorId, date, time);
        dao.addAppointment(a);
    }

    public ArrayList<Appointment> getAllAppointments() {
        return dao.getAllAppointments();
    }

    public void updateStatus(int appointmentId, String status) {
        dao.updateStatus(appointmentId, status);
    }
}
