package manager;

import java.util.ArrayList;
import model.Incident;

public class IncidentManager {

    private ArrayList<Incident> incidents;

    public IncidentManager() {

        incidents = new ArrayList<>();

    }

    public void reportIncident(Incident incident) {

        incidents.add(incident);

        System.out.println("Incident reported successfully.");

    }

    public void showAllIncidents() {

        if (incidents.isEmpty()) {

            System.out.println("No incidents found.");

            return;

        }

        for (Incident incident : incidents) {

            System.out.println("---------------------------");
            System.out.println(incident);

        }

    }

    public Incident findIncidentById(int incidentId) {

        for (Incident incident : incidents) {

            if (incident.getIncidentId() == incidentId) {

                return incident;

            }

        }

        return null;

    }

    public boolean verifyIncident(int incidentId) {

        Incident incident = findIncidentById(incidentId);

        if (incident != null) {

            incident.setStatus("Verified");

            return true;

        }

        return false;

    }

    public boolean deleteIncident(int incidentId) {

        Incident incident = findIncidentById(incidentId);

        if (incident != null) {

            incidents.remove(incident);

            return true;

        }

        return false;

    }

}