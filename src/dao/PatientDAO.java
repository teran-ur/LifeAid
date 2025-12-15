package dao;

import model.Patient;
import java.sql.*;
import java.util.ArrayList;

public class PatientDAO {

    public void addPatient(Patient p) {
        String sql = "INSERT INTO patients(name, age, gender, phone, medical_history) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getMedicalHistory());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updatePatient(Patient p) {
        String sql = "UPDATE patients SET name=?, age=?, gender=?, phone=?, medical_history=? WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getMedicalHistory());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE patient_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("medical_history")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("medical_history")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
