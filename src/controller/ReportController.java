package controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Doctor;
import model.Patient;

import java.util.Map;

public class ReportController {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private PatientDAO patientDAO = new PatientDAO();

    // yearMonth format "YYYY-MM" e.g. "2025-12"
    public String generateMonthlyReport(String yearMonth) {
        StringBuilder sb = new StringBuilder();
        int total = appointmentDAO.getTotalAppointmentsByMonth(yearMonth);
        sb.append("Monthly Report for ").append(yearMonth).append("\n\n");
        sb.append("Total Appointments: ").append(total).append("\n\n");

        sb.append("Appointments per Doctor:\n");
        Map<Integer, Integer> docCounts = appointmentDAO.getDoctorCountsByMonth(yearMonth);
        for (Map.Entry<Integer,Integer> e : docCounts.entrySet()) {
            Doctor d = doctorDAO.getDoctorById(e.getKey());
            String name = d != null ? d.getName() : ("Doctor ID " + e.getKey());
            sb.append("- ").append(name).append(": ").append(e.getValue()).append("\n");
        }

        sb.append("\nVisits per Patient:\n");
        Map<Integer, Integer> patCounts = appointmentDAO.getPatientCountsByMonth(yearMonth);
        for (Map.Entry<Integer,Integer> e : patCounts.entrySet()) {
            Patient p = patientDAO.getPatientById(e.getKey());
            String name = p != null ? p.getName() : ("Patient ID " + e.getKey());
            sb.append("- ").append(name).append(": ").append(e.getValue()).append("\n");
        }

        return sb.toString();
    }
}
