package view;

import controller.AppointmentController;
import controller.DoctorAssignmentController;
import dao.AppointmentDAO;
import dao.DoctorDAO;
import model.Doctor;

import javax.swing.*;
import java.util.ArrayList;

public class AppointmentForm extends JFrame {

    public AppointmentForm() {
        setTitle("Schedule Appointment");
        setSize(420, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblPID = new JLabel("Patient ID:");
        JLabel lblSpec = new JLabel("Specialty:");
        JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
        JLabel lblTime = new JLabel("Time (e.g. 10 AM):");

        JTextField txtPID = new JTextField();
        JTextField txtSpec = new JTextField();
        JTextField txtDate = new JTextField();
        JTextField txtTime = new JTextField();

        JButton btnSave = new JButton("Schedule Appointment");

        lblPID.setBounds(30, 30, 150, 25);
        txtPID.setBounds(200, 30, 170, 25);

        lblSpec.setBounds(30, 70, 150, 25);
        txtSpec.setBounds(200, 70, 170, 25);

        lblDate.setBounds(30, 110, 150, 25);
        txtDate.setBounds(200, 110, 170, 25);

        lblTime.setBounds(30, 150, 150, 25);
        txtTime.setBounds(200, 150, 170, 25);

        btnSave.setBounds(100, 220, 200, 35);

        btnSave.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(txtPID.getText());
                String specialty = txtSpec.getText().trim();
                String date = txtDate.getText().trim();
                String time = txtTime.getText().trim();

                if (specialty.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                DoctorAssignmentController dac = new DoctorAssignmentController();
                Doctor assigned = dac.assignDoctor(specialty, time);

                if (assigned == null) {
                    JOptionPane.showMessageDialog(null,
                            "No doctor found with that specialty.");
                    return;
                }

                AppointmentDAO apDAO = new AppointmentDAO();
                DoctorDAO doctorDAO = new DoctorDAO();
                ArrayList<Doctor> sameSpec = doctorDAO.getDoctorsBySpecialty(specialty);

                // 1️⃣ Check assigned doctor conflict
                if (!apDAO.isDoctorAvailable(assigned.getId(), date, time)) {

                    // 2️⃣ Try to auto-suggest next free doctor
                    for (Doctor d : sameSpec) {
                        if (apDAO.isDoctorAvailable(d.getId(), date, time)) {
                            assigned = d;
                            break;
                        }
                    }

                    // 3️⃣ Still no doctor free?
                    if (!apDAO.isDoctorAvailable(assigned.getId(), date, time)) {
                        JOptionPane.showMessageDialog(null,
                                "All doctors for this specialty are busy at that time.");
                        return;
                    }
                }

                // 4️⃣ Save appointment
                AppointmentController ac = new AppointmentController();
                ac.addAppointment(patientId, assigned.getId(), date, time);

                JOptionPane.showMessageDialog(null,
                        "Appointment scheduled with Dr. " + assigned.getName());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong!");
                ex.printStackTrace();
            }
        });

        add(lblPID); add(txtPID);
        add(lblSpec); add(txtSpec);
        add(lblDate); add(txtDate);
        add(lblTime); add(txtTime);
        add(btnSave);

        setVisible(true);
    }
}
