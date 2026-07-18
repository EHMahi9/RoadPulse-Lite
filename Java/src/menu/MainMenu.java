package menu;

import model.Admin;
import model.Commuter;
import model.User;
import model.Road;
import service.AlertService;
import service.IncidentService;
import service.RoadService;
import service.UserService;
import util.InputHelper;
import util.PasswordUtil;

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

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            int choice = InputHelper.readInt("\nChoose Option : ", 1, 3);

            switch (choice) {

                case 1:
                    login();
                    break;

                case 2:
                    register();
                    break;

                case 3:
                    System.out.println("\nThank you for using RoadPulse Lite.\n");
                    return;

            }

        }

    }

    private void printHeader() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("           ROADPULSE LITE");
        System.out.println("    Road Incident & Traffic Management");
        System.out.println("==============================================");

    }

    private void login() {

        System.out.println("\n=============== LOGIN ===============");

        String email = InputHelper.readString("Email : ");
        String password = InputHelper.readString("Password : ");

        User user = userService.login(email, password);

        if (user == null) {

            System.out.println("\nInvalid email or password.");
            InputHelper.pressEnterToContinue();
            return;

        }

        System.out.println("\nLogin successful!");

        if (user instanceof Admin) {

            AdminMenu adminMenu = new AdminMenu(
                    (Admin) user,
                    userService,
                    roadService,
                    incidentService,
                    alertService
            );

            adminMenu.start();

        } else if (user instanceof Commuter) {

            CommuterMenu commuterMenu = new CommuterMenu(
                    (Commuter) user,
                    userService,
                    roadService,
                    incidentService,
                    alertService
            );

            commuterMenu.start();

        }

    }

    private void register() {

        System.out.println("\n============== REGISTER ==============");

        System.out.println("Register as:");
        System.out.println("1. Commuter");
        System.out.println("2. Admin");

        int type = InputHelper.readInt("\nChoose Type : ", 1, 2);

        String name = InputHelper.readString("Name : ");
        String email = InputHelper.readString("Email : ");
        String password = InputHelper.readString("Password : ");

        User user;

        if (type == 1) {

            user = new Commuter(
                    util.IdGenerator.nextUserId(),
                    name,
                    email,
                    password,
                    100
            );

        } else {

            user = new Admin(
                    util.IdGenerator.nextUserId(),
                    name,
                    email,
                    password,
                    1
            );

        }

        boolean success = userService.registerUser(user);

        if (success) {

            System.out.println("\nRegistration successful! You can now log in.");

        } else {

            System.out.println("\nRegistration failed. Email may already be in use.");

        }

        InputHelper.pressEnterToContinue();

    }

}
