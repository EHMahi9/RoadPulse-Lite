package repository.memory;

import model.Incident;
import repository.IncidentRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryIncidentRepository implements IncidentRepository {

    private final List<Incident> incidents;

    public InMemoryIncidentRepository() {
        incidents = new ArrayList<>();
    }

    @Override
    public boolean save(Incident incident) {
        return incidents.add(incident);
    }

    @Override
    public Incident findById(int incidentId) {

        for (Incident incident : incidents) {

            if (incident.getIncidentId() == incidentId) {
                return incident;
            }

        }

        return null;
    }

    @Override
    public List<Incident> findAll() {
        return incidents;
    }

    @Override
    public boolean update(Incident updatedIncident) {

        for (int i = 0; i < incidents.size(); i++) {

            if (incidents.get(i).getIncidentId() == updatedIncident.getIncidentId()) {

                incidents.set(i, updatedIncident);
                return true;

            }

        }

        return false;
    }

    @Override
    public boolean delete(int incidentId) {

        Incident incident = findById(incidentId);

        if (incident != null) {

            incidents.remove(incident);
            return true;

        }

        return false;
    }
}