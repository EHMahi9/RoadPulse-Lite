package menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Commuter;
import model.Incident;
import model.PublicAlert;
import model.Road;
import model.User;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.IdGenerator;
import util.InputHelper;
import util.TableFormatter;

public class AdminMenu {

    private final Admin admin;

    private final UserService userService;
    private final RoadService roadService;
    private final IncidentService incidentService;
    private final AlertService alertService;

    public AdminMenu(Admin admin,
                     UserService userService,
                     RoadService roadService,
                     IncidentService incidentService,
                     AlertService alertService) {

        this.admin = admin;

        this.userService = userService;
        this.roadService = roadService;
        this.incidentService = incidentService;
        this.alertService = alertService;
    }

    public void start() {

        while (true) {

            printHeader();

            System.out.println("1. View All Users");
            System.out.println("2. View All Roads");
            System.out.println("3. Add New Road");
            System.out.println("4. Update Road Status");
            System.out.println("5. Delete Road");
            System.out.println("6. View All Incidents");
            System.out.println("7. Verify Incident");
            System.out.println("8. Reject Incident");
            System.out.println("9. Delete Incident");
            System.out.println("10. Publish Alert");
            System.out.println("11. View Alerts");
            System.out.println("12. Logout");

            int choice = InputHelper.readInt("\nChoose Option : ", 1, 12);

            switch (choice) {

                case 1:
                    viewAllUsers();
                    break;

                case 2:
                    viewAllRoads();
                    break;

                case 3:
                    addRoad();
                    break;

                case 4:
                    updateRoadStatus();
                    break;

                case 5:
                    deleteRoad();
                    break;

                case 6:
                    viewAllIncidents();
                    break;

                case 7:
                    verifyIncident();
                    break;

                case 8:
                    rejectIncident();
                    break;

                case 9:
                    deleteIncident();
                    break;

                case 10:
                    publishAlert();
                    break;

                case 11:
                    viewAlerts();
                    break;

                case 12:
                    System.out.println("\nLogging out...\n");
                    return;

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("         ADMIN DASHBOARD");
        System.out.println("==========================================");
        System.out.println("Welcome: " + admin.getName());
        System.out.println();

    }

    private void viewAllUsers() {

        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {

            System.out.println("\nNo users found.");
            InputHelper.pressEnterToContinue();
            return;

        }

        String[] headers = {"ID", "Name", "Email", "Type", "Level/Score"};
        List<String[]> rows = new ArrayList<>();

        for (User u : users) {

            String type;
            String extra;

            if (u instanceof Admin) {
                type = "Admin";
                extra = "Level " + ((Admin) u).getAdminLevel();
            } else if (u instanceof Commuter) {
                type = "Commuter";
                extra = "Score " + ((Commuter) u).getTrustScore();
            } else {
                type = "User";
                extra = "-";
            }

            rows.add(new String[]{
                    String.valueOf(u.getUserId()),
                    u.getName(),
                    u.getEmail(),
                    type,
                    extra
            });

        }

        TableFormatter.printTable("============= USERS =============", headers, rows);
        InputHelper.pressEnterToContinue();

    }

    private void viewAllRoads() {

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

        TableFormatter.printTable("============= ROADS =============", headers, rows);
        InputHelper.pressEnterToContinue();

    }

    private void addRoad() {

        System.out.println("\n========== ADD NEW ROAD ==========");

        String roadName = InputHelper.readString("Road Name : ");
        String district = InputHelper.readString("District : ");
        String status = InputHelper.readString("Status : ");

        Road road = new Road(
                IdGenerator.nextRoadId(),
                roadName,
                district,
                status
        );

        boolean success = roadService.addRoad(road);

        if (success) {

            System.out.println("\nRoad added successfully.");

        } else {

            System.out.println("\nFailed to add road.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void updateRoadStatus() {

        System.out.println("\n======= UPDATE ROAD STATUS =======");

        int roadId = InputHelper.readInt("Road ID : ");
        Road road = roadService.findRoadById(roadId);

        if (road == null) {

            System.out.println("\nRoad not found.");
            InputHelper.pressEnterToContinue();
            return;

        }

        System.out.println("Current Status : " + road.getStatus());

        String status = InputHelper.readString("New Status : ");

        boolean success = roadService.updateRoadStatus(roadId, status);

        if (success) {

            System.out.println("\nRoad status updated.");

        } else {

            System.out.println("\nUpdate failed.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void deleteRoad() {

        System.out.println("\n========== DELETE ROAD ==========");

        int roadId = InputHelper.readInt("Road ID : ");
        boolean success = roadService.deleteRoad(roadId);

        if (success) {

            System.out.println("\nRoad deleted successfully.");

        } else {

            System.out.println("\nRoad not found.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void viewAllIncidents() {

        List<Incident> incidents = incidentService.getAllIncidents();

        if (incidents.isEmpty()) {

            System.out.println("\nNo incidents reported.");
            InputHelper.pressEnterToContinue();
            return;

        }

        String[] headers = {"ID", "Type", "Status", "Road", "Reporter", "Time"};
        List<String[]> rows = new ArrayList<>();

        for (Incident i : incidents) {

            rows.add(new String[]{
                    String.valueOf(i.getIncidentId()),
                    i.getIncidentType(),
                    i.getStatus(),
                    i.getRoad().getRoadName(),
                    i.getReporter().getName(),
                    i.getReportTime()
            });

        }

        TableFormatter.printTable("============= INCIDENTS =============", headers, rows);
        InputHelper.pressEnterToContinue();

    }

    private void verifyIncident() {

        System.out.println("\n======= VERIFY INCIDENT =======");

        int incidentId = InputHelper.readInt("Incident ID : ");
        boolean success = incidentService.verifyIncident(incidentId);

        if (success) {

            System.out.println("\nIncident verified successfully.");

        } else {

            System.out.println("\nIncident not found.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void rejectIncident() {

        System.out.println("\n======= REJECT INCIDENT =======");

        int incidentId = InputHelper.readInt("Incident ID : ");
        boolean success = incidentService.rejectIncident(incidentId);

        if (success) {

            System.out.println("\nIncident rejected successfully.");

        } else {

            System.out.println("\nIncident not found.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void deleteIncident() {

        System.out.println("\n======= DELETE INCIDENT =======");

        int incidentId = InputHelper.readInt("Incident ID : ");
        boolean success = incidentService.deleteIncident(incidentId);

        if (success) {

            System.out.println("\nIncident deleted successfully.");

        } else {

            System.out.println("\nIncident not found.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void publishAlert() {

        System.out.println("\n========== PUBLISH ALERT ==========");

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

        String title = InputHelper.readString("Alert Title : ");
        String message = InputHelper.readString("Alert Message : ");

        PublicAlert alert = new PublicAlert(
                IdGenerator.nextAlertId(),
                title,
                message,
                LocalDate.now().toString(),
                road
        );

        boolean success = alertService.publishAlert(alert);

        if (success) {

            System.out.println("\nAlert published successfully.");

        } else {

            System.out.println("\nFailed to publish alert.");

        }

        InputHelper.pressEnterToContinue();

    }

    private void viewAlerts() {

        List<PublicAlert> alerts = alertService.getAllAlerts();

        if (alerts.isEmpty()) {

            System.out.println("\nNo alerts available.");
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
