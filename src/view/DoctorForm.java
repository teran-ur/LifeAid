package view;

import controller.DoctorController;
import dao.DoctorDAO;
import model.Doctor;

import javax.swing.*;

public class DoctorForm extends JFrame {

    public DoctorForm() {
        setTitle("Doctor Management");
        setSize(500, 420);
        setLayout(null);

        JLabel lblId = new JLabel("Doctor ID (for load/update/delete):");
        JTextField txtId = new JTextField();

        JLabel lblName = new JLabel("Name:");
        JLabel lblSpec = new JLabel("Specialty:");
        JLabel lblAvail = new JLabel("Availability:");
        JLabel lblSched = new JLabel("Schedule:");

        JTextField txtName = new JTextField();
        JTextField txtSpec = new JTextField();
        JTextField txtAvail = new JTextField();
        JTextField txtSched = new JTextField();

        JButton btnLoad = new JButton("Load");
        JButton btnSave = new JButton("Add New");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");

        lblId.setBounds(30, 20, 260, 25);
        txtId.setBounds(300, 20, 140, 25);

        lblName.setBounds(30, 60, 120, 25);
        txtName.setBounds(150, 60, 290, 25);

        lblSpec.setBounds(30, 100, 120, 25);
        txtSpec.setBounds(150, 100, 290, 25);

        lblAvail.setBounds(30, 140, 120, 25);
        txtAvail.setBounds(150, 140, 290, 25);

        lblSched.setBounds(30, 180, 120, 25);
        txtSched.setBounds(150, 180, 290, 25);

        btnLoad.setBounds(30, 230, 100, 30);
        btnSave.setBounds(140, 230, 100, 30);
        btnUpdate.setBounds(250, 230, 100, 30);
        btnDelete.setBounds(360, 230, 100, 30);

        add(lblId); add(txtId);
        add(lblName); add(txtName);
        add(lblSpec); add(txtSpec);
        add(lblAvail); add(txtAvail);
        add(lblSched); add(txtSched);
        add(btnLoad); add(btnSave); add(btnUpdate); add(btnDelete);

        DoctorController controller = new DoctorController();
        DoctorDAO dao = new DoctorDAO();

        btnLoad.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Doctor d = dao.getDoctorById(id);
                if (d == null) { JOptionPane.showMessageDialog(null, "Doctor not found"); return; }
                txtName.setText(d.getName());
                txtSpec.setText(d.getSpecialty());
                txtAvail.setText(d.getAvailability());
                txtSched.setText(d.getSchedule());
            } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(null, "Enter valid ID"); }
        });

        btnSave.addActionListener(e -> {
            controller.addDoctor(txtName.getText(), txtSpec.getText(), txtAvail.getText(), txtSched.getText());
            JOptionPane.showMessageDialog(null, "Doctor added!");
        });

        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                controller.updateDoctor(id, txtName.getText(), txtSpec.getText(), txtAvail.getText(), txtSched.getText());
                JOptionPane.showMessageDialog(null, "Doctor updated!");
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Check input"); }
        });

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirm = JOptionPane.showConfirmDialog(null, "Delete doctor id " + id + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteDoctor(id);
                    JOptionPane.showMessageDialog(null, "Doctor deleted!");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Enter valid ID"); }
        });

        setVisible(true);
    }
}
