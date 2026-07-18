package service;

import java.util.List;
import model.Incident;
import repository.IncidentRepository;

public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    /**
     * Report a new incident.
     */
    public boolean reportIncident(Incident incident) {

        if (incident == null) {
            return false;
        }

        if (incidentRepository.findById(incident.getIncidentId()) != null) {
            return false;
        }

        return incidentRepository.save(incident);
    }

    /**
     * Get all incidents.
     */
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    /**
     * Find an incident by ID.
     */
    public Incident findIncidentById(int incidentId) {
        return incidentRepository.findById(incidentId);
    }

    /**
     * Update an incident.
     */
    public boolean updateIncident(Incident incident) {

        if (incident == null) {
            return false;
        }

        return incidentRepository.update(incident);
    }

    /**
     * Delete an incident.
     */
    public boolean deleteIncident(int incidentId) {
        return incidentRepository.delete(incidentId);
    }

    /**
     * Verify an incident.
     */
    public boolean verifyIncident(int incidentId) {

        Incident incident = incidentRepository.findById(incidentId);

        if (incident == null) {
            return false;
        }

        incident.setStatus("Verified");

        return incidentRepository.update(incident);
    }

    /**
     * Reject an incident.
     */
    public boolean rejectIncident(int incidentId) {

        Incident incident = incidentRepository.findById(incidentId);

        if (incident == null) {
            return false;
        }

        incident.setStatus("Rejected");

        return incidentRepository.update(incident);
    }

}