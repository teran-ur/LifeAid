package dao;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTables() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS patients (
                    patient_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    age INTEGER,
                    gender TEXT,
                    phone TEXT,
                    medical_history TEXT
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS doctors (
                    doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    specialty TEXT NOT NULL,
                    availability TEXT,
                    schedule TEXT
                );
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS appointments (
                    appointment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    patient_id INTEGER,
                    doctor_id INTEGER,
                    date TEXT,
                    time TEXT,
                    status TEXT DEFAULT 'Scheduled',
                    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
                );
            """);

            System.out.println("Database tables created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
