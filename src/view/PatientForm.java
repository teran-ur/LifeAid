package view;

import controller.PatientController;
import dao.PatientDAO;
import model.Patient;

import javax.swing.*;

public class PatientForm extends JFrame {

    public PatientForm() {
        setTitle("Patient Management");
        setSize(450, 480);
        setLayout(null);

        JLabel lblId = new JLabel("Patient ID (for load/update/delete):");
        JTextField txtId = new JTextField();

        JLabel lblName = new JLabel("Name:");
        JLabel lblAge = new JLabel("Age:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblPhone = new JLabel("Phone:");
        JLabel lblHistory = new JLabel("Medical History:");

        JTextField txtName = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtGender = new JTextField();
        JTextField txtPhone = new JTextField();
        JTextField txtHistory = new JTextField();

        JButton btnLoad = new JButton("Load");
        JButton btnSave = new JButton("Add New");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        lblId.setBounds(30, 20, 220, 25);
        txtId.setBounds(250, 20, 150, 25);

        lblName.setBounds(30, 60, 120, 25);
        txtName.setBounds(150, 60, 250, 25);

        lblAge.setBounds(30, 100, 120, 25);
        txtAge.setBounds(150, 100, 250, 25);

        lblGender.setBounds(30, 140, 120, 25);
        txtGender.setBounds(150, 140, 250, 25);

        lblPhone.setBounds(30, 180, 120, 25);
        txtPhone.setBounds(150, 180, 250, 25);

        lblHistory.setBounds(30, 220, 120, 25);
        txtHistory.setBounds(150, 220, 250, 25);

        btnLoad.setBounds(30, 270, 100, 30);
        btnSave.setBounds(140, 270, 100, 30);
        btnUpdate.setBounds(250, 270, 100, 30);
        btnDelete.setBounds(140, 320, 100, 30);

        add(lblId); add(txtId);
        add(lblName); add(txtName);
        add(lblAge); add(txtAge);
        add(lblGender); add(txtGender);
        add(lblPhone); add(txtPhone);
        add(lblHistory); add(txtHistory);
        add(btnLoad); add(btnSave); add(btnUpdate); add(btnDelete);

        PatientController controller = new PatientController();
        PatientDAO dao = new PatientDAO();

        btnLoad.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Patient p = dao.getPatientById(id);
                if (p == null) {
                    JOptionPane.showMessageDialog(null, "Patient not found");
                    return;
                }
                txtName.setText(p.getName());
                txtAge.setText(String.valueOf(p.getAge()));
                txtGender.setText(p.getGender());
                txtPhone.setText(p.getPhone());
                txtHistory.setText(p.getMedicalHistory());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter valid ID");
            }
        });

        btnSave.addActionListener(e -> {
            try {
                controller.addPatient(txtName.getText(), Integer.parseInt(txtAge.getText()),
                        txtGender.getText(), txtPhone.getText(), txtHistory.getText());
                JOptionPane.showMessageDialog(null, "Patient added!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Check input"); }
        });

        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                controller.updatePatient(id, txtName.getText(), Integer.parseInt(txtAge.getText()),
                        txtGender.getText(), txtPhone.getText(), txtHistory.getText());
                JOptionPane.showMessageDialog(null, "Patient updated!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Check input"); }
        });

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirm = JOptionPane.showConfirmDialog(null, "Delete patient id " + id + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deletePatient(id);
                    JOptionPane.showMessageDialog(null, "Patient deleted!");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Enter valid ID"); }
        });

        setVisible(true);
    }
}
