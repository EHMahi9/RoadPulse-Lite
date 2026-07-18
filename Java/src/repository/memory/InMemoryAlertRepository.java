package repository.memory;

import model.PublicAlert;
import repository.AlertRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAlertRepository implements AlertRepository {

    private final List<PublicAlert> alerts;

    public InMemoryAlertRepository() {
        alerts = new ArrayList<>();
    }

    @Override
    public boolean save(PublicAlert alert) {
        return alerts.add(alert);
    }

    @Override
    public PublicAlert findById(int alertId) {

        for (PublicAlert alert : alerts) {

            if (alert.getAlertId() == alertId) {
                return alert;
            }

        }

        return null;
    }

    @Override
    public List<PublicAlert> findAll() {
        return alerts;
    }

    @Override
    public boolean update(PublicAlert updatedAlert) {

        for (int i = 0; i < alerts.size(); i++) {

            if (alerts.get(i).getAlertId() == updatedAlert.getAlertId()) {

                alerts.set(i, updatedAlert);
                return true;

            }

        }

        return false;
    }

    @Override
    public boolean delete(int alertId) {

        PublicAlert alert = findById(alertId);

        if (alert != null) {

            alerts.remove(alert);
            return true;

        }

        return false;
    }
}