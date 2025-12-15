package dao;

import model.Doctor;
import java.sql.*;
import java.util.ArrayList;

public class DoctorDAO {

    public void addDoctor(Doctor d) {
        String sql = "INSERT INTO doctors(name, specialty, availability, schedule) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialty());
            ps.setString(3, d.getAvailability());
            ps.setString(4, d.getSchedule());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateDoctor(Doctor d) {
        String sql = "UPDATE doctors SET name=?, specialty=?, availability=?, schedule=? WHERE doctor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialty());
            ps.setString(3, d.getAvailability());
            ps.setString(4, d.getSchedule());
            ps.setInt(5, d.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteDoctor(int id) {
        String sql = "DELETE FROM doctors WHERE doctor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM doctors WHERE doctor_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("availability"),
                        rs.getString("schedule")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("availability"),
                        rs.getString("schedule")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // ⭐ NEW METHOD (required by AppointmentForm auto-suggest logic)
    public ArrayList<Doctor> getDoctorsBySpecialty(String specialty) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors WHERE LOWER(specialty) = LOWER(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, specialty);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialty"),
                        rs.getString("availability"),
                        rs.getString("schedule")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
