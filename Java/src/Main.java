import manager.AlertManager;
import manager.IncidentManager;
import manager.RoadManager;
import manager.UserManager;
import model.Commuter;
import model.User;
import util.InputHelper;

public class Main {

    private static UserManager userManager = new UserManager();
    private static RoadManager roadManager = new RoadManager();
    private static IncidentManager incidentManager = new IncidentManager();
    private static AlertManager alertManager = new AlertManager();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("        ROADPULSE LITE");
            System.out.println("=================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    running = false;
                    System.out.println("\nThank you for using RoadPulse Lite.");
                    break;

                default:
                    System.out.println("Invalid choice!");

            }

        }

        InputHelper.scanner.close();

    }

    private static void register() {

        System.out.println();
        System.out.println("========== REGISTER ==========");

        System.out.print("User ID: ");
        int id = InputHelper.scanner.nextInt();
        InputHelper.scanner.nextLine();

        System.out.print("Name: ");
        String name = InputHelper.scanner.nextLine();

        System.out.print("Email: ");
        String email = InputHelper.scanner.nextLine();

        if (userManager.findUserByEmail(email) != null) {

            System.out.println("This email is already registered.");
            return;

        }

        System.out.print("Password: ");
        String password = InputHelper.scanner.nextLine();

        System.out.print("Trust Score: ");
        int trustScore = InputHelper.scanner.nextInt();
        InputHelper.scanner.nextLine();

        Commuter commuter = new Commuter(
                id,
                name,
                email,
                password,
                trustScore
        );

        userManager.registerUser(commuter);

        System.out.println("Registration completed successfully.");

    }

    private static void login() {

        System.out.println();
        System.out.println("========== LOGIN ==========");

        System.out.print("Email: ");
        String email = InputHelper.scanner.nextLine();

        System.out.print("Password: ");
        String password = InputHelper.scanner.nextLine();

        User user = userManager.login(email, password);

        if (user == null) {

            System.out.println("Invalid email or password.");
            return;

        }

        System.out.println();
        System.out.println("Login Successful!");
        System.out.println("Welcome, " + user.getName());

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println();
            System.out.println("========== USER MENU ==========");
            System.out.println("1. View Profile");
            System.out.println("2. Show All Users");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println();
                    System.out.println(user);

                    break;

                case 2:

                    userManager.showAllUsers();

                    break;

                case 3:

                    loggedIn = false;

                    System.out.println("Logged out successfully.");

                    break;

                default:

                    System.out.println("Invalid choice.");

            }

        }

    }

}