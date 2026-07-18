package manager;

import java.util.ArrayList;
import model.PublicAlert;

public class AlertManager {

    private ArrayList<PublicAlert> alerts;

    public AlertManager() {

        alerts = new ArrayList<>();

    }

    // ===========================
    // Publish Alert
    // ===========================

    public void publishAlert(PublicAlert alert) {

        alerts.add(alert);

        System.out.println("Alert published successfully.");

    }

    // ===========================
    // Show All Alerts
    // ===========================

    public void showAllAlerts() {

        if (alerts.isEmpty()) {

            System.out.println("No alerts available.");

            return;

        }

        for (PublicAlert alert : alerts) {

            System.out.println("--------------------------");
            System.out.println(alert);

        }

    }

    // ===========================
    // Find Alert By ID
    // ===========================

    public PublicAlert findAlertById(int alertId) {

        for (PublicAlert alert : alerts) {

            if (alert.getAlertId() == alertId) {

                return alert;

            }

        }

        return null;

    }

    // ===========================
    // Delete Alert
    // ===========================

    public boolean deleteAlert(int alertId) {

        PublicAlert alert = findAlertById(alertId);

        if (alert != null) {

            alerts.remove(alert);

            return true;

        }

        return false;

    }

}