package service;

import java.util.List;
import model.PublicAlert;
import repository.AlertRepository;

public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    /**
     * Publish a new alert.
     */
    public boolean publishAlert(PublicAlert alert) {

        if (alert == null) {
            return false;
        }

        if (alertRepository.findById(alert.getAlertId()) != null) {
            return false;
        }

        return alertRepository.save(alert);
    }

    /**
     * Get all alerts.
     */
    public List<PublicAlert> getAllAlerts() {
        return alertRepository.findAll();
    }

    /**
     * Find an alert by ID.
     */
    public PublicAlert findAlertById(int alertId) {
        return alertRepository.findById(alertId);
    }

    /**
     * Update an existing alert.
     */
    public boolean updateAlert(PublicAlert alert) {

        if (alert == null) {
            return false;
        }

        return alertRepository.update(alert);
    }

    /**
     * Delete an alert.
     */
    public boolean deleteAlert(int alertId) {
        return alertRepository.delete(alertId);
    }

}