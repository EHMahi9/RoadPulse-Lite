package menu;

import model.Admin;
import model.PublicAlert;
import model.Road;
import model.User;

import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.IdGenerator;
import util.InputHelper;

import java.time.LocalDate;
import java.util.List;

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

            System.out.print("\nChoose Option : ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

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

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("         ADMIN DASHBOARD");
        System.out.println("==========================================");
        System.out.println("Welcome : " + admin.getName());
        System.out.println();

    }

    private void viewAllUsers() {

        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {

            System.out.println("\nNo users found.");
            return;

        }

        System.out.println("\n============= USERS =============");

        for (User user : users) {

            System.out.println(user);
            System.out.println("--------------------------------");

        }

    }

    private void viewAllRoads() {

        List<Road> roads = roadService.getAllRoads();

        if (roads.isEmpty()) {

            System.out.println("\nNo roads available.");
            return;

        }

        System.out.println("\n============= ROADS =============");

        for (Road road : roads) {

            System.out.println(road);
            System.out.println("--------------------------------");

        }

    }

    // ======================================
    // Remaining methods
    // ======================================

    private void addRoad() {

    }

    private void updateRoadStatus() {

    }

    private void deleteRoad() {

    }

    private void viewAllIncidents() {

    }

    private void verifyIncident() {

    }

    private void rejectIncident() {

    }

    private void deleteIncident() {

    }

    private void publishAlert() {

    }

    private void viewAlerts() {

    }

}

private void addRoad() {

    System.out.println("\n========== ADD NEW ROAD ==========");

    System.out.print("Road Name : ");
    String roadName = InputHelper.scanner.nextLine();

    System.out.print("District : ");
    String district = InputHelper.scanner.nextLine();

    System.out.print("Status : ");
    String status = InputHelper.scanner.nextLine();

    Road road = new Road(
            util.IdGenerator.nextRoadId(),
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

}

private void updateRoadStatus() {

    System.out.println("\n======= UPDATE ROAD STATUS =======");

    System.out.print("Road ID : ");
    int roadId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    Road road = roadService.findRoadById(roadId);

    if (road == null) {

        System.out.println("\nRoad not found.");
        return;

    }

    System.out.println("Current Status : " + road.getStatus());

    System.out.print("New Status : ");
    String status = InputHelper.scanner.nextLine();

    boolean success =
            roadService.updateRoadStatus(roadId, status);

    if (success) {

        System.out.println("\nRoad status updated.");

    } else {

        System.out.println("\nUpdate failed.");

    }

}

private void deleteRoad() {

    System.out.println("\n========== DELETE ROAD ==========");

    System.out.print("Road ID : ");
    int roadId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    boolean success =
            roadService.deleteRoad(roadId);

    if (success) {

        System.out.println("\nRoad deleted successfully.");

    } else {

        System.out.println("\nRoad not found.");

    }

}

private void deleteRoad() {

    System.out.println("\n========== DELETE ROAD ==========");

    System.out.print("Road ID : ");
    int roadId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    boolean success =
            roadService.deleteRoad(roadId);

    if (success) {

        System.out.println("\nRoad deleted successfully.");

    } else {

        System.out.println("\nRoad not found.");

    }

}

private void deleteRoad() {

    System.out.println("\n========== DELETE ROAD ==========");

    System.out.print("Road ID : ");
    int roadId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    boolean success =
            roadService.deleteRoad(roadId);

    if (success) {

        System.out.println("\nRoad deleted successfully.");

    } else {

        System.out.println("\nRoad not found.");

    }

}

private void rejectIncident() {

    System.out.println("\n======= REJECT INCIDENT =======");

    System.out.print("Incident ID : ");
    int incidentId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    boolean success = incidentService.rejectIncident(incidentId);

    if (success) {

        System.out.println("\nIncident rejected successfully.");

    } else {

        System.out.println("\nIncident not found.");

    }

}

private void deleteIncident() {

    System.out.println("\n======= DELETE INCIDENT =======");

    System.out.print("Incident ID : ");
    int incidentId = InputHelper.scanner.nextInt();
    InputHelper.scanner.nextLine();

    boolean success = incidentService.deleteIncident(incidentId);

    if (success) {

        System.out.println("\nIncident deleted successfully.");

    } else {

        System.out.println("\nIncident not found.");

    }

}

private void publishAlert() {

    System.out.println("\n========== PUBLISH ALERT ==========");

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

    System.out.print("Alert Title : ");
    String title = InputHelper.scanner.nextLine();

    System.out.print("Alert Message : ");
    String message = InputHelper.scanner.nextLine();

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

}

private void viewAlerts() {

    List<PublicAlert> alerts =
            alertService.getAllAlerts();

    if (alerts.isEmpty()) {

        System.out.println("\nNo alerts available.");
        return;

    }

    System.out.println("\n========== PUBLIC ALERTS ==========");

    for (PublicAlert alert : alerts) {

        System.out.println(alert);
        System.out.println("-----------------------------------");

    }

}