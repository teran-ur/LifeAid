package controller;

import javax.swing.*;

public class NotificationController {

    public void notifyPatient(String message) {
        // GUI popup
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, message, "Patient Notification", JOptionPane.INFORMATION_MESSAGE));
        // Also log
        System.out.println("[PATIENT NOTIFICATION] " + message);
    }

    public void notifyDoctor(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, message, "Doctor Notification", JOptionPane.INFORMATION_MESSAGE));
        System.out.println("[DOCTOR NOTIFICATION] " + message);
    }
}
