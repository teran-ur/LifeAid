package view;

import controller.ReportController;

import javax.swing.*;

public class ReportView extends JFrame {

    public ReportView() {
        setTitle("Monthly Reports");
        setSize(600, 500);
        setLayout(null);

        JLabel lblYM = new JLabel("Month (YYYY-MM):");
        JTextField txtYM = new JTextField();
        JButton btnGen = new JButton("Generate");
        JTextArea area = new JTextArea();
        JScrollPane sp = new JScrollPane(area);

        lblYM.setBounds(20, 20, 120, 25);
        txtYM.setBounds(150, 20, 120, 25);
        btnGen.setBounds(290, 20, 120, 25);
        sp.setBounds(20, 60, 540, 380);

        add(lblYM); add(txtYM); add(btnGen); add(sp);

        ReportController rc = new ReportController();
        btnGen.addActionListener(e -> {
            String ym = txtYM.getText().trim();
            if (ym.matches("\\d{4}-\\d{2}")) {
                String report = rc.generateMonthlyReport(ym);
                area.setText(report);
            } else {
                JOptionPane.showMessageDialog(null, "Enter month in YYYY-MM");
            }
        });

        setVisible(true);
    }
}
