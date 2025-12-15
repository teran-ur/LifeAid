package view;

import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("LifeAid - Main Menu");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnPatients = new JButton("Manage Patients");
        JButton btnDoctors = new JButton("Manage Doctors");
        JButton btnApp = new JButton("Schedule Appointment");
        JButton btnManageAppointments = new JButton("Manage Appointments");
        JButton btnReports = new JButton("Monthly Reports");

        // Button positions
        btnPatients.setBounds(100, 40, 200, 30);
        btnDoctors.setBounds(100, 90, 200, 30);
        btnApp.setBounds(100, 140, 200, 30);
        btnManageAppointments.setBounds(100, 190, 200, 30);
        btnReports.setBounds(100, 240, 200, 30);

        // Button actions
        btnPatients.addActionListener(e -> new PatientForm());
        btnDoctors.addActionListener(e -> new DoctorForm());
        btnApp.addActionListener(e -> new AppointmentForm());
        btnManageAppointments.addActionListener(e -> new AppointmentManagementForm());
        btnReports.addActionListener(e -> new ReportView());

        // Add to window
        add(btnPatients);
        add(btnDoctors);
        add(btnApp);
        add(btnManageAppointments);
        add(btnReports);

        setVisible(true);
    }
}
