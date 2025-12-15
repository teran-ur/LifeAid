package view;

import controller.AppointmentController;
import controller.NotificationController;
import dao.PatientDAO;
import dao.DoctorDAO;
import model.Appointment;
import model.Patient;
import model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AppointmentManagementForm extends JFrame {

    JTable table;
    DefaultTableModel tableModel;
    AppointmentController controller = new AppointmentController();
    NotificationController notifier = new NotificationController();

    public AppointmentManagementForm() {
        setTitle("Manage Appointments");
        setSize(800, 400);
        setLayout(null);

        String[] cols = {"ID", "Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 10, 760, 250);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnComplete = new JButton("Mark Completed");
        JButton btnCancel = new JButton("Mark Cancelled");
        JButton btnDelay = new JButton("Mark Delayed");

        btnRefresh.setBounds(10, 280, 120, 30);
        btnComplete.setBounds(150, 280, 150, 30);
        btnCancel.setBounds(320, 280, 150, 30);
        btnDelay.setBounds(490, 280, 150, 30);

        add(sp); add(btnRefresh); add(btnComplete); add(btnCancel); add(btnDelay);

        btnRefresh.addActionListener(e -> load());
        btnComplete.addActionListener(e -> changeStatus("Completed"));
        btnCancel.addActionListener(e -> changeStatus("Cancelled"));
        btnDelay.addActionListener(e -> changeStatus("Delayed"));

        load();
        setVisible(true);
    }

    private void load() {
        tableModel.setRowCount(0);
        ArrayList<Appointment> list = controller.getAllAppointments();
        PatientDAO pdao = new PatientDAO();
        DoctorDAO ddao = new DoctorDAO();
        for (Appointment a : list) {
            Patient p = pdao.getPatientById(a.getPatientId());
            Doctor d = ddao.getDoctorById(a.getDoctorId());
            String pname = p != null ? p.getName() : ("ID " + a.getPatientId());
            String dname = d != null ? d.getName() : ("ID " + a.getDoctorId());
            tableModel.addRow(new Object[]{a.getId(), a.getPatientId(), pname, a.getDoctorId(), dname, a.getDate(), a.getTime(), a.getStatus()});
        }
    }

    private void changeStatus(String status) {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(null, "Select an appointment"); return; }
        int appointmentId = (int) tableModel.getValueAt(row, 0);
        controller.updateStatus(appointmentId, status);
        // send notifications
        int patientId = (int) tableModel.getValueAt(row, 1);
        int doctorId = (int) tableModel.getValueAt(row, 3);
        notifier.notifyPatient("Your appointment (ID " + appointmentId + ") is now: " + status);
        notifier.notifyDoctor("Appointment (ID " + appointmentId + ") status changed to: " + status);
        load();
    }
}
