package dao;

import model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentDAO {

    // Add a new appointment
    public void addAppointment(Appointment a) {
        String sql = "INSERT INTO appointments(patient_id, doctor_id, date, time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getDate());
            ps.setString(4, a.getTime());
            ps.setString(5, a.getStatus());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Return all appointments
    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY date, time";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Update appointment status
    public void updateStatus(int appointmentId, String newStatus) {
        String sql = "UPDATE appointments SET status=? WHERE appointment_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, appointmentId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ============================================
    // NEW: Check if doctor is free at given date/time
    // ============================================
    public boolean isDoctorAvailable(int doctorId, String date, String time) {
        String sql = "SELECT * FROM appointments WHERE doctor_id=? AND date=? AND time=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setString(2, date);
            ps.setString(3, time);

            ResultSet rs = ps.executeQuery();

            // If result exists → doctor already has appointment at that slot
            return !rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ============================================
    // Monthly Report Functions
    // ============================================

    // Appointments per doctor
    public Map<Integer, Integer> getDoctorCountsByMonth(String yearMonth) {
        Map<Integer, Integer> map = new HashMap<>();
        String sql = "SELECT doctor_id, COUNT(*) as cnt FROM appointments WHERE substr(date,1,7)=? GROUP BY doctor_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, yearMonth);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("doctor_id"), rs.getInt("cnt"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return map;
    }

    // Total appointments for month
    public int getTotalAppointmentsByMonth(String yearMonth) {
        String sql = "SELECT COUNT(*) as cnt FROM appointments WHERE substr(date,1,7)=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, yearMonth);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("cnt");
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    // Visits per patient for month
    public Map<Integer, Integer> getPatientCountsByMonth(String yearMonth) {
        Map<Integer, Integer> map = new HashMap<>();
        String sql = "SELECT patient_id, COUNT(*) as cnt FROM appointments WHERE substr(date,1,7)=? GROUP BY patient_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, yearMonth);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("patient_id"), rs.getInt("cnt"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return map;
    }
}
