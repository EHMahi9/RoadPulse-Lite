package menu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Commuter;
import model.Incident;
import model.PublicAlert;
import model.Road;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.IdGenerator;
import util.InputHelper;
import util.TableFormatter;

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

            int choice = InputHelper.readInt("\nChoose Option : ", 1, 6);

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

                    System.out.println("\nLogging out...\n");
                    return;

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       COMMUTER DASHBOARD");
        System.out.println("======================================");
        System.out.println("Welcome: " + commuter.getName());
        System.out.println();

    }

    private void viewProfile() {

        System.out.println("\n========== MY PROFILE ==========");
        System.out.println(commuter);

        InputHelper.pressEnterToContinue();

    }

    private void viewRoads() {

        List<Road> roads = roadService.getAllRoads();

        if (roads.isEmpty()) {

            System.out.println("\nNo roads available.");
            InputHelper.pressEnterToContinue();
            return;

        }

        String[] headers = {"ID", "Road Name", "District", "Status"};
        List<String[]> rows = new ArrayList<>();

        for (Road r : roads) {

            rows.add(new String[]{
                    String.valueOf(r.getRoadId()),
                    r.getRoadName(),
                    r.getDistrict(),
                    r.getStatus()
            });

        }

        TableFormatter.printTable("========== ROADS ==========", headers, rows);
        InputHelper.pressEnterToContinue();

    }

    private void reportIncident() {

        System.out.println("\n========== REPORT INCIDENT ==========");

        List<Road> roads = roadService.getAllRoads();

        if (roads.isEmpty()) {

            System.out.println("\nNo roads available.");
            InputHelper.pressEnterToContinue();
            return;

        }

        System.out.println("\nAvailable Roads:");

        for (Road r : roads) {

            System.out.println(r.getRoadId() + " - " + r.getRoadName());

        }

        int roadId = InputHelper.readInt("\nRoad ID : ");
        Road road = roadService.findRoadById(roadId);

        if (road == null) {

            System.out.println("\nRoad not found.");
            InputHelper.pressEnterToContinue();
            return;

        }

        String incidentType = InputHelper.readString("Incident Type : ");
        String description = InputHelper.readString("Description : ");

        Incident incident = new Incident(
                IdGenerator.nextIncidentId(),
                incidentType,
                description,
                LocalDateTime.now().toString(),
                "Pending",
                road,
                commuter
        );

        boolean success = incidentService.reportIncident(incident);

        if (success) {

            System.out.println("\nIncident reported successfully.");

        } else {

            System.out.println("\nFailed to report incident.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void viewMyIncidents() {

        List<Incident> allIncidents = incidentService.getAllIncidents();
        boolean found = false;

        String[] headers = {"ID", "Type", "Status", "Road", "Time", "Description"};
        List<String[]> rows = new ArrayList<>();

        for (Incident i : allIncidents) {

            if (i.getReporter().getUserId() == commuter.getUserId()) {

                rows.add(new String[]{
                        String.valueOf(i.getIncidentId()),
                        i.getIncidentType(),
                        i.getStatus(),
                        i.getRoad().getRoadName(),
                        i.getReportTime(),
                        i.getDescription()
                });

                found = true;

            }

        }

        if (!found) {

            System.out.println("\nYou have not reported any incidents.");

        } else {

            TableFormatter.printTable("========== MY INCIDENTS ==========", headers, rows);

        }

        InputHelper.pressEnterToContinue();

    }

    private void viewAlerts() {

        List<PublicAlert> alerts = alertService.getAllAlerts();

        if (alerts.isEmpty()) {

            System.out.println("\nNo public alerts available.");
            InputHelper.pressEnterToContinue();
            return;

        }

        String[] headers = {"ID", "Title", "Road", "Date", "Message"};
        List<String[]> rows = new ArrayList<>();

        for (PublicAlert a : alerts) {

            rows.add(new String[]{
                    String.valueOf(a.getAlertId()),
                    a.getTitle(),
                    a.getRoad().getRoadName(),
                    a.getPublishDate(),
                    a.getMessage()
            });

        }

        TableFormatter.printTable("========== PUBLIC ALERTS ==========", headers, rows);
        InputHelper.pressEnterToContinue();

    }

}
