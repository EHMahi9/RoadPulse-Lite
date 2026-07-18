package menu;

import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.InputHelper;

public class MainMenu {

    private final UserService userService;
    private final RoadService roadService;
    private final IncidentService incidentService;
    private final AlertService alertService;

    public MainMenu(UserService userService,
                    RoadService roadService,
                    IncidentService incidentService,
                    AlertService alertService) {

        this.userService = userService;
        this.roadService = roadService;
        this.incidentService = incidentService;
        this.alertService = alertService;

    }

    public void start() {

        while (true) {

            printHeader();

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("\nChoose : ");

            int choice =
                    InputHelper.scanner.nextInt();

            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:

                    register();

                    break;

                case 2:

                    login();

                    break;

                case 3:

                    System.out.println("\nThank you for using RoadPulse Lite.");

                    return;

                default:

                    System.out.println("\nInvalid Choice.");

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("=======================================");
        System.out.println("          ROADPULSE LITE");
        System.out.println("=======================================");

    }

    private void register() {

        // Next

    }

    private void login() {

        // Next

    }

}