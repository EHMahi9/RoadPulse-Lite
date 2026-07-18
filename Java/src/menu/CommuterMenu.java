package menu;

import java.util.List;
import model.Commuter;
import model.Road;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import model.Incident;
import util.IdGenerator;

import java.time.LocalDateTime;

public class CommuterMenu {

    private final Commuter commuter;

    private final UserService userService;
    private final RoadService roadService;
    private final IncidentService incidentService;
    private final AlertService alertService;

    public CommuterMenu(
            Commuter commuter,
            UserService userService,
            RoadService roadService,
            IncidentService incidentService,
            AlertService alertService) {

        this.commuter = commuter;

        this.userService = userService;
        this.roadService = roadService;
        this.incidentService = incidentService;
        this.alertService = alertService;

    }

    public void start() {

        while (true) {

            printHeader();

            System.out.println("1. View My Profile");
            System.out.println("2. View All Roads");
            System.out.println("3. Report Incident");
            System.out.println("4. View My Incidents");
            System.out.println("5. View Public Alerts");
            System.out.println("6. Logout");

            System.out.print("\nChoose Option : ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:
                    viewProfile();
                    break;

                case 2:
                    viewRoads();
                    break;

                case 3:
                    reportIncident();
                    break;

                case 4:
                    viewMyIncidents();
                    break;

                case 5:
                    viewAlerts();
                    break;

                case 6:

                    System.out.println("\nLogging out...");
                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       COMMUTER DASHBOARD");
        System.out.println("======================================");
        System.out.println("Welcome : " + commuter.getName());
        System.out.println();

    }

    private void viewProfile() {

        System.out.println("\n========== MY PROFILE ==========");

        System.out.println(commuter);

    }

    private void viewRoads() {

        List<Road> roads = roadService.getAllRoads();

        if (roads.isEmpty()) {

            System.out.println("\nNo roads available.");
            return;

        }

        System.out.println("\n========== ROADS ==========");

        for (Road road : roads) {

            System.out.println(road);
            System.out.println("--------------------------------");

        }

    }

    // ===========================
    // Remaining Methods
    // ===========================

    private void reportIncident() {

    }

    private void viewMyIncidents() {

    }

    private void viewAlerts() {

    }

}

private void reportIncident() {

    System.out.println("\n========== REPORT INCIDENT ==========");

    List<Road> roads = roadService.getAllRoads();

    if (roads.isEmpty()) {

        System.out.println("\nNo roads available.");
        return;

    }

    System.out.println("\nAvailable Roads:");

    for (Road road : roads) {

        System.out.println(
                road.getRoadId() +
                " - " +
                road.getRoadName()
        );

    }

    System.out.print("\nRoad ID : ");
    int roadId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    Road road = roadService.findRoadById(roadId);

    if (road == null) {

        System.out.println("\nRoad not found.");
        return;

    }

    System.out.print("Incident Type : ");
    String incidentType = InputHelper.scanner.nextLine();

    System.out.print("Description : ");
    String description = InputHelper.scanner.nextLine();

    Incident incident = new Incident(
            IdGenerator.nextIncidentId(),
            incidentType,
            description,
            LocalDateTime.now().toString(),
            "Pending",
            road,
            commuter
    );

    boolean success =
            incidentService.reportIncident(incident);

    if (success) {

        System.out.println("\nIncident reported successfully.");

    } else {

        System.out.println("\nFailed to report incident.");

    }

}

private void viewMyIncidents() {

    List<Incident> incidents =
            incidentService.getAllIncidents();

    boolean found = false;

    System.out.println("\n========== MY INCIDENTS ==========");

    for (Incident incident : incidents) {

        if (incident.getReporter().getUserId()
                == commuter.getUserId()) {

            System.out.println(incident);
            System.out.println("--------------------------------");

            found = true;

        }

    }

    if (!found) {

        System.out.println("\nYou have not reported any incidents.");

    }

}

private void viewAlerts() {

    List<PublicAlert> alerts =
            alertService.getAllAlerts();

    if (alerts.isEmpty()) {

        System.out.println("\nNo public alerts available.");
        return;

    }

    System.out.println("\n========== PUBLIC ALERTS ==========");

    for (PublicAlert alert : alerts) {

        System.out.println(alert);
        System.out.println("--------------------------------");

    }

}